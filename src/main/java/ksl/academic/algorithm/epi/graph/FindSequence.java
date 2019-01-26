package ksl.academic.algorithm.epi.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

public class FindSequence {

	public static void main(String[] args) {
		
		
		Set<String> dictionary = new HashSet<>();
		dictionary.add("fear");
		dictionary.add("pear");
		dictionary.add("gear");
		dictionary.add("sear");
		dictionary.add("dear");
		dictionary.add("deer");
		dictionary.add("peer");
		dictionary.add("beer");
		dictionary.add("beet");
		dictionary.add("feet");
		dictionary.add("feat");
		Graph g = new Graph(dictionary);
		System.out.println(g);
		
		Vertex s = g.getVertex("pear");
		Vertex t = g.getVertex("feet");
		
		List<Vertex> path = findSequence(g, s, t);
		for (Vertex p : path) {
			System.out.println(p.id);
		}

	}
	
	static List<Vertex> findSequence(Graph g, Vertex s, Vertex t) {
		
		Map<Vertex, Vertex> path = new HashMap<>();
		PriorityQueue<Vertex> minQ = new PriorityQueue<>();
		minQ.add(s);
		s.weight = 0;
		
		while (!minQ.isEmpty()) {
			Vertex x = minQ.remove();
			if (x.equals(t)) break;
			
			for (Vertex adj : g.getAdj(x)) {
				
				int w = x.weight + g.getWeight(x, adj);
				if (w < adj.weight) {
					adj.weight = w;
					path.put(adj, x);
					minQ.add(adj);
				}
			}
		}
		
		List<Vertex> result = new ArrayList<>();
		for (Vertex x = t; x != null; x = path.get(x)) {
			result.add(x);
		}
		Collections.reverse(result);
		return result;
	}
	
	
	static class Graph {
		
		Map<Vertex, List<Vertex>> adjList;
		Map<String, Edge> edgeList;
		Set<String> dictionary;
		
		public Graph(Vertex[] vertices, Edge[] edges) {
			this.adjList = new HashMap<>();
			this.edgeList = new HashMap<>();
			
			for (Vertex v : vertices) {
				adjList.put(v, new ArrayList<>());
			}
			
			for (Edge e : edges) add(e);
		}
		
		public Graph(Set<String> dictionary) {
			this.adjList = new HashMap<>();
			this.edgeList = new HashMap<>();
			
			// Build the graph based on the dictionary
			// Each word is a node
			// an edge exists between two node if their hamming distance is 1
			Iterator<String> iterator = dictionary.iterator();
			while (iterator.hasNext()) {
				
				String word = iterator.next();
				char[] buffer = word.toCharArray();
				for (int i = 0; i < buffer.length; i++) {
					for (int j = 0; j < 26; j++) {
						buffer[i] = (char) ('a' + j);
						
						// include only matching entries in Dictionary
						if (dictionary.contains(String.valueOf(buffer))) {
							Vertex s = add(getVertex(word));
							Vertex t = add(getVertex(String.valueOf(buffer)));
							if (!s.equals(t)) add(new Edge(s, t, 1));
						}
							
					}
					// Reset the word, only change 1 char 
					buffer = word.toCharArray();
				}
			}
			
		}

		private Vertex getVertex(String word) {
			for (Vertex x : adjList.keySet()) {
				if (x.id.equals(word)) {
					return x;
				}
			}
			return new Vertex(word);
		}

		public List<Vertex> getAdj(Vertex v) {
			return adjList.get(v);
		}

		private void add(Edge e) {
			Objects.requireNonNull(e, "Edges can't be null");
			String edgeId = e.id;
			if (edgeList.containsKey(edgeId)) return;
			
			add(e.source, e.target);
			edgeList.put(edgeId, e);
		}

		
		/**
		 * Adding vertex and its neighbor, directional. 
		 * Must add both direction for undirected graph.
		 * @param v
		 * @param t
		 */
		private Vertex add(Vertex v, Vertex... t) {
			
			if (!adjList.containsKey(v)) {
				adjList.put(v, new ArrayList<>());
			}
			adjList.get(v).addAll(Arrays.asList(t));
			return v;
		}
		
		public int getWeight(Vertex s, Vertex t) {
			String edgeId = Edge.genId(s, t);
			return edgeList.get(edgeId).weight;
		}
		
		/**
		 * The string representation of this graph:
		 *  
		 * A: C
		 * B: D
		 * C: E D
		 * D: 
		 * E: B 
		 */
		public String toString() {
			final String TAB = "   ";
			StringBuilder sb = new StringBuilder();
			Vertex[] vertices = adjList.keySet().toArray(new Vertex[] {});
			Arrays.sort(vertices);		
			
			sb.append("Adjacency List: \n");
			for (Vertex v : vertices) {
				sb.append(TAB + v.id + ": ");

				for (Vertex adj : adjList.get(v)) {
					sb.append(adj.id + " ");
				}
				sb.append("\n");
			}
			
			
			sb.append("Edge List: \n");
			for (Edge e : edgeList.values()) {
				sb.append(TAB + e).append(System.lineSeparator());
			}
			return sb.toString();		
		}
	}
	
	static class Vertex implements Comparable<Vertex>{
		
		String id;
		int weight;
		
		public Vertex(String id) {
			this.id = id;
			this.weight = Integer.MAX_VALUE;
		}
		
		public String toString() {
			return id + ":" + weight;
		}
		
		@Override
		public int compareTo(Vertex o) {
			if (o == null) return -1;
			return weight - o.weight;
		}
		
		public boolean equals(Object o) {
			if (o == this) return true;
			if (o == null) return false;
			if (this.getClass() != o.getClass()) return false;
			
			return Objects.equals(id,  ((Vertex) o ).id);
		}
		
		public int hashCode() {
			return Objects.hash(id);
		}
	}
	
	static class Edge {
		int weight;
		Vertex source;
		Vertex target;	
		String id;
		
		public Edge(Vertex source, Vertex target, int weight) {
			this.source = source;
			this.target = target;
			this.weight = weight;
			this.id = genId(source, target);
		}

		public static String genId(Vertex s, Vertex t) {
			return s.id + t.id;
		}
		
		public String toString() {
			return "|"+id+"|="+weight;
		}
		
	}
}

