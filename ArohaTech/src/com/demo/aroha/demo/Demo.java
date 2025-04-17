package com.demo.aroha.demo;

public class Demo {
	public static void main(String[] args) {
		String s1="Vinod";
		String s2=new String("Vinod");
		System.out.println(s1.hashCode());
		System.out.println(s2.hashCode());
		System.out.println(s1==s2);
		String s3="Vinod";
		System.out.println(s1==s3);
		String s4=s1;
		System.out.println(s4);
		s1=s1+"chanagoudanavar";
		System.out.println(s1);
		

	}
}
