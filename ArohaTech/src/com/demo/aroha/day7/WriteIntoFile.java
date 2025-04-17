package com.demo.aroha.day7;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class WriteIntoFile {

	public static void main(String[] args) {
		
		Scanner sc=new Scanner(System.in);
		String filePath = "D:/ArohaFileReading/input.txt"; 
		System.out.println("Enter the content to be added in to file :");
		String content = sc.nextLine();

		try {
			FileWriter writer = new FileWriter(filePath, true); 
			writer.write(content);
			writer.close();
			System.out.println("Content added to file successfully.");
		} catch (IOException e) {
			System.out.println("Error writing to file: " + e.getMessage());
		}

	}

}
