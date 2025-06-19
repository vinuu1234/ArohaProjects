package com.demo.aroha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// 15.SOrt the integers in acsending order using collections.sort()
public class CollectionsExamples {
	public static void main(String[] args) {
		List<Integer> numbers = new ArrayList();
		numbers.add(5);
		numbers.add(2);
		numbers.add(9);
		numbers.add(1);
		numbers.add(3);
		Collections.sort(numbers); // Sorts in ascending order

		System.out.println(numbers);
	}

}
