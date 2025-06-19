package com.demo.aroha.day9.ShopingCart;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepo {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ShopingStore";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";

	public static void saveTransaction(ItemTransaction tx) {
		String sql = "INSERT INTO transaction (transactionId, itemId, quantityPurchased, billAmount) VALUES (?, ?, ?, ?)";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, tx.getTransactionId());
			ps.setInt(2, tx.getItemId());
			ps.setInt(3, tx.getQuantityOfpurchase());
			ps.setDouble(4, tx.getBillAmout());
			ps.executeUpdate();
			System.out.println("Transaction saved to DB.");
		} catch (SQLException e) {
			System.out.println("Error saving transaction: " + e.getMessage());
		}
	}

	public static List<ItemTransaction> getAllTransactions() {
		List<ItemTransaction> list = new ArrayList<>();
		String sql = "SELECT * FROM transaction";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				ItemTransaction tx = new ItemTransaction(
						rs.getInt("transactionId"),
						rs.getInt("itemId"),
						rs.getInt("quantityPurchased"),
						rs.getDouble("billAmount"));
				list.add(tx);
			}
		} catch (SQLException e) {
			System.out.println("Error fetching transactions: " + e.getMessage());
		}
		return list;
	}
}
