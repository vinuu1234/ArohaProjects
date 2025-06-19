package com.demo.aroha.day10.student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GroupStudent {

	public static void main(String[] args) {

		List<Student> students = Arrays.asList(new Student(1, "Alice", 90), new Student(2, "Bob", 70),
				new Student(3, "Charlie", 40), new Student(4, "David", 55), new Student(5, "Eva", 82),
				new Student(6, "Frank", 30));
		
	       
	    	Map<String, List<Student>> groupedstudent=students.stream().collect(Collectors.groupingBy(g->g.getMarks()>80?"Excellent":(g.getMarks()>=50?"Average":"Poor")));
	    	 // Display the result
	    	groupedstudent.forEach((category, list) -> {
	            System.out.println(category + " :");
	            list.forEach(System.out::println);
	            System.out.println();
	        });
		
	}

}
