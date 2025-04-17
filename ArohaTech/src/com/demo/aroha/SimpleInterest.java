package com.demo.aroha;

public class SimpleInterest {

	public static void main(String[] args) {
		
		double principal = 40000;
		double rate = 11;
		double time = 2;
		double result = simpleInterest(principal, rate, time);
		System.out.println(result);

	}

	public static double simpleInterest(double principal, double rate, double time) {

		double interest = (principal * rate * time)/100;
		return interest;

	}

	
}
