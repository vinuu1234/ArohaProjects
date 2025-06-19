package com.demo.Stringmethods;

public class StringMethodExamples {
	public static void main(String[] args) {
		String name = "   Vinod Chanagoudanavar   ";
		System.out.println(name.length());
		System.out.println(name.charAt(0));
		System.out.println(name.substring(2));
		System.out.println(name.substring(2, 8));
		System.out.println("===================================");
		
		//comparison methods
		System.out.println(name.equals("Vinod"));
		System.out.println(name.equals("kumar"));
		System.out.println(name.equals("VinOd Chanagoudanavar"));
		System.out.println(name.equalsIgnoreCase("VINOD Chanagoudanavar"));
		System.out.println("======================================");
		//search methods
		System.out.println(name.indexOf('V'));
		System.out.println(name.indexOf('i'));
		System.out.println(name.indexOf("Vinod"));
		System.out.println(name.indexOf("n"));
		System.out.println(name.startsWith("vin"));
		System.out.println(name.lastIndexOf("av"));
		System.out.println(name.contains("gouda"));
		System.out.println(name.endsWith("var"));
		System.out.println(name.endsWith("var"));
		System.out.println(name.replace('V', 'B'));
		System.out.println(name.replace("Vinod", "Kumar"));
		System.out.println(name.replaceAll("a", "u"));
		System.out.println(name.trim());
		System.out.println(name.toUpperCase());
		System.out.println(name.toLowerCase());
		
		String str1 = "Vinod";
		String str2 = "";
		String str3="   ";
		System.out.println(str1.isEmpty());//false
		System.out.println(str2.isEmpty());//true
		System.out.println(str3.isBlank());//true
		System.out.println(str3.isEmpty());//false

		String myStr = "Hello planet earth, you are a great planet.";
		System.out.println(myStr.indexOf("planet"));
		System.out.println(myStr.lastIndexOf("planet"));
		
		String str = "Split a string by spaces, and also punctuation.";
		String regex = "[,\\.\\s]";
		String[] myArray = str.split(regex);
		for (String s : myArray) {
		  System.out.println(s);
		}
		
	


		

	}

}
