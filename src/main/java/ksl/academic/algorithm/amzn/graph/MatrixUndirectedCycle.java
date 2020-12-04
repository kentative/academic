package ksl.academic.algorithm.amzn.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class MatrixUndirectedCycle {


    public static void main(String[] args) {

        int[][] undirected = new int[][]{
                {0, 1, 0},
                {1, 0, 1},
                {0, 1, 0}
        };

        System.out.println(hasCycle(undirected));
    }

    public static boolean hasCycle(int[][] graph) {

        if (graph == null || graph.length == 0) return false;

        int vCount = graph.length;
        boolean[] visited = new boolean[vCount];
        for (int from = 0; from < vCount; from++) {
            for (int to = 0; to < vCount; to++) {
                if (graph[from][to] == 0 || visited[from]) continue;
                if (containsCycle(graph, from, -1, visited)) return true;
            }
        }
        return false;
    }

    private static boolean containsCycle(int[][] graph, int from, int parent, boolean[] visited) {

        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{from, parent});

        while (!stack.isEmpty()) {
            int[] vertices = stack.pop();
            int f = vertices[0], p = vertices[1];

            if (visited[f]) return true;

            for (int adj : getAdjNodes(graph, f)) {
                if (adj != p) stack.push(new int[]{adj, f});
            }
            visited[f] = true;
        }
        return false;
    }

    private static List<Integer> getAdjNodes(int[][] graph, int from) {

        int vCount = graph.length;
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < vCount; i++) {
            if (graph[from][i] != 0) {
                result.add(i);
            }
        }
        return result;
    }
}