package ksl.academic.algorithm.leet;

import java.util.Arrays;

/**
 * https://www.interviewcake.com/question/java/merge-sorted-arrays
 * In order to win the prize for most cookies sold, my friend Alice and
 * I are going to merge our Girl Scout Cookies orders and enter as one unit.
 * <p>
 * Each order is represented by an "order id" (an integer).
 * We have our lists of orders sorted numerically already, in arrays.
 * Write a method to merge our arrays of orders into one sorted array.
 * <p>
 * We can do this in O(n) time and space.
 */
public class GirlScoutCookie {

    public static void main(String[] args) {

        int[] myArray = new int[]{3, 4, 6, 10, 11, 15};
        int[] alicesArray = new int[]{1, 5, 8, 12, 14, 19};

        System.out.println(Arrays.toString(mergeArrays(myArray, alicesArray)));

    }

    static int[] mergeArrays(int[] a, int[] b) {

        int n = a.length + b.length;
        int[] result = new int[n];
        int ai = 0, bi = 0;
        for (int i = 0; i < n; i++) {
            if (ai < a.length && bi < b.length) {
                if (a[ai] < b[bi]) {
                    result[i] = a[ai++];
                } else {
                    result[i] = b[bi]++;
                }
            } else if (i >= a.length) {
                result[i] = b[bi++];
            } else {
                result[i] = a[ai++];
            }
        }
        return result;
    }

}
