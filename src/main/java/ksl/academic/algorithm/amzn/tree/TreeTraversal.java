package ksl.academic.algorithm.amzn.tree;

import java.util.ArrayDeque;
import java.util.Deque;

public class TreeTraversal {

	public static void main(String[] args) {
		
		// Sorted balanced tree
		Node root = new Node(12); 
		Node n6 = root.addLeft(new Node(6));
		Node n18 = root.addRight(new Node(18));
		
		Node n3 = n6.addLeft(new Node(3));
		n3.addLeft(new Node(2));
		n3.addRight(new Node(4));
		
		Node n9 = n6.addRight(new Node(9));
		n9.addLeft(new Node(8));
		n9.addRight(new Node(10));
		
		Node n15 = n18.addLeft(new Node(15));
		n15.addLeft(new Node(14));
		n15.addRight(new Node(16));
		
		Node n21 = n18.addRight(new Node(21));
		n21.addLeft(new Node(20));
		n21.addRight(new Node(22));
		
		inOrderItr(root);
		
	}
	
	
	public static void inOrder(Node node) {
		
		if (node == null) return;
		inOrder(node.left);
		System.out.print(node.data + " ");
		inOrder(node.right);
		
	}
	
	
	public static void inOrderItr(Node node) {
		
		if (node == null) return;
		
		Deque<Node> stack = new ArrayDeque<>();
		while (!stack.isEmpty() || node != null) {
			
			if (node != null) {
				stack.push(node);
				node = node.left;
			} else {
				node = stack.pop();
				System.out.print(node.data + " ");
				node = node.right;
			}
		}
	}
	
	public static void postOrder(Node node) {
		
		if (node == null) return;
		postOrder(node.left);
		postOrder(node.right);
		System.out.print(node.data + " ");
		
	}
	
	public static void preOrder(Node node) {
		
		if (node == null) return;
		System.out.print(node.data + " ");
		preOrder(node.left);
		preOrder(node.right);
		
	}
	
	
	
	static class Node {
		
		int data;
		Node left;
		Node right;
		
		
		public Node(int data) {
			this.data = data;
		}
		
		public Node addLeft(Node node) {
			left = node;
			return node;
		}
		
		public Node addRight(Node node) {
			right = node;
			return node;
		}
	}
	
}
