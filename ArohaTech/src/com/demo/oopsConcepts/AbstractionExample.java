package com.demo.oopsConcepts;

interface Shape {
	double calculateArea(); 

	default void display() { 
		System.out.println("This is a shape");
	}
}

class Circle implements Shape {
	private double radius;

	public Circle(double radius) {
		this.radius = radius;
	}

	public double calculateArea() {
		return Math.PI * radius * radius;
	}
}

public class AbstractionExample {
	public static void main(String[] args) {

		Shape circle = new Circle(5);
		System.out.println("Circle area: " + circle.calculateArea());
		circle.display();
	}
}
