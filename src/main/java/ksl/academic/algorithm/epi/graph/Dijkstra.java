package ksl.academic.algorithm.epi.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.PriorityQueue;

import com.google.common.base.Preconditions;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;

/**
 * Shortest paths between nodes in a graph
 *
 * initial node
 * distance of node Y
 * 
 * Setup
 * 1. Marked all nodes unvisited. Unvisited set
 * 2. Assign distance value to every node, 0 and infinite
 * 
 * Iterate
 * 3. Current node, calculate distance for all neighboring nodes, use minimum value
 * 4. When all neighboring nodes are visited, mark current node as visited and remove it from unvisited set
 * 5. Finished if destination node is marked as visited, or the smallest value in unvisited set is infinite.
 * 6. select unvisited node that's marked with smallest distance as current node and iterate
 * 
 */
public class Dijkstra {
	
	// Dijkstra with Guava
	public static void main(String[] args) {
		
		MutableValueGraph<Node, Integer> graph = ValueGraphBuilder
				.directed()
				.allowsSelfLoops(false)
				.build();
		
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		Node f = new Node("F");
		Node g = new Node("G");
		Node h = new Node("H");
		Node i = new Node("I");
		
		// Cracking the code interview 6th Edition, page:634
		graph.putEdgeValue(a, b, 5);
		graph.putEdgeValue(a, c, 3);
		graph.putEdgeValue(a, e, 2);
		graph.putEdgeValue(b, d, 2);
		graph.putEdgeValue(c, b, 1);
		graph.putEdgeValue(c, d, 1);
		graph.putEdgeValue(d, a, 1);
		graph.putEdgeValue(d, g, 2);
		graph.putEdgeValue(d, h, 1);
		graph.putEdgeValue(e, a, 1);
		graph.putEdgeValue(e, h, 4);
		graph.putEdgeValue(e, g, 7);
		graph.putEdgeValue(f, b, 3);
		graph.putEdgeValue(f, g, 1);
		graph.putEdgeValue(g, c, 3);
		graph.putEdgeValue(g, i, 2);
		graph.putEdgeValue(h, c, 2);
		graph.putEdgeValue(h, f, 2);
		graph.putEdgeValue(h, g, 2);
		
		System.out.println(graph);
		
		List<Node> shortestPath = shortestPath(graph, a, i);
		for (Node s : shortestPath) {
			System.out.println(s);
		}
		
	}
	
	static List<Node> shortestPath(ValueGraph<Node, Integer> g, Node s, Node t) {
		
		// Initialization - set all node value to infinity
		Preconditions.checkNotNull(g);
		int size = g.nodes().size();
		PriorityQueue<Node> minQ = new PriorityQueue<>(size, new NodeComparator());
		s.setValue(0);
		minQ.add(s);
		Map<Node, Node> path = new HashMap<>(size);
		
		while (!minQ.isEmpty()) {
			Node x = minQ.remove();
			if (x.equals(t)) break;
			if (x.isVisited()) continue;
			
			for (Node adj : g.adjacentNodes(x)) {
				
				if (!g.hasEdgeConnecting(x, adj)) continue;
				
				Optional<Integer> edgeValue = g.edgeValue(x, adj);
				if (edgeValue.isEmpty()) 
					throw new IllegalArgumentException("Must specify edge weight");
				
				int distance = x.getValue() + edgeValue.get();
				if (distance < adj.getValue()) {
					adj.setValue(distance);
					path.put(adj, x);
					minQ.add(adj);
				}
			}
			x.setVisited(true);
		}
		
		List<Node> result = new ArrayList<>(size);
		for (Node x = t; x != null; x = path.get(x)) {
			result.add(0, x);
		}
		
		return result;
		
	}
	
	
	static class NodeComparator implements Comparator<Node> {

		@Override
		public int compare(Node n1, Node n2) {
			Objects.requireNonNull(n1);
			Objects.requireNonNull(n2);			
			return n1.value - n2.getValue();
		}
		
	}
	
	static class Node {
		
		int value;
		String name;
		boolean visited;
		
		public Node(String name) {
			this.name = name;
			this.value = Integer.MAX_VALUE;
		}
		
		public String toString() {
			return name;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}
	}

}
