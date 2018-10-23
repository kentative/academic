package ksl.academic.algorithm.epi.list;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.ArrayDeque;
import java.util.Deque;

public class AddNumberItr {

	static int BASE = 10;

	public static void main(String[] args) {
		Node n1 = buildNode(1, 2, 3);
		Node n2 = buildNode(4, 5, 6, 7);
		Node sumLinkedList = sumLinkedList(n1, n2);
		System.out.println(print(sumLinkedList, 10));
		
	}
	
	public static Node sumLinkedList(Node a, Node b) {
		// Check inputs
		checkNotNull(a);
		checkNotNull(b);

		int aLen = length(a), bLen = length(b);
		if (aLen < bLen) a = pad(a, bLen -aLen);
		else if(bLen < aLen) b = pad(b, aLen - bLen);
		
		Deque<Node> stack = new ArrayDeque<>(aLen + bLen);
		while (a != null) {
			stack.push(a); a = a.next;
			stack.push(b); b = b.next;
		}
		
		Node sum = null;
		int carry = 0;
		while (!stack.isEmpty()) {
			Node n1 = stack.pop();  // Bi
			Node n2 = stack.pop();  // Ai
			int x = n1.getData() + n2.getData() + carry;
			carry = x/10;
			sum = addHead(sum, x%10);
		}
		
		if (carry > 0) sum = addHead(sum, carry);
		return sum;
	}


	private static Node addHead(Node list, int data) {
		Node n = new Node();
		n.data = data;
		if (list != null) {
			n.next = list;
		}
		return n;
	}

	private static Node pad(Node n, int length) {
		
		if (length == 0) return n;
		
		for (int i = 0 ; i < length; i++) {
			n = addHead(n, 0);
		}
		return n;
	}

	private static int length(Node n) {

		int length = 0;
		while (n != null) {
			length++;
			n = n.next;
		}
		return length;
	}

	static class Node {
		private int data;
		private Node next;

		public String toString() {
			return String.valueOf(this.data);
		}

		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

	}

	private static Node buildNode(int... data) {

		Node head = new Node();
		head.setData(data[0]);

		Node list = head;
		for (int i = 1; i < data.length; i++) {
			Node next = new Node();
			next.setData(data[i]);
			list.setNext(next);
			list = next;

		}
		return head;
	}
	
	private static String print(Node n, int max) {

		StringBuilder sb = new StringBuilder();
		Node x = n;
		int i = 0;
		while (x != null) {
			sb.append(x.getData()).append(" ");
			x = x.getNext();
			if (++i >= max) break;
		}
		return sb.toString();
	}
}
