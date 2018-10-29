package ksl.academic.algorithm.epi.tree;

import java.util.HashMap;
import java.util.Map;

import ksl.academic.structure.tree.Node;

public class RedBlackTree {
	
	public static void main(String[] args) {
		
		Node root = new Node(26);
		
		add(root, 17, 41);
		add(root.left, 14, 21);
		add(root.right, 30, 47);
		Map<Integer, String> sb = new HashMap<>();
		printRoot(sb, root, 0);
		
		StringBuilder builder = new StringBuilder();
		
		int n = sb.size() -1;
		for (int i = 0; i < sb.size(); i++) {
			long x = Math.round(Math.pow(n-i, 2));
			builder.append(getIndent(x) + sb.get(i));
			builder.append(System.lineSeparator());
		}
		
		System.out.println(builder.toString());
		
		
	}
	
	private static void add(Node node, int left, int right) {
		node.left = new Node(left);
		node.right = new Node(right);
	}

	
	/**
	 * In-order traversal
	 */
	public static void printTree(StringBuilder sb, Node node) {
		sb.append(System.lineSeparator());
		if (node.left != null) {
			printTree(sb, node.left);
		}
		
		sb.append(node.toString());
		
		if (node.right != null) {
			printTree(sb, node.right);
		}
		
	}
	
	public static void printRoot(Map<Integer, String> sb, Node node, int depth) {
		
		sb.put(depth, node.toString());
		printChildren(sb, node, ++depth);
		
	}
		

	public static void printChildren(Map<Integer, String> sb, Node node, int depth) {
		if (node.left != null) {
			if (!sb.containsKey(depth)) {
				sb.put(depth, node.left.toString());
			} else {
				String string = sb.get(depth);
				sb.put(depth, string + getIndent(depth) + node.left.toString());
			}
		}
		
		if (node.right != null) {
			if (!sb.containsKey(depth)) {
				sb.put(depth, node.right.toString());
			} else {
				String string = sb.get(depth);
				sb.put(depth, string + getIndent(depth) + node.right.toString());
			}
		}
		
		++depth;
		if (node.left != null) printChildren(sb, node.left, depth);
		if (node.right != null) printChildren(sb, node.right, depth);
	}
		

	private static String getIndent(long depth) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < depth; i++) {
			sb.append("    ");
		}
		return sb.toString();
	}
	
}


