package com.demo.oopsConcepts;

//Fully encapsulated class
class BankAccount {
	private String accountNumber;
	private String accountHolder;
	private double balance;

	// Constructor
	public BankAccount(String accountNumber, String accountHolder, double balance) {
		this.accountNumber = accountNumber;
		this.accountHolder = accountHolder;
		this.balance = balance;
	}

	// Getter methods
	public String getAccountNumber() {
		return accountNumber;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public double getBalance() {
		return balance;
	}

	// Setter methods with validation
	public void setAccountHolder(String accountHolder) {
		if (accountHolder != null && !accountHolder.isEmpty()) {
			this.accountHolder = accountHolder;
		}
	}

	// Methods to manipulate balance
	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
		}
	}

	public boolean withdraw(double amount) {
		if (amount > 0 && balance >= amount) {
			balance -= amount;
			return true;
		}
		return false;
	}
}

public class EncapsulationDemo {
	public static void main(String[] args) {
		BankAccount account = new BankAccount("123456", "John Doe", 1000.0);

		// Can't access private fields directly
		// account.balance = 5000; // Compilation error

		// Must use public methods
		account.deposit(500);
		System.out.println("New balance: " + account.getBalance());

		account.setAccountHolder("John Smith");
		System.out.println("Account holder: " + account.getAccountHolder());
	}
}