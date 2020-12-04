package ksl.academic.algorithm.sort;

import java.util.List;

import ksl.academic.algorithm.Utility;


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
public class QuickSort3 implements SortingAlgorithm {

    @Override
    public void sort(List<Integer> data, int n) {
    }


    /**
     * Given an array of 10 items (0-9):
     * n = 10
     * pivot = 4
     * partition 0-4
     * partition 5-10
     */
    @Override
    public void sort(int[] data, int n) {
        quicksort(data, 0, n);
    }


    private void quicksort(int[] data, int lo, int hi) {

        if (lo >= hi) return;

        int p = partition(data, lo, hi);
        quicksort(data, lo, p);
        quicksort(data, p + 1, hi);

    }

    private int partition(int[] data, int lo, int hi) {

        int i = lo;
        int pivot = data[i];
        for (int j = lo + 1; j < hi; j++) {
            if (data[j] < pivot) {
                SortUtil.swap(data, j, ++i);
            }
        }
        SortUtil.swap(data, lo, i);
        return i;
    }

    public static void main(String[] args) {
        // Initializing array
//		int data[] = { 9, 4, 8, 3, 1, 2, 5 };
        int[] data = {1, 2, 3, 4, 5, 8, 9};

        QuickSort3 alg = new QuickSort3();
        alg.sort(data, data.length);

        Utility.print(data);

    }

}
