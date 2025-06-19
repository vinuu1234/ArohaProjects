package com.methodchaining;

public class ATMCard {
    private String cardNumber;
    private boolean isActive;
    private Account account;

    public ATMCard(String cardNumber, Account account) {
        this.cardNumber = cardNumber;
        this.isActive = true;
        this.account = account;
    }

	public boolean isActive() {
        return isActive;
    }

    public Account getAccount() {
        return account;
    }
}


