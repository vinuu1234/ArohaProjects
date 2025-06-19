package com.demo.aroha;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamExamples {
	public static void main(String[] args) {

		List<Integer> list = List.of(10, 11, 13, 17, 20, 21, 23, 30, 40, 47, 50, 60, 63, 70);

		// Filter even numbers from a list of integers using streams.
		List<Integer> evenNumbers = list.stream().filter(n -> n % 2 == 0).collect(Collectors.toList());
		System.out.println("Even Numbers : " + evenNumbers);

		List<String> names = Arrays.asList("Vinod", "Akash", "Kumar", "Sagar", "Anil");
		// Convert a list of strings to uppercase.
		List<String> namesAfterCaseChange = names.stream().map(name -> name.toUpperCase()).collect(Collectors.toList());

		System.out.println(namesAfterCaseChange);

		// Count the number of strings that start with the letter "A".
		List<String> list1 = names.stream().filter(n -> n.startsWith("A")).toList();
		System.out.println(list1);

	}

}
