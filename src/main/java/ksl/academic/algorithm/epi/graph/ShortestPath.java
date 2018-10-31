package ksl.academic.algorithm.epi.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import ksl.academic.structure.graph.Edge;
import ksl.academic.structure.graph.Graph;
import ksl.academic.structure.graph.Vertex;

public class ShortestPath {

	public static void main(String[] args) {


		Vertex[] v = { 
				new Vertex("A"), // 0
				new Vertex("B"), // 1
				new Vertex("C"), // 2
				new Vertex("D"), // 3
				new Vertex("E"), // 4
				new Vertex("F"), // 5
				new Vertex("G"), // 6
				new Vertex("H"), // 7
				new Vertex("I")  // 8
				
		};

		Edge[] edge = { 
				new Edge(v[0], v[1], 5),  // AB 
				new Edge(v[0], v[2], 3),  // AC
				new Edge(v[0], v[4], 2),  // AE
				
				new Edge(v[1], v[3], 2),  // BD
				
				new Edge(v[2], v[1], 1),  // CB
				new Edge(v[2], v[3], 1),  // CD
				
				new Edge(v[3], v[0], 1),  // DA
				new Edge(v[3], v[6], 2),  // DG
				new Edge(v[3], v[7], 1),  // DH
								
				new Edge(v[4], v[0], 1),  // EA
				new Edge(v[4], v[7], 4),  // EH
				new Edge(v[4], v[8], 7),  // EI
				
				new Edge(v[5], v[1], 3),  // FB
				new Edge(v[5], v[6], 1),  // FG

				
				new Edge(v[6], v[2], 3),  // GC
				new Edge(v[6], v[8], 2),  // GI
				
				new Edge(v[7], v[2], 2),  // HC
				new Edge(v[7], v[5], 2),  // HF
				new Edge(v[7], v[6], 2),  // HG
				
		};
				
		
		Graph graph = Graph.createDirectedGraph();
		for (Edge e : edge) graph.addEdge(e);
		System.out.println(graph);
		
		List<Vertex> shortest = shortestPath(graph, v[0], v[8]);
		for (Vertex s : shortest) {
			System.out.println(s);
		}
		System.out.println("Cost " + shortest.get(shortest.size()-1).weight);
//		System.out.println("Cost " + shortest.get(0).getWeight());
	}
	
	
	static List<Vertex> shortestPath(Graph g, Vertex s, Vertex t) {
		PriorityQueue<Vertex> minQ = new PriorityQueue<>();
		Map<Vertex, Vertex> path = new HashMap<>();
		s.weight = 0;
		minQ.add(s);
		
		while (!minQ.isEmpty()) {
			Vertex x = minQ.remove();
			if (x.equals(t)) break;
			
			for(Vertex adj : g.getAdjacent(x)) {
				int w = x.weight + g.getEdgeWeight(x, adj);
				if (w < adj.weight) {
					adj.weight = w;
					minQ.add(adj);
					path.put(adj, x);
				}
			}
		}
		
		List<Vertex> result = new ArrayList<>();
		for (Vertex i = t; i != null; i = path.get(i)) result.add(0, i);
		return result;
	}
}
