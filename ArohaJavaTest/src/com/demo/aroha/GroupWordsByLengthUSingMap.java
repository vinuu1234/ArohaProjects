package com.demo.aroha;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//19.Group words By their length
public class GroupWordsByLengthUSingMap {

	public static void main(String[] args) {
		// Sample list of words
		String[] words = { "Bat", "Ball", "Cat", "Apple", "to", "Dog" };

		// Create a map to store words grouped by length
		Map<Integer, List<String>> map = new HashMap<>();

		// Group words by their length
		for (String word : words) {
			int length = word.length();

			// If the length isn't in the map yet, add it with a new list
			if (!map.containsKey(length)) {
				map.put(length, new ArrayList<>());
			}

			// Add the word to the appropriate list
			map.get(length).add(word);
		}
		System.out.println(map.get(2));

		// Print the grouped words
		for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
			System.out.println("Words with length " + entry.getKey() + ": " + entry.getValue());
		}
	}
}
