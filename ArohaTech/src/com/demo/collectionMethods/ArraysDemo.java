package com.demo.collectionMethods;

import java.util.Arrays;

public class ArraysDemo {
    public static void main(String[] args) {
        // 1. Create and initialize array
        int[] numbers = {5, 2, 8, 1, 9};
        String[] names = {"John", "Alice", "Bob", "Eve"};
        
        // 2. Sort
        Arrays.sort(numbers);//[1, 2, 5, 8, 9]
        System.out.println("Sorted numbers: " + Arrays.toString(numbers));
        
      
        // 3. Binary search (must be sorted)
        System.out.println("Index of 8: " + Arrays.binarySearch(numbers, 8));
        
        // 4. Fill
        int[] filledArray = new int[5];
        Arrays.fill(filledArray, 10);
        System.out.println("Filled array: " + Arrays.toString(filledArray));//[10, 10, 10, 10, 10]
        
        // 5. Copy
        int[] copiedArray = Arrays.copyOf(numbers, numbers.length);
        System.out.println("Copied array: " + Arrays.toString(copiedArray));
        
        int[] rangedCopy = Arrays.copyOfRange(numbers, 1, 3);
        System.out.println("Ranged copy: " + Arrays.toString(rangedCopy));
        
        // 6. Compare
        System.out.println("Arrays equal? " + Arrays.equals(numbers, copiedArray));
        
        // 7. Stream operations
        System.out.println("Sum: " + Arrays.stream(numbers).sum());
        System.out.println("Average: " + Arrays.stream(numbers).average().getAsDouble());
        
      }
}