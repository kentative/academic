package ksl.academic.algorithm.sim;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

// https://www.interviewcake.com/question/java/coin
public class MakingChange {

    static Integer[][] cache;

    public static void main(String[] args) {

        int[] SMALL = new int[]{1, 2, 3};
        int total = 4;

        int[] US_COINS = new int[]{1, 5, 10, 25, 50};

        System.out.println("combinationItr " + combinationItr(SMALL, total));
        System.out.println("permutationItr " + permutationItr(SMALL, total));
        System.out.println("changePossibilitiesBottomUp1 " + changePossibilitiesBottomUp(SMALL, total));

    }

    // Order doesn't count
    public static int changePossibilitiesBottomUp(int[] denominations, int amount) {

        int[] dp = new int[amount + 1];  // array of zeros from 0..amount
        dp[0] = 1;

        System.out.println(Arrays.toString(dp));

        for (int coin : denominations) {

            for (int value = coin; value <= amount; value++) {
                int remainder = value - coin;
                dp[value] += dp[remainder];
                System.out.println(Arrays.toString(dp));
            }

        }

        return dp[amount];
    }

    // Order doesn't count
    public static int changePossibilitiesBottomUp1(int[] denominations, int amount) {

        int[] dp = new int[amount + 1];  // array of zeros from 0..amount
        dp[0] = 1;

        System.out.println(Arrays.toString(dp));

        for (int value = 1; value <= amount; value++) {

            for (int coin : denominations) {

                int remainder = value - coin;
//		            System.out.println("denomination:remainder: "  + coin + ":"+remainder);
                if (remainder >= 0) {
                    dp[value] += dp[remainder];
                    System.out.println(Arrays.toString(dp));
                }
            }

        }

        return dp[amount];
    }


    // Coin order doesn't count as unique, can repeat coin
    public static int combinationItr(int[] denom, int value) {

        List<Param> itemQ = new LinkedList<>();
        itemQ.add(new Param(0, value));

        int result = 0;
        while (!itemQ.isEmpty()) {
            Param p = itemQ.remove(0);

            if (p.remaining == 0) {
                result++;
                continue;
            }

            if (p.remaining < 0) continue;

            // starts at current denomination, don't repeat
            for (int i = p.index; i < denom.length; i++) {
                itemQ.add(new Param(i, p.remaining - denom[i]));
            }
        }
        return result;
    }


    // Coin selection order counts as unique, can repeat coin
    public static int permutationItr(int[] denom, int value) {

        List<Param> paramQ = new LinkedList<>();
        paramQ.add(new Param(0, value));

        int result = 0;
        while (!paramQ.isEmpty()) {
            Param p = paramQ.remove(0);
            if (p.remaining == 0) {
                result++;
                continue;
            }

            if (p.remaining < 0) continue;

            // starts at current first denomination, allow repeat
            for (int i = 0; i < denom.length; i++) {
                paramQ.add(new Param(i, p.remaining - denom[i]));
            }
        }
        return result;
    }

    private static class Param {
        int index;
        int remaining;

        Param(int index, int remaining) {
            this.index = index;
            this.remaining = remaining;
        }

        public String toString() {
            return index + ", " + remaining;
        }
    }
}
