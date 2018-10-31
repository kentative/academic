package ksl.academic.algorithm.set4;

import ksl.academic.structure.graph.Edge;
import ksl.academic.structure.graph.Graph;
import ksl.academic.structure.graph.Vertex;

/**
 * The Class UndirectedGraphCycle.
 */
public class UndirectedGraphCycle {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	// Driver method to test above methods
	public static void main(String args[]) {
		
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