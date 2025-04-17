package com.demo.aroha.weekend;

public class Pattern1 {

	public static void main(String[] args) {

		int n=5;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				char ch=(char) (j+65);
				System.out.print(ch +" ");              
				/*
				  A
				  A B
				  A B C
				  A B C D
				*/
			}
			System.out.println();
			
		}
	}

}
