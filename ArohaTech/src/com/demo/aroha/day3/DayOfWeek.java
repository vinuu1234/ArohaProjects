package com.demo.aroha.day3;

public class DayOfWeek {

	public static void main(String[] args) {
		String date = "11-09-1998";
		String[] d = date.split("-");

		int day = Integer.valueOf(d[0]);
		int month = Integer.valueOf(d[1]);
		int year = Integer.valueOf(d[2]);
		System.out.println("Day value :" + day);
		System.out.println("year " + year);

		int[] formula = { 0, 3, 3, 6, 1, 4, 6, 2, 5, 0, 3, 5 };
		int monthValue = 0;
		for (int i = 0; i <= formula.length - 1; i++) {
			if (month == i) {
				monthValue = formula[i - 1];
			}

		}

		System.out.println("month Value :" + monthValue);
		System.out.println(monthValue);

		int diffYear = year - 1900;
		System.out.println("difference value :" + diffYear);
		
		int rem=0;
		if(month==2 && (year%4==0)&&(year%100!=0)||(year%400==0) ) {
			rem=30;
			System.out.println(year + " is leap year");
		}
		else {
		rem = diffYear / 4;
		}
		System.out.println("Remider Value :" + rem);
		
		int total = day + monthValue + diffYear + rem;
		System.out.println("total Value :" + total);

		int resDay = total % 7;
		System.out.println("ResValue :" + resDay);

		switch (resDay) {
		case 0:
			System.out.println("Sunday");
			break;
		case 1:
			System.out.println("Monday");
			break;

		case 2:
			System.out.println("thusday");
			break;

		case 3:
			System.out.println("Wendsday");
			break;

		case 4:
			System.out.println("Thursday");
			break;

		case 5:
			System.out.println("Friday");
			break;
		case 6:
			System.out.println("Saturday");
			break;

		default:
			break;
		}

	}

}
