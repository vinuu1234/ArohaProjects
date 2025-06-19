package com.demo.aroha.day10;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ProductTest {

	public static void main(String[] args) {
		List<ProductTask> list = new LinkedList<>();
		list.add(new ProductTask(101, "bag", 2000));
		list.add(new ProductTask(102, "mobile", 4000));
		list.add(new ProductTask(103, "laptop", 2000));
		list.add(new ProductTask(104, "apple", 5000));
		list.add(new ProductTask(105, "bottle", 300));
		list.add(new ProductTask(106, "pen", 100));
		list.add(new ProductTask(107, "shoe", 6000));
		list.add(new ProductTask(108, "soap", 80));
		list.add(new ProductTask(109, "powder", 200));
		list.add(new ProductTask(110, "biscuit", 200));
		list.add(new ProductTask(111, "car", 200000));
		list.add(new ProductTask(112, "rice", 2500));
		list.add(new ProductTask(113, "mango", 2000));
		list.add(new ProductTask(114, "bannana", 10000));
		list.add(new ProductTask(115, "book", 12000));
		list.add(new ProductTask(116, "bag", 20000));

		List<String> l = list.stream().filter(p -> p.getPrice() < 1000).map(p -> p.getName().toUpperCase())
				.collect(Collectors.toList());
		System.out.println(l.toString());

	}

}
