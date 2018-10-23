package ksl.academic.algorithm.epi.tree;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class TreeUtil {

	public static String printTree(Node root) {
		
		
		Map<Integer, String> sb = new HashMap<>();
		int maxDepth = heightItr(root);
		
		printRoot(sb, root, 0, maxDepth);
		
		StringBuilder buffer = new StringBuilder();
		for (int i = 0; i < sb.size(); i++) {
			String space = getIndent(i, maxDepth);
			buffer.append(space + sb.get(i));
			buffer.append(System.lineSeparator());
		}
		
		return buffer.toString();
	}
	
	public static int height(Node node) {
		System.out.println(node);
		if (node == null) {
			return -1;
		} else {
			int leftHeight = height(node.left);
			int rightHeight = height(node.right);
			if (leftHeight > rightHeight) {
				System.out.println(node + " left " + (leftHeight+1));
				return leftHeight + 1;
			} else {
				System.out.println(node + " right " + (rightHeight+1));
				return rightHeight + 1;
			}
		}
	}
	
	public static int heightItr(Node node) {
		
		if (node == null) {
			return -1;
		}
		
		Stack<Node> stack = new Stack<>();
		stack.push(node);
		int left = -1, right = -1;
		while (!stack.isEmpty()) {
			
			Node x = stack.pop();
			if (x.left != null) {
				left++;
				stack.push(x.left);
			} 
			
			if (x.right != null) {
				right++;
				stack.push(x.right);
			}
		}
		return (left > right) ?left :right;
	}
	
	
	
	private static void printRoot(Map<Integer, String> sb, Node node, int depth, int maxDepth) {
		sb.put(depth, node.toString());
		printChildren(sb, node, ++depth, maxDepth);
	}
		
	private static void printChildren(Map<Integer, String> sb, Node node, int depth, int maxDepth) {
		if (node.left != null) {
			if (!sb.containsKey(depth)) {
				sb.put(depth, node.left.toString());
			} else {
				// Append to end
				String front = sb.get(depth);
				sb.put(depth, front + getIndent(depth, maxDepth) + node.left.toString());
			}
		}
		
		if (node.right != null) {
			if (!sb.containsKey(depth)) {
				sb.put(depth, node.right.toString());
			} else {
				String front = sb.get(depth);
				sb.put(depth, front + getIndent(depth, maxDepth) + node.right.toString());
			}
		}
		
		++depth;
		if (node.left != null) printChildren(sb, node.left, depth, maxDepth);
		if (node.right != null) printChildren(sb, node.right, depth, maxDepth);
	}
		
	private static String getIndent(int depth, int maxDepth) {
		
		int spaces = 4;
		spaces = (depth == maxDepth) ?1 :((maxDepth - depth) * spaces);
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < spaces; i++) {
			sb.append(" ");
		}
		return sb.toString();
	}
	
//	
//	/**
//	 * In-order traversal
//	 */
//	public static void printTree1(StringBuilder sb, Node node) {
//		
//		sb.append(System.lineSeparator());
//		if (node.left != null) {
//			printTree1(sb, node.left);
//		}
//		
//		sb.append(node.toString());
//		
//		if (node.right != null) {
//			printTree1(sb, node.right);
//		}
//	}
	
}
