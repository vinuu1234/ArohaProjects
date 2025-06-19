package com.demo.aroha.collection;

import java.util.Comparator;
import java.util.TreeSet;

class Employee {
    int id;
    String name;
    int age;

    public Employee(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + age;
    }
}

public class EmployeeTreeSetExample {
    public static void main(String[] args) {
        Comparator<Employee> ageDescComparator = (e1, e2) -> {
            return Integer.compare(e2.age, e1.age); // Descending
        };

        TreeSet<Employee> employees = new TreeSet<>(ageDescComparator);

        employees.add(new Employee(101, "Akash", 30));
        employees.add(new Employee(102, "Vinod", 45));
        employees.add(new Employee(103, "Kumar", 28));

        for (Employee emp : employees) {
            System.out.println(emp);
        }
    }
}
