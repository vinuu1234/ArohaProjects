package com.demo.aroha.day9.ShopingCart;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ItemRepo {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/ShopingStore";
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";

	// Insert new item
	public static void saveItem(Items item) {
		String sql = "INSERT INTO item (itemId, itemName, price, quantity) VALUES (?, ?, ?, ?)";
		

		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			 PreparedStatement ps = conn.prepareStatement(sql);) {
			ps.setInt(1, item.getItemId());
			ps.setString(2, item.getItemName());
			ps.setDouble(3, item.getPrice());
			ps.setInt(4, item.getQuantity());
			ps.executeUpdate();
			System.out.println("Item saved to DB.");
		} catch (SQLException e) {
			System.out.println("Error saving item: " + e.getMessage());
		}
	}

	// Update quantity
	public static void updateItemQuantity(int itemId, int newQuantity) {
		String sql = "UPDATE item SET quantity = ? WHERE itemId = ?";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			 PreparedStatement ps = conn.prepareStatement(sql)) {
			ps.setInt(1, newQuantity);
			ps.setInt(2, itemId);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error updating item quantity: " + e.getMessage());
		}
	}

	// Get all items
	public static List<Items> getAllItems() {
		List<Items> list = new ArrayList<>();
		String sql = "SELECT * FROM item";
		try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			 Statement stmt = conn.createStatement();
			 ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				Items item = new Items(
						rs.getInt("itemId"),
						rs.getString("itemName"),
						rs.getDouble("price"),
						rs.getInt("quantity"));
				list.add(item);
			}
		} catch (SQLException e) {
			System.out.println("Error fetching items: " + e.getMessage());
		}
		return list;
	}
}

