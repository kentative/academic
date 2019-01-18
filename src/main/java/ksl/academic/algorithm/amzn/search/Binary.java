package ksl.academic.algorithm.amzn.search;

import ksl.academic.algorithm.amzn.tree.Node;
import ksl.academic.algorithm.amzn.tree.TreeUtil;

public class Binary {

	public static void main(String[] args) {
		Node root = new Node(4);
		
		Node left = root.addLeft(2);
		left.addLeft(1);
		left.addRight(3);

		Node right = root.addRight(6);
		right.addLeft(5);
		right.addRight(7);
		
		TreeUtil.printInOrder(root);		
		System.out.println();
		System.out.println(new Binary().bsearch(root, 7));
		
	}
	
	public Node bsearch(Node root, int x) {
		
		Node n = root;
		
		while (n != null) {
			if (x < n.data) {
				n = n.left;
			} else if ( x > n.data) {
				n = n.right;
			} else {
				return n;
			}
		}
		
		return n;
	}
}
