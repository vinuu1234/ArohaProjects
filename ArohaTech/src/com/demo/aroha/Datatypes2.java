package com.demo.aroha;

public class Datatypes2 {

	public static void main(String[] args) {

		byte b = (byte) 130;
		System.out.println(b);
		byte b1 = (byte) (10 * 25);
		System.out.println(b1);
		byte b2 = 22;
		System.out.println(b2);
		String s1 = "vinod";
		String s2 = new String("vinod");
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());

		
		/*
		 * String s1 = new String("Asha"); String s2 = new String("asha");
		 * 
		 * System.out.println(s1.hashCode()); System.out.println(s2.hashCode());
		 * 
		 * if (s1.equals(s2)) { System.out.println("yes same"); } else {
		 * System.out.println("no same");
		 * 
		 * }
		 * 
		 * String s3 = "usha"; for (char x : s3.toCharArray()) { System.out.print(x); }
		 */
	}

}
