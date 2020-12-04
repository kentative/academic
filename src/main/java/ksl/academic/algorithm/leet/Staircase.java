package ksl.academic.algorithm.leet;

import java.util.LinkedList;
import java.util.List;

public class Staircase {


    public static void main(String[] args) {

        int[] steps = {1, 2, 3};
        System.out.println(countWays(steps, 4));
    }

    static int countWays(int[] denom, int total) {

        List<Integer> queue = new LinkedList<>();
        queue.add(0);
        int ways = 0;

        while (!queue.isEmpty()) {
            int subtotal = queue.remove(0);

            for (int value : denom) {
                int newTotal = subtotal + value;

                if (newTotal <= total) {
                    if (newTotal == total) {
                        ways++;
                    }
                    queue.add(newTotal);
                }
            }
        }

        return ways;

    }

}
