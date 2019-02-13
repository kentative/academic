package ksl.academic.algorithm.amzn.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class Mst {
	
	public static void main(String[] args) {
		int graph[][] = new int[][] { 
			{ 0, 2, 0, 6, 0 }, 
			{ 2, 0, 3, 8, 5 }, 
			{ 0, 3, 0, 0, 7 }, 
			{ 6, 8, 0, 0, 9 },
			{ 0, 5, 7, 9, 0 } 
		};
		
		Node a = new Node("a");
		Node b = new Node("b");
		Node c = new Node("c");
		Node d = new Node("d");
		Node e = new Node("e");

		Node[] nodes = new Node[] {a, b, c, d, e};
		Graph g = new Graph(Arrays.asList(nodes));
		g.addEdge(a, b, 2);
		g.addEdge(a, d, 6);
		g.addEdge(b, a, 2);
		g.addEdge(b, c, 3);
		g.addEdge(b, d, 8);
		g.addEdge(b, e, 5);
		g.addEdge(c, b, 3);
		g.addEdge(c, e, 7);
		g.addEdge(d, a, 6);
		g.addEdge(d, b, 8);
		g.addEdge(d, e, 9);
		g.addEdge(e, b, 5);
		g.addEdge(e, c, 7);
		g.addEdge(e, d, 9);
		
		mst(g, a);
	}
	
	
	public static Node mst(Graph g, Node s) {
		Map<Node, Node> path = new HashMap<>();
		
		s.weight=0;
		PriorityQueue<Node> minQ = new PriorityQueue<>();
		for (Node v : g.getVertices()) minQ.offer(v);
		
		Node u = null;
		while (!minQ.isEmpty()) {
		
			u = minQ.poll();
			
			for (Node v : g.getAdjList(u)) {
				if (minQ.contains(v)) {
					int weight = u.weight + g.getWeight(u,v);
					if (weight < v.weight) {
						minQ.remove(v);
						v.weight = weight;
						minQ.add(v);
						path.put(v, u);
					}
				}
			}
		}
		
		// return root
	}

	static class Graph {
		
		Map<Node, Set<Node>> adjList;
		Map<Node, Map<Node, Integer>> edgeWeight;
		
		public Graph(List<Node> vertices) {
			this.adjList = new HashMap<>();
			this.edgeWeight = new HashMap<>();
			for (Node n : vertices) {
				adjList.put(n, new HashSet<>());
				edgeWeight.put(n, new HashMap<>());
			}
		}
		
		public void addEdge(Node s, Node t, int weight) {
			adjList.get(s).add(t);
			edgeWeight.get(s).put(t, weight);
			
		}

		public int getWeight(Node u, Node v) {
			return edgeWeight.get(u).get(v);
		}

		public Set<Node> getAdjList(Node u) {
			return adjList.get(u);
		}

		public Set<Node> getVertices() {
			return adjList.keySet();
		}
		
	}
	
	static class Node {
		
		int weight;
		String name;
		
	
		public Node(String name) {
			this.name = name;
		}
		
		public boolean equals(Object obj) {
			
			if (this == obj) return true;
			if ( !(obj instanceof Node)) return false;
			
			Node n = (Node) obj;
			return name.equals(n.name);
		}
		
		public int hashCode() {
			return Objects.hash(name);
		}
	}
	
	static class Edge {
		Node source;
		Node target;
		int weight;
		
		public Edge(Node source, Node target, int weight) {
			this.source = source;
			this.target = target;
			this.weight = weight;
		}
		
		public boolean equals(Object obj) {
			if (this == obj) return true;
			if (!(obj instanceof Edge)) return false;
			
			Edge other = ((Edge) obj);
			return source.equals(other.source) && target.equals(other.target);
		}
		
		public int hashCode() {
			return Objects.hash(source, target);
		}
	}

}
