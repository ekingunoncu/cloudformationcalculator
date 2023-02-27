package com.ekingunoncu.cloudformationcalculator.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Represents the criteria for choosing the appropriate instance type.
 */
@Data
@Builder
@AllArgsConstructor
public class InstanceCriteria {
    /**
     * The maximum number of viewers for the instance type.
     */
    private int maxViewers;

    /**
     * The maximum number of broadcasts for the instance type.
     */
    private int maxBroadcasts;

    /**
     * Checks if the given CalculateInstanceTypeInput matches this instance criteria.
     *
     * @param input The CalculateInstanceTypeInput to check.
     * @return true if the input matches this instance criteria, false otherwise.
     */
    public boolean matchesInput(CalculateInstanceTypeInput input) {
        return input.getNumViewers() <= maxViewers && input.getNumBroadcasts() <= maxBroadcasts;
    }
}
