package com.demo.aroha.day7;

import java.io.*;
import java.nio.file.*;

public class CopyFile {
	public static void main(String[] args) {

		String sourceFileName = "D:/ArohaFileReading/Sample.txt";
		String destinationFileName = "D:/ArohaFileReading/demo.txt";

		int charCount = 0;
		int wordCount = 0;
		int lineCount = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(sourceFileName));
				BufferedWriter writer = new BufferedWriter(new FileWriter(destinationFileName));) {
			String line;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				charCount += line.length();
				String[] words = line.trim().split("\\s+");
				if (!line.trim().isEmpty()) {
					lineCount++;

					wordCount += words.length;
				}
				writer.write(line);
				writer.newLine();
			}

			System.out.println("1 file copied");
			System.out.println("This file has '" + charCount + "' characters, '" + wordCount + "' words, '" + lineCount
					+ "' lines.");
		} catch (FileNotFoundException e) {
			System.out.println("Source file not found: " + sourceFileName);
		} catch (IOException e) {
			System.out.println("An error occurred during file copying: " + e.getMessage());
		}
	}
}
