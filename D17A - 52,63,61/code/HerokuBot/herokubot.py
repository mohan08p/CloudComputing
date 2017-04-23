import time
from slackclient import SlackClient
import heroku3
import requests

SLACK_BOT_TOKEN = 'XXXX-XXXXXX'
SLACK_BOT_ID = 'XXXXXXXXX'
SLACK_BOT_TAG = "<@" + SLACK_BOT_ID + ">"
HEROKU_API_KEY = 'XXXXXX-XXXXXXXXXXX-XXXXXXX-XXXXXXXX'

slack_client = SlackClient(SLACK_BOT_TOKEN)
heroku_conn = heroku3.from_key(HEROKU_API_KEY)

def create_app(app_name):
    try:
        heroku_conn.create_app(app_name)
        return True
    except TypeError as te:
        return False

def rename_app(old_app_name, new_app_name):
    try:
        heroku_conn.app(old_app_name).rename(new_app_name)
        return True
    except requests.exceptions.HTTPError as he:
        return False

def delete_app(app_name):
    try:
        heroku_conn.app(app_name).delete()
        return True
    except requests.exceptions.HTTPError as he:
        return False

def app_exists(app_name):
    apps = heroku_conn.apps()
    for app in apps:
        if app.name == app_name:
            return True
    return False

def fork_app(original_app_name, new_app_name):
    response = ''
    if app_exists(original_app_name) == False:
        response = original_app_name + ' does not exist'
        return False, response
    if app_exists(new_app_name) == False:
        response = new_app_name + ' does not exist. Creating...\n'
        create_success = create_app(new_app_name)
        if create_success:
            response += (new_app_name + ' created successfully\n')
        else:
            response += (new_app_name + ' could not be created')
            return False, response
    slug_id = get_latest_slug_id(original_app_name)
    create_new_release(slug_id, new_app_name)
    response += (original_app_name + ' has been forked successfully')
    return True, response

def get_latest_slug_id(app_name):
    url = 'https://api.heroku.com/apps/' + app_name + '/releases'
    headers = {'Content-Type': 'application/json',
               'Accept': 'application/vnd.heroku+json; version=3',
               'Authorization': 'Bearer ' + HEROKU_API_KEY}
    response = requests.get(url=url, headers=headers)
    return response.json()[-1]['slug']['id']

def create_new_release(slug_id, new_app_name):
    url = 'https://api.heroku.com/apps/' + new_app_name + '/releases'
    headers = {'Content-Type': 'application/json',
               'Accept': 'application/vnd.heroku+json; version=3',
               'Authorization': 'Bearer ' + HEROKU_API_KEY}
    data = '{"slug": "' + slug_id + '"}'
    requests.post(url=url, headers=headers, data=data)

def get_logs(app_name, lines):
    if app_exists(app_name) == False:
        response = app_name + ' does not exist'
        return False, response
    log_url = create_log_url(app_name, lines)
    return True, requests.get(log_url).text

def create_log_url(app_name, lines):
    url = 'https://api.heroku.com/apps/' + app_name + '/log-sessions'
    headers = {'Content-Type': 'application/json',
               'Accept': 'application/vnd.heroku+json; version=3',
               'Authorization': 'Bearer ' + HEROKU_API_KEY}
    data = '{"lines": "' + lines + '"}'
    r = requests.post(url=url, headers=headers, data=data)
    return r.json()["logplex_url"]

def handle_command(command, channel):
    """
        Receives commands directed at the bot and determines if they
        are valid commands. If so, then acts on the commands.
    """

    print('Received request : ' + command)
    response = ''
    # parse command arguments
    try:

        if command.startswith("create app"):
            app_name = command.split(" ")[2]
            success = create_app(app_name)
            if success:
                response = app_name + ' has been created successfully'
            else:
                response = app_name + ' could not be created as the name has been taken already'

        elif command.startswith("rename app"):
            old_app_name = command.split(" ")[2]
            new_app_name = command.split(" ")[4]
            success = rename_app(old_app_name, new_app_name)
            if success:
                response = old_app_name + ' has been renamed successfully'
            else:
                response = old_app_name + ' could not be renamed as it does not exist, or the name ' + new_app_name + \
                           ' has already been taken'

        elif command.startswith("delete app"):
            app_name = command.split(" ")[2]
            success = delete_app(app_name)
            if success:
                response = app_name + ' has been deleted successfully'
            else:
                response = app_name + ' does not exist'

        elif command.startswith("list apps"):
            apps = heroku_conn.apps()
            response = ''
            for app in apps:
                response = response + "ID : " + app.id + " Name : " + app.name + "\n"

        elif command.startswith("fork app"):
            original_app_name = command.split(" ")[2]
            new_app_name = command.split(" ")[4]
            success, response = fork_app(original_app_name, new_app_name)

        elif command.startswith("get logs for"):
            app_name = command.split(" ")[3]
            lines = command.split(" ")[4][6:]
            success, response = get_logs(app_name, lines)

        else:
            response = "Invalid command."
            print("An invalid command was given : " + command)

    except Exception as e:
        print("An exception was thrown : " + str(e))
        response = 'An error occurred.'

    slack_client.api_call("chat.postMessage", channel=channel,
                          text=response, as_user=True)


def parse_slack_output(slack_rtm_output):
    """
        The Slack Real Time Messaging API is an events firehose.
        this parsing function returns None unless a message is
        directed at the Bot, based on its ID.
    """
    output_list = slack_rtm_output
    if output_list and len(output_list) > 0:
        for output in output_list:
            if output and output['type'] == 'message' and 'text' in output:
                message_text = output['text']
                if message_text != '' and message_text.startswith(SLACK_BOT_TAG):
                    return message_text.split(SLACK_BOT_TAG)[1].strip().lower(), output['channel']
    return None, None


if __name__ == "__main__":
    READ_WEBSOCKET_DELAY = 1
    if slack_client.rtm_connect():
        print("HerokuBot has started successfully.")
        while True:
            command, channel = parse_slack_output(slack_client.rtm_read())
            if command and channel:
                handle_command(command, channel)
            time.sleep(READ_WEBSOCKET_DELAY)
    else:
        print("Connection failed. Invalid Slack token or bot ID ?")