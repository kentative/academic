package ksl.academic.algorithm.leet;

// Leetcode #221
public class MaximalSquare {

	public static void main(String[] args) {
		int[][] square = {
				{1, 1, 1, 1, 0},
				{1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1},
				{1, 1, 1, 1, 1}
			};
		
		System.out.println(maximalSquare(square));
	}
	
    public static int maximalSquare(int[][] grid) {
    	
    	int row = grid.length, col = (row > 0) ? grid[0].length :0;
    	int[][] dp = new int[row+1][col+1];

    	int max = 0;
    	for (int i = 1; i <= row; i++) {
    		for (int j = 1; j <= col; j++) {

    			int t = dp[i-1][j];
    			int l = dp[i][j-1];
    			int tl= dp[i-1][j-1];
    			
    			if (grid[i-1][j-1] == 1) {
    				dp[i][j] = Math.min(tl, Math.min(t, l)) +1;
    				max = Math.max(max, dp[i][j]);
    			}
    			
    		}
    	}
    	
    	return max * max;
    }
}
