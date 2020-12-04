package ksl.academic.algorithm.sort;

import java.util.List;

import ksl.academic.algorithm.Utility;

/**
 * Iterates, consuming one input element each repetition, generating the sorted output immediately.
 * <ol>At each iteration:
 * <li>removes one element from input data
 * <li>insert data at the sorted index
 * <li>repeat until end of list
 * </ol>
 *
 * <ul>
 * <li>Category     - Simple Sort
 * <li>Time         - average: O(n^2)
 * <li>Space        - n
 * <li>Stability    - Stable
 * <li>Adaptability -
 * </ul>
 *
 * @author Kent
 * @since 07.01.2018
 */
public class InsertionSort implements SortingAlgorithm {


    /**
     * for each input data x
     * compare x with previous,
     * if smaller, shift array right
     * else set array value at current position to x
     *
     * @param data - unsorted data
     * @param n    - size limit
     */
    public void sort(int[] data, int n) {

        // for each input data
        // x = element to sort
        for (int i = 1; i < n; i++) {
            int j = i;
            int x = data[i];
            while ((j > 0) && (data[j - 1] > x)) {
                data[j] = data[j - 1];
                j--;
            }
            data[j] = x;
        }
    }

    /**
     * for each input data x
     * compare x with previous,
     * if smaller, shift array right
     * else set array value at current position to x
     *
     * @param data - unsorted data
     * @param n    - size limit
     */
    public void sort(List<Integer> data, int n) {

        for (int i = 1; i < n; i++) {
            Integer x = data.remove(i);
            int j = i;
            while (j > 0 && x < data.get(j - 1)) {
                j--;
            }
            data.add(j, x);
        }
    }

    public static void main(String[] args) {
        // Initializing array
        int[] data = {9, 4, 8, 3, 1, 2, 5};
//		int data[] = { 1, 2, 3, 4, 5, 8, 9 };

        InsertionSort alg = new InsertionSort();
        alg.sort(data, data.length);

        Utility.print(data);

    }

}