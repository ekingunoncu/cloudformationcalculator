package com.ekingunoncu.cloudformationcalculator;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

import com.ekingunoncu.cloudformationcalculator.service.CalculateInstanceTypeService;

/**
 * Spring configuration class for the CloudFormationCalculator application.
 * This class is used to configure the Spring application context with necessary beans and properties.
 */
@Configuration
@PropertySource("classpath:application.yml")
// this is very important, otherwise you will get a null pointer exception
// importing with @Import is way much faster than component scanning which make
// our cold star much faster
@Import(value = { CalculateInstanceTypeService.class })
public class SpringConfig {

}
