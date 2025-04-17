package com.demo.aroha;

import java.util.*;

public class States {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		// Nested map: State -> District -> Taluk -> List of Villages
		Map<String, Map<String, Map<String, List<String>>>> locationMap = new HashMap<>();

		// Sample data
		locationMap.put("Tamil Nadu",
				Map.of("Chennai",
						Map.of("Guindy", List.of("Village A", "Village B"), "Egmore",
								List.of("Village C", "Village D")),
						"Madurai", Map.of("Madurai North", List.of("Village E", "Village F"), "Madurai South",
								List.of("Village G", "Village H"))));

		locationMap.put("Karnataka",
				Map.of("Bangalore",
						Map.of("KR Puram", List.of("Village I", "Village J"), "Yelahanka",
								List.of("Village K", "Village L")),
						"Mysore", Map.of("Mysore North", List.of("Village M", "Village N"), "Mysore South",
								List.of("Village O", "Village P"))));

		// Select State
		System.out.println("Select a State:");
		List<String> states = new ArrayList<>(locationMap.keySet());
		for (int i = 0; i < states.size(); i++) {
			System.out.println((i + 1) + ". " + states.get(i));
		}
		int stateChoice = scanner.nextInt() - 1;
		String selectedState = states.get(stateChoice);

		// Select District
		System.out.println("Select a District:");
		Map<String, Map<String, List<String>>> districtsMap = locationMap.get(selectedState);
		List<String> districts = new ArrayList<>(districtsMap.keySet());
		for (int i = 0; i < districts.size(); i++) {
			System.out.println((i + 1) + ". " + districts.get(i));
		}
		int districtChoice = scanner.nextInt() - 1;
		String selectedDistrict = districts.get(districtChoice);

		// Select Taluk
		System.out.println("Select a Taluk:");
		Map<String, List<String>> taluksMap = districtsMap.get(selectedDistrict);
		List<String> taluks = new ArrayList<>(taluksMap.keySet());
		for (int i = 0; i < taluks.size(); i++) {
			System.out.println((i + 1) + ". " + taluks.get(i));
		}
		int talukChoice = scanner.nextInt() - 1;
		String selectedTaluk = taluks.get(talukChoice);

		// Select Village
		System.out.println("Select a Village:");
		List<String> villages = taluksMap.get(selectedTaluk);
		for (int i = 0; i < villages.size(); i++) {
			System.out.println((i + 1) + ". " + villages.get(i));
		}
		int villageChoice = scanner.nextInt() - 1;
		String selectedVillage = villages.get(villageChoice);

		// Final selection
		System.out.println("\nYou selected:");
		System.out.println("State: " + selectedState);
		System.out.println("District: " + selectedDistrict);
		System.out.println("Taluk: " + selectedTaluk);
		System.out.println("Village: " + selectedVillage);

		scanner.close();
	}

}
