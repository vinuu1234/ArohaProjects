package com.practice;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CSVFileReader {
	// private Student student;

	public static void main(String[] args) throws IOException {
		String csvFile = "D://123/Student.csv";
		// String validFile="D://validfile.csv";
		// String errorFIle="D://invalidfile.csv";

		BufferedReader br = new BufferedReader(new FileReader(csvFile));
		br.readLine(); // skip header

		String line;
		// List<Student> list = new ArrayList<>();

		Set<String> uniq = new HashSet<>();
		List<String[]> slist = new ArrayList<>();
		List<String[]> elist = new ArrayList<>();
		while ((line = br.readLine()) != null) {
			// System.out.println(line);
			String[] data = line.split(",");
			String id = data[0];
			String firstName = data[1];
			String lastName = data[2];
			String gender = data[3];
			String dob = data[4];
			String address = data[5];
			String mobile = data[6];

			// Student student = new Student(id, firstName, lastName, gender, dob, address,
			// mobile);
			// list.add(student);
			boolean error = false;

			if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || gender.isEmpty() || dob.isEmpty()
					|| address.isEmpty() || mobile.isEmpty()) {

				elist.add(data);
				error = true;
			}
			String uniqueKey = firstName + "|" + lastName + "|" + gender + "|" + dob;
			if (uniq.contains(uniqueKey)) {
				elist.add(data);
				error = true;
			} else {
				uniq.add(uniqueKey);
			}

			Pattern p = Pattern.compile("[^a-zA-Z0-9\\s]");
			Matcher m = p.matcher(address);
			String cleanAddress = address;
			if (m.find()) {
				cleanAddress = p.matcher(address).replaceAll("");
				data[5] = cleanAddress;
				// System.out.println(cleanAddress);
			}

			if (!Pattern.matches("^[789]\\d{9}$", mobile)) {
				error = true;
			}

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
			try {
				LocalDate birthDate = LocalDate.parse(dob, formatter);
				LocalDate currentDate = LocalDate.now();

				// Check if date is in future
				if (birthDate.isAfter(currentDate)) {
					error = true;
				}

				// Check if age is greater than 100
				int age = Period.between(birthDate, currentDate).getYears();
				if (age > 100) {
					error = true;
				}
			} catch (DateTimeParseException e) {
				error = true;
				System.out.println(e);
			}

			if (error == false) {
				slist.add(data);
			}

		}
		try (BufferedWriter validWriter = new BufferedWriter(new FileWriter("D://ArohaFileHandling/validfile.csv"))) {
			//validWriter.writeAll(slist);
			validWriter.flush();
			System.out.println("Reading file");
		}

		// Write failed records with error messages
		try (BufferedWriter failedWriter = new BufferedWriter(new FileWriter("D://ArohaFileHandling/invalidfile.csv"))) {
			//failedWriter.writeAll(elist);
			failedWriter.flush();
			System.out.println("Invalidfile");
		}

	}

}
