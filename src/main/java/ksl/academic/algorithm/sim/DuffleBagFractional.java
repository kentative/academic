package ksl.academic.algorithm.sim;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Fractional allow, limited items
 *  - sort items by value
 *  - take highest value first
 *  - take fractional items to finish off
 */
public class DuffleBagFractional {

	public static void main(String[] args) {

		CakeType[] cakes = { new CakeType(2, 11), new CakeType(4, 41), new CakeType(6, 37), new CakeType(8, 53) };

		System.out.println(getMaxValue(cakes, 17));
	}

	// Fractional allow, limited items
	private static double getMaxValue(CakeType[] cakes, int cap) {

		sortByRatio(cakes);
		double totalValue = 0d;
		for (CakeType c : cakes) {

			int w = (int) c.weight;
			int v = (int) c.value;

			if (w <= cap) {
				cap = cap - w;
				totalValue += v;

			} else {
				double fraction = cap / (double) w;
				totalValue += (v * fraction);
				break;
			}
		}
		return totalValue;
	}

	private static void sortByRatio(CakeType[] cakes) {
		Arrays.sort(cakes, new Comparator<CakeType>() {
			@Override
			public int compare(CakeType o1, CakeType o2) {
				return Double.compare((o2.value / (double) o2.weight), (o1.value / (double) o1.weight));
			}
		});
	}

	public static class CakeType {

		final int weight;
		final int value;

		public CakeType(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

		public String toString() {
			return String.valueOf(value / (double) weight);
		}
	}

}
