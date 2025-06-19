package com.demo.Date;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

public class UtilDateExample {
	public static void main(String[] args) {
		// Current date and time
		Date now = new Date();
		System.out.println("Current Date (util): " + now);

		// Formatting
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String formattedDate = sdf.format(now);
		System.out.println("Formatted Date: " + formattedDate);

		// Converting between java.util.Date and java.time
		// Date to Instant
		Instant instant = now.toInstant();
		System.out.println("Instant: " + instant);

	}
}