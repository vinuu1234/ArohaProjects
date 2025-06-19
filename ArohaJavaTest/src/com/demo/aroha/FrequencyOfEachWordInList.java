package com.demo.aroha;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//17.Frequency of each word in a List
public class FrequencyOfEachWordInList {

	public static void main(String[] args) {
		List<String> words = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");

		Map<String, Integer> map = new HashMap<String, Integer>();

		for (String string : words) {

			if (map.containsKey(string)) {
				map.put(string, map.get(string) + 1);
			} else {
				map.put(string, 1);
			}
		}
		
		for (Map.Entry<String, Integer> entry : map.entrySet()) {
			String key = entry.getKey();
			Integer val = entry.getValue();
			System.out.println("Frequency of each word is "+ key +" "+ val);
		}	
		
	}
}
