package ksl.academic.algorithm.leet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class SumMinSubArray {

    static int MAX_VALUE = 1000000007;

    public static void main(String[] args) {
        int[] data = {3, 2, 1, 4};
//		System.out.println(findSumMinSubArray(data));

        System.out.println(sumSubarrayMinsDetailed(data));

    }

    static int sumSubarrayMinsDetailed(int[] A) {

        int MOD = 1_000_000_007;
        int N = A.length;

        // prev has i* - 1 in increasing order of A[i* - 1]
        // where i* is the answer to query j
        Stack<Integer> stack = new Stack();
        int[] prev = new int[N];
        for (int i = 0; i < N; ++i) {
            while (!stack.isEmpty() && A[i] <= A[stack.peek()])
                stack.pop();
            prev[i] = stack.isEmpty() ? -1 : stack.peek();
            stack.push(i);
        }

        // next has k* + 1 in increasing order of A[k* + 1]
        // where k* is the answer to query j
        stack = new Stack();
        int[] next = new int[N];
        for (int k = N - 1; k >= 0; --k) {
            while (!stack.isEmpty() && A[k] < A[stack.peek()])
                stack.pop();
            next[k] = stack.isEmpty() ? N : stack.peek();
            stack.push(k);
        }

        // Use prev/next array to count answer
        long ans = 0;
        for (int i = 0; i < N; ++i) {
            ans += (i - prev[i]) * (next[i] - i) % MOD * A[i] % MOD;
            ans %= MOD;
        }
        return (int) ans;

    }

    static int sumSubarrayMins(int[] A) {

        int sol = 0;
        Map<Integer, Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < A.length; ++i) {

            // smallest value in this stack
            while (!stack.isEmpty() && A[i] < A[stack.peek()]) stack.pop();

            int cur = 0;

            if (stack.isEmpty()) {
                cur = (i + 1) * A[i];
            } else {
                cur = map.get(stack.peek()) + (i - stack.peek()) * A[i];
            }
            cur %= MAX_VALUE;
            stack.push(i);
            map.put(i, cur);
            sol += cur;
            sol %= MAX_VALUE;
        }

        return sol;
    }

    static int findSumMinSubArray(int[] data) {

        int n = data.length;
        int minSum = 0;
        for (int i = 1; i <= n; i++) {
            List<int[]> subList = findSubArray(data, i);
            for (int[] sub : subList) {
                minSum = (minSum + min(sub)) % MAX_VALUE;
            }
        }
        return minSum;
    }

    private static int min(int[] data) {
        int min = data[0];
        for (int i = 0; i < data.length; i++) {
            if (data[i] < min) min = data[i];
        }
        return min;
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
