package com.demo.aroha.day3;

public class PatternProgram2 {

	public static void main(String[] args) {
		int n=5;
		for (int i = 0; i <n; i++) {
			for (int j = 0; j < n; j++) {
				if(j==n-1-i) {
					System.out.print("# ");
				}
				else {
					System.out.print(" ");
				}
				
			}
			System.out.println();
			
		}

	}

}
