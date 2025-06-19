package com.methodchaining;


public class Account {
    private double balance;

    public Account(double initialBalance) {
        this.balance = initialBalance;
    }

    public boolean hasSufficientBalance(double amount) {
        return balance >= amount;
    }

    public void deductBalance(double amount) {
        if (hasSufficientBalance(amount)) {
            balance -= amount;
        }
    }

    public void addToBalance(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    public double getBalance() {
        return balance;
    }
}