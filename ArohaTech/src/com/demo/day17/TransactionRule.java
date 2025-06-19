package com.demo.day17;

public class TransactionRule {

    public boolean validateTransaction(String transactionType, double amount) {
        if (transactionType.equalsIgnoreCase("D")) {
            return amount >= 100 && amount <= 500000;
        } else if (transactionType.equalsIgnoreCase("W")) {
            return amount >= 50 && amount <= 200000;
        }
        return false;
    }
}
