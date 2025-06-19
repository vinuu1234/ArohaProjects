package com.demo.oopsConcepts;

class MathOperations {
	public int add(int a, int b) {
		return a + b;
	}

	public double add(double a, double b) {
		return a + b;
	}

	public int add(int a, int b, int c) {
		return a + b + c;
	}

	public String add(String a, String b) {
		return a + b;
	}
}

public class OverloadingExample {
	public static void main(String[] args) {
		MathOperations math = new MathOperations();

		System.out.println("Int addition: " + math.add(5, 3));
		System.out.println("Double addition: " + math.add(5.5, 3.2));
		System.out.println("Three int addition: " + math.add(5, 3, 2));
		System.out.println("String concatenation: " + math.add("Hello", " World"));
	}

}
