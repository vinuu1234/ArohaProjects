package com.demo.aroha.weekend;

public class Pattern3 {

	public static void main(String[] args) {

		int n = 3;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= n - i; j++) {
				char ch = (char) (j + 65);

				System.out.print(ch + " ");
				// A B C D
				// A B C
				// A B
				// A

			}
			System.out.println();

		}
	}

}
