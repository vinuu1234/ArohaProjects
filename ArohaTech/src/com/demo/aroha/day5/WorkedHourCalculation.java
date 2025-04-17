package com.demo.aroha.day5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

class Employee {
	private int empId;
	private String designation;
	private double[] hoursWorked;

	public Employee(int empId, String designation, double[] h1) {
		super();
		this.empId = empId;
		this.designation = designation;
		this.hoursWorked = h1;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double[] getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double[] hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", designation=" + designation + ", hoursWorked=" + hoursWorked + "]";
	}

}

class Payment {
	private String designation;
	private double[] paymentPerHour;

	public Payment(String designation, double[] hours) {
		super();
		this.designation = designation;
		this.paymentPerHour = hours;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public double[] getPaymentPerHour() {
		return paymentPerHour;
	}

	public void setPaymentPerHour(double[] paymentPerHour) {
		this.paymentPerHour = paymentPerHour;
	}

	@Override
	public String toString() {
		return "Payment [designation=" + designation + ", paymentPerHour=" + paymentPerHour + "]";
	}

}

public class WorkedHourCalculation {

	public static void main(String[] args) {
		Employee e1 = new Employee(100, "A", new double[] { 8, 8, 7.5, 0, 8 });
		Employee e2 = new Employee(200, "B", new double[] { 9, 9.5, 8, 0, 0 });
		Employee e3 = new Employee(300, "C", new double[] { 9, 8.5, 7, 6, 0 });

		HashMap<Integer, Employee> hm = new HashMap<>();
		hm.put(e1.getEmpId(), e1);
		hm.put(e2.getEmpId(), e2);
		hm.put(e3.getEmpId(), e3);

		//System.out.println(hm);
		
		double[] hours = new double[10];
		String designation = "";
		for (Entry<Integer, Employee> entry : hm.entrySet()) {
			Integer eId = entry.getKey();
			designation = entry.getValue().getDesignation();
			hours = entry.getValue().getHoursWorked();
			// System.out.println(Arrays.toString(hours));
			// System.out.println(designation);

			Payment p = new Payment(designation, hours);

			double payHour = 0;

			double aTotal = 0;
			double bTotal = 0;
			double cTotal = 0;

			for (int i = 0; i < hours.length; i++) {
				switch (designation) {
				case "A": {
					if (hours[i] <= 8) {
						payHour = hours[i] * 125;
					} else if (hours[i] > 8) {
						payHour = hours[i] * 165;
					}
					aTotal = aTotal + payHour;


					System.out.println(designation + " Payment for  " + hours[i] + " Is " + payHour);
					break;

				}

				case "B": {
					if (hours[i] <= 9) {
						payHour = hours[i] * 140;

					} else if (hours[i] > 9) {
						payHour = hours[i] * 200;
					}
					bTotal = bTotal + payHour;

					System.out.println(designation + " Payment for " + hours[i] + " Is " + payHour);
					break;

				}

				case "C": {
					if (hours[i] <= 7) {
						payHour = hours[i] * 180;

					} else if (hours[i] > 7) {
						payHour = hours[i] * 220;
					}
					cTotal = cTotal + payHour;

					System.out.println(designation + " Payment for " + hours[i] + " Is " + payHour);
					break;

				}

				}

			}
			System.out.println(eId + " " + designation +" Total "+aTotal);
			System.out.println(eId + " " +designation +" Total "+bTotal);
			System.out.println(eId + " " +designation +" Total "+cTotal);

			

		}
		
	}
}
