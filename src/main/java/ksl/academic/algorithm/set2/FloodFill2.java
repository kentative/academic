package ksl.academic.algorithm.set2;

public class FloodFill2 {

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

    }

    private static void fill(boolean[][] bitmap, int r, int c, boolean color) {

        // fill the color at the current cell
        bitmap[r][c] = color;

        int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for (int[] d : dir) {
            int[] next = {r + d[0], c + d[1]};
            if (isFeasible(bitmap, next[0], next[1], color)) {
                visited[next[0]][next[1]] = true;
                fill(bitmap, next[0], next[1], color);
            }
        }
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
