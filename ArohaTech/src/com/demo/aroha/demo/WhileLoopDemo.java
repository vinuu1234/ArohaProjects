package com.demo.aroha.demo;

import java.util.Scanner;

public class WhileLoopDemo {
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		while(true) {
			System.out.println("Enter Name");
			String name=sc.nextLine();
			if(true) {
				System.out.println("hello");
				
			}
			else {
			System.out.println("Hi");
			break;
			}
		}
		System.out.println("How are you");
	}

}
