package com.demo.aroha.day5;

import java.util.Scanner;

public class FindAndReplaceWord {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the Sentence : ");
		String sentence = sc.nextLine();

		System.out.println("Enter the Target Word : ");
		String targetWord = sc.nextLine();

		System.out.println("Enter the Replaced : ");
		String ReplacedWord = sc.nextLine();

		String sentenceWithRepacedWord = "";

		// Checking the sentence contains perticular word or not
		if (sentence.contains(targetWord)) {
			System.out.println(targetWord + ": Word is found in sentence :" + sentence);
			// Replacing target word with replaced word
			sentenceWithRepacedWord = sentence.replace(targetWord, ReplacedWord);

		}

		System.out.println("Replaced sentence is : " + sentenceWithRepacedWord);

	}

}
