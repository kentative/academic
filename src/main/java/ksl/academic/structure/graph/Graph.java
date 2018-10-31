package ksl.academic.structure.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.Test;

/**
 * The Class Graph.
 *
 * @author Kent
 * @see TaskScheduling for barebone Graph implementation without Guava
 */
/**
 * @author Kent
 *
 */
public class Graph {
	
	/** The is directed. */
	private boolean isDirected; 
	
	/** 
	 * The adjacency list of this graph.
	 * key - the vertex
	 * value - the list of vertices connected to the vertex
	 *   
	 **/
	Map<Vertex, Collection<Vertex>> adjList;

	/** The list of edges  key - the edge id - constructed from the id of vertices  value - edge. */
	Map<String, Edge> edges;

	/**
	 * Static factory builder to create a directed graph.
	 *
	 * @return an empty directed graph
	 */
	public static Graph createDirectedGraph() {
		return new Graph(true);
	}
	
	/**
	 * Static factory builder to create an undirected graph.
	 *
	 * @return an empty undirected graph
	 */
	public static Graph createUndirectedGraph() {
		return new Graph(false);
	}
	
	/**
	 * Constructs a empty graph.
	 *
	 * @param isDirected true if the 
	 */
	private Graph(boolean isDirected) {
		adjList = new HashMap<>();
		edges = new HashMap<>();
		this.isDirected = isDirected;
	}
	
	/**
	 * Add a vertex and its neighbor. 
	 * @param v - the vertex to be added
	 */
	public void add(Vertex v) {
		Objects.requireNonNull(v, "Vertex cannot be null.");
		if (!adjList.containsKey(v)) {
			adjList.put(v, new ArrayList<>());
		}
	}
	
	/**
	 * Add a vertex and its neighbor. 
	 * @param v - the vertex to be added
	 * @param adj - optional, the list adjacent vertices 
	 */
	public void addAdjacent(Vertex v, Vertex... adj) {

		add(v);
		for (Vertex x : adj) {
			add(x);
			adjList.get(v).add(x);
			if (!isDirected) adjList.get(x).add(v);			
		}
	}

	/**
	 * Gets the vertices.
	 *
	 * @return the vertices
	 */
	public Set<Vertex> getVertices() {
		return adjList.keySet();
	}
	
	/**
	 * Gets adjacent vertices of v.
	 * 
	 * *** NOTE *** need to investigate connecting edges 
	 * 
	 * @param v the vertex
	 * @return the list of adjacent vertices
	 */
	public Collection<Vertex> getAdjacent(Vertex x) {
		return adjList.get(x);
	}
	
	
	/**
	 * Adds one or more edges to the graph. 
	 *
	 * @param edges the edges to be added
	 */
	public void addEdge(Edge... edges) {
		for (Edge e : edges) {
			addAdjacent(e.source, e.target); // Generate the adjacency list
			this.edges.put(e.id, e);
			if (!isDirected) {
				Edge reverse = new Edge(e.target, e.source);
				this.edges.put(reverse.id, reverse);
			}
		}
	}
	
	
	/**
	 * Gets the edge weight between two vertices.
	 *
	 * @param s the source
	 * @param t the target
	 * @return the edge weight of s and t
	 */
	public int getEdgeWeight(Vertex s, Vertex t) {
		return edges.get(Edge.generateId(s,t)).weight;
	}
	
	
	/**
	 * The string representation of this graph:
	 * <pre>
     * Adjacency List: 
     *	A: C 
     *	B: C D 
     *	C: A D E B 
     *	D: C B 
     *	E: C 
     * Edge List: 
     *	|CD|=6
     *	|AC|=10
     *	|CE|=2
     *	|BD|=1
     *	|CB|=1
	 * </pre>
	 * @return the string
	 */
	public String toString() {
		
		final String tab = "\t";
		StringBuilder sb = new StringBuilder();
		Vertex[] vertices = adjList.keySet().toArray(new Vertex[] {});
		Arrays.sort(vertices);		
		
		sb.append("Adjacency List: \n");
		for (Vertex v : vertices) {
			sb.append(tab + v.name + ": ");
			adjList.get(v).forEach(adj->sb.append(adj.name + " "));
			sb.append("\n");
		}
		
		
		sb.append("Edge List: \n");
		edges.values().forEach(e->sb.append(tab + e).append(System.lineSeparator()));
		return sb.toString();		
	}

	
	public static class GraphTest {
		
		@Test
		public void directedGraph() {
	
			Vertex[] vertices = {
					new Vertex("A"),
					new Vertex("B"),
					new Vertex("C"),
					new Vertex("D"),
					new Vertex("E") 
			};
			
			Edge[] edges = {
					new Edge(vertices[0], vertices[2], 10),
					new Edge(vertices[2], vertices[3], 6),
					new Edge(vertices[2], vertices[4], 2),
					new Edge(vertices[2], vertices[1], 1),
					new Edge(vertices[1], vertices[3], 1)
			};
			
			Graph graph = new Graph(false);
			graph.addEdge(edges);
			System.out.println(graph);
		}
		
		@Test
		public void undirectedGraph() {
			Vertex[] vertices = {
					new Vertex("A"),
					new Vertex("B"),
					new Vertex("C"),
					new Vertex("D")				
				};
				
				Edge[] edges = {
						new Edge(vertices[0], vertices[1]),
						new Edge(vertices[1], vertices[2]),
						new Edge(vertices[2], vertices[3]),
						new Edge(vertices[3], vertices[0]),
						
				};
				
				Graph graph = Graph.createUndirectedGraph();
				graph.addEdge(edges);
				
				System.out.println(graph);
		}
	}
	
}
