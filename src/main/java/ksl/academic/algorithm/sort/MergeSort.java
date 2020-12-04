package ksl.academic.algorithm.sort;

import java.util.Arrays;
import java.util.List;

public class MergeSort implements SortingAlgorithm {


    /**
     * @param data - unsorted data
     * @param n    - size limit
     */
    public void sort(int[] data, int n) {

        if (data.length <= 1) {
            return;
        }

        sort(data, 0, n, Arrays.copyOf(data, n));
    }


    /**
     * @param temp  - the unsorted array
     * @param left  - the starting index
     * @param right - the ending index
     * @param data  - the sorted array
     * @return
     */
    private void sort(int[] data, int left, int right, int[] temp) {

        if (right - left < 2) {
            return;
        }

        int mid = (left + right) / 2;
        sort(data, left, mid, temp);
        sort(data, mid, right, temp);

        merge(temp, left, mid, right, data);
    }


    /**
     * @param data   - source data
     *               left is source[left to mid-1]
     *               right is source[mid to right -1]
     * @param left
     * @param mid
     * @param right
     * @param result
     */
    private void merge(int[] data, int left, int mid, int right, int[] result) {

        int l = left; // first index of left portion starts from left
        int r = mid;  // first index of right portion starts from mid
        for (int i = left; i < right; i++) {

            if (l < mid && (r >= right || data[l] <= data[r])) {
                result[i] = data[l++];
            } else {
                result[i] = data[r++];
            }
        }
    }


    @Override
    public void sort(List<Integer> data, int n) {
        // TODO Auto-generated method stub

    }

    public static void main(String[] args) {
        // Initializing array
        int[] data = {9, 4, 8, 3, 1, 2, 5};

        System.out.print("Initial Array: ");
        printArray(data, 0, data.length - 1);
        System.out.println();
        mergesort(data, 0, data.length - 1);

        System.out.println(Arrays.toString(data));
    }

    // Sorting in non decreasing order
    private static void mergesort(int[] data, int lo, int hi) {

        if (lo >= hi) return;

        int mid = (lo + hi) / 2;
        mergesort(data, lo, mid);
        mergesort(data, mid + 1, hi);

        merge(data, lo, mid, hi);
    }

    private static void merge(int[] data, int lo, int mid, int hi) {

        int[] temp = new int[data.length];
        int l = lo, r = hi, m = mid + 1;

        int i = lo;
        while (l <= mid && m <= r) {
            if (data[l] <= data[m]) {
                temp[i++] = data[l++];
            } else {
                temp[i++] = data[m++];
            }
        }

        while (l <= mid)
            temp[i++] = data[l++];

        while (m <= r) {
            temp[i++] = data[m++];
        }

        for (int i1 = lo; i1 <= hi; i1++) {
            data[i1] = temp[i1];
        }
    }

//	private static void merge1(int[] data, int lo, int mid, int hi) {
//		System.out.print("Left: ");
//		printArray(data, lo, mid);
//		System.out.print(" Right: ");
//		printArray(data, mid + 1, hi);
//		System.out.println();
//		int temp[] = new int[data.length];
//		int l = lo;
//		int r = hi;
//		int m = mid + 1;
//		int k = l;
//
//		while (l <= mid && m <= r) {
//			if (data[l] <= data[m]) {
//				temp[k++] = data[l++];
//			} else {
//				temp[k++] = data[m++];
//			}
//		}
//
//		while (l <= mid)
//			temp[k++] = data[l++];
//
//		while (m <= r) {
//			temp[k++] = data[m++];
//		}
//
//		for (int i1 = lo; i1 <= hi; i1++) {
//			data[i1] = temp[i1];
//		}
//
//		System.out.print("After Merge: ");
//		printArray(data, lo, hi);
//		System.out.println();
//	}

    public static void printArray(int[] arr, int i, int j) {
        System.out.print("[");
        for (int k = i; k < j; k++) {
            System.out.print(arr[k] + ", ");
        }
        System.out.print(arr[j] + "]");
    }

}