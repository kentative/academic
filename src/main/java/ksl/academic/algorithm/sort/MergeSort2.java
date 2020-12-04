package ksl.academic.algorithm.sort;

import java.util.Arrays;
import java.util.List;

import ksl.academic.algorithm.Utility;

public class MergeSort2 implements SortingAlgorithm {


    public static void main(String[] args) {
        // Initializing array
        int[] data = {9, 4, 8, 3, 1, 2, 5};

        MergeSort2 alg = new MergeSort2();
        alg.sort(data, data.length);

        Utility.print(data);

    }

    /**
     * @param data - unsorted data
     * @param n    - size limit
     */
    public void sort(int[] data, int n) {
        if (data.length <= 1) return;
        sort(data, 0, n, Arrays.copyOf(data, n));
    }

    /**
     * @param data   - the unsorted array
     * @param left   - the starting index
     * @param right  - the ending index
     * @param buffer - the temporary buffer array
     * @return
     */
    private void sort(int[] data, int left, int right, int[] buffer) {

        System.out.println("Sorting: " + Utility.print(data, left, right - 1));
        if (right - left <= 1) return;

        int mid = (left + right) / 2;
        sort(buffer, left, mid, data);
        sort(buffer, mid, right, data);

        merge(buffer, left, mid, right, data);
    }


    /**
     * @param buffer - source data
     *               left is source[left to mid-1]
     *               right is source[mid to right -1]
     * @param left
     * @param mid
     * @param right
     * @param sorted
     */
    private void merge(int[] buffer, int left, int mid, int right, int[] sorted) {

        System.out.print("Merging " + Utility.print(sorted, left, mid - 1) + " " + Utility.print(sorted, mid, right - 1));

        int iLeft = left; // first index of left portion starts from left
        int iRight = mid;  // first index of right portion starts from mid
        for (int i = left; i < right; i++) {

            if (iLeft < mid && (iRight >= right || buffer[iLeft] <= buffer[iRight])) {

                sorted[i] = buffer[iLeft++];
            } else {
                sorted[i] = buffer[iRight++];
            }
        }

        System.out.println(" = " + Utility.print(sorted, left, right - 1));
    }


    @Override
    public void sort(List<Integer> data, int n) {
        // TODO Auto-generated method stub

    }

}