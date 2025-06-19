package com.demo.collectionMethods;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class ArrayListDemo {
    public static void main(String[] args) {
        // 1. Create ArrayList
        List<String> fruits = new ArrayList<>();
        
        // 2. Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        fruits.add(1, "Blueberry"); // Add at specific index
        
        System.out.println("Initial List: " + fruits);
        
        // 3. Access elements
        System.out.println("First fruit: " + fruits.get(0));
        System.out.println("Index of Banana: " + fruits.indexOf("Banana"));
        
        // 4. Modify elements
        fruits.set(2, "Blackberry");
        System.out.println("After modification: " + fruits);
        
        // 5. Remove elements
        fruits.remove("Blueberry");
        fruits.remove(0);
        System.out.println("After removal: " + fruits);
        
        // 6. Check elements
        System.out.println("Contains Cherry? " + fruits.contains("Cherry"));
        System.out.println("Is empty? " + fruits.isEmpty());
        System.out.println("Size: " + fruits.size());
        
        // 7. Iteration
        System.out.println("\nIterating with for-each:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }
        
        System.out.println("\nIterating with iterator:");
        Iterator<String> it = fruits.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
        
        // 8. Sort
        Collections.sort(fruits);
        System.out.println("\nSorted list: " + fruits);
        
        // 9. Convert to array
        String[] fruitArray = new String[fruits.size()];
        fruitArray = fruits.toArray(fruitArray);
        
        // 10. Clear
        fruits.clear();
        System.out.println("After clear: " + fruits);
    }
}
