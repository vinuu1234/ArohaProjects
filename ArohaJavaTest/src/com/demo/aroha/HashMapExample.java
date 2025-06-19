package com.demo.aroha;

import java.util.HashMap;
import java.util.Map;

//16.Create HashMap of Student roll numbers and names ,print all entries
public class HashMapExample {

	public static void main(String[] args) {
		HashMap<Integer, String> studentMap = new HashMap<>();

		studentMap.put(100, "Vinod");
		studentMap.put(200, "Anil");
		studentMap.put(300, "Kumar");
		studentMap.put(400, "Akash");
		studentMap.put(500, "Sagar");

		for (Map.Entry<Integer, String> values : studentMap.entrySet()) {
			System.out.println(values);
		}

	

	}
}
