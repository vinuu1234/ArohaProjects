package com.demo.aroha;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class LocalDateTimePrint {

	public static void main(String[] args) {
		// 8.print todays date
		LocalDateTime dateTime = LocalDateTime.now();
		System.out.println(dateTime);

		// 9.get todays date and convert to 18/06/2025
		LocalDate date = LocalDate.now();
		System.out.println("Date Before convertion : " + date);

		// 10.converting date to string
		DateTimeFormatter formater = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		String dateAfterConvertion = date.format(formater);
		System.out.println("Date after convertion : " + dateAfterConvertion);

		// 11.Adding 30 days to current date
		LocalDate nextmonth = date.plusDays(30);
		System.out.println("Next month : " + nextmonth);

		// 12.Find days between 2025-06-01 and 2025-06-18
		LocalDate startDate = LocalDate.of(2025, 06, 01);
		LocalDate endDate = LocalDate.of(2025, 06, 18);

		Period p = Period.between(startDate, endDate);
		System.out.println("Days between two dates : " + p.getDays());

		//14. what is the day of a week on 15-Aug-2025
		LocalDate date1 = LocalDate.of(2025, 8, 15);
		System.out.println(date1.getDayOfWeek());

	}
}
/*
 * 2025-06-18T18:21:58.578402 Date Before convertion : 2025-06-18 Date after
 * convertion : 18-06-2025
 */
