package ksl.academic.algorithm.sort;

import java.util.List;

/**
 * 
 * Category
 * Time
 * Space
 * Stability
 * Adaptability
 * 
 * 
 * Size 10000000
 * MergeSort array: 1499
 * ----------------------------------------
 * Size 10000000
 * QuickSort array: 2359
 * 
 * 
 * @since June 30 2018
 */
public interface SortingAlgorithm {

	/**
	 * 
	 * @param data
	 * @param n
	 */
	void sort(int data[], int n);
	
	
	/**
	 * 
	 * @param data
	 * @param n
	 */
	void sort(List<Integer> data, int n);
}
