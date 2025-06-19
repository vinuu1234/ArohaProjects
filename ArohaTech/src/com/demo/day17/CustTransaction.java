package com.demo.day17;

public class CustTransaction extends BankCustomer {
	private String transactionType;
	private double transactionAmount;

	private TransactionRule transactionRule = new TransactionRule();

	// Zero-arg constructor
	public CustTransaction() {
		super();
	}

	// Five-arg constructor
	public CustTransaction(int accountNumber, String name, double currentBalance, String transactionType,
			double transactionAmount) {
		super(accountNumber, name, currentBalance);
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
	}

	// Method to calculate balance after transaction
	double updatedAmount;
	public void calcBalanceAfterTransaction() {
		if (!transactionRule.validateTransaction(transactionType, transactionAmount)) {
			System.out.println("Transaction declined: Amount not within allowed limits.");
			return;
		}

		if (transactionType.equalsIgnoreCase("D")) {
			updatedAmount=getCurrentBalance() + transactionAmount;
		} else if (transactionType.equalsIgnoreCase("W")) {
			if (transactionAmount <= getCurrentBalance()) {
				updatedAmount=getCurrentBalance() - transactionAmount;
			} else {
				System.out.println("Transaction declined: Insufficient balance.");
			}
		} else {
			System.out.println("Invalid transaction type. Use 'D' or 'W'.");
		}
	}

	// Getters and Setters
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	@Override
	public void display() {
		super.display();
		System.out.println("Transaction Type : " + (transactionType.equalsIgnoreCase("D") ? "Deposit" : "Withdrawal"));
		System.out.println("Transaction Amount: ₹" + transactionAmount);
		System.out.println("Updated amount :" + updatedAmount);
	}
}
