package com.demo.aroha;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchMailIdFromTextFile {
	public static void main(String[] args) {
		String inputFile = "D://Aroha/EmailCheckInput.txt";
		String outputFile = "D://Aroha/SearchedEmailIds.txt";

		Pattern emailPattern = Pattern.compile("[a-zA-Z0-9][\\w.-]*@gmail\\.com", Pattern.CASE_INSENSITIVE);

		try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
				BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile))) {
			String line;
			while ((line = br.readLine()) != null) {
				Matcher matcher = emailPattern.matcher(line);
				while (matcher.find()) {
					String email = matcher.group();
					bw.write(email);
					bw.newLine();
					System.out.println("Found email: " + email);
				}
			}
			bw.flush();
			System.out.println("Email extraction completed");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
}