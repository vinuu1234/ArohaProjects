package com.demo.Stringmethods;

public class StringBufferMethods {

	public static void main(String[] args) {
		// constructor and basic methods
		StringBuffer sb1 = new StringBuffer();
		System.out.println("New empty StringBuffer =" + sb1 );
		System.out.println("Capacity: " + sb1.capacity());
		System.out.println("Length: " + sb1.length());

		// append methods
		sb1.append("Hello");
		System.out.println("After append: " + sb1);
		sb1.append(" World!");
		System.out.println("After append: " + sb1);
		sb1.append(123);
		System.out.println("After append: " + sb1);
		sb1.append(true);
		System.out.println("After append: " + sb1);

		// insert methods
		sb1.insert(5, " Java");
		System.out.println("After insert: " + sb1);
		sb1.insert(0, "START: ");
		System.out.println("After insert: " + sb1);

		// delete methods
		sb1.delete(0, 7);
		System.out.println("After delete: " + sb1);
		sb1.deleteCharAt(5);
		System.out.println("After deleteCharAt(5)" + sb1);

		// replace
		sb1.replace(6, 11, "Universe");
		System.out.println("After replace" + sb1);

		// reverse
		sb1.reverse();
		System.out.println("After reverse()" + sb1);
		sb1.reverse(); // reverse back

		// capacity and ensureCapacity
		sb1.ensureCapacity(100);
		System.out.println("Capacity after ensureCapacity(100): " + sb1.capacity());

		// setLength
		sb1.setLength(10);
		System.out.println("After setLength(10): " + sb1 );
		System.out.println("New length: " + sb1.length());

		// charAt and setCharAt
		System.out.println("Character at position 3: " + sb1.charAt(3));
		sb1.setCharAt(3, 'X');
		System.out.println("After setCharAt(3, 'X'): " + sb1 );

		// substring
		System.out.println("Substring(2, 6): " + sb1.substring(2, 6));
		System.out.println("Substring(5): " + sb1.substring(5));

		// indexOf and lastIndexOf
		StringBuffer sb2 = new StringBuffer("apple orange apple banana");
		System.out.println("New StringBuffer " + sb2);
		System.out.println("indexOf: " + sb2.indexOf("apple"));
		System.out.println("indexOf: " + sb2.indexOf("apple", 5));
		System.out.println("lastIndexOf: " + sb2.lastIndexOf("apple"));

		String a[] = { "12", "-50", "78", "112", "-119" };

		int sum = 0;
		for (int i = 0; i < a.length; i++) {
			sum+=Integer.parseInt(a[i]);
		}
		System.out.println(sum);
	}
}
