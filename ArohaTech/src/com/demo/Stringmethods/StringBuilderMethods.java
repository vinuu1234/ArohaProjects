package com.demo.Stringmethods;

public class StringBuilderMethods {
	public static void main(String[] args) {
		StringBuilder sb1 = new StringBuilder();
		String s1 = new String();
		System.out.println(s1);

		System.out.println(sb1);
		System.out.println("Initial capacity =" + sb1.capacity());// 16
		System.out.println("length" + sb1.length());// 0

		StringBuilder sb2 = new StringBuilder(20); // Custom capacity
		System.out.println(sb2);
		System.out.println("Capacity = " + sb2.capacity());// 20

		StringBuilder sb3 = new StringBuilder("Hello"); // Initialized with a string
		System.out.println(sb3);// Hello
		System.out.println(sb3.capacity());//

		// append() methods
		sb3.append(" World!");
		System.out.println(sb3);// Hello World!
		sb3.append(123);
		System.out.println(sb3);// Hello World!123
		sb3.append(true);
		System.out.println(sb3);// Hello World!123true

		// insert() methods
		sb3.insert(5, " Java");// Hello Java World!123true
		System.out.println(sb3);
		sb3.insert(0, "START: ");// START: Hello Java World!123true
		System.out.println(sb3);

		// delete() methods
		sb3.delete(0, 7);
		System.out.println("After delete(0, 7)" + sb3);// Hello Java World!123true
		sb3.deleteCharAt(5);
		System.out.println("After deleteCharAt " + sb3);// HelloJava World!123true

		// replace()
		sb3.replace(6, 11, "Universe");
		System.out.println("After replace(6, 11): " + sb3);// HelloJUniverseorld!123true

		// reverse()
		sb3.reverse();
		System.out.println("After reverse(): " + sb3);
		sb3.reverse(); // Reverse back to original
		System.out.println(sb3);
		// capacity management
		sb3.ensureCapacity(100);
		System.out.println(sb3.capacity());// 100

		sb3.setLength(10);
		System.out.println(sb3);
		System.out.println(sb3.length());// 10

		// charAt() and setCharAt()
		System.out.println("CharAt " + sb3.charAt(3));// l
		sb3.setCharAt(3, 'X');
		System.out.println(sb3);// HelXoJUniv

		// substring()
		System.out.println(sb3.substring(2, 6));// lXoJ
		System.out.println(sb3.substring(5));// JUniv

		// indexOf() and lastIndexOf()
		StringBuilder sb4 = new StringBuilder("apple orange apple banana");
		System.out.println("New StringBuilder: " + sb4);
		System.out.println("indexOf " + sb4.indexOf("apple"));
		System.out.println("indexOf " + sb4.indexOf("apple", 5));
		System.out.println("lastIndexOf " + sb4.lastIndexOf("apple"));

	}
}