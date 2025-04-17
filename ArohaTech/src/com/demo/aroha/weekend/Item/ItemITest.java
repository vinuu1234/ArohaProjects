package com.demo.aroha.weekend.Item;

import java.util.HashMap;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Scanner;

public class ItemITest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		HashMap<Integer, Items> hm = new HashMap<>();
		HashMap<Integer, ItemTransaction> it = new HashMap<>();

		System.out.println("============ Menu ===============");
		System.out.println("1. Add New Items");
		System.out.println("2. Do Transactions");
		System.out.println("3. View All Items");
		System.out.println("4. View All Transactions");
		System.out.println("5. View Transaction by ID");
		System.out.println("6. View Item by ID");
		System.out.println("7. EXIT");
		while (true) {

			System.out.println("Enter Your Choice :");
			int choice = sc.nextInt();
			sc.nextLine();

			switch (choice) {
			case 1: {

				System.out.println("Enter the ItemId:");
				int itemId = sc.nextInt();
				sc.nextLine();
				System.out.println("Enter the Item Name :");
				String itemName = sc.nextLine();
				System.out.println("Enter Price of the Item :");
				double itemPrice = sc.nextDouble();
				sc.nextLine();
				System.out.println("Enter the Quantity of Items :");
				int quantity = sc.nextInt();

				Items item = new Items(itemId, itemName, itemPrice, quantity);

				hm.put(item.getItemId(), item);
				System.out.println("Item added ");
				for (Entry<Integer, Items> entry : hm.entrySet()) {

					Items i = entry.getValue();
					System.out.println(i);

				}

				break;
			}
			case 2: {
				Random random = new Random();

				int transId = random.nextInt(9000000) + 1000000;
				System.out.println("Enter the Item Id :");
				int itemId = sc.nextInt();
				sc.nextLine();

				for (Entry<Integer, Items> entry : hm.entrySet()) {
					// int i1=entry.getValue().getItemId();

					System.out.println("Enter Qantity needed ");
					int quantityNeeded = sc.nextInt();
					sc.nextLine();

					if (hm.containsKey(itemId)) {
						double price = hm.get(itemId).getPrice();
						double totalAmout = quantityNeeded * price;
						int totalQuantity = hm.get(itemId).getQuantity();
						String iname = hm.get(itemId).getItemName();
						System.out.println("price of " + iname + " is " + price);

						if (quantityNeeded > totalQuantity) {
							System.out.println("Available Stocks :" + totalQuantity);

							System.out.println("No Stock found item has only " + totalQuantity + " Stocks");

							break;
						}

						totalQuantity = totalQuantity - quantityNeeded;

						Items item = new Items(itemId, iname, price, totalQuantity);

						hm.put(item.getItemId(), item);
						System.out.println("Total amout of " + quantityNeeded + " Items is " + totalAmout);

						System.out.println("=== Available Items After Transaction ===");

						for (Items i : hm.values()) {
							System.out.println(i);

						}

						ItemTransaction t1 = new ItemTransaction(transId, itemId, quantityNeeded, totalAmout);

						it.put(t1.getTransactionId(), t1);

						break;

					} else {
						System.out.println("Item Not Found !!!");
					}

				}
				break;

			}
			case 3: {
				System.out.println("=== Available Items in the Store ===");

				for (Items i : hm.values()) {
					System.out.println(i);

				}
				break;
			}
			case 4: {
				System.out.println("==== Transactions happened ====");

				for (ItemTransaction i : it.values()) {
					System.out.println(i);

				}
				break;

			}
			case 5: {
				System.out.println("Enter the TrasId :");
				int transId = sc.nextInt();

				if (it.containsKey(transId)) {
					System.out.println(it.get(transId));
					break;
				}
				System.out.println("Transaction Not Found !!!");
				break;

			}
			case 6: {
				System.out.println("Enter the Item Id :");
				int itemId = sc.nextInt();

				if (hm.containsKey(itemId)) {
					System.out.println(hm.get(itemId));
					break;
				}
				System.out.println("Item Not Found !!!");
				break;

			}
			case 7: {
				System.out.println("Exitting !!!");
				return;
				// break;
			}
			default:
				System.out.println("Please Enter correct Choice ");
			}
		}
	}

}
