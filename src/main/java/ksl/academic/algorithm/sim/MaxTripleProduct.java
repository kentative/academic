package ksl.academic.algorithm.sim;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

import ksl.academic.algorithm.Utility;

public class MaxTripleProduct {
	public static void main(String[] args) {

		int[] data = { -1, -2, -3, 4, 5};
		int[] data2 = {-9, -12, -3, -8, 2, 6};
		System.out.println(tripleProduct(data));
		
		System.out.println(maximumProduct(data));
		System.out.println(maximumProduct2(data));
	}
	
	// O(n) - single scan 
	public static int maximumProduct(int[] nums) {
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n: nums) {
            if (n <= min1) {
                min2 = min1;
                min1 = n;
            } else if (n <= min2) {     // n lies between min1 and min2
                min2 = n;
            }
            if (n >= max1) {            // n is greater than max1, max2 and max3
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n >= max2) {     // n lies betweeen max1 and max2
                max3 = max2;
                max2 = n;
            } else if (n >= max3) {     // n lies betwen max2 and max3
                max3 = n;
            }
        }
        
        // This is the answer...
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }
	
	// Leetcode #628
	public static int maximumProduct2(int[] nums) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>(3);
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(4, Collections.reverseOrder());
        
        for(int i=0; i<nums.length; i++) {
            int num = nums[i];
            
            if(num < 0) {
                maxHeap.add(num);
                if(maxHeap.size() > 3) maxHeap.poll();
            } else {
                minHeap.add(num);
                if(minHeap.size() > 3) minHeap.poll();
            }
        }
        
        int[] sorted = new int[6];
        int i = 2;
        int j = 3;
        while(!maxHeap.isEmpty()) {
            sorted[i--] = maxHeap.poll();
        }
        while(!minHeap.isEmpty()) {
            sorted[j++] = minHeap.poll();
        }
        
        int max = Math.max(
        		(sorted[i+1] * sorted[i+2] * sorted[j-1]), 
        		(sorted[j-1] * sorted[j-2] * sorted[j-3]));
        return max;
    }

	
	static long tripleProduct(int[] data) {
		int[] maxList = new int[3];

		for (int i = 0; i < 3; i++)
			maxList[i] = data[i];

		long max = product(maxList);
		for (int i = 3; i < data.length; i++) {
			
			System.out.print("max List ");
			Utility.print(maxList);
			int[] list = replaceSmallest(maxList, data[i]);
			
			System.out.print("Replaced ");
			Utility.print(list);
			long product = product(list);
			if (product > max) {
				max = product;
			}
			maxList = list;
		}

		return max;

	}
	
	private static int[] replaceSmallest(int[] triple, int x) {
		int[] result = Arrays.copyOf(triple, 3); 
		int a = Math.abs(triple[0]);
		int b = Math.abs(triple[1]);
		int c = Math.abs(triple[2]);
		if (a <= b && a <= c) result[0] = x;
		else if (b <= c)      result[1] = x;
		else                  result[2] = x;
		
		return result;
	}

	private static long product(int[] triple) {
		return triple[0] * triple[1] * triple[2];
	}
}
