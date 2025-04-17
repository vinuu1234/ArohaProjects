package com.demo.aroha.day5;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class WordReplacement {
	static int foundCount = 0;

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the sentence");
		String sentence = scan.nextLine();
		System.out.println("Enter the world to find in the sentence");
		String worldToFind = scan.nextLine();

		String[] words = sentence.split(" ");
		foundCount = 0;
		for (String word : words) {
			if (word.equalsIgnoreCase(worldToFind)) {
				foundCount++;
			}
		}

		//System.out.println(worldToFind + " is found " + foundCount + " times");

		if (foundCount >= 1) {
			System.out.println("Enter the word to replace");
			String replacedWord = scan.nextLine();
			// System.out.println("Enter the occurrence number you want to replace (1-based
			// index)");
			// int occurrenceToReplace = scan.nextInt();

			for (int i = 0; i < words.length; i++) {
				if (words[i].equalsIgnoreCase(worldToFind)) {
					// if (occurrenceCounter == occurrenceToReplace) {
					words[i] = replacedWord;

				}
			}
			//System.out.println(Arrays.toString(words));
			
			for (String string : words) {
				System.out.print(string + " ");
			}
		}

	}
}