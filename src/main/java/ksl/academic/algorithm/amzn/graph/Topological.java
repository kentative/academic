package ksl.academic.algorithm.amzn.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Topological {

	static class Graph 
	{ 
	    private int vCount;   // No. of vertices 
	    private Map<Integer, Set<Integer>> adjList; // Adjacency List 
	  
		//Constructor 
	    Graph(Integer v) 
	    { 
	        this.vCount = v;
	        this.adjList = new HashMap<>();
	        for (int i=0; i < v; ++i) 
	            adjList.put(i, new HashSet<>());
	    } 
	  
	    // Function to add an edge into the graph 
	    void addEdge(int from, int to) {
	    	adjList.get(from).add(to);
    	} 
	    
	    
	    // A recursive function used by topologicalSort 
	    void visit(int vertex, boolean visited[], Deque<Integer> stack) 
	    {
	    	
	    	if (stack.contains(vertex)) {
	    		System.out.println("pernament");
	    		return;
	    	}
	    	
	    	if (visited[vertex]) {
	    		System.out.println("Cycle detected");
	    		return;
	    	}
	    	
	        // Mark the current node as visited. 
	        visited[vertex] = true; 
	  
	        // Recur for all the vertices adjacent to this vertex 
	        for (int adj : adjList.get(vertex)) {
//	            if (!visited[adj]) 
	            	visit(adj, visited, stack); 
	        } 
	  
	        // Push current vertex to stack which stores result 
	        stack.push(vertex); 
	    } 
	  
	    // The function to do Topological Sort. It uses recursive topologicalSortUtil() 
	    void topologicalSort() 
	    { 
	        Deque<Integer> stack = new ArrayDeque<>(); 
	  
	        // Mark all the vertices as not visited 
	        boolean visited[] = new boolean[vCount]; 
	  
	        // Call the recursive helper function to store 
	        // Topological Sort starting from all vertices 
	        // one by one 
	        for (int i = 0; i < vCount; i++) {
	            if (!visited[i]) {
	            	visit(i, visited, stack); 
	            }
	        }
	  
	        // Print contents of stack 
	        while (!stack.isEmpty()) System.out.print(stack.pop() + " "); 
	    }
	}
	
	public static void main(String args[]) 
    { 
        // Create a graph given in the above diagram 
        Graph g = new Graph(6); 
        g.addEdge(5, 2);
//        g.addEdge(2, 5);
        g.addEdge(5, 0); 
        g.addEdge(4, 0); 
        g.addEdge(4, 1); 
        g.addEdge(2, 3); 
        g.addEdge(3, 1); 
  
        System.out.println("Following is a Topological sort of the given graph"); 
        g.topologicalSort(); 
    } 
}
