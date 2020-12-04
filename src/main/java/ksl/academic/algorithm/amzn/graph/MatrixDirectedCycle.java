package ksl.academic.algorithm.amzn.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MatrixDirectedCycle {


    public static void main(String[] args) {
        int[][] directed = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 0, 0}
        };

        System.out.println(hasCycle(directed));
    }


    public static boolean hasCycle(int[][] directed) {

        if (directed == null || directed.length == 0) return false;

        int vCount = directed.length;
        boolean[] visited = new boolean[vCount];

        for (int from = 0; from < vCount; from++) {
            for (int to = 0; to < vCount; to++) {
                // for adjMatrix, make sure to check if the node is valid (!=0)
                if (directed[from][to] == 0 || visited[from]) continue;
                if (containsCycle(directed, from, visited)) return true;
            }
        }
        return false;
    }

    private static boolean containsCycle(int[][] directed, int from, boolean[] visited) {

        // DFS to traverse and detect cycle
        // if node is already visited, then it's a cycle
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(from);

        while (!stack.isEmpty()) {
            int f = stack.pop();

            if (visited[f]) {
                System.out.println("alright visited vertex: " + f);
                return true;
            }

            visited[f] = true;
            for (int adj : getAdjNodes(directed, f)) {
                stack.push(adj);
            }
        }
        return false;
    }

    private static List<Integer> getAdjNodes(int[][] g, int from) {
        int vCount = g.length;

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < vCount; i++) {
            if (g[from][i] == 1) {
                result.add(i);
                System.out.println(from + " adj: " + i);
            }
        }
        return result;
    }
}
