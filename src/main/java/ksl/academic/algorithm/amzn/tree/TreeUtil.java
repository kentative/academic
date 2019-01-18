package ksl.academic.algorithm.amzn.tree;

public class TreeUtil {

	/**
	 * Left, middle, right
	 * @param root
	 */
	public static void printInOrder(Node node) {
		
		if (node == null) return;
		
		printInOrder(node.left);		
		System.out.print(node.data + " ");		
		printInOrder(node.right);
	}
}
