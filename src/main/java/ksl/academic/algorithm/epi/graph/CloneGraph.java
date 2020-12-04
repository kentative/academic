package ksl.academic.algorithm.epi.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Stack;

import ksl.academic.structure.graph.Edge;
import ksl.academic.structure.graph.Graph;
import ksl.academic.structure.graph.Vertex;

public class CloneGraph {

    static List<Vertex> visited = new ArrayList<>();

    public static void main(String[] args) {

        Vertex[] vertices = {
                new Vertex("A"), // 0
                new Vertex("B"), // 1
                new Vertex("C"), // 2
                new Vertex("D"), // 3
                new Vertex("E")  // 4
        };

        Edge[] edges = {
                new Edge(vertices[0], vertices[2], 10),  // AC
                new Edge(vertices[2], vertices[4], 3),   // CE
                new Edge(vertices[2], vertices[3], 4),   // CD
                new Edge(vertices[4], vertices[1], 1),   // EB
                new Edge(vertices[1], vertices[3], 1)}; // BD

        Graph graph = Graph.createDirectedGraph();
        graph.addEdge(edges);

//		Graph clone = cloneGraphDFS(graph);
//		
//		System.out.println(graph);
//		
//		System.out.println("CLONED DFS");
//		System.out.println(clone);
//		
//		visited.clear();
//		Graph cloneBFS = cloneGraphBFS(graph);
//		System.out.println("CLONED BFS");
//		System.out.println(cloneBFS);

        visited.clear();
        System.out.println("CLONED BFSITR");
        System.out.println(cloneDFSItr(graph));


    }

    private static Graph cloneGraphBFS(Graph graph) {

        Objects.requireNonNull(graph, "Source graph cannot be null");

        Graph clone = Graph.createDirectedGraph();

        PriorityQueue<Vertex> queue = new PriorityQueue<>();
        Vertex s = graph.getVertices().iterator().next();
        queue.add(s);
        while (!queue.isEmpty()) {

            Vertex x = queue.remove();
            if (visited.contains(x)) break;
            clone.add(new Vertex(x.name, x.weight));
            visited.add(x);

            for (Vertex adj : graph.getAdjacent(x)) {
                int w = graph.getEdgeWeight(x, adj);
                clone.addEdge(new Edge(x, adj, w));
                queue.add(adj);
            }
        }

        return clone;
    }


    private static Graph cloneGraphDFS(Graph graph) {

        visited = new ArrayList<>();
        if (graph == null) return null;
        Graph clone = Graph.createDirectedGraph();
        for (Vertex v : graph.getVertices()) {
            cloneDFS(graph, v, clone);
        }
        return clone;
    }

    private static void cloneDFS(Graph g, Vertex v, Graph c) {

        if (visited.contains(v)) return;

        c.add(new Vertex(v.name, v.weight));
        visited.add(v);

        for (Vertex adj : g.getAdjacent(v)) {
            int w = g.getEdgeWeight(v, adj);
            c.addEdge(new Edge(v, adj, w));
            cloneDFS(g, adj, c);
        }
    }

    private static Graph cloneDFSItr(Graph graph) {

        Graph clone = Graph.createDirectedGraph();

        // Initialization
        Stack<Vertex> stack = new Stack<>();
        Vertex s = graph.getVertices().iterator().next();
        stack.push(s);

        while (!stack.isEmpty()) {
            Vertex x = stack.pop();
            if (visited.contains(x)) continue;

            clone.add(new Vertex(x.name, x.weight));
            visited.add(x);
            for (Vertex adj : graph.getAdjacent(x)) {
                clone.addEdge(new Edge(x, adj, graph.getEdgeWeight(x, adj)));
                stack.push(adj);
            }
        }
        return clone;
    }

}

