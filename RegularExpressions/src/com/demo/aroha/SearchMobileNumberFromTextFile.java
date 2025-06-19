package com.demo.aroha;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchMobileNumberFromTextFile {
	public static void main(String[] args) throws IOException {

		String file = "D://Aroha/MobileNumberSearchData.txt";
		String outputFile = "D://Aroha/SearchedMobileNumbers.txt";
		Pattern p = Pattern.compile("[7-9][0-9]{9}");

		BufferedReader br = new BufferedReader(new FileReader(file));
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile));
		String line;
		while ((line = br.readLine()) != null) {
			Matcher m = p.matcher(line);

			while (m.find()) {
				bw.write(m.group());
				 bw.newLine();
				System.out.println(m.group());
			}
 		}
		bw.flush();

	}

}
