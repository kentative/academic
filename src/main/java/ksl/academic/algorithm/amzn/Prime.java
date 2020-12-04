package ksl.academic.algorithm.amzn;

import java.util.ArrayList;
import java.util.List;

public class Prime {

    public static void main(String[] args) {
        System.out.println(listPrimes(50));
    }

    private static List<Integer> listPrimes(int n) {

        boolean[] cache = new boolean[n + 1];

        for (int i = 2; i < n; i++) {
            for (int j = i * i; j <= n; j += i) {
                cache[j] = true;
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (!cache[i]) result.add(i);
        }

        return result;
    }
}
