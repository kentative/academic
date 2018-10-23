package ksl.academic.algorithm.sim;

import java.util.Arrays;

/**
 * https://www.interviewcake.com/question/java/cake-thief
 * 
 * This class has 2 variances:
 * 
 * Unlimited items, whole number
 * - for each capacity and each item starting from 0 
 * 		if item weight is <= current capacity
 * 			update the current capacity value with 
 * 				max(no item, item) *
 * 
 * Limited items, whole number
 * - for each capacity and each item starting from 0 
 * 		if item weight is <= current capacity
 * 			update the current capacity value with 
 * 				max(no item, item) 
 * 		else 
 * 			update current = previous item
 *  		 			 
 */
public class DuffleBag {

	public static void main(String[] args) {

		CakeType[] cakes = { 
				new CakeType(7, 160), 
				new CakeType(3, 90), 
				new CakeType(2, 15)
		};
		
		System.out.println(unboundedDuffle(cakes, 20));
		System.out.println(boundedDuffle(cakes, 10));
		
	}

	private static int unboundedDuffle(CakeType[] cakes, int cap) {

		int cache[] = new int[cap + 1];
		
		for (int c = 1; c <= cap; c++) {
			for (CakeType cake : cakes) {
				if (cake.weight <= c) {
					cache[c] = Math.max(cache[c], cache[c - cake.weight] + cake.value);
				}
			}
		}
		return cache[cap];
	}
	
	private static int boundedDuffle(CakeType[] cakes, int capacity) {

		int itemCount = cakes.length;
		int cache[][] = new int[itemCount+1][capacity + 1];

		for (int c = 1; c <= capacity; c++) {
			for (int i = 1; i <= itemCount; i++) {
				
				int weight = cakes[i-1].weight, value = cakes[i-1].value;
				
				if (weight <= c) {
					cache[i][c] = Math.max(
							cache[i-1][c], 
							cache[i-1][c - weight] + value);
				} else {
					cache[i][c] = cache[i-1][c];
				}
			}
		}
		
		return cache[cakes.length][capacity];
	}

	public static class CakeType {

		final int weight;
		final int value;

		public CakeType(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		public String toString() {
			return weight + " " + value;
		}
	}

}
