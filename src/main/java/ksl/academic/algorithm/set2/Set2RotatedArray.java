package ksl.academic.algorithm.set2;

public class Set2RotatedArray {

	public enum Row { A, B, C, D, E }
	public enum Direction { Left, Top, Right, Bottom }

	
	
	public static void main(String[] args) {
		
		int[][] dataSet = new int[6][6];
		
		dataSet[0] = new int[] {1, 2, 3, 4, 5, 6};
		dataSet[1] = new int[] {2, 3, 4, 5, 6, 1};
		dataSet[2] = new int[] {3, 4, 5, 6, 1, 2};
		dataSet[3] = new int[] {4, 5, 6, 1, 2, 3};
		dataSet[4] = new int[] {5, 6, 1, 2, 3, 4};
		dataSet[5] = new int[] {6, 1, 2, 3, 4, 5};

		
		int[] data = dataSet[1]; 
		int offset = findOffset(data);
		int result = -1;
		int start = 0; 
		int end = data.length;
		int x = 3;
		if (offset < 0) {
			result = binarySearch(data, start, end, x);
		} else {
			result = binarySearch(data, start, offset, x);
			if (result == -1) {
				result = binarySearch(data, offset, end, x);
			}
		}

		System.out.println(result);
		
	}
	
	
	/**
	 * O(log n) - optimal
	 * 
	 * @param data - the data array
	 * @param start - inclusive
	 * @param end - exclusive (i.e., n-1)
	 * @param x - the value to be searched
	 * @return the index of the data array containing the x value
	 */
	private static int binarySearch(int[] data, int start, int end, int x) {

		if (start > end ) {
			return -1;
		}
		
		int mid = (start + end)/2;
		if (x < data[mid])  {
			return binarySearch(data, start, mid-1, x);
		} else if (x > data[mid])  {
			return binarySearch(data, mid+1, end, x);
		} else {
			return mid;
		}
	}
	
	/**
	 * O(n), 
	 * optimal - believe to be O(log n)
	 *  
	 */
	@SuppressWarnings("unused")
	private static int findOffsetSimple(int[] data) {
		
		for (int i = 0; i < data.length-1; i++) {
			if (data[i] > data[i+1]) {
				return i;
			}
		}
		return 0;
	}
	

	
	// O(log n) - similar to binary search
	private static int findOffset(int[] data) {
	
		// Perform separately: not rotated  
		if (data[0] <= data[data.length-1]) {
			return 0;
		} else {
			return findPivot(data, 0, data.length-1);
		}
		
	}
	
	/**
	 *
	 * O(log n) - similar to binary search
	 */
	private static int findPivot(int[] data, int start, int end) {

		if (start > end) {
			return -1;
		}
		
		int mid = (start + end)/2;
		// sorted left side
		boolean leftSorted = data[start] <= data[mid];
		boolean rightSorted = data[mid+1] <= data[end];
		
		if (leftSorted && rightSorted)  {
			return mid+1;
		}
		if (leftSorted) {
			// look in the right partition
			return findPivot(data, mid, end);
		}
		if (rightSorted) {
			// look in the left partition
			return findPivot(data, start, mid);
		}
		return -1;
	}

}
