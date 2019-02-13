package ksl.academic.algorithm.leet;

import ksl.academic.algorithm.Utility;
import ksl.academic.algorithm.sort.SortUtil;

public class QuickSelect {
	public static void main(String[] args) {

//		int size = 10;
//		int[] data = new int[size];
//		for (int i = 0; i < size; i++) {
//			data[i] = (size-i);
//		}
//		
//		int[] data = {12, 3, 2, 5, 3, 23, 35, 11};
		int[] data = {2, 3, 3, 1};
//		System.out.println(findKthMin(data, 200));
		System.out.println(quickSelect(data, 0, data.length, 2));
	}

	private static int quickSelect(int[] data, int lo, int hi, int k) {
		
		while (true) {
			if (lo == hi) return data[lo];
			int p = partition(data, 0, data.length, (lo+hi)/2);
			
			if      (k < p) hi = p;  //check range, this should be p
			else if (k > p) lo = p+1;				
			else return data[p];	
			System.out.println("lo " + lo + " hi " + hi);
		}
	}
	
	private static int partition(int[] data, int lo, int hi, int pivotIndex) {
		
		int i = lo;
		int pivot = data[pivotIndex];
		
		// move pivot to front
		SortUtil.swap(data, pivotIndex, lo);
		
		for (int j = lo+1; j < hi; j++) {
			if (data[j] < pivot) {
				SortUtil.swap(data, j, ++i);
				Utility.print(data);
			}
		}
		SortUtil.swap(data,  lo,  i);
		Utility.print(data);
		return i;
	}
	
	static int findKthMin(int[] data, int k) {

		int repeatCount = 0;
		int n = data.length, i = 0;
		boolean repeat = false;
		while (i < n) {
			// if current index
			if (i < k) {
				if (data[i] > data[k]) {
					SortUtil.swap(data, i, k);
				}
			} else if ( i > k) {
				if (data[i] < data[k]) {
					SortUtil.swap(data, i, k);
					repeat = true;
				}
			}

			if ((i == n-1) && repeat) {
				repeat = false;
				repeatCount++;
				i = 0;
			} else {
				i++;
			}
			
		}
		
		System.out.println("repeating " + repeatCount);
		return data[k];
	}
	
}
