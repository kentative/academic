package ksl.academic.structure;

public class Node<T> {
	
	public T data;
	
	public Node<T> left;
	
	public Node<T> right;

	public Node(T data) {
		this.data = data;
	}
	
	public T getData() {
		return data;
	}

	public Node<T> setData(T data) {
		this.data = data;
		return this;
	}

	public Node<T> getLeft() {
		return left;
	}

	public Node<T> setLeft(Node<T> left) {
		this.left = left;
		return left;
	}

	public Node<T> getRight() {
		return right;
	}

	public Node<T> setRight(Node<T> right) {
		this.right = right;
		return right;
	}
}
