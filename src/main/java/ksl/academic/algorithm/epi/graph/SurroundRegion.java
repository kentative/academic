package ksl.academic.algorithm.epi.graph;

import java.util.ArrayList;
import java.util.List;

public class SurroundRegion {

    public static void main(String[] args) {

        char B = 'B', W = 'W';
        Vertex[][] grid = {
                {new Vertex(B), new Vertex(B), new Vertex(B), new Vertex(B), new Vertex(B)},
                {new Vertex(B), new Vertex(W), new Vertex(W), new Vertex(B), new Vertex(B)},
                {new Vertex(B), new Vertex(B), new Vertex(B), new Vertex(B), new Vertex(B)},
                {new Vertex(B), new Vertex(W), new Vertex(W), new Vertex(W), new Vertex(B)},
                {new Vertex(B), new Vertex(B), new Vertex(B), new Vertex(W), new Vertex(W)}
        };

        printGrid(grid);


        System.out.println("Enclosed....");
//		surroundRegion(grid);
//		printGrid(grid);

        System.out.println("My Enclosed....");
        walkDFS(grid);
        printGrid(grid);
    }

    static void printGrid(Vertex[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }

    }

    static Vertex[][] surroundRegion(Vertex[][] grid) {
        if (grid == null) return null;

        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                if (!grid[i][j].isVisited() && grid[i][j].color == 'W')
                    walkBFS(grid, i, j);
            }
        }

        return grid;
    }

    private static void walkBFS(Vertex[][] grid, int i, int j) {
        List<Coord> queue = new ArrayList<>();
        Coord c = new Coord(i, j);
        queue.add(c);
        grid[c.x][c.y].setVisited(true);

        boolean shouldMark = true;
        int idx = 0;
        int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        while (idx < queue.size()) {
            Coord curr = queue.get(idx++);
            if (curr.x == 0 || curr.x == grid.length - 1 ||
                    curr.y == 0 || curr.y == grid[0].length - 1) {
                shouldMark = false;
            } else {
                for (int[] d : dir) {
                    Coord next = new Coord(curr.x + d[0], curr.y + d[1]);
                    Vertex v = grid[next.x][next.y];
                    if (!v.isVisited() && v.color == 'W') { // feasible
                        v.setVisited(true);
                        queue.add(next);
                    }
                }
            }
        }

        if (shouldMark) {
            for (Coord coord : queue) {
                grid[coord.x][coord.y].setColor('B');
            }
        }

    }


    static void walkDFS(Vertex[][] grid) {

        int r = grid.length;
        int c = grid[0].length;

        // Mark all bordered W as visited
        for (int i = 0; i < r; i += r - 1) {
            for (int j = 0; j < c; j += c - 1) {
                mark(grid, i, j);
            }
        }

        // All non visited W are enclosed, update to 'B'
        for (int i = 1; i < r - 1; i++) {
            for (int j = 1; j < c - 1; j++) {
                Vertex v = grid[i][j];
                if (v.color == 'W' && !v.isVisited()) v.color = 'B';
            }
        }
    }

    static void mark(Vertex[][] grid, int i, int j) {

        if (!walkable(grid, i, j)) return;

        Vertex v = grid[i][j];
        v.setVisited(true);
        if (v.color == 'B') return;

        int[][] dir = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        for (int[] d : dir) mark(grid, i + d[0], j + d[1]);

    }

    private static boolean walkable(Vertex[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && !grid[i][j].visited;
    }


    static class Vertex {
        char color;
        boolean visited;

        public Vertex(char color) {
            this.color = color;
        }

        public void setVisited(boolean b) {
            this.visited = b;

        }

        public String toString() {
            return String.valueOf(color);
        }

        public char getColor() {
            return color;
        }

        public void setColor(char color) {
            this.color = color;
        }

        public boolean isVisited() {
            return visited;
        }
    }

}



