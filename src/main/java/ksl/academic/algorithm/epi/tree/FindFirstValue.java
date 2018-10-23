package ksl.academic.algorithm.epi.tree;

import java.util.ArrayList;
import java.util.List;

public class FindFirstValue {
	
	public static void main(String[] args) {
		
		Node root = new Node(26);
		add(root, 17, 41);
		add(root.left, 17, 21, true);
		add(root.right, 30, 47);
		
		System.out.println(TreeUtil.printTree(root));
		System.out.println(findFirst(root, 17));
		
		List<int[]> data = new ArrayList<>();
		data.add(new int[] {1, 2,3});
		
		int test = -8;
		test >>>= 1;
		
		System.out.println(test);
		System.out.println(Integer.MAX_VALUE);
		
	}
	
	static Node findFirst(Node node, int k) {
		Node first = null;
		Node current = node;
		
		while (current != null) {
			if (k > current.value ) {
				current = current.right;
			} else if ( k < current.value) {
				current = current.left;
			} else {
				first = current;
				current = current.left;
			}
			
		}
		return first;
	}
	
	
	private static void add(Node node, int left, int right) {
		node.left = new Node(left);
		node.right = new Node(right);
	}
	
	private static void add(Node node, int left, int right, boolean isRed) {
		node.left = new Node(left, isRed);
		node.right = new Node(right, isRed);
	}
}
