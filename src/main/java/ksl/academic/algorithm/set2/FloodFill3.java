package ksl.academic.algorithm.set2;

import java.util.LinkedList;
import java.util.List;

public class FloodFill3 {

    public enum Row {A, B, C, D, E}

    public enum Direction {Left, Top, Right, Bottom}

    // don't need visited flag -- use color
    static boolean[][] visited;

    public static void main(String[] args) {

        boolean[][] bitmap = new boolean[4][4];
        bitmap[0][1] = true;
        bitmap[0][3] = true;

        bitmap[1][0] = true;
        bitmap[1][1] = true;
        bitmap[1][2] = true;

        bitmap[2][1] = true;

        bitmap[3][1] = true;
        bitmap[3][2] = true;

        printBitMap(bitmap);

        visited = new boolean[bitmap.length][bitmap[0].length];
        fill(bitmap, Row.D.ordinal(), 0, true);

        printBitMap(bitmap);


        boolean[][] bitmap2 = new boolean[4][4];
        bitmap2[0][1] = true;
        bitmap2[0][3] = true;

        bitmap2[1][0] = true;
        bitmap2[1][1] = true;
        bitmap2[1][2] = true;

        bitmap2[2][1] = true;

        bitmap2[3][1] = true;
        bitmap2[3][2] = true;
        printBitMap(fill3(bitmap2, Row.D.ordinal(), 0, true));

    }

    static class Coord {
        int r;
        int c;

        public Coord(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static boolean[][] fill3(boolean[][] grid, int r, int c, boolean color) {

        List<Coord> queue = new LinkedList<>();
        queue.add(new Coord(r, c));

        while (!queue.isEmpty()) {

            Coord x = queue.remove(0);

            if (grid[x.r][x.c] == color) continue;

            int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
            for (int[] d : dir) {
                Coord adj = new Coord(x.r + d[0], x.c + d[1]);
                if (isFeasible(grid, x.r + d[0], x.c + d[1], color)) {
                    grid[adj.r][adj.c] = color;
                    queue.add(adj);
                }
            }
        }

        // fill the color at the current cell
        grid[r][c] = color;
        return grid;
    }

    private static void fill(boolean[][] bitmap, int r, int c, boolean color) {

        int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for (int[] d : dir) {
            int[] next = {r + d[0], c + d[1]};
            if (isFeasible(bitmap, next[0], next[1], color)) {
                bitmap[r][c] = color;
                fill(bitmap, next[0], next[1], color);
            }
        }
        // fill the color at the current cell
        bitmap[r][c] = color;
    }

    private static boolean isFeasible(boolean[][] bitmap, int r, int c, boolean color) {

        boolean isInbound =
                r >= 0 && r < bitmap.length &&
                        c >= 0 && c < bitmap[0].length;

        // the adjacent value is the same, this cell isn't applicable
        return isInbound && bitmap[r][c] != color; // && !visited[r][c];
    }

    private static void printBitMap(boolean[][] bitmap) {
        StringBuilder sb = new StringBuilder();

        for (int r = 0; r < bitmap.length; r++) {
            for (int c = 0; c < bitmap.length; c++) {
                if (bitmap[r][c]) {
                    sb.append("1 ");
                } else {
                    sb.append("0 ");
                }
            }
            sb.append(System.lineSeparator());
        }
        System.out.println(sb.toString());
    }
}
