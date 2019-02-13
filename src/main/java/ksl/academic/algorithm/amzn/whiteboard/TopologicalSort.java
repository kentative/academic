package ksl.academic.algorithm.amzn.whiteboard;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TopologicalSort {
	
	public static void main(String[] args) {
		 Graph g = new Graph(6); 
	        g.addEdge(5, 2);
//	        g.addEdge(2, 5);
	        g.addEdge(5, 0); 
	        g.addEdge(4, 0); 
	        g.addEdge(4, 1); 
	        g.addEdge(2, 3); 
	        g.addEdge(3, 1); 
	  
	        System.out.println("Following is a Topological sort of the given graph"); 
	        System.out.println(topological(g)); 
	}

	public static List<Integer> topological(Graph g) {

		int n = g.getSize();
		boolean[] visited = new boolean[n];
		Deque<Integer> stack = new ArrayDeque<>(n);
		for (int v : g.getVertices()) {
			visit(g, v, visited, stack);
		}

		List<Integer> result = new ArrayList<>(n);
		while (!stack.isEmpty()) {
			result.add(stack.pop());
		}
		return result;
	}

	private static void visit(Graph g, int v, boolean[] visited, Deque<Integer> stack) {

		if (stack.contains(v))
			return;

		if (visited[v])
			throw new IllegalArgumentException("Not a DAG, has a cycle");

		visited[v] = true;

		for (int adj : g.getAdjList(v)) {
			visit(g, adj, visited, stack);
		}

		stack.push(v);
	}

	static class Graph {
		int size;
		Map<Integer, Set<Integer>> adjList;

		public Graph(int size) {
			this.size = size;
			adjList = new HashMap<>(size);

			for (int i = 0; i < size; i++) {
				adjList.put(i, new HashSet<>());
			}
		}

		public void addEdge(int from, int to) {
			if (!isValid(from) || !isValid(to))
				return;

			adjList.get(from).add(to);
		}

		public int getSize() {
			return size;
		}

		public Set<Integer> getAdjList(int v) {
			if (!isValid(v))
				return new HashSet<>();
			return adjList.get(v);
		}

		public Set<Integer> getVertices() {
			return adjList.keySet();
		}

		private boolean isValid(int v) {
			if (!adjList.containsKey(v)) {
				throw new IllegalArgumentException("Invalid vertex: " + v);
			}
			return true;
		}

	}

}
