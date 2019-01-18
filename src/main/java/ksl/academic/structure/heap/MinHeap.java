package ksl.academic.structure.heap;

import java.util.PriorityQueue;

import ksl.academic.structure.graph.Vertex;

/**
 * 
 * @author Kent
 *
 */
public class MinHeap {
	
	private int[] data;
	private int size;
	private int maxsize;

	private static final int FRONT = 1;

	public MinHeap(int maxsize) {
		this.maxsize = maxsize;
		this.size = 0;
		data = new int[this.maxsize + 1];
		data[0] = Integer.MIN_VALUE;
	}

	private int parent(int pos) {
		return pos / 2;
	}

	private int leftChild(int pos) {
		return (2 * pos);
	}

	private int rightChild(int pos) {
		return (2 * pos) + 1;
	}

	private boolean isLeaf(int pos) {
		if (pos >= (size / 2) && pos <= size) {
			return true;
		}
		return false;
	}

	private void swap(int fpos, int spos) {
		int tmp = data[fpos];
		data[fpos] = data[spos];
		data[spos] = tmp;
	}

	private void minHeapify(int pos) {
		if (!isLeaf(pos)) {
			if (data[pos] > data[leftChild(pos)] || data[pos] > data[rightChild(pos)]) {
				
				if (data[leftChild(pos)] < data[rightChild(pos)]) {
					swap(pos, leftChild(pos));
					minHeapify(leftChild(pos));
				} else {
					swap(pos, rightChild(pos));
					minHeapify(rightChild(pos));
				}
			}
		}
	}

	public void insert(int element) {
		data[++size] = element;
		int current = size;

		while (data[current] < data[parent(current)]) {
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public void print() {
		for (int i = 1; i <= size / 2; i++) {
			System.out.print(
					" PARENT : " + data[i] + " LEFT CHILD : " + data[2 * i] + " RIGHT CHILD :" + data[2 * i + 1]);
			System.out.println();
		}
	}

	public void minHeap() {
		for (int pos = (size / 2); pos >= 1; pos--) {
			minHeapify(pos);
		}
	}

	public int remove() {
		int popped = data[FRONT];
		data[FRONT] = data[size--];
		minHeapify(FRONT);
		return popped;
	}

	public static void main(String... arg) {
		System.out.println("The Min Heap is ");
		MinHeap minHeap = new MinHeap(15);
		minHeap.insert(5);
		minHeap.insert(3);
		minHeap.insert(17);
		minHeap.insert(10);
		minHeap.insert(84);
		minHeap.insert(19);
		minHeap.insert(6);
		minHeap.insert(22);
		minHeap.insert(9);
		minHeap.minHeap();

		minHeap.print();
		System.out.println("The Min val is " + minHeap.remove());
		System.out.println("The Min val is " + minHeap.remove());
		System.out.println("The Min val is " + minHeap.remove());
		System.out.println("The Min val is " + minHeap.remove());
		System.out.println("The Min val is " + minHeap.remove());
		
		PriorityQueue<Vertex> q = new PriorityQueue<>();
		q.add(new Vertex("A", 6));
		q.add(new Vertex("B", 2));
		q.add(new Vertex("Z", 2));
		q.add(new Vertex("X", 4));
		
		
		System.out.println(q.remove());
		System.out.println(q.remove());
		System.out.println(q.remove());
	}
}