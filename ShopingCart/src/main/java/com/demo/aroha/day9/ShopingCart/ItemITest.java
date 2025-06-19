package com.demo.aroha.day9.ShopingCart;


import java.util.*;

public class ItemITest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("============ Menu ===============");
        System.out.println("1. Add New Items");
        System.out.println("2. Do Transactions");
        System.out.println("3. View All Items");
        System.out.println("4. View All Transactions");
        System.out.println("5. EXIT");

        while (true) {
            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: {
                    System.out.println("Enter the ItemId:");
                    int itemId = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Enter the Item Name:");
                    String itemName = sc.nextLine();

                    System.out.println("Enter Price of the Item:");
                    double itemPrice = sc.nextDouble();

                    System.out.println("Enter the Quantity of Items:");
                    int quantity = sc.nextInt();

                    Items item = new Items(itemId, itemName, itemPrice, quantity);
                    ItemRepo.saveItem(item);
                    break;
                }

                case 2: {
                    System.out.println("Enter the Item Id:");
                    int itemId = sc.nextInt();

                    List<Items> items = ItemRepo.getAllItems();
                    Items matchedItem = null;

                    for (Items i : items) {
                        if (i.getItemId() == itemId) {
                            matchedItem = i;
                            break;
                        }
                    }

                    if (matchedItem == null) {
                        System.out.println("Item Not Found!");
                        break;
                    }

                    System.out.println("Enter Quantity Needed:");
                    int quantityNeeded = sc.nextInt();

                    if (quantityNeeded <= 0) {
                        System.out.println("Quantity must be greater than zero.");
                        break;
                    }

                    if (quantityNeeded > matchedItem.getQuantity()) {
                        System.out.println("Available Stocks: " + matchedItem.getQuantity());
                        System.out.println("Not enough stock.");
                        break;
                    }

                    int newQty = matchedItem.getQuantity() - quantityNeeded;
                    double totalBill = quantityNeeded * matchedItem.getPrice();
                    int transId = random.nextInt(9000000) + 1000000;

                    // Update item quantity
                    ItemRepo.updateItemQuantity(itemId, newQty);

                    // Save transaction
                    ItemTransaction tx = new ItemTransaction(transId, itemId, quantityNeeded, totalBill);
                    TransactionRepo.saveTransaction(tx);

                    System.out.println("Transaction Successful! Total Bill: " + totalBill);
                    break;
                }

                case 3: {
                    System.out.println("=== Available Items in the Store ===");
                    List<Items> items = ItemRepo.getAllItems();
                    for (Items i : items) {
                        System.out.println(i);
                    }
                    break;
                }

                case 4: {
                    System.out.println("==== Transactions Happened ====");
                    List<ItemTransaction> transactions = TransactionRepo.getAllTransactions();
                    for (ItemTransaction tx : transactions) {
                        System.out.println(tx);
                    }
                    break;
                }

                case 5: {
                    System.out.println("Exiting...");
                    return;
                }

                default:
                    System.out.println("Please Enter Correct Choice.");
            }
        }
    }
}
			/*
			 * case 6: { System.out.println("Enter the Item Id :"); int itemId =
			 * sc.nextInt();
			 * 
			 * if (hm.containsKey(itemId)) { System.out.println(hm.get(itemId)); break; }
			 * System.out.println("Item Not Found !!!"); break;
			 * 
			 * } case 7: { System.out.println("Exitting !!!"); return; // break; }
			 */	
