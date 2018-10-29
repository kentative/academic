package ksl.academic.algorithm.epi.tree;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import com.google.common.base.Preconditions;

import ksl.academic.structure.tree.Node;
import ksl.academic.structure.tree.TreeUtil;


/**
 * Same as #DepthFirstTraversal
 * This is a practice session
 * @author Kent
 *
 */
public class TreeTraversal {

	public static void main(String[] args) {
		Node root = new Node(26);
		TreeUtil.add(root, 17, 41);
		TreeUtil.add(root.left, 17, 21, true);
		TreeUtil.add(root.right, 30, 47);
		
		System.out.println(TreeUtil.printTree(root));
		
//		inOrder(root);
//		System.out.println();
//		inOrderItr(root);
		
//		preOrder(root);
//		System.out.println();
//		preOrderItr(root);
		
		postOrder(root);
		System.out.println();
		postOrderItr(root);
	
		inorderItr2(root);
	}

	static void inOrder(Node n) {
		if (n != null) {
			inOrder(n.left);
			System.out.print(n + " ");
			inOrder(n.right);
		}
	}

	static void preOrder(Node n) {
		if (n != null) {
			System.out.print(n + " ");
			preOrder(n.left);
			preOrder(n.right);
		}
	}

	static void postOrder(Node n) {
		if (n != null) {
			postOrder(n.left);
			postOrder(n.right);
			System.out.print(n + " ");
		}
	}
	
	static void preOrderItr(Node n) {
		Preconditions.checkNotNull(n);
		Deque<Node> stack = new ArrayDeque<>();
		
		stack.push(n);
		while (!stack.isEmpty()) {
			Node x = stack.pop();
			
			System.out.print(x + " ");
			if (x.right != null) stack.push(x.right);
			if (x.left != null) stack.push(x.left);
		}
		
	}

	// push left until null, pop and visit node, x = right
	static void inOrderItr(Node n) {
		Preconditions.checkNotNull(n);
		Deque<Node> stack = new ArrayDeque<>();
		
		Node x = n;
		while (!stack.isEmpty() || x != null) {
			
			if (x != null) {
				stack.push(x);
				x=x.left;
			} else {
				x = stack.pop();
				System.out.print(x + " ");
				x = x.right;
			}
		}
	}
	
	// push left until null, 
	// peek right, if null visit, and pop to last
	// else x = peek.right
	static void postOrderItr(Node n) {
		Preconditions.checkNotNull(n);
		Deque<Node> stack = new ArrayDeque<>();
		
		Node x = n;
		Node last = null;
		while (!stack.isEmpty() || x != null) {
			
			if (x != null) {
				stack.push(x);
				x=x.left;
			} else {
				Node peek = stack.peek();
				if (peek.right != null && last != peek.right) {
					x = peek.right;
				} else {
					System.out.print(peek + " ");
					last = stack.pop();
				}
			}
		}
	}
		

	
	static void levelOrder(Node n) {

		Preconditions.checkNotNull(n);
		List<Node> queue = new LinkedList<>();
		
		queue.add(n);
		
		while (!queue.isEmpty()) {
			Node x = queue.remove(0);
			if (x == null) continue;
			
			System.out.println(x);
			queue.add(x.left);
			queue.add(x.right);
		}
	}
	
	public static void inorderItr2(Node n) {

		Deque<Node> stack = new ArrayDeque<>();
		
		Node x = n;
		while (!stack.isEmpty() || x != null) {
			if (x != null) {
				stack.push(x);
				x = x.left;
			} else {
				x = stack.pop();
				System.out.print(x + " ");
				x = x.right;
			}
		}
	}

}
