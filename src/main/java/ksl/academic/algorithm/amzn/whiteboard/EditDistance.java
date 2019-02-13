package ksl.academic.algorithm.amzn.whiteboard;

import java.util.Arrays;

public class EditDistance {

	public static void main(String[] args) {
		
		System.out.println(editDistance("cat", "dog"));
	}
	
	public static int editDistance(String s1, String s2) {
		char[] buff1 = s1.toCharArray();
		char[] buff2 = s2.toCharArray();
		
		int s1Len = s1.length(), s2Len = s2.length();
		int[][] cache = new int[s1Len+1][s2Len+1];
		
		for (int i = 0; i <= s1Len; i++) cache[i][0] = i;
		for (int j = 0; j <= s2Len; j++) cache[0][j] = j;
		
		for (int i = 1; i <= s1Len; i++) {
			for (int j = 1; j <= s2Len; j++) {
				int replace = cache[i-1][j-1];
				int delete = cache[i][j-1];
				int insert = cache[i-1][j];
				
				if (buff1[i-1] == buff2[j-1]) cache[i][j] = replace;
				else cache[i][j] = 1+Math.min(replace, Math.min(delete, insert));
				
			}
		}
		
		for (int i = 0; i < cache.length; i++) {
			System.out.println(Arrays.toString(cache[i]));
		}
		return cache[s1Len][s2Len];
		
	}
}
