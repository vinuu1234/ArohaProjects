package com.demo.aroha.day4;

import java.util.Arrays;

public class BillAmout {

	public static void main(String[] args) {
		String transaction = "B129,5000000.25;A1552,6545;B1029,455;B14,125;B104,12.25;ABMG,2.2";
		transaction(transaction);
	}

	public static void transaction(String transactionData) {
		String[] transactions = transactionData.split(";"); // SPLIT IN ; AND STORE THE ELEMENTS IN THE ARRAY FORMAT
		System.out.println("====="+Arrays.toString(transactions)+"============");
		float total = 0;
		
		float highestAmount = Float.MIN_VALUE;
		float lowestAmount = Float.MAX_VALUE;
		String highestBillId = "";
		String lowestBillId = "";
		for (String transaction : transactions) {
			String[] parts = transaction.split(","); // split in , and store in array format
			System.out.println("Parts"+Arrays.toString(parts));

			String billNumber = parts[0]; // store the 0th element in billNumber
			System.out.println("==Bill Number====="+billNumber+"============");
			float billAmount = Float.parseFloat(parts[1]); // converting string to float
			total = total + billAmount; // to find the toatl
			if (billAmount > highestAmount) {
				highestAmount = billAmount;
				highestBillId = billNumber;
			}

			if (billAmount < lowestAmount) {
				lowestAmount = billAmount;
				lowestBillId = billNumber;
			}

		}
		float average = total / transactions.length; // finding the average

		System.out.println("Total Bill Amount: " + total);
		System.out.println("Average Bill Amount: " + average);
		System.out.println("Highest bill amount is INR " + highestAmount + " and the bill number is " + highestBillId);
		System.out.println("Lowest bill amount is INR " + lowestAmount + " and the bill number is " + lowestBillId);

	}

}
