package ksl.academic.algorithm.set2;

/**
 * First attempt
 *
 * @author Kent
 */
public class FloodFill0 {

    public enum Row {A, B, C, D, E}

    public enum Direction {Left, Top, Right, Bottom}


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

        floodFill(bitmap, Row.D.ordinal(), 0, true);

        printBitMap(bitmap);

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

    public static void floodFill(boolean[][] bitmap, int r, int c, boolean color) {

        // check bitmap size (at least 1 cell)
        boolean[][] visited = new boolean[bitmap.length][bitmap[0].length];
        fill(bitmap, r, c, color, visited);
    }

    private static void fill(boolean[][] bitmap, int r, int c, boolean color, boolean[][] visited) {

        // we are using -1 values to indicate that the cell is not applicable
        if (r < 0 || c < 0) {
            return;
        }
        System.out.println("filling " + r + " " + c);
        // already visited
        if (visited[r][c]) {
            return;
        }

        visited[r][c] = true;

//		int[][] dir = {{0, 1},  {0,-1}, {-1,0}, {1,0}};		
//		for (int[] d : dir) {
//			int[] cell = isFeasible(bitmap, r+d[0], c+d[1], bitmap[r][c]);
//			fill(bitmap, cell[0], cell[1], color, visited);
//		}

        for (Direction d : Direction.values()) {
            int[] cell = getAdjacent(bitmap, r, c, d, bitmap[r][c]);
            fill(bitmap, cell[0], cell[1], color, visited);
        }

        // fill the color at the current cell
        bitmap[r][c] = color;

    }

    private static int[] isFeasible(boolean[][] bitmap, int r, int c, boolean color) {
        int[] cell = new int[2];

        int rMax = bitmap.length - 1;
        int cMax = bitmap[0].length - 1;
        r = (r < 0) ? 0 : r;
        r = (r > rMax) ? rMax : r;
        c = (c < 0) ? 0 : c;
        r = (c > cMax) ? cMax : c;

        // the adjacent value is the same, this cell isn't applicable
        if (bitmap[r][c] != color) {
            cell[0] = -1;
            cell[1] = -1;
        } else {
            cell[0] = r;
            cell[1] = c;
        }

        return cell;
    }

    private static int[] getAdjacent(boolean[][] bitmap, int r, int c, Direction d, boolean color) {

        int[] cell = new int[2];
        switch (d) {
            case Left:
                r--;
                break;
            case Right:
                r++;
                break;
            case Top:
                c--;
                break;
            case Bottom:
                c++;
                break;
        }

        int rMax = bitmap.length - 1;
        int cMax = bitmap[0].length - 1;
        r = (r < 0) ? 0 : r;
        r = (r > rMax) ? rMax : r;
        c = (c < 0) ? 0 : c;
        r = (c > cMax) ? cMax : c;


        // if color doesn't match with original color or
        // the adjacent value is the same, this cell isn't applicable
        if (bitmap[r][c] != color) {
            cell[0] = -1;
            cell[1] = -1;
        } else {
            cell[0] = r;
            cell[1] = c;
        }

        return cell;
    }

}
