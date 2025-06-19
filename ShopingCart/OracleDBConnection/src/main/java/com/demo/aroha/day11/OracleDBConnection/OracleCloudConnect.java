package com.demo.aroha.day11.OracleDBConnection;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleCloudConnect {
    public static void main(String[] args) {
        // JDBC URL format for Oracle
        String jdbcUrl = "jdbc:oracle:thin:@//ec2-3-111-0-185.ap-south-1.compute.amazonaws.com:1521/orcl";
        
        // Oracle credentials
        String username = "JAVA_FSD_vinod";
        String password = "JAVA_FSD_vinod";

        Connection connection = null;

        try {
            // Load Oracle JDBC Driver (optional in newer versions, but good practice)
            Class.forName("oracle.jdbc.driver.OracleDriver");

            // Connect to the database
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            if (connection != null) {
                System.out.println("Successfully connected to Oracle database on AWS!");
                System.out.println("User id is " + username);
            } else {
                System.out.println("Failed to make connection!");
            }

        } catch (SQLException e) {
            System.out.println("SQLException occurred!");
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("Oracle JDBC Driver not found!");
            e.printStackTrace();
        } finally {
            // Always close connection
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}

/*
 * import java.sql.*;
 * 
 * public class OracleDBConnection { public static void main(String[] args) { //
 * Change these values according to your DB String url =
 * "ec2-3-111-0-185.ap-south-1.compute.amazonaws.com"; // orcl is SID String
 * username = "JAVA_FSD_vinod"; String password = "JAVA_FSD_vinod";
 * 
 * try { // Load Oracle JDBC Driver
 * Class.forName("oracle.jdbc.driver.OracleDriver");
 * 
 * // Connect to DB Connection conn = DriverManager.getConnection(url, username,
 * password);
 * 
 * System.out.println("Connected successfully!");
 * 
 * // Sample query Statement stmt = conn.createStatement(); ResultSet rs =
 * stmt.executeQuery("SELECT * FROM your_table");
 * 
 * while (rs.next()) { System.out.println("Column 1: " + rs.getString(1)); }
 * 
 * // Close connections rs.close(); stmt.close(); conn.close(); } catch
 * (Exception e) { e.printStackTrace(); } } }
 */