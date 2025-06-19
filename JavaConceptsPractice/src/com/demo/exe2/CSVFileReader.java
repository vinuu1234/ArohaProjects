package com.demo.exe2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class CSVFileReader {
	public static void main(String[] args) throws IOException {
		String csvFile = "D://Aroha/Student.csv";
		String validFile = "D://Aroha/validfile.csv";
		String errorFile = "D://Aroha/invalidfile.csv";

		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		String header = br.readLine(); // save header for valid file

		String line;
		List<String[]> validRecords = new ArrayList<>();
		List<String[]> invalidRecords = new ArrayList<>();
		Set<String> uniqueKeys = new HashSet<>();

		while ((line = br.readLine()) != null) {
			String[] data = line.split(",");
			if (data.length < 7) {
				invalidRecords.add(new String[] { line, "Insufficient fields" });
				continue;
			}

			String id = data[0].trim();
			String firstName = data[1].trim();
			String lastName = data[2].trim();
			String gender = data[3].trim();
			String dob = data[4].trim();
			String address = data[5].trim();
			String mobile = data[6].trim();

			StringBuilder errorMessage = new StringBuilder();
			boolean isValid = true;

			// 1. Check for empty fields
			if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || gender.isEmpty() || dob.isEmpty()
					|| address.isEmpty() || mobile.isEmpty()) {
				errorMessage.append("Empty fields; ");
				isValid = false;
			}

			// 2. Clean address (remove special characters)
			String cleanAddress = address.replaceAll("[^a-zA-Z0-9\\s]", "");
			data[5] = cleanAddress;

			// 3. Validate mobile number
			if (!Pattern.matches("^[789]\\d{9}$", mobile)) {
				errorMessage.append("Invalid mobile; ");
				isValid = false;
			}

			// 4. Validate date of birth
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy")
						.withResolverStyle(ResolverStyle.STRICT); // Strict parsing prevents invalid dates

				// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
				LocalDate birthDate = LocalDate.parse(dob, formatter);
				LocalDate currentDate = LocalDate.now();

				if (birthDate.isAfter(currentDate)) {
					errorMessage.append("Future date; ");
					isValid = false;
				}
				

			} catch (DateTimeParseException e) {
				errorMessage.append("Invalid date format; ");
				isValid = false;
			}

			// 5. Check for duplicate records
			String uniqueKey = firstName + "|" + lastName + "|" + gender + "|" + dob;
			if (uniqueKeys.contains(uniqueKey)) {
				errorMessage.append("Duplicate record; ");
				isValid = false;
			} else {
				uniqueKeys.add(uniqueKey);
			}

			if (isValid) {
				validRecords.add(data);
			} else {
				invalidRecords.add(new String[] { line, errorMessage.toString() });
			}
		}

		// Write valid records
		try (BufferedWriter validWriter = new BufferedWriter(new FileWriter(validFile))) {
			validWriter.write(header); // write header first
			validWriter.newLine();
			for (String[] record : validRecords) {
				validWriter.write(String.join(",", record));
				validWriter.newLine();
			}
			System.out.println("Valid records written to: " + validFile);
		}

		// Write invalid records with error messages
		try (BufferedWriter failedWriter = new BufferedWriter(new FileWriter(errorFile))) {
			failedWriter.write("Original Record,Error Message");
			failedWriter.newLine();
			for (String[] record : invalidRecords) {
				failedWriter.write(record[0] + "," + record[1]);
				failedWriter.newLine();
			}
			System.out.println("Invalid records written to: " + errorFile);
		}

		br.close();
	}
}