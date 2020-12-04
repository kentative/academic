package ksl.academic.algorithm.epi.array;

import ksl.academic.algorithm.Utility;

public class Spiral2 {


    public static void main(String[] args) {

        int[][] grid = new int[6][5];
        grid[0] = new int[]{1, 2, 3, 4, 5};
        grid[1] = new int[]{6, 7, 8, 9, 10};
        grid[2] = new int[]{11, 12, 13, 14, 15};
        grid[3] = new int[]{16, 17, 18, 19, 20};
        grid[4] = new int[]{21, 22, 23, 24, 25};
        grid[5] = new int[]{26, 27, 28, 29, 30};

        Utility.toString(grid);
        spiral(grid);

    }

    //	walk right, hDist, then down, then left, etc...
    static void spiral(int[][] data) {

        int row = data.length, col = (row > 0) ? data[0].length : 0;
        int hDist = col - 1;
        int vDist = row - 1;

        int[][] dir = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
        Coord start = new Coord(0, 0);

        // top and bottom of first iteration needs to be the same hDist
        start = walk(data, start, dir[0], hDist);
        for (int i = 0; i < row * col; i += 4) {
            start = walk(data, start, dir[1], vDist--);
            start = walk(data, start, dir[2], hDist--);
            start = walk(data, start, dir[3], vDist--);
            start = walk(data, start, dir[0], hDist--);
        }
    }

    private static Coord walk(int[][] data, Coord start, int[] dir, int bound) {

        int x = start.x, y = start.y;
        for (int i = 0; i < bound; i++) {
            System.out.println(data[x][y]);
            y += dir[0];
            x += dir[1];
        }

        return new Coord(x, y);
    }

    static private class Coord {
        int x;
        int y;

        Coord(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}
