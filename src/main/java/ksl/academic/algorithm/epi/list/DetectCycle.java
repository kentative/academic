package ksl.academic.algorithm.epi.list;

public class DetectCycle {

	public static void main(String[] args) {
		
		Node n = buildNode(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		n.next.next.next
			.next.next.next
				.next.next.next = n.next.next.next
						.next.next.next
						;
		System.out.println(hasCycle(n));
		System.out.println(getCycle(n));
		System.out.println(print(n, 20));
		
	}
	
	static boolean hasCycle(Node n) {
		if (n == null) return false;
		
		Node slow = n, fast = n;
		
		while (fast != null && fast.next != null) {
			slow = slow.next;
			if (fast.next == null) return false;
			fast = fast.next.next;
			
			if (slow == fast) return true;
		}
		return false;
	}
	
	static Node getCycle(Node n) {
		
		if (n == null) return null;
		
		Node s = n, f = n;
		boolean hasCycle = false;
		while (f != null && f.next != null) {
			s = s.next;
			f = f.next.next;
			
			if (s == f) {
				hasCycle = true;
				break;
			}
		}

		if (!hasCycle) return null;
		
		System.out.println("first slow :" + print(s, 1));
		
		s = n;
		while (s != f) {
			
			System.out.println("slow :" + print(s, 1));
			System.out.println("fast :" + print(f, 1));
			s = s.next;
			f = f.next;
		}
		return s;
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
