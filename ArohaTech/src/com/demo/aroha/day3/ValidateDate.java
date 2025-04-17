package com.demo.aroha.day3;

public class ValidateDate {

	public static void main(String[] args) {

		String purchaseDate = "12/04/2025";
		int cnt = 0;
		String target = "/";

		for (int i = 0; i < purchaseDate.length(); i++) {
			cnt++;

		}
		if (cnt != 10) {
			System.out.println("Date not formated currectly");
		} else if (purchaseDate.contains(target)) {
			System.out.println("Date formated currectly");
		}
		System.out.println(cnt);

		String[] date = purchaseDate.split("/");

		int day = Integer.valueOf(date[0]);
		int month = Integer.valueOf(date[1]);
		int year = Integer.valueOf(date[2]);

		System.out.println(day);
		System.out.println(month);
		System.out.println(year);

		int daysInMonth;
		switch (month) {
		case 2:
			daysInMonth = isLeapYear(year) ? 29 : 28; // to see if the year is lleap year and select day based on
														// condition
			break;
		case 4: // in the month of April, june, sep, nov the daysInMoth are 30
		case 6:
		case 9:
		case 11:
			daysInMonth = 30;
			break;
		default:
			daysInMonth = 31; // in jan, mar, may, july, aug. oct, dec the days are 31
		}
		System.out.println(daysInMonth);

	}

	private static boolean isLeapYear(int year) {

		return (year % 4 == 0 && year % 100 != 0 || (year % 400 == 0));
	}

}
