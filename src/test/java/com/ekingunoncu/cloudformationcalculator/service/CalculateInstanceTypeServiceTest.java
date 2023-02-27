package com.ekingunoncu.cloudformationcalculator.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.ekingunoncu.cloudformationcalculator.model.CalculateInstanceTypeInput;

class CalculateInstanceTypeServiceTest {

    private CalculateInstanceTypeService service;

    @BeforeEach
    void setUp() {
        service = new CalculateInstanceTypeService();
    }

    @Test
    void testCalculate_withValidInput_returnsCorrectInstanceType_4xlarge() {
        CalculateInstanceTypeInput input = CalculateInstanceTypeInput.builder().numBroadcasts(400).numViewers(100)
                .build();
        String instanceType = service.calculate(input);
        assertEquals("c5.4xlarge", instanceType);
    }

    @Test
    void testCalculate_withValidInput_returnsCorrectInstanceType_9xlarge() {
        CalculateInstanceTypeInput input = CalculateInstanceTypeInput.builder().numBroadcasts(401).numViewers(600)
                .build();
        String instanceType = service.calculate(input);
        assertEquals("c5.9xlarge", instanceType);
    }

}
