package ksl.academic.algorithm.amzn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairSum {

    public static void main(String[] args) {
        int[] data = {-2, -1, 0, 3, 5, 6, 7, 9, 13, 14};

        List<int[]> sumList = findSum(data, 12);
        for (int[] pair : sumList) {
            System.out.println(Arrays.toString(pair));
        }
    }

    // O(n) time and space
    public static List<int[]> findSum(int[] data, int sum) {

        List<int[]> result = new ArrayList<>();
        if (data == null) return result;

        int n = data.length;
        Map<Integer, Boolean> cache = new HashMap<>(n);


        for (int x : data) {
            int complement = sum - x;
            if (cache.getOrDefault(x, false)) {
                cache.put(complement, false);
                result.add(new int[]{x, complement});
                System.out.println("consuming " + x);
            } else {
                System.out.println("adding " + complement);
                cache.put(complement, true);
            }
        }

        return result;
    }

}
