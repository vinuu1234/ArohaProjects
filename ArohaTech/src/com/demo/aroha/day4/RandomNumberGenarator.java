package com.demo.aroha.day4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Random;

public class RandomNumberGenarator {

	public static void main(String[] args) {

		Random random = new Random();
		int arrSize = 10;
		int[] randomNumArr = new int[arrSize];
		for (int i = 0; i < arrSize; i++) {
			randomNumArr[i] = random.nextInt(900) + 100;// generating random number and storing it in int array.

		}
		System.out.println(Arrays.toString(randomNumArr));

		ArrayList<Integer> al = new ArrayList<>();// creating array list
		LinkedList<Integer> ll = new LinkedList<>();// creating linked list.

		int length = randomNumArr.length;
		int mid = length / 2;

		// Adding Elements to array list
		for (int i = 0; i < randomNumArr.length - mid; i++) {
			al.add(randomNumArr[i]);

		}

		// Adding Elements to Linked list
		for (int i = mid; i < randomNumArr.length; i++) {
			ll.add(randomNumArr[i]);

		}
		System.out.println(
				"==================Processing &adding all odd numbers  and finding sum and avg	==========================");

		// Processing &adding all odd numbers
		int total = 0;
		int oddCnt = 0;
		int avg = 0;

		for (int i = 0; i < al.size(); i++) {

			if (al.get(i) % 2 != 0) {
				// System.out.println("Odd Numbers in Array List :"+ al.get(i));
				total = total + al.get(i);
				oddCnt++;
			}

		}

		avg = total / oddCnt;

		System.out.println("count of odd numbers :" + oddCnt);

		System.out.println("total of odd numbers :" + total);
		System.out.println("Average of odd numbers :" + avg);

		System.out.println(
				"==================processing Linked list and add 3 digit even number and finding sum and avg	==========================");

		int evenCnt = 0;
		int highest = ll.get(0);
		int lowest = ll.get(0);

		for (int i = 0; i < ll.size(); i++) {

			if (ll.get(i) % 2 == 0) {
				total = total + ll.get(i);
				// System.out.println("Even Numbers in List : " + ll.get(i));

				if (ll.get(i) > highest) {
					highest = ll.get(i);
				} else if (ll.get(i) < lowest) {
					lowest = ll.get(i);
				}
				evenCnt++;

			}

		}

		avg = total / evenCnt;
		System.out.println("count of Even numbers :" + evenCnt);
		System.out.println("total of even numbers :" + total);
		System.out.println("Average of even numbers :" + avg);
		System.out.println("Highest Even number in List is :" + highest);
		System.out.println("Lowest Even number in List is :" + lowest);

	}

}
