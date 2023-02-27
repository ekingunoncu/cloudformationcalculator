#!/bin/bash

mvn clean install

# Set the S3 bucket and key for the JAR file
S3_BUCKET=cloudformationcalculator-lambda
S3_KEY=app.jar

# Set the Lambda function name and region
LAMBDA_FUNCTION_NAME=cloudformationcalculator
LAMBDA_REGION=eu-west-1

# Upload the JAR file to S3
aws s3 cp ./target/cloudformationcalculator-1.0-SNAPSHOT-aws.jar s3://${S3_BUCKET}/${S3_KEY}

# Update the Lambda function with the new code
aws lambda update-function-code \
    --function-name ${LAMBDA_FUNCTION_NAME} \
    --region ${LAMBDA_REGION} \
    --s3-bucket ${S3_BUCKET} \
    --s3-key ${S3_KEY}