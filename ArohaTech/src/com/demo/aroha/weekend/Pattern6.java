package com.demo.aroha.weekend;
// 10 
// 20 21 
// 11 12 13 
// 22 23 24 25 
// 14 15 16 17 18 


public class Pattern6 {

	public static void main(String[] args) {

		int n = 5;
		int low = 10;
		int high = 20;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j <= i; j++) {
				if (i % 2 == 0) {
					System.out.print(low + " ");
					low++;
				}
				else {
					System.out.print(high+" ");
					high++;
				}
			}
			System.out.println();

		}
	}

}
