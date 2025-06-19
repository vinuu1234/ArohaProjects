package com.demo.day17;

public class BankCustomer {
    private int accountNumber;
    private String name;
    private double currentBalance;

    // Zero-arg constructor
    public BankCustomer() {
        this.accountNumber = 0;
        this.name = "Unknown";
        this.currentBalance = 0.0;
    }

    // Three-arg constructor (Constructor Overloading)
    public BankCustomer(int accountNumber, String name, double currentBalance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.currentBalance = currentBalance;
    }

    // Setters
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCurrentBalance(double currentBalance) {
        this.currentBalance = currentBalance;
    }

    // Getters
    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    // Display method
    public void display() {
        System.out.println("Account Number   : " + accountNumber);
        System.out.println("Customer Name    : " + name);
        System.out.println("Current Balance  : ₹" + currentBalance);
    }
}
