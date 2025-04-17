package com.demo.aroha.day5;

import java.util.LinkedList;

public class Customer {

	private double initialMeterReading;
	private double finalmeterReading;
	private double unitConsumed;

	public Customer(double initialMeterReading, double finalmeterReading) {
		super();
		this.initialMeterReading = initialMeterReading;
		this.finalmeterReading = finalmeterReading;
		computeUnit(initialMeterReading, finalmeterReading);
	}

	@Override
	public String toString() {
		return "Customer [initialMeterReading=" + initialMeterReading + ", finalmeterReading=" + finalmeterReading
				+ "unitConsumed"+unitConsumed+"]";
	}

	public static double computeUnit(double initialMeterReading, double finalmeterReading) {
		double unitConsumed = finalmeterReading - initialMeterReading;
		//System.out.println("Unit Consumed " + unitConsumed);
		return unitConsumed;
	}

	public static void main(String[] args) {
		Customer c1 = new Customer(1000, 1200);
		Customer c2 = new Customer(1200, 1500);
		Customer c3 = new Customer(1500, 1700);
		Customer c4 = new Customer(1700, 1800);
		Customer c5 = new Customer(1800, 1930);
		
		
		CustomerType ct=new CustomerType(1000,1200,"domestic");
		
		LinkedList<Customer> list = new LinkedList<>();
		list.add(c1);
		list.add(c2);
		list.add(c3);
		list.add(c4);
		list.add(c5);

		System.out.println(list);
	}

}

class CustomerType extends Customer {

	private String custometType;

	double unitconsumed;
	double bill;

	public CustomerType(double initialMeterReading, double finalmeterReading, String custometType) {
		super(initialMeterReading, finalmeterReading);
		this.custometType = custometType;

		unitconsumed = computeUnit(initialMeterReading, finalmeterReading);
		if(custometType.equalsIgnoreCase("domestic")) {
			if (unitconsumed <= 500) {
				bill = unitconsumed * 1.10;
			}
			else if (unitconsumed >= 501&&unitconsumed <= 800) {
				bill = unitconsumed * 1.70;
			}
			else if (unitconsumed >= 801&&unitconsumed <= 1200) {
				bill = unitconsumed * 2.20;
			}
			else if (unitconsumed > 1201) {
				bill = unitconsumed * 2.50;
			}
		}
		else if(custometType.equalsIgnoreCase("commertial")) {
		
			if (unitconsumed <= 1000) {
				bill = unitconsumed * 2.20;
			}
			else if (unitconsumed >= 1001&&unitconsumed <= 2500) {
				bill = unitconsumed * 2.90;
			}
			else if (unitconsumed >= 2501&&unitconsumed <= 5000) {
				bill = unitconsumed * 3.50;
			}
			else if (unitconsumed > 5001) {
				bill = unitconsumed * 4.10;
			}
		}
		
		System.out.println("Computed Bill "+bill);
	}

	

}
