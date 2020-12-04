package ksl.academic.algorithm.epi.recursion;

/**
 * Example of trade-offs:  the Towers of Hanoi with memoization
 * For each of the six permutation of (src,dst,tmp) and for each n,
 * save the steps for hanoi(n,src,dst,tmp).
 * <p>
 * By doing a bottom-up construction, one requires only two rows in
 * the matrix of all solutions:  previous and current.
 * <p>
 * The permutation can be stored as a single octal digit.  Each
 * permutation is of the numbers 0, 1, and 2, meaning that all must
 * total to 3, and only an ordered pair needs to be retained.  This
 * will be considered to be src and dst.  If this ordered pair is
 * considered as a base-3 number, then the range is 01 through 21
 * --- meaning the magnitudes are 1 through 7.
 * <p>
 * The space complexity, however, is nasty:  the two rows have
 * (2)*6 strings --- with three numbers, there are six permutations.
 * Each string, however, contains the full solution for the problem
 * of a given value of n --- pow(2, n) - 1 disk movements.
 * <p>
 * Above 23: java -Xms1024m -Xmx1024m
 * <p>
 * Perpetrator:  Timothy Rolfe
 */
public class TowerOfHanoiBottomUp {

    static String[] solve(int n) {  // 6 empty strings for 01 through 21; null for unused cells 0 and 4
        String[] prev = {null, "", "", "", null, "", "", ""},
                curr = new String[8],   // compute into this
                temp;                   // interchange prev and curr

        int i, // Outer loop on level of solution
                j; // Inner loop for the six problems at level i

        // Final result will be in prev
        for (i = 1; i <= n; i++) {  // Populate curr from prev
            for (j = 0; j < curr.length; j++) {  // 00, 11, and 22 are not allowed
                int src = j / 3, dst = j % 3,
                        tmp = 3 - src - dst;

                if (src == dst) continue;  // I.e., 00 or 11
                //     h(i-1,src,tmp,dst)   src to dst    h(i-1,tmp,dst,src)
                curr[j] = prev[src * 3 + tmp] + (src * 3 + dst) + prev[tmp * 3 + dst];
            }
            // Swap curr and prev.
            temp = curr;
            curr = prev;
            prev = temp;
        }

        return prev;
    }

    static String recHanoi(int n, int src, int dst, int tmp) {
        String addend;
        if (n == 0) return "";
        // Two-digit data movement treated as base-3:  01 through 21
        // Get the magnitude of the base-3 number and store as octal
        addend = String.format("%1o", 3 * src + dst);
        return recHanoi(n - 1, src, tmp, dst) + addend +
                recHanoi(n - 1, tmp, dst, src);
    }

    public static void main(String[] args) {  // Specifications for the recursive calls
        int[][] prob = {{0, 1, 2},
                {0, 2, 1},
                {1, 0, 2},
                {1, 2, 0},
                {2, 0, 1},
                {2, 1, 0}};
        String[] soln;
        int k, n = args.length > 0 ? Integer.parseInt(args[0])
                : 4;
        long elapsed = -System.nanoTime();

        soln = solve(n);
        elapsed += System.nanoTime();
        System.out.printf("Dynamic solutions in %.3f sec.\n", 1E-9 * elapsed);
        elapsed = -System.nanoTime();
        for (k = 0; k < prob.length; k++) {
            String rec = recHanoi(n, prob[k][0], prob[k][1], prob[k][2]);
            int idx = prob[k][0] * 3 + prob[k][1];

            System.out.printf("%d:  (%d, %d, %d) ", k,
                    prob[k][0], prob[k][1], prob[k][2]);
            System.out.printf("%s\n", rec.equals(soln[idx]));
        }
        elapsed += System.nanoTime();
        System.out.printf("Recursive solutions in %.3f sec.\n", 1E-9 * elapsed);
    }
}
/* The program BottomTime.java invokes BottomTime.solve(n) for a range
   of sizes and captures elapsed time and required space for each size.

    1:  0.930 msec.  6 characters (1 per soln)
    2:  0.041 msec.  18 characters (3 per soln)
    3:  0.054 msec.  42 characters (7 per soln)
    4:  0.067 msec.  90 characters (15 per soln)
    5:  0.091 msec.  186 characters (31 per soln)
    6:  0.159 msec.  378 characters (63 per soln)
    7:  0.139 msec.  762 characters (127 per soln)
    8:  0.199 msec.  1530 characters (255 per soln)
    9:  0.200 msec.  3066 characters (511 per soln)
   10:  0.240 msec.  6138 characters (1023 per soln)
   11:  0.309 msec.  12282 characters (2047 per soln)
   12:  0.450 msec.  24570 characters (4095 per soln)
   13:  0.666 msec.  49146 characters (8191 per soln)
   14:  0.986 msec.  98298 characters (16383 per soln)
   15:  1.833 msec.  196602 characters (32767 per soln)
   16:  3.913 msec.  393210 characters (65535 per soln)
   17:  7.199 msec.  786426 characters (131071 per soln)
   18:  20.509 msec.  1572858 characters (262143 per soln)
   19:  51.087 msec.  3145722 characters (524287 per soln)
   20:  93.289 msec.  6291450 characters (1048575 per soln)
   21:  191.975 msec.  12582906 characters (2097151 per soln)
   22:  264.635 msec.  25165818 characters (4194303 per soln)
   23:  589.194 msec.  50331642 characters (8388607 per soln)
   24:  1391.239 msec.  100663290 characters (16777215 per soln)
   25:  2347.687 msec.  201326586 characters (33554431 per soln)
*/