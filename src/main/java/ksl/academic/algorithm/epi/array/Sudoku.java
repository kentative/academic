package ksl.academic.algorithm.epi.array;

import ksl.academic.algorithm.Utility;


// https://www.stolaf.edu//people/hansonr/sudoku/exactcovermatrix.htm
// http://garethrees.org/2007/06/10/zendoku-generation/#section-4.2
public class Sudoku {

    public static void main(String[] args) {

        int[][] grid = new int[9][9];
        grid[0] = new int[]{1, 8, 9, 4, 5, 6, 7, 2, 3};
        grid[1] = new int[]{2, 0, 0, 0, 0, 0, 0, 0, 0};
        grid[2] = new int[]{3, 0, 0, 0, 0, 0, 0, 0, 0};
        grid[3] = new int[]{4, 0, 0, 0, 0, 0, 0, 0, 0};
        grid[4] = new int[]{5, 0, 0, 0, 0, 0, 0, 0, 0};
        grid[5] = new int[]{6, 0, 0, 0, 0, 0, 0, 0, 0};
        grid[6] = new int[]{7, 0, 0, 0, 0, 0, 0, 0, 0};
        grid[7] = new int[]{8, 0, 0, 0, 0, 0, 0, 0, 0};
        grid[8] = new int[]{9, 0, 0, 0, 0, 0, 0, 0, 0};

        int[][] grid2 = new int[9][9];
        grid2[0] = new int[]{8, 0, 0, 0, 0, 0, 0, 0, 0};
        grid2[1] = new int[]{0, 0, 3, 6, 0, 0, 0, 0, 0};
        grid2[2] = new int[]{0, 7, 0, 0, 9, 0, 2, 0, 0};
        grid2[3] = new int[]{0, 5, 0, 0, 0, 7, 0, 0, 0};
        grid2[4] = new int[]{0, 0, 0, 0, 4, 5, 7, 0, 0};
        grid2[5] = new int[]{0, 0, 0, 1, 0, 0, 0, 3, 0};
        grid2[6] = new int[]{0, 0, 1, 0, 0, 0, 0, 6, 8};
        grid2[7] = new int[]{0, 0, 8, 5, 0, 0, 0, 1, 0};
        grid2[8] = new int[]{0, 9, 0, 0, 0, 0, 4, 0, 0};

        System.out.println(Utility.toString(grid2));
        System.out.println(isValid(grid2));

    }

    /**
     * Beautiful!
     *
     * @param grid - the Sodoku grid
     * @return true if the grid is valid
     */
    static boolean isValid(int[][] grid) {

        int size = 9, b = 3; // grid and block size
        for (int i = 0; i < size; i++) {


            // sodoku numbers 1-9 (size+1 for index 9)
            boolean[][] cache = {
                    new boolean[size + 1],
                    new boolean[size + 1],
                    new boolean[size + 1]};

            for (int j = 0; j < size; j++) {
                // Check all cells in row
                if (hasDuplicate(cache[0], grid[i][j])) return false;

                // Check all cells in column
                if (hasDuplicate(cache[1], grid[j][i])) return false;

                // Check all cells in block
                int r = (i / b) * b + j / b;
                int c = (i % b) * b + j % b;
                if (hasDuplicate(cache[2], grid[r][c])) return false;
            }
        }
        return true;
    }

    static boolean hasDuplicate(boolean[] cache, int x) {
        if (x == 0) return false;
        if (!cache[x]) {
            cache[x] = true;
            return false;
        }
        return true;
    }

}
