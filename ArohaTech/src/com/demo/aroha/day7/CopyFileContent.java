package com.demo.aroha.day7;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class CopyFileContent {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String source = "D:/ArohaFileReading/Sample.txt";
		String destination = "D:/ArohaFileReading/demo.txt";
		try {
			FileReader reader = new FileReader(source);
			FileWriter writer = new FileWriter(destination);
			//sc = new Scanner(source);
			int ch;
			int cnt=0;
			while ((ch=reader.read())!=-1) {
				cnt++;
				writer.write(ch); // Copy each character
			}
			System.out.println(cnt);

			sc.close();
			writer.close();

			System.out.println("File copied successfully.");
		} catch (IOException e) {
			System.out.println("An error occurred: " + e.getMessage());
		}
	}
}
