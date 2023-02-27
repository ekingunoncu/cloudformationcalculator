package com.ekingunoncu.cloudformationcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Represents the input values for calculating the appropriate instance type.
 */
@Data
@Builder
@AllArgsConstructor
public class CalculateInstanceTypeInput {
    /**
     * The number of viewers for the instance type.
     */
    private int numViewers;

    /**
     * The number of broadcasts for the instance type.
     */
    private int numBroadcasts;
}
