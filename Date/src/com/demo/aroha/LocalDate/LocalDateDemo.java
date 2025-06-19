package com.demo.aroha.LocalDate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateDemo {
	public static void main(String[] args) {
		
		LocalDate specificDate = LocalDate.of(2023, 6, 15);
		System.out.println("Specific date: " + specificDate);

		LocalDate date = LocalDate.now();

		// Default format (ISO-8601)
		System.out.println("ISO format: " + date);

		// Custom formats
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		System.out.println("Custom format: " + date.format(formatter));

		formatter = DateTimeFormatter.ofPattern("MMM dd, yyyy");
		System.out.println("Another format: " + date.format(formatter));
		
		//parsing string to date
		LocalDate parsedDate = LocalDate.parse("2023-12-25");  
		System.out.println(parsedDate);
		
		String date1="5/5/2025";
		
		LocalDate d= LocalDate.parse(date1);
		System.out.println(d);
		


	}
}