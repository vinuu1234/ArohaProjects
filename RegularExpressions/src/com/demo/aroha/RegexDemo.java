package com.demo.aroha;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexDemo {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter pattern");

			String pattern = sc.next();
			Pattern p = Pattern.compile(pattern);
			//Matcher m = p.matcher("aababbaba");
			Matcher m = p.matcher("abaabaaabbcaba");
			while (m.find()) {
				System.out.println(m.start() + "====" + m.group());
			}
		}
	}

}
