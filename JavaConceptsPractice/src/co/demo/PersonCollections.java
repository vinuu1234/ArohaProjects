package co.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.demo.collections.Person;

public class PersonCollections {
	public static void main(String[] args) {
		List<Person> list1 = List.of(new Person("Vinod", 26, "Bengalure"), new Person("Kumar", 30, "Pune"),
				new Person("Raju", 32, "Mumbai"), new Person("Ganesh", 29, "Delhi"),
				new Person("Hanuman", 36, "Bangalure"));

		List<Person> list2 = List.of(new Person("Akash", 26, "Bengalure"), new Person("Ramesh", 30, "Pune"),
				new Person("Raju", 32, "Mumbai"), new Person("Gagan", 29, "Delhi"),
				new Person("Hanuman", 36, "Bangalure"));

		List<Person> list3 = new ArrayList<>();
		for (Person person : list1) {
			if (!list2.contains(person)) {
				list3.add(person);
			}

		}
		System.out.println(list3);
		List<Person> list4 = list1.stream().filter(person -> !list2.contains(person)).collect(Collectors.toList());
		System.out.println(list4);
	}

}