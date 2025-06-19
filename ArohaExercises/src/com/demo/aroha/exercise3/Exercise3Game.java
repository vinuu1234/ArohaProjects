package com.demo.aroha.exercise3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Exercise3Game {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the input : ");
		String input = sc.nextLine();
		System.out.println("Input: " + input);

		String[] s = input.split("\\s+");
		int score = 0;
		List<Integer> numbers = new ArrayList<>();

		System.out.println("Tokens: " + Arrays.toString(s));

		for (String token : s) {
			try {
				// Handle numbers
				int num = Integer.parseInt(token);
				if (num < -999 || num > 999) {
					System.out.println("Number out of range: " + num);
					continue;
				}
				score += num;
				numbers.add(num);
				System.out.println("Added " + num + ", score: " + score);
			} catch (NumberFormatException e) {
				// Handle commands
				char command = token.toLowerCase().charAt(0);
				switch (command) {
				case 'c':
					//checking list is not empty
					if (!numbers.isEmpty()) {
						int last = numbers.remove(numbers.size() - 1);
						score -= last;
						System.out.println("Cancelled " + last + ", score: " + score);
					}
					break;
				case 'd':
					if (!numbers.isEmpty()) {
						int last = numbers.get(numbers.size() - 1);
						int doubled = last * 2;
						score += doubled;
						numbers.add(doubled);
						System.out.println("Doubled " + last + " to " + doubled + ", score: " + score);
					}
					break;
				case '+':
					if (numbers.size() >= 2) {
						int sum = numbers.get(numbers.size() - 1) + numbers.get(numbers.size() - 2);
						score += sum;
						numbers.add(sum);
						System.out.println("Added last two: " + sum + ", score: " + score);
					}
					break;
				default:
					System.out.println("Invalid command: " + token);
				}
			}
		}

		System.out.println("Final Score: " + score);
	}

}
