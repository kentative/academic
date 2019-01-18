package ksl.academic.algorithm.set4;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * The Class UndirectedGraphCycle.
 * from https://www.geeksforgeeks.org/detect-cycle-undirected-graph/
 * This code is contributed by Aakash Hasija
 */
public class UndirectedGraphCycleSample {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	// Driver method to test above methods
	public static void main(String args[]) {
		// Create a graph given in the above diagram
		Graph g1 = new Graph(5);
		g1.addEdge(1, 0);
		g1.addEdge(0, 2);
		g1.addEdge(2, 0);
		g1.addEdge(0, 3);
		g1.addEdge(3, 4);
		if (g1.isCyclic())
			System.out.println("Graph contains cycle");
		else
			System.out.println("Graph doesn't contains cycle");

		Graph g2 = new Graph(3);
		g2.addEdge(0, 1);
		g2.addEdge(1, 2);
		if (g2.isCyclic())
			System.out.println("Graph contains cycle");
		else
			System.out.println("Graph doesn't contains cycle");
	}

	/**
	 * The Class Graph.
	 * This class represents a directed graph using adjacency list
	 */
	static class Graph {
		
		/** The v. */
		private int V; // No. of vertices
		
		/** The adj. */
		private LinkedList<Integer>[] adj; // Adjacency List Representation

		/**
		 * Instantiates a new graph.
		 *
		 * @param v the number of vertices
		 */
		// Constructor
		Graph(int v) {
			V = v;
			adj = new LinkedList[v];
			for (int i = 0; i < v; ++i)
				adj[i] = new LinkedList();
		}

		/**
		 * Adds the edge.
		 *
		 * @param v the v
		 * @param w the w
		 */
		// Function to add an edge into the graph
		void addEdge(int v, int w) {
			adj[v].add(w);
			adj[w].add(v);
		}

		/**
		 * Checks if is cyclic util.
		 * A recursive function that uses visited[] and parent to detect cycle. 
		 * If the next node to be visited is not a parent, and is already visited, it's a cycle.
		 * 
		 * @param v the current node to examine
		 * @param visited the visited cache
		 * @param parent the parent of v
		 * @return true if there is a cycle, false otherwise
		 */
		// cycle in subgraph reachable from vertex v.
		Boolean isCyclicUtil(int v, Boolean visited[], int parent) {
			// Mark the current node as visited
			visited[v] = true;
			Integer i;

			// Recur for all the vertices adjacent to this vertex
			Iterator<Integer> it = adj[v].iterator();
			while (it.hasNext()) {
				i = it.next();

				// If an adjacent is not visited, then recur for that
				// adjacent
				if (!visited[i]) {
					if (isCyclicUtil(i, visited, v))
						return true;
				}

				// If an adjacent is visited and not parent of current
				// vertex, then there is a cycle.
				else if (i != parent)
					return true;
			}
			return false;
		}

		/**
		 * Checks if is cyclic.
		 *
		 * @return the boolean
		 */
		// Returns true if the graph contains a cycle, else false.
		Boolean isCyclic() {
			// Mark all the vertices as not visited and not part of
			// recursion stack
			Boolean visited[] = new Boolean[V];
			for (int i = 0; i < V; i++)
				visited[i] = false;

			// Call the recursive helper function to detect cycle in
			// different DFS trees
			// This loop is needed because we want to check all disconnected nodes
			for (int u = 0; u < V; u++)
				if (!visited[u]) // Don't recur for u if already visited
					if (isCyclicUtil(u, visited, -1)) // default parent to -1 since this is root
						return true;

			return false;
		}

	}
}