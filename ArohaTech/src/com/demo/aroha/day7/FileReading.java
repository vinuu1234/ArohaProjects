package com.demo.aroha.day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

public class FileReading {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the word to be searched :");
		String target = sc.next();
		try {
			File file = new File("D:/ArohaFileReading/Sample.txt");

			sc = new Scanner(file);

			// Reading and printing file content line by line
			int wordCount = 0;
			int cnt = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.contains(target)) {

					wordCount++;

					System.out.println(line);

				}
			}
			System.out.println("===========================");
			System.out.println(target + " is Present " + wordCount + " times");

			sc.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found: " + e.getMessage());
		}

	}

}
