package ksl.academic.algorithm.set4;

import com.google.common.base.Preconditions;

import ksl.academic.structure.Node;

// TODO: Auto-generated Javadoc
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
//		add(root, 17, 41);
//		add(root.left, 17, 21, true);
//		add(root.right, 30, 47);
	}
	
	/**
	 * Height.
	 *
	 * @param node the node
	 * @return the int - the tree height using the depth of the left-most branch
	 */
	public int height(Node<Integer> node) {
		Preconditions.checkNotNull(node);
		
		return (node == null) ?-1 :height(node.left) +1;
	}
	
	
	
}
