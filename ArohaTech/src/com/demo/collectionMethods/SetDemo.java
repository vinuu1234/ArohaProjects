package com.demo.collectionMethods;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class SetDemo {
    public static void main(String[] args) {
        // 1. Create HashSet (unordered)
        Set<String> countries = new HashSet<>();
        
        // 2. Add elements
        countries.add("USA");
        countries.add("China");
        countries.add("India");
        countries.add("China"); // Duplicate - won't be added
        
        System.out.println("HashSet: " + countries);
        
        // 3. Check elements
        System.out.println("Contains India? " + countries.contains("India"));
        System.out.println("Is empty? " + countries.isEmpty());
        System.out.println("Size: " + countries.size());
        
        // 4. Remove elements
        countries.remove("China");
        System.out.println("After removal: " + countries);
        
        // 5. Iteration
        System.out.println("\nIterating with for-each:");
        for (String country : countries) {
            System.out.println(country);
        }
        
        System.out.println("\nIterating with iterator:");
        Iterator<String> it = countries.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        
        // 6. TreeSet (sorted)
        Set<String> sortedCountries = new TreeSet<>(countries);
        sortedCountries.add("Brazil");
        sortedCountries.add("Australia");
        System.out.println("TreeSet (sorted): " + sortedCountries);
        
        // 7. Set operations
        Set<String> asia = new HashSet<>();
        asia.add("India");
        asia.add("China");
        asia.add("Japan");
        
       
    }
    
   }