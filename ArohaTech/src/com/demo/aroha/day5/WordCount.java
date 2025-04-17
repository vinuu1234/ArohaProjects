package com.demo.aroha.day5;

import java.util.Arrays;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the Sentence : ");
		String sentence=sc.nextLine();
		
		
		//spliting string in array of strings
		String[] words=sentence.split("\\s+");
		System.out.println(Arrays.toString(words));
		
		int cnt=0;
		for (String string : words) {
			cnt++;
		}
		System.out.println("Count of Words "+cnt);
		
		
		/*
		 * //counting number of words int wordCount=words.length;
		 * System.out.println(wordCount);
		 */

	}

}

