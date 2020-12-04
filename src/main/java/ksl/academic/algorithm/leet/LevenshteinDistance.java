package ksl.academic.algorithm.leet;

public class LevenshteinDistance {


    public static void main(String[] args) {
        System.out.println(compute("kite", "sitting"));
        System.out.println(editDistance("kite".toCharArray(), "sitting".toCharArray()));

    }

    private static int minimum(int a, int b, int c) {
        return Math.min(Math.min(a, b), c);
    }


    // Internet version
    public static int compute(CharSequence lhs, CharSequence rhs) {
        int[][] distance = new int[lhs.length() + 1][rhs.length() + 1];

        // Fill columns with 0..left, rows with 1..right
        for (int i = 0; i <= lhs.length(); distance[i][0] = i++) ;
        for (int j = 1; j <= rhs.length(); distance[0][j] = j++) ;

        for (int i = 1; i <= lhs.length(); i++)
            for (int j = 1; j <= rhs.length(); j++) {
                distance[i][j] = minimum(
                        distance[i - 1][j] + 1,  // deletion                                
                        distance[i][j - 1] + 1,  // insertion                        
                        distance[i - 1][j - 1] + ((lhs.charAt(i - 1) == rhs.charAt(j - 1)) ? 0 : 1));
            }

        return distance[lhs.length()][rhs.length()];
    }

    // Kent's version
    static int editDistance(char[] seq1, char[] seq2) {

        int n = seq1.length, m = seq2.length;

        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) dp[i][0] = i;
        for (int j = 0; j <= m; j++) dp[0][j] = j;

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                int delete = dp[i - 1][j];
                int insert = dp[i][j - 1];
                int replace = dp[i - 1][j - 1];

                if (seq1[i - 1] == seq2[j - 1])
                    dp[i][j] = replace;
                else
                    dp[i][j] = 1 + Math.min(replace, Math.min(delete, insert));
            }
        }

        return dp[n][m]; // Max of all minima
    }
}