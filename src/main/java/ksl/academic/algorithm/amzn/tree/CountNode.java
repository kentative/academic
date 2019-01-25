package ksl.academic.algorithm.amzn.tree;

public class CountNode {


	public static void main(String[] args) {
		Node root = new Node(3, "3");
		root.left = new Node (1, "1");
		root.right = new Node (4, "4");
		
		CountNode alg = new CountNode();
		
		
		System.out.println(alg.search(root, 2));
		System.out.println(alg.countNode(root));
		
	}
	
	public Node search(Node root, int x) {
		
		Node n = root;
		while (n != null) {
			if (x < n.data) {
				n = n.left;
			} else if (x > n.data ) {
				n = n.right;
			} else {
				return n;
			}
		}
		return n;
	}
	
	public int countNode(Node root) {
		
		if (root == null) return 0;
		
		int d = 0;
		Node left = root.left;
		Node right = root.right;
		while (right != null ) {
			left = left.left;
			right = right.right;
			d++;
		}
		
		if (left == null) { // balanced
			return (1 << (d +1)) -1; 			
		} else {
			return countNode(root.left) + countNode(root.right) + 1;
		}
	}
	
	static class Node {
		int data;
		Node left;
		Node right;
		String name;
		
		public Node(int data, String name) {
			this.data = data;
			this.name = name;
		}
		
		public String toString() {
			return name;
		}
	}
}
