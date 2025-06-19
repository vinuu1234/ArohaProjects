package com.demo.aroha;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//18.First occurance of a integer in a List
public class FirstOccuranceOfIntegerInList {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(5, 6, 2, 83, 42, 4, 3, 4, 9, 7, 2);

		Set<Integer> set = new LinkedHashSet();

		for (Integer num : list) {

			if (set.contains(num)) {
				System.out.println("First repeating integer is :" + num);
				break;
			}
			set.add(num);
		}

	}
}
