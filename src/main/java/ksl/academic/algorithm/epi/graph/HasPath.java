package ksl.academic.algorithm.epi.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.graph.GraphBuilder;
import com.google.common.graph.MutableGraph;

public class HasPath {

	public static void main(String[] args) {
	
		MutableGraph<Integer> graph = GraphBuilder.directed().allowsSelfLoops(true).build();
		graph.putEdge(0, 1);
		graph.putEdge(0, 2);
		graph.putEdge(1, 2);
		graph.putEdge(2, 0);
		graph.putEdge(2, 3);
		graph.putEdge(3, 3);
		
		System.out.println(hasPath(graph, 1, 3));
		System.out.println(hasPath(graph, 3, 1));
		
	}

	private static List<Integer> hasPath(MutableGraph<Integer> graph, int source, int target) {
		
		int n = graph.nodes().size();
		boolean[] visited = new boolean[n];
		Map<Integer, Integer> path = new HashMap<>(n);
		
		Deque<Integer> stack = new ArrayDeque<>(n);
		stack.push(source);		
		while (!stack.isEmpty()) {
			
			int x = stack.pop();
			if (visited[x])  continue;  // already visited, skip node
			if (x == target) break;     // found target, exit loop
			
			for (int adj : graph.adjacentNodes(x)) {
				if (graph.hasEdgeConnecting(x, adj)) {
					stack.push(adj);
					path.put(adj, x);
				}
			}
			visited[x] = true;
		}
		
		// No path
		if (!path.containsKey(target)) 
			return null;
		
		List<Integer> result = new ArrayList<>();
		for (Integer x = target; x != null; x = path.get(x)) {
			result.add(0, x);
		}
		return result;
	}
}
