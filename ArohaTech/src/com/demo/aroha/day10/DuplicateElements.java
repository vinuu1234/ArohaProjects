package com.demo.aroha.day10;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class DuplicateElements {

	public static void main(String[] args) {
		List<String> inputList = Arrays.asList("apple", "banana", "apple", "orange", "banana", "grape");

		// Set to track seen elements
		Set<String> set = new HashSet();

		// Stream + filter to collect duplicates
		Set<String> duplicates = inputList.stream().filter(item -> !set.add(item)) // if already seen, add() returns
																					// false
				.collect(Collectors.toSet());

		// Output
		System.out.println("Duplicate elements: " + duplicates);
	}

}
