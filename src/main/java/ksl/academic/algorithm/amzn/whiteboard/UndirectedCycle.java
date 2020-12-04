package ksl.academic.algorithm.amzn.whiteboard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class UndirectedCycle {

    public static void main(String... args) {


        Node a = new Node("A");
        Node b = new Node("B");
        Node c = new Node("C");
        Node d = new Node("D");

        Graph g = new Graph();
        g.add(a, b)
                .add(b, c)
                .add(c, d)
                .add(d, a);


        boolean hasCycle = false;
        for (Node n : g.getNodes()) {
            if (detectCycle(g, n, new HashSet<>())) {
                hasCycle = true;
                break;
            }
        }
        System.out.println(hasCycle);

        for (Node n : g.getNodes()) {
            List<Node> cycle = findCycle(g, n, new HashSet<>());
            if (!cycle.isEmpty()) {
                System.out.println(cycle);
            }
        }

    }

    static List<Node> findCycle(Graph g, Node n, Set<Node> visited) {

        // Objects.requireNonNull(g);

        Map<Node, Node> path = new HashMap<>();

        Deque<Node[]> stack = new ArrayDeque<>();
        stack.push(new Node[]{n, null}); //starting point and parent

        while (!stack.isEmpty()) {
            Node[] x = stack.pop();
            Node node = x[0], parent = x[1];

            if (visited.contains(node)) {
                return buildPath(path, node);
            }
            visited.add(node);

            for (Node adj : g.getAdjList(node)) {
                if (adj != parent) {
                    stack.push(new Node[]{adj, node});
                    path.put(adj, node);
                }
            }

        }
        return new ArrayList<>();
    }

    static List<Node> buildPath(Map<Node, Node> path, Node n) {
        List<Node> result = new LinkedList<>();
        Node x = path.get(n);
        while (x != null && x != n) {
            result.add(0, x);
            x = path.get(x);
        }

        result.add(0, x);
        return result;
    }


    static boolean detectCycle(Graph g, Node n, Set<Node> visited) {

        // Objects.requireNonNull(g);

        Deque<Node[]> stack = new ArrayDeque<>();
        stack.push(new Node[]{n, null}); //starting point and parent

        while (!stack.isEmpty()) {
            Node[] x = stack.pop();
            Node node = x[0], parent = x[1];

            if (visited.contains(node)) {
                return true;
            }
            visited.add(node);

            for (Node adj : g.getAdjList(node)) {
                if (adj != parent) {
                    stack.push(new Node[]{adj, node});
                }
            }

        }
        return false;
    }


    static class Graph {
        Map<Node, Set<Node>> adjList;

        public Graph() {
            adjList = new HashMap<>();
        }

        public Graph add(Node n, Node... adjNodes) {
            if (!adjList.containsKey(n)) {
                adjList.put(n, new HashSet<>());
            }

            for (Node adj : adjNodes) {
                adjList.get(n).add(adj);

                if (!adjList.containsKey(adj)) {
                    adjList.put(adj, new HashSet<>());
                }
                adjList.get(adj).add(n);
            }
            return this;
        }

        public Set<Node> getNodes() {
            return adjList.keySet();
        }

        public Set<Node> getAdjList(Node n) {
            if (!adjList.containsKey(n)) return new HashSet<>();
            return adjList.get(n);
        }
    }

    static class Node {
        String name;

        public Node(String name) {
            this.name = name;
        }

        public String toString() {
            return name;
        }
    }

}
