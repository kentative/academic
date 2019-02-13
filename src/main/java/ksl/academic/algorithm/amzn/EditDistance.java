package ksl.academic.algorithm.amzn;

public class EditDistance {

	
	public static void main(String[] args) {
		
		System.out.println(editDistance("kite", "sitting"));
		
	}
	
	
	public static int editDistance(String w1, String w2) {
		
		char[] b1 = w1.toCharArray();
		char[] b2 = w2.toCharArray();

		int b1Len = b1.length, b2Len = b2.length;
		
		int[][] dp = new int[b1Len+1][b2Len+1];
		for (int i = 1; i <= b1Len; i++) dp[i][0] = i; // fill first column
		for (int i = 1; i <= b2Len; i++) dp[0][i] = i; // fill first row
		
		for (int i = 1; i <= b1Len; i++) {
			for (int j = 1; j <= b2Len; j++) {
				if (b1[i-1] == b2[j-1]) {
					dp[i][j] = dp[i-1][j-1];
				} else {
					dp[i][j] = 1+ Math.min(dp[i-1][j-1], 
							Math.min(dp[i][j-1], dp[i-1][j]));
				}
			}
		}
		
		
		return dp[b1Len][b2Len];

	}
}
