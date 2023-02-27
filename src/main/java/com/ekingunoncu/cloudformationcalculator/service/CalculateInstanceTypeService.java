package com.ekingunoncu.cloudformationcalculator.service;

import java.util.Map;

import org.springframework.stereotype.Service;
import com.ekingunoncu.cloudformationcalculator.model.CalculateInstanceTypeInput;
import com.ekingunoncu.cloudformationcalculator.model.InstanceCriteria;

import java.util.LinkedHashMap;

/**
 * Service for calculating instance types based on given input criteria.
 */
@Service
public class CalculateInstanceTypeService {

    /**
     * A map containing the instance criteria as keys and their corresponding instance types as values.
     * The order of elements in the map is preserved by using a LinkedHashMap.
     */
    private final Map<InstanceCriteria, String> loadInstanceTypeMap = new LinkedHashMap<>();

    /**
     * Initializes the loadInstanceTypeMap with key-value pairs representing the instance criteria and instance types.
     */
    public CalculateInstanceTypeService() {
        loadInstanceTypeMap.put(new InstanceCriteria(200, 50), "c5.large");
        loadInstanceTypeMap.put(new InstanceCriteria(400, 100), "c5.xlarge");
        loadInstanceTypeMap.put(new InstanceCriteria(800, 200), "c5.2xlarge");
        loadInstanceTypeMap.put(new InstanceCriteria(2000, 400), "c5.4xlarge");
        loadInstanceTypeMap.put(new InstanceCriteria(Integer.MAX_VALUE, Integer.MAX_VALUE), "c5.9xlarge");
    }

    /**
     * Given the input criteria, returns the corresponding instance type from the loadInstanceTypeMap.
     * If no instance type matches the input criteria, an IllegalArgumentException is thrown.
     *
     * @param input The input criteria for calculating the instance type.
     * @return The instance type corresponding to the input criteria.
     * @throws IllegalArgumentException if no instance type matches the input criteria.
     */
    public String calculate(CalculateInstanceTypeInput input) {
        try {
            String instanceType = loadInstanceTypeMap.keySet().stream()
                    .filter(criteria -> criteria.matchesInput(input))
                    .findFirst()
                    .map(loadInstanceTypeMap::get)
                    .orElseThrow(() -> new IllegalArgumentException("No instance type matches the input criteria"));
            return instanceType;
        } catch (IllegalArgumentException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new IllegalArgumentException("Invalid input values", ex);
        }
    }
}
