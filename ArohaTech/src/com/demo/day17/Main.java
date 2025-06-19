package com.demo.day17;

public class Main {
	public static void main(String[] args) {
		// Valid Deposit
		CustTransaction t1 = new CustTransaction(10100, "vinod", 45000, "D", 5000);
		t1.calcBalanceAfterTransaction();
		t1.display();

	}
}
