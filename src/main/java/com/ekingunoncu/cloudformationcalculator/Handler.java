package com.ekingunoncu.cloudformationcalculator;

import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.CloudFormationCustomResourceEvent;
import com.ekingunoncu.cloudformationcalculator.model.CalculateInstanceTypeInput;
import com.ekingunoncu.cloudformationcalculator.service.CalculateInstanceTypeService;

import software.amazon.lambda.powertools.cloudformation.Response;

/**
 * Handler for the AWS Lambda function that responds to CloudFormation custom
 * resource events.
 */
public class Handler extends AbstractHandler<SpringConfig> {

        private CalculateInstanceTypeService calculateInstanceTypeService;

        /**
         * Constructs a new Handler object and initializes the
         * CalculateInstanceTypeService object.
         */
        public Handler() {
                calculateInstanceTypeService = applicationContext.getBean(CalculateInstanceTypeService.class);
        }

        /**
         * Handles the CREATE event by parsing the input parameters, calculating the
         * instance type, and returning a success response.
         * 
         * @param event   the CloudFormation custom resource event
         * @param context the AWS Lambda context
         * @return a CloudFormation response with the calculated instance type
         */
        @Override
        protected Response create(CloudFormationCustomResourceEvent event, Context context) {
                CalculateInstanceTypeInput input = parseInputParameters(event);
                String instanceType = calculateInstanceTypeService.calculate(input);
                Map<String, Object> responseData = Map.of("instanceType", instanceType);
                return Response.builder().status(Response.Status.SUCCESS).value(responseData).build();
        }

        /**
         * Parses the input parameters from the CloudFormation custom resource event.
         * 
         * @param event the CloudFormation custom resource event
         * @return a CalculateInstanceTypeInput object with the parsed input parameters
         */
        private CalculateInstanceTypeInput parseInputParameters(CloudFormationCustomResourceEvent event) {
                String expectedViews = (String) event.getResourceProperties().get("expectedViews");
                String expectedBroadcasts = (String) event.getResourceProperties().get("expectedBroadcasts");
                int expectedViewsInt = Integer.parseInt(expectedViews);
                int expectedBroadcastsInt = Integer.parseInt(expectedBroadcasts);
                return CalculateInstanceTypeInput.builder().numBroadcasts(expectedBroadcastsInt)
                                .numViewers(expectedViewsInt)
                                .build();
        }

        /**
         * Handles the DELETE event by throwing an UnsupportedOperationException since
         * it is not implemented.
         * 
         * @param event   the CloudFormation custom resource event
         * @param context the AWS Lambda context
         * @return a CloudFormation response indicating that the DELETE event is not
         *         implemented
         * @throws UnsupportedOperationException since the DELETE event is not
         *                                       implemented
         */
        @Override
        protected Response delete(CloudFormationCustomResourceEvent event, Context context) {
                throw new UnsupportedOperationException("Unimplemented method 'delete'");
        }

        /**
         * Handles the UPDATE event by throwing an UnsupportedOperationException since
         * it is not implemented.
         * 
         * @param event   the CloudFormation custom resource event
         * @param context the AWS Lambda context
         * @return a CloudFormation response indicating that the UPDATE event is not
         *         implemented
         * @throws UnsupportedOperationException since the UPDATE event is not
         *                                       implemented
         */
        @Override
        protected Response update(CloudFormationCustomResourceEvent event, Context context) {
                throw new UnsupportedOperationException("Unimplemented method 'update'");
        }

}
