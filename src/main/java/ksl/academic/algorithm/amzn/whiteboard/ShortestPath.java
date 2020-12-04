package ksl.academic.algorithm.amzn.whiteboard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ShortestPath {

    public static void main(String[] args) {
        int[][] grid = {
                {0, 1, 1, 3},
                {4, 8, 8, 3},
                {3, 1, 1, 3},
                {9, 1, 3, 3}
        };

        List<Coord> result = findShortestPath(grid);
        for (Coord coord : result) {
            System.out.println(
                    String.format("(%1$d, %2$d)", coord.r, coord.c));
        }
    }

    // source node = cell with value = 0
    // target node = cell with value = 9
    // path cell with value = 1-8
    static List<Coord> findShortestPath(int[][] grid) {

        int row = grid.length, col = grid[0].length;
        int[] source = getCell(grid, row, col, 0); // assuming only 1
        int[] target = getCell(grid, row, col, 9); // assuming only 1

        // Distance
        int[][] distance = new int[row][col];
        setCell(distance, row, col, Integer.MAX_VALUE);

        // Path
        Map<Coord, Coord> path = new HashMap<>();

        // Visited, not needed since cost will be higher

        Deque<int[]> stack = new ArrayDeque<>();
        distance[source[0]][source[1]] = 0;
        stack.push(source);

        while (!stack.isEmpty()) {

            int[] coord = stack.pop();
            int r = coord[0], c = coord[1];

            for (int[] adj : getAdjCoords(grid, coord, row, col)) {
                int adjR = adj[0], adjC = adj[1];
                int cost = distance[r][c] + grid[adjR][adjC];
                if (cost < distance[adjR][adjC]) {
                    distance[adjR][adjC] = cost;
                    stack.push(new int[]{adjR, adjC});
                    path.put(new Coord(adj), new Coord(coord));
                }
            }

        }

        List<Coord> result = new LinkedList<>();
        Coord x = new Coord(target);
        while (x != null) {
            result.add(0, x);
            x = path.get(x);
        }
        return result;
    }

    static List<int[]> getAdjCoords(int[][] grid, int[] coord, int row, int col) {
        // right, up, left, down
        int[][] dir = new int[][]{{0, 1}, {-1, 0}, {0, -1}, {1, 0}};

        List<int[]> adjList = new ArrayList<>();
        for (int[] d : dir) {
            int r = coord[0] + d[0], c = coord[1] + d[1];
            if ((r >= 0 && r < row && c >= 0 && c < col) && grid[r][c] != 0) {
                adjList.add(new int[]{r, c});
            }
        }
        return adjList;
    }

    static int[] getCell(int[][] grid, int row, int col, int value) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if (grid[r][c] == value)
                    return new int[]{r, c};
            }
        }
        throw new RuntimeException("No cell with specified value: " + value);
    }

    static void setCell(int[][] grid, int row, int col, int value) {
        for (int r = 0; r < row; r++) {
            for (int c = 0; c < col; c++) {
                grid[r][c] = value;
            }
        }
    }

    static class Coord {
        int r;
        int c;

        public Coord(int[] coord) {
            this.r = coord[0];
            this.c = coord[1];
        }

        public int hashCode() {
            return Objects.hash(r, c);
        }

        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (this.getClass() != obj.getClass()) return false;

            Coord coord = (Coord) obj;
            return r == coord.r && c == coord.c;
        }
    }

}
