package ksl.academic.algorithm.set1;

import java.util.Arrays;
import java.util.HashSet;

public class ArraySum {
	
	public static void main (String[] args) {

		int[] data = {1, 2, 3, 4};
		int sum = 8;
		
		System.out.println(Arrays.binarySearch(data, 3));
		
		findPair(data, sum);
	}
	
	public static void findElement(int[] data, int sum) {

		if (data.length < 2) { 
			System.out.println("insufficient data"); 
			return;
		}
		
		if (data[0] > sum) {
			System.out.println("not found");
			return;
		}
		
		for (int i = 0; i < data.length; i++) {
			for (int j = i; j < data.length - 1; j++) {
				if (data[i] + data[j] == sum) {
					System.out.println(data[i] + " " + data[j]);
					return;
				}
			}
		}
		System.out.println("not found");
	}
	
	public static void findElement2(int[] data, int sum) {

		if (data.length < 2) { 
			System.out.println("insufficient data"); 
			return;
		}
		
		if (data[0] > sum) {
			System.out.println("not found");
			return;
		}
		
		for (int i = 0; i < data.length; i++) {
			int x = sum - data[i];
			if (Arrays.binarySearch(data, x) > 0) {
				System.out.println(data[i] + " " + x);
				return;
			}
		}
		System.out.println("not found");
	}

	public static void findElement3(int[] data, int sum) {

		if (data.length < 2) { 
			System.out.println("insufficient data"); 
			return;
		}
		
		if (data[0] > sum) {
			System.out.println("not found");
			return;
		}
		
		int start = 0;
		int end = data.length-1;
		
		// 10, mid = 5, 11:mid = 5
		while (start < end) {
			if (data[start] + data[end] < sum) {
				start++;
			} else if (data[start] + data[end] > sum) {
				end--;
			} else {
				System.out.println(data[start] + " " + data[end]);
				return;
			}
		}
		System.out.println("not found");
	}



	public static void findPair(int[] data, int sum) {
	
		if (data.length < 2) {
			System.out.println("insufficient data");
		}
		
		
		HashSet<Integer> complements = new HashSet<>();
		for (int i = 0; i < data.length; i++) {
			int c = sum - data[i];
			if (complements.contains(c)) {
				System.out.println(data[i] + " " + c);
				return;
			} else {
				complements.add(c);
			}
		}
		System.out.println("not found");
	}
}
