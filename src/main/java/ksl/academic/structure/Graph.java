package ksl.academic.structure;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.google.common.collect.ListMultimap;
import com.google.common.collect.MultimapBuilder;

import ksl.academic.algorithm.epi.graph.TaskScheduling;

// TODO: Auto-generated Javadoc
/**
 * The Class Graph.
 *
 * @author Kent
 * @see TaskScheduling for barebone Graph implementation without Guava
 */
public class Graph {
	
	/** 
	 * The adjacency list of this graph.
	 * key - the vertex
	 * value - the list of vertices connected to the vertex
	 *   
	 **/
	ListMultimap<Vertex, Vertex> adjList;

	/** The list of edges  key - the edge id - constructed from the id of vertices  value - edge. */
	Map<String, Edge> edges;

	/**
	 * Constructs a empty graph.
	 */
	public Graph() {
		adjList = MultimapBuilder.hashKeys().arrayListValues().build();
		edges = new HashMap<>();
	}
	
	/**
	 * Constructs a graph with the set of vertices and edges defined. 
	 * 
	 * @param vertices - the set of vertices
	 * @param edges - the set of edges
	 */
	public Graph(Vertex[] vertices, Edge[] edges) {
		this.adjList = MultimapBuilder.hashKeys().arrayListValues().build();
		this.edges = new HashMap<>();
		
		checkNotNull(vertices, "Vertices require");
		checkNotNull(edges, "Edges require");
		
//		for(Vertex v : vertices) adjList.put(v, new ArrayList<Vertex>());
		for (Edge e : edges) addEdge(e); 
	}

	/**
	 * Add a vertex and its neighbor. 
	 * @param v - the vertex to be added
	 * @param adj - optional, the list adjacent vertices 
	 */
	public void add(Vertex v, Vertex... adj) {
		
		Objects.requireNonNull(v, "Vertex cannot be null.");
//		if (!adjList.containsKey(v)) {
//			adjList.put(v, new ArrayList<>(adj.length));
//		}
		adjList.get(v).addAll(Arrays.asList(adj));
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
	 * Gets the adjacent.
	 *
	 * @param x the x
	 * @return the adjacent
	 */
	public List<Vertex> getAdjacent(Vertex x) {
		return adjList.get(x);
	}
	
	/**
	 * Adds the edge.
	 *
	 * @param e the e
	 */
	public void addEdge(Edge e) {
		add(e.getSource(), e.getTarget()); // Generate the adjacency list
		this.edges.put(Edge.generateId(e.getSource(), e.getTarget()), e);
	}
	
	/**
	 * Gets the edge weight.
	 *
	 * @param x the x
	 * @param v the v
	 * @return the edge weight
	 */
	public int getEdgeWeight(Vertex x, Vertex v) {
		return edges.get(Edge.generateId(x,v)).getWeight();
	}
	
	
	/**
	 * The string representation of this graph:
	 *  
	 * A: C
	 * B: D
	 * C: E D
	 * D: 
	 * E: B.
	 *
	 * @return the string
	 */
	public String toString() {
		final String tab = "   ";
		StringBuilder sb = new StringBuilder();
		Vertex[] vertices = adjList.keySet().toArray(new Vertex[] {});
		Arrays.sort(vertices);		
		
		sb.append("Adjacency List: \n");
		for (Vertex v : vertices) {
			sb.append(tab + v.getName() + ": ");

			for (Vertex adj : adjList.get(v)) {
				sb.append(adj.getName() + " ");
			}
			sb.append("\n");
		}
		
		
		sb.append("Edge List: \n");
		for (Edge e : edges.values()) {
			sb.append(tab + e).append(System.lineSeparator());
		}
		
		// Value list
		sb.append("Value: \n");
		adjList.values().stream().forEach(x -> sb.append(tab + x + System.lineSeparator()));
		return sb.toString();		
	}


	/** The Constant V. */
	public static final Vertex[] V = {
			//          0               1               2               3               4
			new Vertex("A"),new Vertex("B"),new Vertex("C"),new Vertex("D"),new Vertex("E") 
	};
	
	/** Sample edges. */
	public static Edge[] E = {
			new Edge(V[0], V[2], 10),
			new Edge(V[2], V[3], 6),
			new Edge(V[2], V[4], 2),
			new Edge(V[2], V[1], 1),
			new Edge(V[1], V[3], 1)
	};
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		Graph graph = new Graph(V, E);
		System.out.println(graph);
	}
}
