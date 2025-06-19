package com.demo.aroha;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameExtractor {
	public static void main(String[] args) {
		
		Pattern p=Pattern.compile("[a-zA-Z0-9][a-zA-Z0-9$_.]*[.]csv");
		File f=new File("D://123");
		String[] s= f.list();
		for (String string : s) {
			Matcher m=p.matcher(string);
			if (m.find()&&m.group().equals(string)) {
				System.out.println(m.group());
			}
		}
	}

}
