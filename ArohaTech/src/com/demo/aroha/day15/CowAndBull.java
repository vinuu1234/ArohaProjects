package com.demo.aroha.day15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class CowAndBull {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		Random rand = new Random();
		
		//generating secret number
		List<Integer> digits = new ArrayList<>();
		while (digits.size() < 3) {
			int digit = rand.nextInt(10);
			if (!digits.contains(digit)) {
				digits.add(digit);
			}
		}

		String s = "";
		for (int digit : digits) {
			s=s+digit;
		}
		System.out.println(s);
		
		
		//generating guessing number
		 while (true) {
	            System.out.print("Enter your guess: ");
	            String guess = sc.nextLine();
	            
	            if(guess.length()!=3) {
	            	System.out.println("Invalid Number !!!!");
	            }
	            
	            Set<Character> unique=new LinkedHashSet<>();
	            for (int i = 0; i < guess.length(); i++) {
	            	unique.add(guess.charAt(i));
					
				}
	           
	            }
	            
	            

	}
}
