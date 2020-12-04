package ksl.academic.algorithm.leet;

import java.util.ArrayList;
import java.util.List;

import ksl.academic.algorithm.Utility;

public class SubArraySum {

    public static void main(String[] args) {

        int[] data = {3, 2, 1, 4};

        System.out.println(subArraySumBrute(data));
        System.out.println(subArraySum(data));

    }

    // function compute sum all sub-array
    static long subArraySum(int[] data) {

        long result = 0;

        // computing sum of sub array using formula
        int n = data.length;
        for (int i = 0; i < n; i++) {
            // x * (x at first index) + (x at other indices)
            result += (data[i] * (i * (n - i) + (n - i)));
//			result += (data[i] * (i + 1) * (n - i));
        }

        // return all sub array sum
        return result;
    }


    static long subArraySumBrute(int[] data) {

        int sum = 0;
        for (int i = 1; i <= data.length; i++) {
            List<int[]> subList = findSubArray(data, i);

            for (int[] sub : subList) Utility.print(sub);

            for (int[] sub : subList) {
                for (int k = 0; k < sub.length; k++) {
                    sum += sub[k];
                }
            }

        }
        return sum;
    }

    private static List<int[]> findSubArray(int[] data, int size) {
        List<int[]> result = new ArrayList<>();
        int n = data.length;
        for (int i = 0, j = size; j <= n; i++, j++) {
            int[] sub = new int[size];
            for (int k = i; k < j; k++) {
                sub[k % size] = data[k];
            }
            result.add(sub);

        }
        return result;
    }
}
