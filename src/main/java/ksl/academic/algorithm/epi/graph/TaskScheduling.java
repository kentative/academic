package ksl.academic.algorithm.epi.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class TaskScheduling {

	public static void main(String[] args) {
		
		Vertex[] v = {
			new Vertex("5"),  // 2 7
			new Vertex("7"),  // 3 4 7
			new Vertex("3"),  // 1 4 6
			new Vertex("2"),  // 0
			new Vertex("8"),  // 4 5
			new Vertex("9"),  // 5
			new Vertex("10"), // 6
			new Vertex("11")  // 7 0 5 6
		};
		
		Edge[] e = {
			new Edge(v[1], v[4]),
			new Edge(v[1], v[6]),
			new Edge(v[2], v[7]),
			new Edge(v[3], v[4]),
			new Edge(v[3], v[7]),
			new Edge(v[4], v[5]),
			new Edge(v[7], v[0]),
			new Edge(v[7], v[5]),
			new Edge(v[7], v[6])
		}; 
		
		Graph g = new Graph(v, e);
		System.out.println(g);
		
		List<Vertex> path = topological(g);
		for (Vertex p : path) {
			System.out.println(p.getId());
		}
	}
	
	
	static List<Vertex> topological(Graph g) {
		
		List<Vertex> result = new LinkedList<>();
		// nodes with no incoming edge must be first!, (i.e., can't start with node 2)
		for (Vertex x : g.getVertices()) {
			System.out.println("Starts visiting: " + x);
			if (!visit(g, x, result)) {
				return null;
			}
			System.out.println("Finished visiting: " + x);
		}
		
		return result;
	}
	
	static boolean visit(Graph g, Vertex v, List<Vertex> result) {
		
		if (v.isCompleted) return true;
		if (v.isVisited) return false;
		v.setVisited(true);
		
		for (Vertex adj : g.getAdj(v)) {
			visit(g, adj, result);
		}
		v.setCompleted(true);
		System.out.println("adding result after visiting all ancestors: " + v);
		result.add(0, v);
		return true;
	}
	
	static class Graph {
		
		Map<Vertex, List<Vertex>> adjList;
		Map<String, Edge> edgeList;
		
		public Graph(Vertex[] vertices, Edge[] edges) {
			this.adjList = new HashMap<>();
			this.edgeList = new HashMap<>();
			
			for (Vertex v : vertices) {
				adjList.put(v, new ArrayList<>());
			}
			
			for (Edge e : edges) add(e);
		}
		
		public Set<Vertex> getVertices() {
			return adjList.keySet();
		}

		public List<Vertex> getAdj(Vertex v) {
			return adjList.get(v);
		}

		private void add(Edge e) {
			Objects.requireNonNull(e, "Edges can't be null");
			String edgeId = e.getId();
			if (edgeList.containsKey(edgeId)) return;
			
			add(e.getSource(), e.getTarget());
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
			return edgeList.get(edgeId).getWeight();
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
				sb.append(TAB + v.getId() + ": ");

				for (Vertex adj : adjList.get(v)) {
					sb.append(adj.getId() + " ");
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
		boolean isCompleted;
		boolean isVisited;
		
		public Vertex(String id) {
			this.id = id;
			this.weight = Integer.MAX_VALUE;
		}
		
		public String getId() { return id; }
		public void setId(String id) {
			this.id = id;
		}
		public int getWeight() {
			return weight;
		}
		public void setWeight(int weight) {
			this.weight = weight;
		}
		
		public String toString() {
			return id + ":" + weight;
		}
		
		@Override
		public int compareTo(Vertex o) {
			if (o == null) return -1;
			return weight - o.getWeight();
		}
		
		public boolean equals(Object o) {
			if (o == this) return true;
			if (this.getClass() != o.getClass()) return false;
			
			return Objects.equals(id,  ((Vertex) o ).getId());
		}
		
		public int hashCode() {
			return Objects.hash(id);
		}

		public boolean isVisited() {
			return isVisited;
		}

		public void setVisited(boolean isVisited) {
			this.isVisited = isVisited;
		}

		public void setCompleted(boolean isCompleted) {
			this.isCompleted = isCompleted;
		}

		public boolean isCompleted() {
			return isCompleted;
		}
	}
	
	static class Edge {
		int weight;
		Vertex source;
		Vertex target;	
		String id;

		public Edge(Vertex source, Vertex target) {
			this.source = source;
			this.target = target;
			this.weight = 0;
			this.id = genId(source, target);
		}

		
		public Edge(Vertex source, Vertex target, int weight) {
			this.source = source;
			this.target = target;
			this.weight = weight;
			this.id = genId(source, target);
		}

		public static String genId(Vertex s, Vertex t) {
			return s.getId() + t.getId();
		}
		
		public String toString() {
			return "|"+id+"|="+weight;
		}
		
		public int getWeight() {
			return weight;
		}

		public Vertex getSource() {
			return source;
		}

		public Vertex getTarget() {
			return target;
		}

		public String getId() {
			return id;
		}

	}
}

