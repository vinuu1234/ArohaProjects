package com.demo.aroha;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckStringContainsDigit {

	public static void main(String[] args) {

		Pattern p = Pattern.compile("[0-9]");
		String s1 = "369383";
		String s2 = "369#73A";
		// Matcher m = p.matcher(s1);
		Matcher m = p.matcher(s2);

		while (m.find()) {
			System.out.println("valid : " + m.group());
		}
	}
}
