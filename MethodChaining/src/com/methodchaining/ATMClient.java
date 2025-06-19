package com.methodchaining;

import java.util.Scanner;

public class ATMClient {
	public static void main(String[] args) {
		Account account = new Account(10000.0);

		ATMCard card = new ATMCard("1234-5678-9012-3456", account);

		System.out.println("Welcome to Aroha Bank ATM");
		System.out.println("Initial balance: " + account.getBalance());

		Scanner sc = new Scanner(System.in);
		boolean continueTransactions = true;

		while (continueTransactions) {
			String result = ATMTransaction
					.withCard(card)
					.validateCard()
					.displayMenu()
					.complete();

			System.out.println("\nTransaction Status: " + result);
			System.out.println("Updated balance: " + account.getBalance());

			System.out.print("\nPerform another transaction? (yes/no): ");
			String choice = sc.nextLine().toLowerCase();
			continueTransactions = choice.equals("yes");
		}

		System.out.println("\nThank you for using Aroha Bank ATM!");
		sc.close();
	}
}
