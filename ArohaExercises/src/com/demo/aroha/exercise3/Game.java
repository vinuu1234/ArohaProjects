package com.demo.aroha.exercise3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/*Assume you are playing a game and you need to arrive at final score based on the rules given below.
The game accepts up to 100 inputs (characters or numbers) separated by blank space. Numbers (integers) must be between -999 and +999.
Example:
3 4 5 d D c C +
If the input is a number, score has to be incremented / decremented by that number.
If the input is “c” or “C”, previous number shouldn’t be considered.
If the input is “d” or “D”, double the previous number and add it to the score. If c or C comes after d or D, it should be ignored in the score.
If the input is “+”, add the last 2 numbers and add it to the score.
Display the input and score.
Use Scanner sc = new Scanner(System.in); to accept the input.
Example
                     3    4    5    d       D      c      C         +
Score = 0 + 3 + 4 + 5 + 10 + 20 – 20 – 10 + (5+4) = 21
*/public class Game {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the input: ");
		String input = sc.nextLine();
		System.out.println("Input: " + input);

		String[] tokens = input.split("\\s+");
		int score = 0;
		List<Integer> numbers = new ArrayList<>();

		for (String token : tokens) {
			try {
				// Handle numbers
				int num = Integer.parseInt(token);
				if (num >= -999 && num <= 999) {
					score += num;
					numbers.add(num);
					System.out.println("Added " + num + ", score: " + score);
				} else {
					System.out.println("Number out of range: " + num);
				}
			} catch (NumberFormatException e) {
				// Handle commands
				if (token.length() != 1) {
					System.out.println("Invalid command: " + token);
					continue;
				}

				char command = Character.toLowerCase(token.charAt(0));
				switch (command) {
				case 'd':
					if (!numbers.isEmpty()) {
						int last = numbers.get(numbers.size() - 1);
						int doubled = last * 2;
						score += doubled;
						numbers.add(doubled);
						System.out.println("Doubled " + last + " to " + doubled + ", score: " + score);
					}
					break;
				case 'c':
					if (!numbers.isEmpty()) {
						int removed = numbers.remove(numbers.size() - 1);
						score -= removed;
						System.out.println("Cancelled " + removed + ", score: " + score);
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
