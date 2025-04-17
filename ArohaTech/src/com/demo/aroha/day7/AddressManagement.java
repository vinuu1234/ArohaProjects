package com.demo.aroha.day7;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class AddressManagement {

	public static void main(String[] args) {
		
		List<String> contries=new ArrayList<>();
		
		contries.add("India");
		contries.add("Usa");
		contries.add("Russia");
		contries.add("England");
		contries.add("Frans");
		contries.add("Jermany");
		contries.add("Shrilanka");
		contries.add("China");
		contries.add("Nepal");
		contries.add("SOuth Africa");
		
		List<String> states=new ArrayList<>();
		states.add("Karnataka");
		states.add("Tamilanadu");
		states.add("Andra Pradesh");
		states.add("Maharastra");
		states.add("Kerala");
		states.add("Panjab");
		states.add("UP");
		states.add("MP");

		

		TreeMap<Integer,List<String>> t=new TreeMap<>();
		t.put(1, contries);
		System.out.println(t);
		TreeMap<Integer,List<String>> t1=new TreeMap<>();
		t1.put(91, states);
		t1.put(32, states);
		

	}

}
