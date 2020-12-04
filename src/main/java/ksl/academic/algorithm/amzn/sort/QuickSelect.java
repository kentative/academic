package ksl.academic.algorithm.amzn.sort;

import java.util.Arrays;

import ksl.academic.algorithm.sort.SortUtil;

public class QuickSelect {
    public static void main(String[] args) {

        int[] data = {0, 9, 4, 8, 3, 1, 2, 5, 6, 21, 12, 11, 0};

        System.out.println(quickSelect(data, 0, data.length, 11));

        Quicksort.quicksort(data, 0, data.length - 1);
        System.out.println(Arrays.toString(data));

    }

    public static int quickSelect(int[] data, int lo, int hi, int k) {

        while (true) {
            if (lo == hi) return data[lo];

            int p = partition(data, lo, hi);

            if (k < p) hi = p;
            else if (k > p) lo = p + 1;
            else return data[p];
        }
    }

    private static int partition(int[] data, int lo, int hi) {

        int m = (lo + hi) / 2;
        if (m < lo) SortUtil.swap(data, m, lo);
        if (m > hi) SortUtil.swap(data, m, hi);
        if (m > lo) SortUtil.swap(data, m, lo); // swap mid to front

        // pivot is first element,
        int pivot = data[lo];
        int i = lo;
        for (int j = lo + 1; j < hi; j++) {
            if (data[j] < pivot) {
                SortUtil.swap(data, j, ++i);
            }
        }
        SortUtil.swap(data, lo, i);
        return i;

    }
}
