package ksl.academic.algorithm.leet;

import java.util.ArrayDeque;
import java.util.Deque;

import ksl.academic.algorithm.Utility;

public class DecodeDP {

    public static void main(String[] args) {

        char[] data = "1".repeat(10).toCharArray();
        long start = System.currentTimeMillis();
        System.out.println("ITR " + countDecodeItr(data));
        System.out.println("Duration: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        System.out.println("DP " + countDecodeDp(data));
        System.out.println("Duration: " + (System.currentTimeMillis() - start));
    }

    static int countDecodeDp(char[] data) {

        int[] cache = new int[data.length + 1];
        return count(data, data.length, cache);

    }

    static int sumDp = 0;

    private static int count(char[] data, int k, int[] cache) {
        if (k == 0) return 1;

        int i = data.length - k;
        if (data[i] == '0') return 0;

        // Memo-ization
        if (cache[i] != 0) return cache[i];

        sumDp = count(data, k - 1, cache);
        if (k >= 2 && data[i] <= '2' && data[i + 1] <= '6') {
            sumDp += count(data, k - 2, cache);
        }

        // Cache value
        cache[i] = sumDp;
        return sumDp;
    }

    static int countDecodeItr(char[] data) {

        if (data == null) return -1;
        int[] cache = new int[data.length + 1];

        int sum = 0;
        int n = data.length;
        Deque<Integer> stack = new ArrayDeque<>(n);
        stack.push(0);

        while (!stack.isEmpty()) {

            int i = stack.pop();
            if (i >= n) continue;
            if (data[i] == '0') return 0;

            if (cache[i] != 0) {
                sum += cache[i];
                continue;
            }

            // 1-26
            if ((n - i) > 2 && data[i] <= '2' && data[i + 1] <= '6') {
                stack.push(i + 1);
                stack.push(i + 2);
            } else {
                sum++;
                stack.push(i + 1);
            }
            cache[i] = sum;
            Utility.print(cache);
        }

        return sum;

    }
}
