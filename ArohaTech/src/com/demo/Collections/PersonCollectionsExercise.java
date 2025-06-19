package com.demo.Collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

class Person {
    private String name;
    private int age;
    private String city;

    public Person(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getCity() {
        return city;
    }

    // Proper equals() and hashCode() implementations for collection operations
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name) &&
                Objects.equals(city, person.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, city);
    }

    @Override
    public String toString() {
        return name + " (Age: " + age + ") from " + city;
    }
}

public class PersonCollectionsExercise {
    public static void main(String[] args) {
        // Create first list of Person objects
        List<Person> list1 = new ArrayList<>();
        list1.add(new Person("Aarav", 25, "Mumbai"));
        list1.add(new Person("Diya", 30, "Delhi"));
        list1.add(new Person("Arjun", 35, "Bangalore"));
        list1.add(new Person("Priya", 40, "Hyderabad"));
        list1.add(new Person("Vihaan", 45, "Chennai"));

        // Create second list of Person objects
        List<Person> list2 = new ArrayList<>();
        list2.add(new Person("Diya", 30, "Delhi"));
        list2.add(new Person("Arjun", 35, "Bangalore"));
        list2.add(new Person("Ishaan", 50, "Kolkata"));
        list2.add(new Person("Ananya", 55, "Pune"));
        list2.add(new Person("Kabir", 60, "Ahmedabad"));

        List<Person> inList1NotList2 = new ArrayList<>();
        for (Person person : list1) {
            if (!list2.contains(person)) {
                inList1NotList2.add(person);
            }
        }
        System.out.println("People in list1 but not in list2 (using for-each):");
        inList1NotList2.forEach(System.out::println);

        // Approach 2: Using streams to find people in list1 but not in list2
        List<Person> inList1NotList2Stream = list1.stream()
                .filter(person -> !list2.contains(person))
                .collect(Collectors.toList());
        System.out.println("People in list1 but not in list2 (using streams):");
        inList1NotList2Stream.forEach(System.out::println);

        // Find people present in both lists
        List<Person> inBothLists = list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toList());
        System.out.println("People present in both lists:");
        inBothLists.forEach(System.out::println);
    }
}