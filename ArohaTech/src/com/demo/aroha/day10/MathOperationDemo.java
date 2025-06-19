package com.demo.aroha.day10;

import java.util.Scanner;

@FunctionalInterface
interface MathOperation {
	int operate(int a, int b);
}

public class MathOperationDemo {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("\nChoose Operation:");
			System.out.println("1. Addition");
			System.out.println("2. Subtraction");
			System.out.println("3. Multiplication");
			System.out.println("4. Division");
			System.out.println("5. Exit");

			int choice = sc.nextInt();

			if (choice == 5) {
				System.out.println("Exiting...");
				break;
			}

			System.out.print("Enter first number: ");
			int num1 = sc.nextInt();
			System.out.print("Enter second number: ");
			int num2 = sc.nextInt();

			MathOperation op = null;

			switch (choice) {
			case 1:
				op = (a, b) -> a + b;
				break;
			case 2:
				op = (a, b) -> a - b;
				break;
			case 3:
				op = (a, b) -> a * b;
				break;
			case 4:
				if (num2 == 0) {
					System.out.println("Error: Cannot divide by zero!");
					continue;
				}
				op = (a, b) -> a / b;
				break;
			default:
				System.out.println("Invalid choice!");
				continue;
			}

			int result = op.operate(num1, num2);
			System.out.println("Result: " + result);
		}

		sc.close();
	}
}
