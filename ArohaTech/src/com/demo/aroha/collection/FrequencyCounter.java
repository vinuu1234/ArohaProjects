package com.demo.aroha.collection;

import java.util.*;

public class FrequencyCounter {
    public static Map<Integer, Integer> countFrequencies(List<Integer> list) {
        Map<Integer, Integer> freqMap = new HashMap<>();

        for (Integer num : list) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }

        return freqMap;
    }

    public static void printByDescendingFrequency(Map<Integer, Integer> freqMap) {
        List<Map.Entry<Integer, Integer>> sortedEntries = new ArrayList<>(freqMap.entrySet());

        sortedEntries.sort((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()));

        for (Map.Entry<Integer, Integer> entry : sortedEntries) {
            System.out.println(entry.getKey() + " => " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(4, 2, 2, 3, 4, 4, 1, 2);

        Map<Integer, Integer> frequencyMap = countFrequencies(numbers);

        System.out.println("Frequencies in descending order:");
        printByDescendingFrequency(frequencyMap);
    }
}
