package com.demo.aroha.weekend;

public class Pattern2 {

	public static void main(String[] args) {

		int n=3;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= i; j++) {
				char ch=(char) (i+65);
				System.out.print(ch);
				
				/*
				 * A 
				 * B B 
				 * C C C 
				 * D D D D
				 */
			}
			System.out.println();
			
		}
	}

}
