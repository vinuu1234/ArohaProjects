package com.demo.aroha.CSVFileReading;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Mtcars{

    public static void main(String[] args) {
        String jdbcURL = "jdbc:mysql://localhost:3306/CSVReading"; 
        String username = "root";
        String password = "root";
        String csvFile = "D:/mtcars.csv";

        String sql = "INSERT INTO mtcars (car_name, mpg, cyl, disp, hp, drat, wt, qsec, vs, am, gear, carb) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection connection = DriverManager.getConnection(jdbcURL, username, password);
            BufferedReader reader = new BufferedReader(new FileReader(csvFile))
        ) {
            PreparedStatement stmt = connection.prepareStatement(sql);
            String line;
            reader.readLine(); // skip header

            while ((line = reader.readLine()) != null) {
            	System.out.println(line);
                String[] data = line.split(",", 13); // limit to 13 parts
                String carName = data[0].replace("\"", "").trim(); // clean car name quotes

                stmt.setString(1, carName);
                stmt.setFloat(2, Float.parseFloat(data[1]));
                stmt.setInt(3, Integer.parseInt(data[2]));
                stmt.setFloat(4, Float.parseFloat(data[3]));
                stmt.setInt(5, Integer.parseInt(data[4]));
                stmt.setFloat(6, Float.parseFloat(data[5]));
                stmt.setFloat(7, Float.parseFloat(data[6]));
                stmt.setFloat(8, Float.parseFloat(data[7]));
                stmt.setInt(9, Integer.parseInt(data[8]));
                stmt.setInt(10, Integer.parseInt(data[9]));
                stmt.setInt(11, Integer.parseInt(data[10]));
                stmt.setInt(12, Integer.parseInt(data[11]));

                stmt.executeUpdate();
            }

            System.out.println("Data successfully inserted into mtcars table.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
