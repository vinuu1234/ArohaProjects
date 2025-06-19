package com.demo.aroha;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ArraysWithZerosLast {

	public static void main(String[] args) {
		int[] a = { 1, 0, 5, 0, 0, 4, 2 };
		int[] a1 = new int[a.length];
		int k=0;

		for (int i = 0; i < a.length; i++) {
			if(a[i]!=0) {
				a1[k]=a[i];
				k++;

			}
		}
		for (int j = 0; j < a1.length; j++) {
			System.out.println(a1[j]);
		}
	}

}
