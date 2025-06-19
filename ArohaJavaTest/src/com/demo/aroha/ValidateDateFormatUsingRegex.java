package com.demo.aroha;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateDateFormatUsingRegex {

	public static void main(String[] args) {

		// Pattern dateValidation=Pattern.compile("[\\d]{2}][/][\\d]{2}[/][\\d]{4}");

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter the word to check");
			String word = sc.nextLine();
			// Pattern for check letter starts with capital Alpabets and ends with .
			Pattern p1 = Pattern.compile("[A-Z][a-zA-Z]*[.]");
			
			// Pattern for date validation dd/MM/yyyy.
			Pattern dateValidation = Pattern.compile("[\\d]{2}][/][\\d]{2}[/][\\d]{4}");
			//Matcher m = p1.matcher(word);
			Matcher m = dateValidation.matcher(word);

			if (m.find()) {
				System.out.println("valid : " + m.group());
			} else {
				System.out.println("invalid match");
			}

		}

	}
}
/*
 * Enter the word to check vinod invalid match Enter the word to check Vinod
 * invalid match Enter the word to check Vinod. valid : Vinod. Enter the word to
 * check V. valid : V. Enter the word to check Va. valid : Va. Enter the word to
 * check vinod. invalid match Enter the word to check
 */
