import boto3  
import json

def lambda_handler(event, context):

    sqs = boto3.resource('sqs')

    queue = sqs.get_queue_by_name(QueueName='test-messages')
    response=""
    message=queue.receive_messages(QueueUrl='https://sqs.ap-southeast-1.amazonaws.com/643716182770/test-messages',MaxNumberOfMessages=10,VisibilityTimeout=1)

    for msg in message :
        response=response+msg.body+","
    return "Messages in the queue are:"+response
	