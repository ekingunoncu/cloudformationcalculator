# CloudFormation Instance Type Calculator

This project is a simple AWS Lambda function that takes two arguments and returns the corresponding EC2 instance type.

## How to use

To use this Lambda function, you can invoke it with two arguments: `expectedViews` and `expectedBroadcasts`. The function will then return the corresponding EC2 instance type based on the following table:

| expectedViews | expectedBroadcasts | instanceType |
|---------------|--------------------|--------------|
|      0-200    |        0-50        |    c5.large  |
|     200-400   |       50-100        |   c5.xlarge  |
|    400-800   |       100-200       |    c5.2xlarge  |
|   800-2000   |      200-400       |    c5.4xlarge |
|   2000-MaxInt   |      400-MaxInt       |    c5.9xlarge |

- Note:  In between 2000-MaxInt, 400-MaxInt it won't be a vertically scalable solution for example if expectedViews 10000 the instance type will be c5.9xlarge, or if expectedViews is 1000000 instance type will still be c5.9xlarge. This case is not covered for the sake of brevity. 
We can diversify the output instance types for more ranges.

This custom resource style dynamic input creation way can be used in any cloudformation template to calculate any value by converting the logic of course but the solution is ready to apply for any case you might even get parameters from databases using lambda or you can feed the template with any API.

# cloudformationcalculator
