package ksl.academic.algorithm.amzn.tree;

public class Node {
	
	public Node left;
	public Node right;
	public int data;
	
	public Node(int x) {
		this.data = x;
	}
	
	public Node addLeft(int x) {
		left = new Node(x);
		return left;
	}
	
	public Node addRight(int x) {
		right = new Node(x);
		return right;
	}
	
	public String toString() {
		return String.valueOf(data);
	}
}