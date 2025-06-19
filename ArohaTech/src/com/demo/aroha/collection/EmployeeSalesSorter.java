package com.demo.aroha.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class EmployeeSalesSorter {
    public static Map<String, Integer> sort(Map<String, Integer> salesMap) {
        return new TreeMap<>(salesMap);
    }

    public static void main(String[] args) {
        Map<String, Integer> employeeSales = new HashMap<>();
        employeeSales.put("RAmu", 5000);
        employeeSales.put("Akash", 7000);
        employeeSales.put("Vinod", 6000);

        Map<String, Integer> sortedMap = sort(employeeSales);

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
