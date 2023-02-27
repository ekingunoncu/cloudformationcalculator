package com.ekingunoncu.cloudformationcalculator;

import java.lang.reflect.ParameterizedType;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

import software.amazon.lambda.powertools.cloudformation.AbstractCustomResourceHandler;

/**
 * An abstract base class for handlers that use Spring IOC to manage
 * dependencies.
 * 
 * @param <T> The configuration class for creating the Spring application
 *            context.
 */
public abstract class AbstractHandler<T> extends AbstractCustomResourceHandler {

    /**
     * The Spring application context used for managing dependencies.
     */
    public ApplicationContext applicationContext;

    /**
     * Creates an instance of AbstractHandler and initializes the Spring application
     * context.
     * The configuration class is inferred from the generic type parameter T.
     * If T has the @Configuration annotation, it is used to create the context.
     * Otherwise, a new context is created with the configClass.
     */
    public AbstractHandler() {
        // Gets config class to create an Application context
        Class<T> configClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
                .getActualTypeArguments()[0];
        // check if T has @Configuration annotation
        if (configClass.isAnnotationPresent(Configuration.class)) {
            applicationContext = new AnnotationConfigApplicationContext(configClass);
        }
        // create Spring application context
        applicationContext = new AnnotationConfigApplicationContext(configClass);
    }
}
