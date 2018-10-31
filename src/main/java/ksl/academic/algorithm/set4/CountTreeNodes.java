package ksl.academic.algorithm.set4;

import ksl.academic.structure.tree.Node;
import ksl.academic.structure.tree.TreeUtil;

/**
 * The Class CountTreeNodes.
 */
public class CountTreeNodes {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Node root = new Node(26);
		TreeUtil.add(root, 17, 41);
		TreeUtil.add(root.left, 17, 21, true);
		TreeUtil.add(root.right, 30, 47);
		TreeUtil.add(root.left.left, 31, 42);
		
		System.out.println(count(root));
	}
	
	
	/**
	 * Count.
	 *
	 * @param node the node
	 * @return the int
	 */
	public static int count(Node node) {
		
		// traverse both subtree and count depth while right != null
		int h = 0;
		Node left = node;
		Node right = node;
		while (right != null) {
			h++;
			left = left.left;
			right = right.right;
		}
		
		if (left == null) {
			System.out.println("balanced");
			return (1 << h) -1;
		} else {
			System.out.println("left heavy");
			return 1 + count(node.left) + count(node.right);
		}
	}
	
	
	
}
