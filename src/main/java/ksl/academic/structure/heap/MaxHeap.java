package ksl.academic.structure.heap;

import java.util.Arrays;

/**
 * Tree-based data structure
 * 
 * Max heap property :
 * If P is a parent node of C, 
 *   - then the key of P is >= key of C
 * 
 * poll/offer - O(log(n))
 * 
 * priority queue
 * 10/18/2018
 */
public class MaxHeap {

	private static final int DEFAULT = 10;
	private int[] data;
	private int size;
	private int capacity;
	private static final int FRONT = 1;
	
	public MaxHeap(int capacity) {
		this.capacity = (capacity < DEFAULT) ?DEFAULT :capacity;
		this.size = 0;
		this.data = new int[capacity];
		data[0] = Integer.MAX_VALUE;
	}
	
	// add data at end, heapifyUp
	public void offer(int x) {

		if (size+1 == capacity) resize();
		int i = ++size;
		data[i] = x;
		heapifyUp(i);
	}
	
	// remove data in front, heapifyDown
	public int poll() {
		
		int result = data[FRONT];
		data[FRONT] = data[size--];
		heapifyDown(FRONT);
		return result;
		
	}

	private void swap(int current, int parent) {
		int t = data[current];
		data[current] = data[parent];
		data[parent] = t;
	}

	private void resize() {
		capacity *= 2; 
		data = Arrays.copyOf(data, capacity);
	}

	// swap while parent < current data
	private void heapifyUp(int i) {
		
		int parent = parent(i);
		if (i > 0 && data[parent] < data[i]) {
			swap(i, parent);
			heapifyUp(parent);
		}
	}
	
	//	find max(left, right, i), swap with i
	private void heapifyDown(int i) {
	
		int left = leftChild(i);
		int right = rightChild(i);
		int max = i;
		
		if (left <= size+1 && data[left]  > data[max]) max = left;
		if (right <= size+1 && data[right] > data[max]) max = right;
		if (max != i) {
			swap(i, max);
			heapifyDown(max);
		}
	}

	// always peek in front
	public int peek() {
		return data[FRONT];
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

	public static void main(String[] args) {
		
		MaxHeap heap = new MaxHeap(10);

		int n = 4;
		for (int i = 0; i < n; i++) {
			heap.offer(i+1);
		}
 
		System.out.println('\u0000');
		
//		heap.offer(100);
		heap.poll();
		heap.poll();
		
		System.out.println(heap.peek());
	}
}
