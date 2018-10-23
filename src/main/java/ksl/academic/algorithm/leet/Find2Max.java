package ksl.academic.algorithm.leet;

public class Find2Max {

	public static void main(String[] args) {
		Node n = new Node(10);
		n.left = new Node(5);
		n.right = new Node(15);		
		n.right.right = new Node(20);
		n.left.right = new Node(7);
		n.right.right.right = new Node(25);
		
		System.out.println(find2Max(n));
	}
	
	static int find2Max(Node n) {
		if (n == null || n.right == null && n.left == null) {
			throw new IllegalArgumentException("Need at least 2 elements");
		}
		
		Node parent = findMaxParent(n);
		if (parent == null) {
			return findMax(n.left).value;
		} 
		
		Node second;
		Node left = parent.right.left;
		if (left != null ) {
			second = findMax(left);
		} else {
			second = parent;
		}
		
		return second.value;
	}
	
	
	private static Node findMax(Node n) {
		if (n == null) return null;
		
		while (n.right != null) {
			n = n.right;
		}
		return n;
		
	}
	
	private static Node findMaxParent(Node n) {
		if (n.right == null) return null;
		
		while (n.right != null && n.right.right != null) {
			n = n.right;
		}
		return n;
	}

	static class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value) {
			this.value = value;
		}
		
	}
}
