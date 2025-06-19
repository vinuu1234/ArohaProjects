package com.demo.FunctionalInterfaces;

import java.util.Iterator;
import java.util.function.Predicate;

public class PredicateExample {

	public static void main(String[] args) {
		
		Predicate<Integer> p=i->i%2==0;
		System.out.println(p.test(10));
		System.out.println(p.test(15));
		System.out.println("===============================");

		Predicate<String> p1=s->s.length()>5;
		System.out.println(p1.test("VInodjd"));
		
		
		System.out.println("===============================");
		String[] name= {"Vinod","Akash","Venkatesh","Hanauman","Shivakumar","Ganesh"};
		
		Predicate<String> p2=n->n.length()>5;
		
		for (String string : name) {
			if(p2.test(string)) {
				System.out.println(string);
			}
		}
		
		
		//Predicate joining
		int[] x= {1,5,10,15,20,25,30,35};
		
		Predicate<Integer> p3=n->n%2==0;
		Predicate<Integer> p4=n->n>10;
		
		for (int j : x) {
			
			if(p3.and(p4).test(j)) {
				System.out.println(j);
			}
			
		}

		
	}
}
