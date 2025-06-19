package com.demo.Stringmethods;

public class PrimitivesToString {

	public static void main(String[] args) {
		// Primitive values
		int intValue = 42;
		long longValue = 123456789L;
		float floatValue = 3.14f;
		double doubleValue = 2.71828;
		boolean boolValue = true;
		char charValue = 'A';

		// Method 1: String.valueOf()
		String intStr1 = String.valueOf(intValue);
		String longStr1 = String.valueOf(longValue);
		String floatStr1 = String.valueOf(floatValue);
		String doubleStr1 = String.valueOf(doubleValue);
		String boolStr1 = String.valueOf(boolValue);
		String charStr1 = String.valueOf(charValue);

		// Method 2: Using wrapper class toString()
		String intStr2 = Integer.toString(intValue);
		String longStr2 = Long.toString(longValue);
		String floatStr2 = Float.toString(floatValue);
		String doubleStr2 = Double.toString(doubleValue);
		String boolStr2 = Boolean.toString(boolValue);
		String charStr2 = Character.toString(charValue);

		// Method 3: Concatenation with empty string
		String intStr3 = intValue + "";
		String longStr3 = longValue + "";
		String floatStr3 = floatValue + "";
		String doubleStr3 = doubleValue + "";
		String boolStr3 = boolValue + "";
		String charStr3 = charValue + "";

		System.out.println("int to String: " + intStr1);
		System.out.println("long to String: " + longStr1);
		System.out.println("float to String: " + floatStr1);
		System.out.println("double to String: " + doubleStr1);
		System.out.println("boolean to String: " + boolStr1);
		System.out.println("char to String: " + charStr1);
	}

}
