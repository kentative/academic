package ksl.academic.algorithm.amzn.graph;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UndirectedGraphCycle {

	public static void main(String[] args) {
		
		Node a = new Node("A");
		Node b = new Node("B");
		Node c = new Node("C");
		Node d = new Node("D");
		Node e = new Node("E");
		Node f = new Node("F");
		
		Graph g = new Graph();
		g.addEdge(a, b);
		g.addEdge(b, c);
//		g.addEdge(c, d);
		
		g.addEdge(d, e);
		g.addEdge(e, f);
		g.addEdge(f, a);
//		g.addEdge(a, d);
		
		System.out.println(g);
		System.out.println(hasCycle(g));
		
	}
	
	public static boolean hasCycle(Graph g) {
		
		Set<Node> visited = new HashSet<>();
		for (Node n : g.getNodes()) {
			if (visited.contains(n)) continue;
			
			// [0] - node, [1] - parent
			Deque<Node[]> queue = new ArrayDeque<>();
			queue.offer(new Node[] {n, null});
			
			while (!queue.isEmpty()) {
				Node[] x = queue.poll();
				Node node = x[0];
				Node parent = x[1];
				
				if (visited.contains(node)) return true;
				visited.add(node);
				for (Node adj : g.getAdjList(node)) {
					if (adj != parent) queue.add(new Node[] {adj, node});
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
		
		public Set<Node> getAdjList(Node x) {
			return adjList.get(x);
		}

		public Set<Node> getNodes() {
			return adjList.keySet();
		}
		
		public void addEdge(Node a, Node b) {
			if (!adjList.containsKey(a)) {
				adjList.put(a, new HashSet<Node>());
			}
			adjList.get(a).add(b);
			
			if (!adjList.containsKey(b)) {
				adjList.put(b, new HashSet<Node>());
			}
			adjList.get(b).add(a);
		}
		
		public String toString() {
			StringBuilder sb = new StringBuilder();
			for (Node k : adjList.keySet()) {
				sb.append(k + " - " + Arrays.toString(adjList.get(k).toArray(new Node[] {})));
				sb.append(System.lineSeparator());
			}
			return sb.toString();
		}
	}
	
	static class Node {
		String name;
		
		public String toString() {
			return name;
		}
		
		public Node(String name) {
			this.name = name;
		}
	}
}
