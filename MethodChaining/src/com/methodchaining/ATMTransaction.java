package com.methodchaining;


public class ATMTransaction {
    private ATMCard card;
    private boolean cardValid;
    private boolean sufficientBalance;
    private double amount;
    private String transactionStatus;

    private ATMTransaction(ATMCard card) {
        this.card = card;
    }

    public static ATMTransaction withCard(ATMCard card) {
        return new ATMTransaction(card);
    }

    public ATMTransaction validateCard() {
        this.cardValid = card != null && card.isActive();
        if (!cardValid) {
            this.transactionStatus = "Invalid or inactive card";
        }
        return this;
    }

    public ATMTransaction displayMenu() {
        if (!cardValid) {
            this.transactionStatus = "Cannot display menu - invalid card";
            return this;
        }

        int choice = ATMMenu.displayOptions();
        switch (choice) {
            case 1: // Withdraw
                double withdrawAmount = ATMMenu.getWithdrawalAmount();
                return this.withdraw(withdrawAmount);
                
            case 2: // Check Balance
                ATMMenu.displayBalance(card.getAccount().getBalance());
                this.transactionStatus = "Balance checked";
                return this;
                
            case 3: // Deposit
                double depositAmount = ATMMenu.getDepositAmount();
                return this.deposit(depositAmount);
                
            case 4: // Exit
                this.transactionStatus = "Transaction cancelled by user";
                return this;
                
            default:
                this.transactionStatus = "Invalid menu option selected";
                return this;
        }
    }

    public ATMTransaction withdraw(double amount) {
        if (!cardValid) {
            this.transactionStatus = "Cannot process - invalid card";
            return this;
        }
        
        this.amount = amount;
        this.sufficientBalance = card.getAccount().hasSufficientBalance(amount);
        
        if (sufficientBalance) {
            card.getAccount().deductBalance(amount);
            this.transactionStatus = "Successfully withdrew " + amount;
        } else {
            this.transactionStatus = "Insufficient balance";
        }
        return this;
    }

    public ATMTransaction deposit(double amount) {
        if (!cardValid) {
            this.transactionStatus = "Cannot process - invalid card";
            return this;
        }
        
        if (amount > 0) {
            card.getAccount().addToBalance(amount);
            this.transactionStatus = "Successfully deposited " + amount;
        } else {
            this.transactionStatus = "Invalid deposit amount";
        }
        return this;
    }

    public String complete() {
        return transactionStatus;
    }
}