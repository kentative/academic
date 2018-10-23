package ksl.academic.algorithm.epi.list;

public class PartitionList {

	public static void main(String[] args) {
		
		Node n = buildNode(22, 3, 18, 14, 16, 5, 2);
		int pivot = 5;
		
		System.out.println(print(pivot(n, pivot)));
	}
	
	static Node pivot(Node n, int x) {
		if (n == null) return null;
		
		Node left = new Node();
		Node right = new Node();
		Node hLeft = left, hRight = right;
		
		while ( n != null) {
			if (n.data <= x) {
				left.next = n;
				left = n;
			} else {
				right.next = n;
				right = n;
			}
			n = n.next;
		}
		
		left.next = hRight.next;
		right.next = null;
		
		return hLeft.next;
		
	}
	
	
	
	private static Node buildNode(int...data) {
		
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
	
	private static String print(Node n) {

		StringBuilder sb = new StringBuilder();
		Node x = n;
		while (x != null) {
			sb.append(x.getData()).append(" ");
			x = x.getNext();
		}
		return sb.toString();
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

}
