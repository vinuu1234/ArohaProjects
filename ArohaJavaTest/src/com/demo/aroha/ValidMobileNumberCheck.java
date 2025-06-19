package com.demo.aroha;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidMobileNumberCheck {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter the mobile");
			String mobile = sc.nextLine();
			Pattern p = Pattern.compile("[789][0-9]{9}");
			Pattern p1 = Pattern.compile("^[789][0-9]{9}$");
			Matcher m = p1.matcher(mobile);

			if (m.find()) {
				System.out.println("valid : "+m.group());
			}else {
				System.out.println("invalid match");
			}
			
		}	
	}
}

/*
 * Enter the mobile 7899736527 valid : 7899736527 Enter the mobile 5677887766
 * invalid match Enter the mobile 9999999999999 invalid match Enter the mobile
 * 9988776655 valid : 9988776655 Enter the mobile 12334433211 invalid match
 * Enter the mobile
 * 
 */