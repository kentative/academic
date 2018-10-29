package ksl.academic.algorithm.epi.tree;

import java.util.Stack;

import ksl.academic.structure.tree.Node;
import ksl.academic.structure.tree.TreeUtil;

public class IsBst {
	
	public static void main(String[] args) {
		
		Node root = new Node(26);
		add(root, 17, 41);
		add(root.left, 14, 21);
		add(root.right, 30, 47);
		
		inorder(root);
		
		System.out.println(TreeUtil.printTree(root));		
		System.out.println(isBinearySearchTree(root));
	}
	
	static boolean isBinearySearchTree(Node node) {
		
		Stack<Node> stack = new Stack<>();
		int previous = Integer.MIN_VALUE;
		while (!stack.isEmpty() || node != null) {
			
			// Always exhaust left branch first
			if (node != null) {
				stack.push(node);
				node = node.left;
				continue;
			}
			
			node = stack.pop();
			if (previous > node.value) return false;
			previous = node.value;
			node = node.right;
		}
		
		return true;
	}
	
	static void inorder(Node n) {
		
		if (n == null) return;
		inorder(n.left);
		System.out.println(n);
		inorder(n.right);
	}
	
	private static void add(Node node, int left, int right) {
		node.left = new Node(left);
		node.right = new Node(right);
	}
	
	
}
