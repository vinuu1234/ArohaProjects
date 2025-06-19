package com.demo;

public class Person {
	private String name;

	public Person setName(String name) {
		this.name = name;
		return this; // Returning the current object
	}

	public Person printName() {
		System.out.println("Name: " + name);
		return this;
	}

	public static void main(String[] args) {
		new Person().setName("Alice").printName(); // Method chaining in action
	}
}
