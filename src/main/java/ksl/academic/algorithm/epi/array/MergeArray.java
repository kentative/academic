package ksl.academic.algorithm.epi.array;

import com.google.common.base.Preconditions;

import ksl.academic.algorithm.Utility;

public class MergeArray {

    public static void main(String[] args) {
        int[] even = {2, 4, 6, 8};
        int[] odd = {1, 3, 5};

        Utility.print(merge(even, odd));


    }

    private static int[] merge(int[] a, int[] b) {

        // null lists
        Preconditions.checkNotNull(a, "Can't have null");
        Preconditions.checkNotNull(b, "Can't have null");

        int aLen = a.length, bLen = b.length;
        int n = aLen + bLen;

        // empty list
        if (n == 0) return new int[]{};

        int[] result = new int[n];
        int i = 0, j = 0, k = 0;
        while (k < n) {
            if (i >= aLen) {
                while (k < n && j < bLen) result[k++] = b[j++];
                break;
            }
            if (j >= bLen) {
                while (k < n && i < aLen) result[k++] = a[i++];
                break;
            }

            if (a[i] < b[j]) result[k++] = a[i++];
            else result[k++] = b[j++];

        }
        return result;
    }
}
