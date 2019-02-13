package ksl.academic.algorithm.amzn.sort;

import java.util.ArrayDeque;
import java.util.Deque;

import ksl.academic.algorithm.sort.SortUtil;

public class Quicksort {

	public static void main(String[] args) {
		
		int size = 1 << 18;
		int[] dataLarge = new int[size];
		for (int i = size-1, j = 0; i >=0; i--, j++) {
			dataLarge[j] = i;
		}
		
		int data[] = { 0, 9, 4, 8, 3, 1, 2, 5};//, 6, 21, 12, 11, 0 };
		long start = System.currentTimeMillis();
		quicksort(dataLarge, 0, dataLarge.length-1);
		System.out.println(System.currentTimeMillis() - start);
//		System.out.println(Arrays.toString(data));
		
	}
	
	/**
	 * Using median of 3 (reduces run time of worst case from 8 seconds to 47 ms)
	 * @param data
	 * @param lo
	 * @param hi
	 * @return
	 */
	private static int partition(int[] data, int lo, int hi) {
		
//		int mid = (lo + hi)/2;
//		System.out.println(Arrays.toString(new int[] {data[lo], data[mid], data[hi]}));
//		if (data[mid] < data[lo]) SortUtil.swap(data, mid, lo);
//		if (data[mid] > data[hi]) SortUtil.swap(data, mid, hi);
//		if (data[mid] > data[lo]) SortUtil.swap(data, mid, lo);
		
		int pivot = data[lo];
//		System.out.println("pivot " + pivot + " " + Arrays.toString(data));
		int i = lo-1, j=hi+1;
		
		while (true) {
			while (data[++i] < pivot);
			while (data[--j] > pivot);
			if (i >= j) {
//				System.out.println("return " + j);
				return j;
			}
			SortUtil.swap(data, i, j);
//			System.out.println("swap (" + i + "," + j + ") " + Arrays.toString(data));
		}
		
	}
	
	
	private static void quicksortItr(int[] data, int low, int high) {
		
		Deque<int[]> stack = new ArrayDeque<>(data.length);
		
		stack.push(new int[] {low, high});
		while (!stack.isEmpty()) {
		
			int[] range = stack.pop();
			int lo = range[0], hi = range[1];
			if (lo >= hi) continue;
			
			int p = partition(data, lo, hi);
			stack.push(new int[] {lo, p});
			stack.push(new int[] {p+1, hi});
		}
	}
	
	public static void quicksort(int[] data, int lo, int hi) {
		
		if (lo >= hi) return;
		
		int pivot = partition(data, lo, hi);
		quicksort(data, lo, pivot);
		quicksort(data, pivot+1, hi);
		
	}
}
