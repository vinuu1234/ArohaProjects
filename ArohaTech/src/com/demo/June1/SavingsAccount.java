package com.demo.June1;


@AccType(type = "Savings", minBalance = 1000.0, bankName="SBI Jayanagar")
public class SavingsAccount {
    private String accNumber;
    private double balance;

    public SavingsAccount(String accNumber, double balance) {
        this.accNumber = accNumber;
        this.balance = balance;
    }

    public void printDetails() {
        System.out.println("Account No: " + accNumber + ", Balance: " + balance);
    }

    public static void main(String[] args) {
        SavingsAccount acc = new SavingsAccount("78901", 500);
        acc.printDetails();

        AccType annotation = acc.getClass().getAnnotation(AccType.class);
        System.out.println("----- bank name is " + annotation.bankName()+ " ----");
        System.out.println("Account Type: " + annotation.type());
        System.out.println("Minimum Balance: Rs. " + annotation.minBalance());

        if (acc.balance < annotation.minBalance()) {
            System.out.println("WATCH :::  Warning: Balance below required minimum!");
        } else {
            System.out.println("NOTE::: Balance meets the minimum requirement.");
        }
    }
}

