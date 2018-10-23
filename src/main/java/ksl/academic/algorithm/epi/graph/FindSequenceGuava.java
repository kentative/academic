package ksl.academic.algorithm.epi.graph;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.graph.MutableValueGraph;
import com.google.common.graph.ValueGraph;
import com.google.common.graph.ValueGraphBuilder;

/**
 * Solve the transform string (9.5) problem using Guava API
 * 
 * @author Kent
 */
public class FindSequenceGuava {

	public static void main(String[] args) throws IOException, URISyntaxException {

		// Read 500+ entries from file (needs JDK 11)
		// This is completely resource safe, per API
		String data = Files.readString(Paths.get(ClassLoader.getSystemResource("FourLetterWords.txt").toURI()));
		Set<String> d = new HashSet<>();
		data.lines().forEach(x -> d.add(x));
		
		// This is needed to make sure Node are the same instance
		Map<String, Node> nodeMap = new HashMap<>(d.size());
		Iterator<String> iterator = d.iterator();
		while (iterator.hasNext()) {
			Node n = new Node(iterator.next());
			nodeMap.put(n.getName(), n);
		}
		
		System.out.println(
				findSequence(
						buildGraph(nodeMap), 
						nodeMap.get("city"), 
						nodeMap.get("like")));

	}

	/**
	 * Build a graph using Hammming distance of 1. 
	 * This effectively creates a network of words that are 1 edit away.
	 * O(n^2) 
	 * @param nodeMap - the map containing the words and its corresponding node
	 * @return the ValueGraph with edges set to cost 1. 
	 */
	private static ValueGraph<Node, Integer> buildGraph(Map<String, Node> nodeMap) {
		
		MutableValueGraph<Node, Integer> graph = ValueGraphBuilder.directed().allowsSelfLoops(true).build();
		
		// Enumerate all possible values
		Iterator<String> iter1 = nodeMap.keySet().iterator();
		while (iter1.hasNext()) {
			String word1 = iter1.next();
			Iterator<String> iter2 = nodeMap.keySet().iterator();
			while (iter2.hasNext()) {
				String word2 = iter2.next();
				if (hammingDistance(word1, word2) == 1) {
					graph.putEdgeValue(nodeMap.get(word1), nodeMap.get(word2), 1);					
				}
			}
		}
		return graph;
	}
	
	private static int hammingDistance(String word1, String word2) {
		
		if (word1.length() != word2.length()) return -1;

		char[] s1 = word1.toCharArray();
		char[] s2 = word2.toCharArray();
		int d = 0;
		for (int i = 0; i < s1.length; i++) {
			if (s1[i] != s2[i]) d++;
		}
		return d;
	}

	static List<Node> findSequence(ValueGraph<Node, Integer> g, Node s, Node t) {

		if (s.equals(t)) return List.of(t);
		
		int size = g.nodes().size();
		Map<Node, Node> path = new HashMap<>();
		PriorityQueue<Node> minQ = new PriorityQueue<>(size, new NodeComparator());
		
		s.setValue(0);
		minQ.add(s);

		while (!minQ.isEmpty()) {
			
			Node x = minQ.remove();
			if (x.isVisited()) continue;
			if (x.equals(t))   break;
			
			for (Node adj : g.adjacentNodes(x)) {

				if (!g.hasEdgeConnecting(x, adj)) continue;
				Integer edgeCost = g.edgeValueOrDefault(x, adj, null);
				if (edgeCost == null) 
					throw new IllegalArgumentException("Must have an edge weight");
				
				int cost = x.getValue() + edgeCost;
				if (cost < adj.getValue()) {
					adj.setValue(cost);
					path.put(adj, x);
					minQ.add(adj);
				}
			}
			x.setVisited(true);
		}

		// Path not found
		if (!path.containsKey(t)) return null;
		
		// Note LinkedList instead of ArrayList to add in reverse order
		List<Node> result = new LinkedList<>();
		for (Node x = t; x != null; x = path.get(x)) {
			result.add(0, x);
		}
		return result;
	}
	
	static class NodeComparator implements Comparator<Node> {
		public int compare(Node n1, Node n2) {
			Preconditions.checkNotNull(n1);
			Preconditions.checkNotNull(n2);
			return n1.getValue() - n2.getValue();
		}
	}
	
	static class Node {
		
		private String name;
		private int value;
		private boolean isVisited;
		
		Node (String name) {
			this.name = name;
			this.value = Integer.MAX_VALUE;
		}
		
		public String toString() {
			return name +"("+ value + ")";
		}
		
		public boolean equals(Object obj) {
			if (obj == this) return true;
			if (obj == null) return false;
			if (obj.getClass() != this.getClass()) return false;
			
			return ((Node) obj).name.equals(this.name);
		}
		
		public int hashCode() {
			return Objects.hash(name);
		}
		
		public String getName() {
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
		
		public int getValue() {
			return value;
		}
		
		public void setValue(int value) {
			this.value = value;
		}

		public boolean isVisited() {
			return isVisited;
		}

		public void setVisited(boolean isVisited) {
			this.isVisited = isVisited;
		}
	}
}
