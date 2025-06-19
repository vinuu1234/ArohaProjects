package com.demo.Stringmethods;

public class StringToPrimitive {

	public static void main(String[] args) {
		// String values
		String intStr = "42";
		String longStr = "123456789";
		String floatStr = "3.14";
		String doubleStr = "2.71828";
		String boolStr = "true";
		String charStr = "A";

		// Method 1: parseXxx() - returns primitive directly
		int intValue1 = Integer.parseInt(intStr);
		long longValue1 = Long.parseLong(longStr);
		float floatValue1 = Float.parseFloat(floatStr);
		double doubleValue1 = Double.parseDouble(doubleStr);
		boolean boolValue1 = Boolean.parseBoolean(boolStr);
		char charValue1 = charStr.charAt(0); // Special case for char

		// Method 2: valueOf() - returns wrapper, then unboxing
		int intValue2 = Integer.valueOf(intStr);
		long longValue2 = Long.valueOf(longStr);
		float floatValue2 = Float.valueOf(floatStr);
		double doubleValue2 = Double.valueOf(doubleStr);
		boolean boolValue2 = Boolean.valueOf(boolStr);

		// Error handling example
		String invalidNumber = "123abc";
		try {
			int badValue = Integer.parseInt(invalidNumber);
			System.out.println("Parsed value: " + badValue);
		} catch (NumberFormatException e) {
			System.out.println("Cannot parse '" + invalidNumber + "' to int");
		}

		System.out.println("String to int: " + intValue1);
		System.out.println("String to long: " + longValue1);
		System.out.println("String to float: " + floatValue1);
		System.out.println("String to double: " + doubleValue1);
		System.out.println("String to boolean: " + boolValue1);
		System.out.println("String to char: " + charValue1);
	}

}
