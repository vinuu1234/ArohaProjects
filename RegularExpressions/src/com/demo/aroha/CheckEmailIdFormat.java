package com.demo.aroha;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckEmailIdFormat {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter the EMail id :");

			String mobile = sc.next();
			Pattern p = Pattern.compile("[\\w][\\w]*@[a-zA-Z]+([.][a-zA-Z]+)+");
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
