package com.demo.aroha.day3;

import java.util.Scanner;

public class ArithmeticOperationOf4Digits {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num1 = 0;
		int num2 = 0;
		String oper;
		int count = 0;

		while (true) {
			System.out.println("Enter the Number1 (4 digits):");
			num1 = scan.nextInt();

			if (String.valueOf(num1).length() == 4) { // converting int to string and length checking
				while (true) {
					System.out.println("Enter the Number 2 (4 digits):");
					num2 = scan.nextInt();

					if (String.valueOf(num2).length() == 4 && num2 > num1) { // converting int to string and length
																				// checking
						while (true) {
							System.out.println("Enter the Arithmetic operator (+, -, *, /):");
							oper = scan.next();

							if (oper.equals("+") || oper.equals("-") || oper.equals("*") || oper.equals("/")) {
								switch (oper) {
								case "+":
									count = num1 + num2;
									System.out.println("Result: " + count);
									break;
								case "-":
									count = num1 - num2;
									System.out.println("Result: " + count);
									break;
								case "*":
									count = num1 * num2;
									System.out.println("Result: " + count);
									break;
								case "/":
									count = num1 / num2;
									break;
								}
								break; // break out of the operator loop
							} else {
								System.out.println("Invalid operator.");
							}
						}
						break; // Break out of the Number 2 loop
					} else if (num1 > num2) {
						System.out.println("num2 must be greater than num1");
					} else {
						System.out.println("Number 2 is not greater than number 1.");
					}

				}
				break; // break out of the Number 1 loop
			} else {
				System.out.println("Number 1 is not a 4-digit number");
			}
		}
	}
}