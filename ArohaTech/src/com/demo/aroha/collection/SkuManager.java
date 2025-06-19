package com.demo.aroha.collection;

import java.util.HashSet;
import java.util.Set;

public class SkuManager {
    public static void main(String[] args) {
        Set<String> skuSet = new HashSet<>();

        // Adding SKUs
        skuSet.add("SKU123");
        skuSet.add("SKU456");
        skuSet.add("SKU789");
        skuSet.add("SKU123"); // Duplicate - will be ignored

        // Check if SKU exists
        String checkSku = "SKU456";
        if (skuSet.contains(checkSku)) {
            System.out.println(checkSku + " exists.");
        } else {
            System.out.println(checkSku + " does not exist.");
        }

        // Print all unique SKUs
        System.out.println("All unique SKUs:");
        for (String sku : skuSet) {
            System.out.println(sku);
        }
    }
}
