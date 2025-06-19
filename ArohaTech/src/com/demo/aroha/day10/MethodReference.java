package com.demo.aroha.day10;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.List;

public class MethodReference {

	public static void main(String[] args) {
		List<Employee> employees = new ArrayList();
		employees.add(new Employee(101, "Alice", 45000));
		employees.add(new Employee(102, "Bob", 30000));
		employees.add(new Employee(103, "Charlie", 50000));

		// Sort using method reference
		employees.sort(Comparator.comparing(Employee::getSalary));

		// Print sorted list
		System.out.println("Employees sorted by salary (ascending):");
		employees.forEach(System.out::println);
	}

}
