# Message-queuing-using-AWS-Serverless-
Requirements
Creation of an Amazon Web Service Account
1. Go to https://aws.amazon.com/free and Sign Up for AWS Service
2. Enter the details required by amazon web services. Note: Use a credit card for payment options.

Introduction
The message queuing system is implemented using the various Amazon web services such as Lambda function, API Gateway, SQS- Queue Service.
These services put together help implement our api for queuing or stacking the messages on the cloud. 
The API Gateway is used to deploy the functions onto an endpoint for universal access. 
The SQS helps in queuing the messages in the cloud that can retrieved online. 

Steps of Implementation
Creating lambda Functions
 Step 1: Creating the Lambda functions to perform enqueuing on the message queue- enqueue.py.
 Step2: Creating a lambda function to retrieve the messages from the queue service-test.py
 
 Creating a SQS 
 Step 1: Go to services tab of the AWS service and select SQS
 Step 2: Press on the Create a new queue button
 Step 3: Type in the name of the queue as "test-messages"

Creating an API
Step 1:Under the services tab of the AWS service > API Gateway
Step 2:Create an API button
Step 3:Name of the API 
Step 4: Under the actions tab of the API Gateway click on Create Resources> message
Step 5:Now under message resource go to actions>Create method
Step 6: Add a post method>lambda function>link to the lambda function enqueue.py
Step 7:  Add a get method>lambda function>link to the lambda function test.py
Step 8: Actions>Deploy API. Name the stage as newdev and Delpoy.

Viewing your Endpoint
An Inovke URL shall be displayed on the side append /messages to the url and visit it



