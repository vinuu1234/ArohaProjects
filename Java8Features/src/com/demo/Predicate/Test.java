package com.demo.Predicate;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Test {
	public static void main(String[] args) {
		ArrayList<Employee> list=new ArrayList<>();
		list.add(new Employee("Vinod", 30000));
		list.add(new Employee("Ganesh", 80000));
		list.add(new Employee("Maruti", 60000));
		list.add(new Employee("Hanuman", 60000));
		list.add(new Employee("Ram", 90000));
		list.add(new Employee("Krishna", 80000));
		
		Predicate<Employee> p=e->e.getSalary()>50000;
		
		for (Employee employee : list) {
			if(p.test(employee)) {
				System.out.println(employee);
			}
		}

	}

}
