package com.demo.aroha.weekend;

import java.util.Arrays;

public class BinaryToDecimal {

	public static void main(String[] args) {
		
		int[] a = {1,0,0,1,0,1,1,0};
		
		int res=0;
		int sum=0;
		for (int i = a.length-1; i >= 0; i--) {

			if(a[i]==1) {
				
				res=(int) Math.pow(2, a.length-1-i);
	            System.out.println(res);
	            sum+=res;

			}
			
			
		}
		System.out.println("Decimal Number of "+Arrays.toString(a)+ " is " +sum);

	}

}
