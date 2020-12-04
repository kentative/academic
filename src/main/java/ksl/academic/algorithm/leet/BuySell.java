package ksl.academic.algorithm.leet;

import com.google.common.base.Preconditions;

import ksl.academic.algorithm.Utility;

public class BuySell {

    public static void main(String[] args) {

        int[] pricesM = {2, 7, 5, 4, 10, 2};
        int[] pricesD = {10, 7, 5, 4, 1};
        int[] pricesU = {1, 2, 3, 4, 5};

        Utility.print(buySell(pricesM));
        System.out.println();
        Utility.print(buySell(pricesD));
        System.out.println();
        Utility.print(buySell(pricesU));
        System.out.println();

        System.out.println(getMaxProfit(pricesM));
        System.out.println(getMaxProfit(pricesD));
        System.out.println(getMaxProfit(pricesU));

    }

    static int[] buySell(int[] prices) {

        Preconditions.checkNotNull(prices, "Require non-null prices");

        int n = prices.length;
        int[] max = new int[n];
        max[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            max[i] = (prices[i] > prices[max[i + 1]]) ? i : max[i + 1];
        }

        Utility.print(max);
        Delta maxDelta = new Delta(Integer.MIN_VALUE, 0, 1);
        int min = 0;
        for (int i = 1; i < n; i++) {

            int x = prices[max[i]] - prices[i];
            if (x <= 0) {
                x = prices[max[i]] - prices[min];
                if (x > maxDelta.value) {
                    maxDelta = new Delta(x, min, max[i]);
                }
            } else if (x > maxDelta.value) {
                maxDelta = new Delta(x, i, max[i]);
            }

            if (prices[i] < prices[min]) {
                min = i;
            }

        }


        System.out.println("Profit: " + (prices[maxDelta.end] - prices[maxDelta.start]));
        return new int[]{maxDelta.start, maxDelta.end};
    }

    public static int getMaxProfit(int[] stockPrices) {

        if (stockPrices.length < 2) {
            throw new IllegalArgumentException("Getting a profit requires at least 2 prices");
        }

        // we'll greedily update minPrice and maxProfit, so we initialize
        // them to the first price and the first possible profit
        int minPrice = stockPrices[0];
        int maxProfit = stockPrices[1] - stockPrices[0];

        // start at the second (index 1) time
        // we can't sell at the first time, since we must buy first,
        // and we can't buy and sell at the same time!
        // if we started at index 0, we'd try to buy *and* sell at time 0.
        // this would give a profit of 0, which is a problem if our
        // maxProfit is supposed to be *negative*--we'd return 0.
        for (int i = 1; i < stockPrices.length; i++) {
            int currentPrice = stockPrices[i];

            // see what our profit would be if we bought at the
            // min price and sold at the current price
            int potentialProfit = currentPrice - minPrice;

            // update maxProfit if we can do better
            maxProfit = Math.max(maxProfit, potentialProfit);

            // update minPrice so it's always
            // the lowest price we've seen so far
            minPrice = Math.min(minPrice, currentPrice);
        }

        return maxProfit;
    }

    static class Delta {
        int value;
        int start;
        int end;

        public Delta(int value, int start, int end) {
            this.value = value;
            this.start = start;
            this.end = end;
        }
    }
}
