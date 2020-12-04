package ksl.academic.algorithm.sort;

import java.util.List;


/**
 * Comparison sort, an improved selection sort using a heap data structure instead
 * of a linear max function.
 * <p>
 * slower than heapsort but has better worst case O(n log n)
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
 * <li>Space        - in-place (n)
 * <li>Stability    - not stable
 * <li>Adaptability -
 * </ul>
 *
 * @author Kent
 * @since 07.01.2018
 */
public class HeapSort implements SortingAlgorithm {


    /**
     * @param data - unsorted data
     * @param n    - size limit
     */
    public void sort(int[] data, int n) {


    }


    @Override
    public void sort(List<Integer> data, int n) {

    }

}