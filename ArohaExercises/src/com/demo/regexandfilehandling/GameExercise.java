package com.demo.regexandfilehandling;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class GameExercise {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the input: ");
		String input = sc.nextLine();
		System.out.println("Input: " + input);

		String[] inputs = input.split("\\s+");
		int score = 0;
		List<Integer> list = new ArrayList<>();

		// Regular expressions for validation
		Pattern numberPattern = Pattern.compile("-?\\d+");
		Pattern commandPattern = Pattern.compile("[cCdD+]");

		for (String token : inputs) {
			if (numberPattern.matcher(token).matches()) {
				//Parsing String number into int type
				int num = Integer.parseInt(token);
				
				//Checking Number is within the range
				if (num < -999 || num > 999) {
					System.out.println("Number out of range: " + num);
					continue;
				}
				score += num;
				list.add(num);
			} else if (commandPattern.matcher(token).matches()) {
				// Handling commands
				char command = Character.toLowerCase(token.charAt(0));
				switch (command) {

				// If value is c / C, checking list contains numbers or not if not break the come out of loop
				case 'c':
					if (!list.isEmpty()) {
						int last = list.remove(list.size() - 1);
						score -= last;
					}
					break;

				// If value is d / D, checking list contains numbers or not if not break the loop and come out of loop
				case 'd':
					if (!list.isEmpty()) {
						int last = list.get(list.size() - 1);
						int doubled = last * 2;
						score += doubled;
						list.add(doubled);
					}
					break;
				case '+':
					if (list.size() >= 2) {
						int sum = list.get(list.size() - 1) + list.get(list.size() - 2);
						score += sum;
						list.add(sum);
					}
					break;
				}
			} else {
				System.out.println("Invalid input: " + token);
			}
		}

		System.out.println("Final Score: " + score);
	}

}
