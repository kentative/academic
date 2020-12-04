package ksl.academic.algorithm.amzn;

public class Lcs {

    public static void main(String[] args) {
        String s1 = "AGGTAB";
        String s2 = "GXTXAYB";

        char[] X = s1.toCharArray();
        char[] Y = s2.toCharArray();
        int m = X.length;
        int n = Y.length;

        System.out.println("Length of LCS is" + " " + lcs(X, Y, m, n));
    }


    /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    static int lcsNaive(char[] X, char[] Y, int m, int n) {
        if (m == 0 || n == 0)
            return 0;
        if (X[m - 1] == Y[n - 1])
            return 1 + lcsNaive(X, Y, m - 1, n - 1);
        else
            return Math.max(lcsNaive(X, Y, m, n - 1), lcsNaive(X, Y, m - 1, n));
    }


    /* Returns length of LCS for X[0..m-1], Y[0..n-1] */
    static int lcs(char[] seq1, char[] seq2, int s1Len, int s2Len) {

        int[][] cache = new int[s1Len + 1][s2Len + 1];

        for (int i = 0; i <= s1Len; i++) cache[i][0] = 0;
        for (int i = 0; i <= s2Len; i++) cache[0][i] = 0;

        for (int i = 1; i <= s1Len; i++) {
            for (int j = 1; j <= s2Len; j++) {
                if (seq1[i - 1] == seq2[j - 1]) {
                    cache[i][j] = cache[i - 1][j - 1] + 1;
                } else {
                    cache[i][j] = Math.max(cache[i - 1][j], cache[i][j - 1]);
                }
            }
        }

        return cache[s1Len][s2Len];
    }
}
