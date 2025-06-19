package com.demo.aroha.day15;

public class MangoPuzzle {
	public static void main(String[] args) {
		int x = 7; // Start from 7 since it must be divisible by 7

		while (true) {
			if (x % 2 == 1 && x % 3 == 1 && x % 4 == 1 && x % 5 == 1 && x % 6 == 1 && x % 7 == 0) {
				System.out.println("The number of mangoes is: " + x);
				break;
			}
			x += 7; // Increment by 7 to ensure x stays a multiple of 7
		}
	}
}
