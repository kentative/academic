package ksl.academic.algorithm.amzn;

/**
* Practice for AMZN
* https://www.interviewcake.com/question/java/cake-thief
* 
* This class has 2 variances:
* 
* Unlimited items, whole number
* - for each capacity and each item starting from 0 
* 		if item weight is <= current capacity
* 			update the current capacity value with 
* 				max(no item, item) 
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
public class Knapsack {

	public static void main(String[] args) {
		CakeType[] cakes = new CakeType[] {
			new CakeType(7, 160),
			new CakeType(3, 90),
			new CakeType(2,15)
		};
		
		int capacity = 20;
		
		System.out.println(unlimitedItem(cakes, capacity));
		System.out.println(limitedItem(cakes, 10));
		
	}
	
	/**
	 * Unlimited cakes
	 * 
	 * @param cakes
	 * @param capacity
	 * @return
	 */
	private static int unlimitedItem(CakeType[] cakes, int capacity) {

		int[] max = new int[capacity+1];
		for (int c = 1; c <= capacity; c++) {
			for (CakeType cake : cakes) {
				if (cake.weight <= c) {
					max[c] = Math.max(
							max[c],                           // don't take item
							max[c-cake.weight] + cake.value); // take item
				}
			}
		}
		return max[capacity];
	}
	
	
	/**
	 * for each capacity value
	 * 		for each item 
	 * 			 if (item weight <= current capacity) 
	 * 				max = skip item, take item
	 * 			 else
	 * 				max = max of previous item 
	 * 
	 * @param cakes
	 * @param capacity
	 * @return
	 */
	public int maxValueLimitedItem(CakeType[] cakes, int capacity) {
		
		int itemCount = cakes.length;
		int[][] max = new int[itemCount+1][capacity+1];
		
		for (int c = 1; c <= capacity; c++) {
			for (int i = 1; i <= itemCount; i++) {
				CakeType cake = cakes[i-1];
				if (cake.weight <= c) {
					max[i][c] = Math.max(
							max[i-1][c], 
							max[i-1][c-cake.weight] + cake.value);
				} else {
					max[i][c] = max[i-1][c];
				}
			}
		}
		return max[itemCount][capacity];
	}
	
	
	private static int limitedItem(CakeType[] cakes, int capacity) {
		
		int itemCount = cakes.length;
		int[][] max = new int[itemCount+1][capacity+1];
		
		for (int c = 1; c <= capacity; c++) {
			for (int i = 1; i <= itemCount; i++) {
				CakeType cake = cakes[i-1];
				max[i][c] = (cake.weight <= c)
					 ? Math.max(max[i-1][c], max[i-1][c-cake.weight] + cake.value)
					 : max[i-1][c];
			}
		}
		
		return max[itemCount][capacity];
	}
	

	static class CakeType {
		int weight;
		int value;
		
		public CakeType(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}
	}
}
