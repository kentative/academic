package ksl.academic.algorithm.amzn.tree;

import java.util.HashMap;
import java.util.Map;

public class MaxRange {

    public static void main(String[] args) {

        int[] numbers = {0, 2, 3, 1, 5, 15, 23, 4, 6, 8, 7};
        System.out.println(getMaxRange(numbers));
    }

    public static Range getMaxRange(int[] numbers) {

        Map<Integer, Boolean> cache = new HashMap<>();
        for (int n : numbers) {
            cache.put(n, true);
        }

        Range max = new Range();
        for (int n : numbers) {
            if (!cache.get(n)) continue;

            cache.put(n, false);

            int length = 1;
            int left = n - 1;
            while (cache.containsKey(left)) {
                length++;
                cache.put(left, false);
                left--;
            }

            int right = n + 1;
            while (cache.containsKey(right)) {
                length++;
                cache.put(right, false);
                right++;
            }

            if (length > max.size) {
                max.min = left + 1;
                max.max = right - 1;
                max.size = length;
            }
        }

        return max;
    }

    static class Range {
        int size;
        int min;
        int max;

        public String toString() {
            return size + " - [" + min + ", " + max + "]";
        }
    }
}
