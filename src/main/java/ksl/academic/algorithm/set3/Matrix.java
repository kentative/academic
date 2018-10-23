package ksl.academic.algorithm.set3;


public class Matrix {

/**
 * Matrix multiplication method.
 * @param m1 Multiplicand
 * @param m2 Multiplier
 * @return Product
 */
    public static double[][] multiplyByMatrix(double[][] m1, double[][] m2) {
    	
        int m1c = m1[0].length; // m1 columns length
        int m2r = m2.length;    // m2 rows length
        int r = m1.length;      // m result rows length
        int c = m2[0].length;   // m result columns length
        
        if(m1c != m2r) return null; // matrix multiplication is not possible
        
        double[][] mResult = new double[r][c];
        for(int i = 0; i < r; i++) {           // rows from m1
            for(int j = 0; j < c; j++) {       // columns from m2
                for(int k = 0; k < m1c; k++) { // columns from m1
                    mResult[i][j] += m1[i][k] * m2[k][j];
                }
            }
        }
        return mResult;
    }

    public static String toString(double[][] m) {
        String result = "";
        for(int i = 0; i < m.length; i++) {
            for(int j = 0; j < m[i].length; j++) {
                result += String.format("%11.2f", m[i][j]);
            }
            result += "\n";
        }
        return result;
    }

    public static void main(String[] args) {
        // #1
        double[][] multiplicand = new double[][] {
                {3, -1, 2},
                {2,  0, 1},
                {1,  2, 1}
        };
        double[][] multiplier = new double[][] {
                {2, -1, 1},
                {0, -2, 3},
                {3,  0, 1}
        };
        System.out.println("#1\n" + toString(multiplyByMatrix(multiplicand, multiplier)));
        // #2
        multiplicand = new double[][] {
                {1, 2, 0},
                {-1, 3, 1},
                {2, -2, 1}
        };
        multiplier = new double[][] {
                {2},
                {-1},
                {1}
        };
        System.out.println("#2\n" + toString(multiplyByMatrix(multiplicand, multiplier)));
        // #3
        multiplicand = new double[][] {
                {1, 2, -1},
                {0,  1, 0}
        };
        multiplier = new double[][] {
                {1, 1, 0, 0},
                {0, 2, 1, 1},
                {1, 1, 2, 2}
        };
        System.out.println("#3\n" + toString(multiplyByMatrix(multiplicand, multiplier)));
    }
}