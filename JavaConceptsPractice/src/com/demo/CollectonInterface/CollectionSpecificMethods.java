package com.demo.CollectonInterface;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CollectionSpecificMethods {

	public static void main(String[] args) {
		List<Student> list = new ArrayList<>();
		list.add(new Student(10, "Vinod", 88));
		list.add(new Student(20, "Shiva", 58));
		list.add(new Student(30, "Raju", 78));
		list.add(new Student(40, "AKsh", 78));
		list.add(new Student(50, "Ramu", 85));
		for (Student student : list) {
			System.out.println(student);
		}
		// System.out.println(list);
		List<Student> list2 = new ArrayList<>();
		list2.add(new Student(50, "Arun", 66));
		list2.add(new Student(60, "Hemanth", 76));
		list2.add(new Student(70, "Ganesh", 96));
		list.addAll(list2);
		System.out.println("============After Adding collection to other collection==================");

		for (Student student : list) {
			System.out.println(student);
		}
		// System.out.println("aftre adding list2 : " + list);

		list.remove(new Student(60, "Hemanth", 76));
		// System.out.println(list);
		System.out.println("========After removing Hemanth object============");
		for (Student student : list) {
			System.out.println(student);
		}
		list.removeAll(list2);
		System.out.println("===========After Removing list2===================");

		System.out.println("After removing : " + list);
		for (Student student : list) {
			System.out.println(student);
		}
		System.out.println("==========================");
		for (Student student : list) {
			if (list2.containsAll(list)) {
				System.out.println(student);
			}
		}
	}

}
