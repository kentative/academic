package ksl.academic.algorithm.sim;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

// https://www.interviewcake.com/question/java/coin
public class MakingChange2 {

    // Order doesn't count
    public static int changePossibilitiesBottomUp(int amount, int[] denominations) {

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


    private static final Map<String, Integer> memo = new HashMap<>();

    private static int changePossibilitiesTopDown(int amountLeft, int[] denominations, int currentIndex) {

        // check our memo and short-circuit if we've already solved this one
        String memoKey = currentIndex + ", " + amountLeft;
        if (memo.containsKey(memoKey)) {
            System.out.println("grabbing memo [" + memoKey + "]");
            return memo.get(memoKey);
        }

        // base cases:
        // we hit the amount spot on. yes!
        if (amountLeft == 0) return 1;

        // we overshot the amount left (used too many coins)
        if (amountLeft < 0) return 0;

        // we're out of denominations
        if (currentIndex == denominations.length) return 0;

        System.out.printf("checking ways to make %d with %s\n",
                amountLeft, Arrays.toString(Arrays.copyOfRange(denominations,
                        currentIndex, denominations.length)));

        // choose a current coin
        int currentCoin = denominations[currentIndex];

        // see how many possibilities we can get
        // for each number of times to use currentCoin
        int numPossibilities = 0;
        while (amountLeft >= 0) {
            numPossibilities += changePossibilitiesTopDown(amountLeft, denominations,
                    currentIndex + 1);
            amountLeft -= currentCoin;
        }

        // save the answer in our memo so we don't compute it again
        memo.put(memoKey, numPossibilities);
        return numPossibilities;
    }


    @Test
    public void sampleInputTest() {
        final int actual = changePossibilitiesBottomUp(4, new int[]{1, 2, 3});
        final int expected = 4;
        assertEquals(expected, actual);
    }

    @Test
    public void oneWayToMakeZeroCentsTest() {
        final int actual = changePossibilitiesBottomUp(0, new int[]{1, 2});
        final int expected = 1;
        assertEquals(expected, actual);
    }

    @Test
    public void noWaysIfNoCoinsTest() {
        final int actual = changePossibilitiesBottomUp(1, new int[]{});
        final int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void bigCoinValueTest() {
        final int actual = changePossibilitiesBottomUp(5, new int[]{25, 50});
        final int expected = 0;
        assertEquals(expected, actual);
    }

    @Test
    public void bigTargetAmountTest() {
        final int actual = changePossibilitiesBottomUp(50, new int[]{5, 10});
        final int expected = 6;
        assertEquals(expected, actual);
    }

    @Test
    public void changeForOneDollarTest() {
        final int actual = changePossibilitiesBottomUp(100, new int[]{1, 5, 10, 25, 50});
        final int expected = 292;
        assertEquals(expected, actual);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(MakingChange2.class);
        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }
        if (result.wasSuccessful()) {
            System.out.println("All tests passed.");
        }

//    	int[] denomination = new int[]{1, 2, 3};
//		System.out.println("combinationItr " + changePossibilitiesTopDown(4, denomination, 0));
    }
}
