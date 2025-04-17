package com.demo.aroha.weekend;

public class Pattern4 {

	public static void main(String[] args) {

		int n=3;
		for (int i = 0; i <= n; i++) {
			for (int j = i; j <= n; j++) {
				char ch=(char) (j+65);
				
				System.out.print(ch+" ");
				//A B C D
				//B C D
				//C D
				//D

			}
			
			System.out.println();
			
		}
	}

}
