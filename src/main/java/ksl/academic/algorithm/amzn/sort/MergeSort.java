package ksl.academic.algorithm.amzn.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        // Initializing array
        int[] data = {9, 4, 8, 3, 1, 2, 5};

        System.out.print("Initial Array: ");
        System.out.println(Arrays.toString(data));


        mergesort(data, 0, data.length - 1);

        System.out.print("Sorted Array: ");
        System.out.println(Arrays.toString(data));
    }

    // Sorting in non decreasing order
    private static void mergesort(int[] data, int lo, int hi) {

        if (lo >= hi) return;

        int mid = (lo + hi) / 2;

        mergesort(data, lo, mid);      // sort left
        mergesort(data, mid + 1, hi);  // sort right

        merge(data, lo, mid, hi);      // merge
    }

    /**
     * @param data
     * @param lo
     * @param mid
     * @param hi
     */
    private static void merge(int[] data, int lo, int mid, int hi) {

        int[] temp = new int[data.length];
        int l = lo, r = hi, m = mid + 1;

        int i = lo;
        while (l <= mid && m <= r) {
            if (data[l] <= data[m]) {
                temp[i++] = data[l++];
            } else {
                temp[i++] = data[m++];
            }
        }

        while (l <= mid)
            temp[i++] = data[l++];

        while (m <= r) {
            temp[i++] = data[m++];
        }

        // Copy Temp array back into data array
        for (int i1 = lo; i1 <= hi; i1++) {
            data[i1] = temp[i1];
        }
    }

}
