package ksl.academic.algorithm.sim;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;

import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraphBuilder;


// https://www.interviewcake.com/question/java/mesh-message
public class MeshMessage {

    public static void main(String[] args) {

        Map<String, String[]> network = new HashMap<String, String[]>() {{
            put("Min", new String[]{"William", "Jayden", "Omar"});
            put("William", new String[]{"Min", "Noam"});
            put("Jayden", new String[]{"Min", "Amelia", "Ren", "Noam"});
            put("Ren", new String[]{"Jayden", "Omar"});
            put("Amelia", new String[]{"Jayden", "Adam", "Miguel"});
            put("Adam", new String[]{"Amelia", "Miguel", "Sofia", "Lucas"});
            put("Miguel", new String[]{"Amelia", "Adam", "Liam", "Nathan"});
            put("Noam", new String[]{"Nathan", "Jayden", "William"});
            put("Omar", new String[]{"Ren", "Min", "Scott"});
        }};

        findShortestPath(network, "Jayden", "Nathan").forEach(System.out::println);

    }

    private static Map<String, Node> nodeMap;

    static List<Node> findShortestPath(Map<String, String[]> network, String source, String target) {
        if (network == null || source == null || target == null) {
            throw new IllegalArgumentException("Parameters can't be null");
        }

        if (source.equals(target)) return Arrays.asList(new Node(source));

        nodeMap = new HashMap<>();
        for (String id : network.keySet()) {
            if (nodeMap.get(id) == null) {
                nodeMap.put(id, new Node(id));
            }
            for (String adj : network.get(id)) {
                if (nodeMap.get(adj) == null) {
                    nodeMap.put(adj, new Node(adj));
                }
            }
        }

        MutableValueGraph<Node, Integer> g = buildGraph(network);
        return computePath(g, nodeMap.get(source), nodeMap.get(target));
    }


    private static List<Node> computePath(MutableValueGraph<Node, Integer> g, Node s, Node t) {

        PriorityQueue<Node> minQ = new PriorityQueue<>(g.nodes().size(), (n1, n2) -> n1.getValue() - n2.getValue());

        s.setValue(0);
        minQ.add(s);
        Map<Node, Node> result = new HashMap<>(g.nodes().size());
        while (!minQ.isEmpty()) {
            Node x = minQ.remove();
            if (x.isVisited()) continue;
            if (x.equals(t)) break;

            for (Node adj : g.adjacentNodes(x)) {
                if (g.hasEdgeConnecting(x, adj)) {
                    int cost = x.getValue() + g.edgeValueOrDefault(x, adj, Integer.MAX_VALUE);
                    if (cost < adj.getValue()) {
                        adj.setValue(cost);
                        result.put(adj, x);
                    }
                }
                minQ.add(adj);
            }
            x.setVisited(true);
        }

        List<Node> path = new LinkedList<>();
        for (Node x = t; x != null; x = result.get(x)) path.add(0, x);
        return path;
    }


    private static MutableValueGraph<Node, Integer> buildGraph(Map<String, String[]> network) {
        MutableValueGraph<Node, Integer> graph = ValueGraphBuilder.directed().allowsSelfLoops(false).build();
        for (String id : network.keySet()) {
            for (String adj : network.get(id)) {
                graph.putEdgeValue(nodeMap.get(id), nodeMap.get(adj), 1);
            }
        }
        return graph;
    }


    static class Node {
        private String id;
        private int value;
        private boolean visited;

        public Node(String id) {
            this.id = id;
            this.value = Integer.MAX_VALUE;
        }

        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (obj == null) return false;
            if (obj.getClass() != this.getClass()) return false;

            return Objects.equals(((Node) obj).id, id);
        }

        public String toString() {
            return id;
        }

        public int hashCode() {
            return Objects.hash(id);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

    }

}
