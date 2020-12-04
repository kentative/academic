package ksl.academic.algorithm.crack;

import com.google.common.base.Preconditions;

public class MissingNumber {
    public static void main(String[] args) {

        int[] data = {1, 2, 3, 4, 6, 7, 8, 9, 10}; // missing 5
        System.out.println(findMissingTriangleSeries(data));
    }


    /**
     * Find the missing number in a triangular series
     *
     * @param numbers - the list of integers starting from 1,
     * @return
     */
    static int findMissingTriangleSeries(int[] numbers) {

        Preconditions.checkNotNull(numbers);

        int sum = 0;
        int n = numbers.length;
        for (int i = 0; i < n; i++) sum += numbers[i];

        int m = n + 1;
        return (m * (m + 1)) / 2 - sum;
    }
}
