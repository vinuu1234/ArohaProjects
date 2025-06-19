package com.demo.oopsConcepts;

class Vehicle {
	public void start() {
		System.out.println("Vehicle is starting");
	}

	public void stop() {
		System.out.println("Vehicle is stopping");
	}
}

class Car extends Vehicle {
	@Override
	public void start() {
		System.out.println("Car is starting with ignition");
	}

	public void sound() {
		System.out.println("Beep beep!");
	}
}

class Bike extends Vehicle {
	@Override
	public void start() {
		System.out.println("Bike is starting with kick");
	}
}

public class OverridingExample {
	public static void main(String[] args) {
		// Parent reference to child object
		Vehicle vehicle1 = new Car();
		Vehicle vehicle2 = new Bike();

		// Runtime polymorphism - JVM decides which method to call
		vehicle1.start(); // Calls Car's start()
		vehicle1.stop(); // Calls Vehicle's stop()
		// vehicle1.honk(); // Compilation error - Vehicle doesn't have honk()

		vehicle2.start(); // Calls Bike's start()
		vehicle2.stop(); // Calls Vehicle's stop()

		
	}

}
