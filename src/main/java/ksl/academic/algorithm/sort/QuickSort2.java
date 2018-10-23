package ksl.academic.algorithm.sort;

import java.util.List;


/**
 * Two or three times faster than merge sort and heap sort.
 * Divide and conquer algorithm
 * Divide large array into smaller sub-array
 * Recursively sort the sub arrays
 * 
 * <ol>Steps:
 * <li>Pick pivot: Pick an element from the array, the pivot
 * <li>Partition operation: reorder the array so that all elements with values less than the pivot is on one side
 * <li>Recursively apply pivot and partition
 * </ol> 
 * 
 * <ul>
 * <li>Category     - comparison sort, Efficient sort
 * <li>Time         - average: O(n log(n)) worst: O(n^2)
 * <li>Space        - n
 * <li>Stability    - Stable, unless optimized 
 * <li>Adaptability - 
 * </ul>
 * 
 * @author Kent
 * @since 07.01.2018
 */
public class QuickSort2 implements SortingAlgorithm {

	@Override
	public void sort(List<Integer> data, int n) {}

	
	/**
	 * Given an array of 10 items (0-9):
	 * n = 10
	 * pivot = 4
	 * 		partition 0-4
	 * 		partition 5-9
	 */
	@Override
	public void sort(int[] data, int n) {
		quicksort(data, 0, n-1);
	}
	
	
	/**
	 * 
	 * @param data - data array
	 * @param lo - inclusive
	 * @param hi - inclusive
	 */
	private void quicksort(int[] data, int lo, int hi) {
		if (lo < hi) {
			int p = partition(data, lo, hi);
			quicksort(data, lo, p-1);
			quicksort(data, p+1, hi);
		}
	}
	
	
	private int partition(int[] data, int lo, int hi) {
		int x = data[hi];
		int i = lo-1;
		for (int j = lo; j <= hi-1; j++) {
			if (data[j] <= x) {
				i++;
				SortUtil.swap(data, i, j);
			}
		}
		SortUtil.swap(data,  i+1,  hi);
		return i+1;
	}
}
