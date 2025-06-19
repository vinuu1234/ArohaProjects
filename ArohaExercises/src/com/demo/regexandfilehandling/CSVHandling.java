package com.demo.regexandfilehandling;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVHandling {
	public static void main(String[] args) {
		String originalFile = "D://123/Student.csv";
		String validFile = "D://123/valid.csv";
		String invalidFile = "D://123/invalid.csv";

		try (BufferedReader br = new BufferedReader(new FileReader(originalFile));
				BufferedWriter validWriter = new BufferedWriter(new FileWriter(validFile));
				BufferedWriter invalidWriter = new BufferedWriter(new FileWriter(invalidFile))) {

			Set<String> uniqueSet = new LinkedHashSet<>();
			br.readLine();
			String line;

			while ((line = br.readLine()) != null) {
				String[] data = line.split(",");

				if (data.length < 7) {
					invalidWriter.write(line + "Insufficient fields)");
					invalidWriter.newLine();
					continue;
				}

				String id = data[0].trim();
				String firstName = data[1].trim();
				String lastName = data[2].trim();
				String gender = data[3].trim();
				String dob = data[4].trim();
				String address = data[5].trim();
				String mobile = data[6].trim();

				if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || gender.isEmpty() || dob.isEmpty()
						|| address.isEmpty() || mobile.isEmpty()) {
					invalidWriter.write(line + " Empty fields)");
					invalidWriter.newLine();
					continue;
				}

				// Checking for duplicates
				String uniqueKey = firstName + "|" + lastName + "|" + gender + "|" + dob;
				if (uniqueSet.contains(uniqueKey)) {
					invalidWriter.write(line + " Duplicate record)");
					invalidWriter.newLine();
					continue;
				} else {
					uniqueSet.add(uniqueKey);
				}

				Pattern p = Pattern.compile("[^a-zA-Z0-9\\s]");
				Matcher m = p.matcher(address);
				String cleanAddress = address;
				if (m.find()) {
					cleanAddress = p.matcher(address).replaceAll("");
					data[5] = cleanAddress;
					// System.out.println(cleanAddress);
				}
				// Clean address
				/*
				 * address = address.replaceAll("[^a-zA-Z0-9\\s]", ""); data[5] = address;
				 */
				// Validate mobile
				Pattern mobilePattern = Pattern.compile("^[789]\\d{9}$");
				if (!mobilePattern.matcher(mobile).matches()) {
					invalidWriter.write(line + " Invalid mobile)");
					invalidWriter.newLine();
					continue;
				}

				// Validate DOB
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
				try {
					LocalDate birthDate = LocalDate.parse(dob, formatter);
					if (birthDate.isAfter(LocalDate.now())) {
						invalidWriter.write(line + " Future date)");
						invalidWriter.newLine();
						continue;
					}
				} catch (Exception e) {
					invalidWriter.write(line + " Invalid date format)");
					invalidWriter.newLine();
					System.out.println(e);
					continue;
				}

				//  valid data
				validWriter.write(String.join(",", data));
				validWriter.newLine();
			}

			validWriter.flush();
			invalidWriter.flush();

			System.out.println("Processing completed successfully");
			System.out.println(uniqueSet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}