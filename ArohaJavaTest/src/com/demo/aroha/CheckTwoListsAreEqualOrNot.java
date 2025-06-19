package com.demo.aroha;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//20.Check weither two lists contain the same elements regardless of order or duplicates
public class CheckTwoListsAreEqualOrNot {

	public static void main(String[] args) {
		// Example lists
		List<String> list1 = Arrays.asList("a", "b", "c", "d");
		List<String> list2 = Arrays.asList("b", "c", "a");

		// Convert both lists to HashSet (automatically ignores order and duplicates)
		Set<String> set1 = new HashSet<>(list1);
		Set<String> set2 = new HashSet<>(list2);

		// Compare the sets
		boolean isSame = set1.equals(set2);

		System.out.println("Do the lists have same elements? " + isSame);
	}

}
