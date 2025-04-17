package com.demo.aroha.day6;

import java.util.Arrays;
import java.util.Scanner;

public class ShortFormOfName {

	public static void main(String[] args) {
		String s = "    Vinod    KUmar Ashok Chanagoudanavar";
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println("Enter Name :");
			String name = sc.nextLine();

			String shortFormOfName = "";

			String[] parts = name.trim().split("\\s+");

			if (parts.length == 1) {
				shortFormOfName = parts[0];
			} else {

				for (int i = 0; i < parts.length - 1; i++) {
					// System.out.println(Arrays.toString(s1));
					shortFormOfName = shortFormOfName + parts[i].charAt(0) + " ";

				}
				shortFormOfName = shortFormOfName + parts[parts.length - 1];
			}

			System.out.println("Short Form Of Name Is : "+shortFormOfName);

		}
	}

}
