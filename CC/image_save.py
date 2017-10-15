import pysftp

with pysftp.Connection('139.59.72.151', username='root', password='harshgupta@071994') as sftp:
    with sftp.cd('public'):             # temporarily chdir to public
        file=input('Enter the filename')
        sftp.put(file)  # upload file to public/ on remote
        #sftp.get('a.txt')         # get a remote file
        print(sftp.listdir())
        print("Image saved successfully")
