package com.methodchaining;


import java.util.Scanner;

public class ATMMenu {
    private static Scanner sc = new Scanner(System.in);
    
    public static int displayOptions() {
        System.out.println("\nATM Menu:");
        System.out.println("1. Withdraw Money");
        System.out.println("2. Check Balance");
        System.out.println("3. Deposit Money");
        System.out.println("4. Exit");
        System.out.print("Please enter your choice (1-4): ");
        
        return sc.nextInt();
    }

    public static double getWithdrawalAmount() {
        System.out.print("Enter amount to withdraw: ");
        return sc.nextDouble();
    }

    public static double getDepositAmount() {
        System.out.print("Enter amount to deposit: ");
        return sc.nextDouble();
    }

    public static void displayBalance(double balance) {
        System.out.println("Current Balance: " + balance);
    }
}