package com.demo.aroha.weekend;

import java.util.Arrays;

public class DecimalToBinary {

	public static void main(String[] args) {
		
		int a = 10;
		
		int temp=a;
		int rem=0;
		String decimalNumber="";
		while(temp>0) {
			rem=temp%2;
			temp=temp/2;
			decimalNumber=rem+decimalNumber;
			//System.out.println(rem);
			
		}
		System.out.println("Binary equivalent of "+a+" is "+decimalNumber);

	}

}
