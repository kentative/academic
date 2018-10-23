package ksl.academic.algorithm.sim;

public class Knapsack2 {

	public static void main(String[] args) {
		int knapsackMaxWeight = 5;
		int profit[] = { 200, 240, 140, 150 };
		int weight[] = { 1, 3, 2, 5 };

		int maxProfit = profit(weight, profit, weight.length, knapsackMaxWeight);
		System.out.println(maxProfit);
	}

	private static int profit(int[] w, int[] v, int cur, int cap) {

		if (cap == 0 || cur == 0) return 0; 

		if (w[cur - 1] > cap) {
			return profit(w, v, cur - 1, cap);
			
		} else {
			int including = v[cur - 1] + profit(w, v, cur - 1, cap - w[cur - 1]);
			int excluding = profit(w, v, cur - 1, cap);
			return Math.max(including, excluding);
		}
	}
}