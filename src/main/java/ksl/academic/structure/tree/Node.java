package ksl.academic.structure.tree;


// TODO: Auto-generated Javadoc
/**
 * The Class Node.
 */
public class Node {
	
	/** The red. */
	// Red = 1, Black = 0
	public boolean red;
	
	/** The lower. */
	public int lower;
	
	/** The upper. */
	public int upper;
	
	/** The value. */
	public int value;

	/** The parent. */
	public Node parent;
	
	/** The left. */
	public Node left;
	
	/** The right. */
	public Node right;

	/**
	 * Instantiates a new node.
	 *
	 * @param value the value
	 */
	public Node(int value) {
		this.value = value;
	}
	
	/**
	 * Instantiates a new node.
	 *
	 * @param value the value
	 * @param isRed the is red
	 */
	public Node(int value, boolean isRed) {
		this.value = value;
		this.red = isRed;
	}

	/**
	 * Instantiates a new node.
	 *
	 * @param left the left
	 * @param right the right
	 */
	public Node(Node left, Node right) {
		this.left = left;
		this.right = right;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		char color = (red) ? 'R' : 'B';
		return color + ":" + value;
	}

	/**
	 * Rotate left.
	 *
	 * @param x the x
	 */
	public void rotateLeft(Node x) {
		Node y = x.right;
		x.right = y.left;

		y.left.parent = x;
		y.parent = x.parent;

		if (x.parent == null) {
			x = y;
		} else if (x.parent.left == x) {
			x.parent.left = y;
		} else {
			x.parent.right = y;
		}

		y.left = x;
		x.parent = y;

	}

}
