package ksl.academic.algorithm.epi.tree;

public class Node {
	// Red = 1, Black = 0
	boolean red;
	int lower;
	int upper;
	
	int value;

	Node parent;
	Node left;
	Node right;

	Node(int value) {
		this.value = value;
	}
	
	Node(int value, boolean isRed) {
		this.value = value;
		this.red = isRed;
	}

	Node(Node left, Node right) {
		this.left = left;
		this.right = right;
	}

	public String toString() {
		char color = (red) ? 'R' : 'B';
		return color + ":" + value;
	}

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
