package ksl.academic.algorithm.epi.list;

public class RemoveDuplicate {

	public static void main(String[] args) {
		
		Node n = buildNode(1, 1, 1, 5, 5, 7, 4, 4, 2, 1);
		System.out.println(print(removeDuplicate(n)));
	}
	
	static Node removeDuplicate(Node n) {
		if (n == null) return null;
		
		Node last = n, h = n;
		
		while (n != null) {
			
			if (n.data  != last.data) {
				last.next = n;
				last = n;
			}
			
			n = n.next;
		}
		
		return h;
	}
	
	private static Node buildNode(int...data) {
		
		Node head = new Node();
		head.data = data[0];
		
		Node list = head;
		for (int i = 1; i < data.length; i++) {
			Node next = new Node();
			next.data = data[i];
			list.next = next;
			list = next;
			
		}
		return head;
	}
	
	private static String print(Node n) {

		StringBuilder sb = new StringBuilder();
		Node x = n;
		while (x != null) {
			sb.append(x.data).append(" ");
			x = x.next;
		}
		return sb.toString();
	}
	
	static class Node {
		private int data;
		private Node next;

		
		public String toString() {
			return String.valueOf(this.data);
		}
	}

}
