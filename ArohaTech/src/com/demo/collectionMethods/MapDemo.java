package com.demo.collectionMethods;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class MapDemo {
    public static void main(String[] args) {
        // 1. Create HashMap
        Map<String, Integer> population = new HashMap<>();
        
        // 2. Add elements
        population.put("USA", 3000);
        population.put("China", 4000);
        population.put("India", 5000);
        population.put("China", 7000); // Updates existing value
        
        System.out.println("HashMap: " + population);
        
        // 3. Access elements
        System.out.println("Population of India: " + population.get("India"));
        System.out.println("Get or default: " + population.getOrDefault("Japan", -1));
        
        // 4. Check elements
        System.out.println("Contains key China? " + population.containsKey("China"));
        System.out.println("Contains value ? " + population.containsValue(7000));
        System.out.println("Is empty? " + population.isEmpty());
        System.out.println("Size: " + population.size());
        
        // 5. Remove elements
        population.remove("USA");
        System.out.println("After removal: " + population);
        
        // 6. Iteration
        System.out.println("Iterating with for-each:");
        for (Map.Entry<String, Integer> entry : population.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        
        System.out.println("Keys:");
        for (String key : population.keySet()) {
            System.out.println(key);
        }
        
        System.out.println("Values:");
        for (int value : population.values()) {
            System.out.println(value);
        }
        
        // 7. TreeMap (sorted by keys)
        Map<String, Integer> sortedPopulation = new TreeMap<>(population);
        sortedPopulation.put("Brazil", 2500);
        sortedPopulation.put("Australia", 4300);
        System.out.println("TreeMap (sorted by keys): " + sortedPopulation);
        
       
        // 10. Clear
        population.clear();
        System.out.println("After clear: " + population);
    }
}