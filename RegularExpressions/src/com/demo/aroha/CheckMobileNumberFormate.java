package com.demo.aroha;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckMobileNumberFormate {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter the mobile number :");

			String mobile = sc.next();
			Pattern p = Pattern.compile("(0|91)?[7-9][0-9]{9}");
			Matcher m = p.matcher(mobile);

			if (m.find() && m.group().equals(mobile)) {
				System.out.println("Valid mobile number : "+m.group());
			}
			else {
				System.out.println("Invalid mobile !!!");
			}
		}

	}

}
