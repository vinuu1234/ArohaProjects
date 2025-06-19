package com.demo.aroha;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter the String");
			String name = sc.nextLine();
			Pattern p = Pattern.compile("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");

			Matcher m = p.matcher(name);

			if (m.find()) {
				System.out.println("valid : "+m.group());
			}else {
				System.out.println("invalid match");
			}
			
		}
	}

}
/*
 * Enter the String vinod@123-patil.com valid : vinod@123-patil.com Enter the
 * String #vinod@123-patil.com invalid match Enter the String v@123.patil.com
 * valid : v@123.patil.com Enter the String
 * 
 * @123.patil.com invalid match Enter the String vinod.123@123.patil.com valid :
 * vinod.123@123.patil.com Enter the String
 * 
 * invalid match Enter the String vinod@123+patil.com invalid match Enter the
 * String vinod@123.com.in valid : vinod@123.com.in Enter the String
 * vinod@123.com.in valid : vinod@123.com.in Enter the String vinod@123.com.in
 * valid : vinod@123.com.in Enter the String vinod@123.c invalid match Enter the
 * String
 */