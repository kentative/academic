package ksl.academic.algorithm.crack;

import java.util.Arrays;
import java.util.Random;

public class ShuffleSet {

	private static Random r = new Random();
	
	public static void main(String[] args) {
		
		int size = 1000;
		int[] data = new int[size];
		for (int i = 0; i < size; i++) data[i] = i;
		
		int[] subset = pickMIteratively(data, 999);
		
		System.out.println(deviation(data));
		System.out.println(deviation(subset));
	}
	
	static int[] pickMIteratively(int[] original, int m) {
		
		int[] subset = Arrays.copyOf(original, m);
		for (int i = m; i< original.length; i++) {
			int k = r.nextInt(i+1);
			if (k < m) {				
				subset[k] = original[i];
			}
		}
		return subset;
		
	}
	
	/**
	 * M = mean of Xi
	 * 
	 * deviation = sqrt(sum((xi - M)^2)/n)
	 * @param data
	 * @return
	 */
	private static double deviation(int[] data) {
		
		double mean = 0;
		for (int x : data) mean += x;
		mean = mean/data.length;

		double sqDiffSum = 0;
		for (int i = 0; i < data.length; i++) {
			sqDiffSum += Math.pow(data[i] - mean, 2);
		}
		return Math.sqrt(sqDiffSum/data.length);
	}
}
