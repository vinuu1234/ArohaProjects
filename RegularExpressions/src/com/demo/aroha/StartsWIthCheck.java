package com.demo.aroha;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StartsWIthCheck {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		/*
		 * while (true) { System.out.println("Enter the name"); String name =
		 * sc.nextLine();
		 * 
		 * Pattern p = Pattern.compile("[aA][a-zA-Z]*");
		 * 
		 * Matcher m = p.matcher(name);
		 * 
		 * if (m.find()) { System.out.println("Matched name :"+m.group()); }else {
		 * System.out.println("Not matched"); } }
		 */
		
		
		//Ends with A/a
		/*
		 * while (true) { System.out.println("Enter the name"); String name =
		 * sc.nextLine();
		 * 
		 * Pattern p = Pattern.compile("[a-zA-Z]*[aA]");
		 * 
		 * Matcher m = p.matcher(name);
		 * 
		 * if (m.find()) { System.out.println("Matched name :"+m.group()); }else {
		 * System.out.println("Not matched"); } }
		 */
		
		//starts with a/A and ends with l/L
		/*
		 * while (true) { System.out.println("Enter the name"); String name =
		 * sc.nextLine();
		 * 
		 * Pattern p = Pattern.compile("[aA][a-zA-Z]*[aA]");
		 * 
		 * Matcher m = p.matcher(name);
		 * 
		 * if (m.find()) { System.out.println("Matched name :"+m.group()); }else {
		 * System.out.println("Not matched"); } }
		 */
		
		while (true) {
			System.out.println("Enter the name");
			String name = sc.nextLine();

			Pattern p = Pattern.compile("[aA][a-zA-Z]*il");

			Matcher m = p.matcher(name);

			if (m.find()) {
				System.out.println("Matched name :"+m.group());
			}else {
			System.out.println("Not matched");
			}
		}
		
	}
}
