package ksl.academic.algorithm.amzn.array;

import java.util.ArrayDeque;
import java.util.Deque;

public class QueueWithStack {

	
	public static void main(String[] args) {
	
		QueueWithStack q = new QueueWithStack();
		
		System.out.println(q.poll());
		q.offer(1);
		System.out.println(q.poll());
		q.offer(2);
		q.offer(3);
		System.out.println(q.poll());
		q.offer(4);
		System.out.println(q.poll());
		System.out.println(q.poll());
		
		
	}
	
	
	private Deque<Integer> s1;
	private Deque<Integer> s2;
	private int size;
	
	public QueueWithStack() {
		this.size = 0;
		this.s1 = new ArrayDeque<>();
		this.s2 = new ArrayDeque<>();
	}
	
	public void offer(Integer x) {
		s1.push(x);
		size++;
	}
	
	public Integer poll() {
		if (size == 0) return null;
		
		if (s2.isEmpty()) {
			while (!s1.isEmpty()) {
				s2.push(s1.pop());
			}
		}
		
		size--;
		return s2.pop();
	}
}
