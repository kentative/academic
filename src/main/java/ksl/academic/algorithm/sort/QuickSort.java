package ksl.academic.algorithm.sort;

import java.util.ArrayDeque;
import java.util.Currency;
import java.util.Deque;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ksl.academic.algorithm.Utility;


/**
 * Two or three times faster than merge sort and heap sort.
 * Divide and conquer algorithm
 * Divide large array into smaller sub-array
 * Recursively sort the sub arrays
 *
 * <ol>Steps:
 * <li>Pick pivot: Pick an element from the array, the pivot
 * <li>Partition operation: reorder the array so that all elements with values less than the pivot is on one side
 * <li>Recursively apply pivot and partition
 * </ol>
 *
 * <ul>
 * <li>Category     - comparison sort, Efficient sort
 * <li>Time         - average: O(n log(n)) worst: O(n^2)
 * <li>Space        - n
 * <li>Stability    - Stable, unless optimized
 * <li>Adaptability -
 * </ul>
 *
 * @author Kent
 * @since 07.01.2018
 */
public class QuickSort implements SortingAlgorithm {

    private static final Logger logger = LoggerFactory.getLogger(QuickSort.class);

    @Override
    public void sort(List<Integer> data, int n) {
    }


    /**
     * Given an array of 10 items (0-9):
     * n = 10
     * pivot = 4
     * partition 0-4
     * partition 5-9
     */
    @Override
    public void sort(int[] data, int n) {
        quicksort(data, 0, n - 1);
//		quicksortItr(data, 0, n-1);
    }


    /**
     * @param data - data array
     * @param low  - inclusive
     * @param high - inclusive
     */
    private void quicksort(int[] data, int low, int high) {

        if (low >= high) return;

        int p = partition(data, low, high);
        quicksort(data, low, p);
        quicksort(data, p + 1, high);
    }

    private void quicksortItr(int[] data, int low, int high) {

        Deque<int[]> stack = new ArrayDeque<>(data.length);

        stack.push(new int[]{low, high});
        while (!stack.isEmpty()) {

            int[] range = stack.pop();
            int lo = range[0], hi = range[1];
            if (hi - lo < 1) continue;

            int p = partition(data, lo, hi);
            stack.push(new int[]{lo, p});
            stack.push(new int[]{p + 1, hi});
            count++;
        }
    }

    /**
     * Using median of 3 (reduces run time of worst case from 8 seconds to 47 ms)
     * Align value to be low < mid < high
     * and pick median as pivot (swap to low in this case)
     * median = max(  min(a,b), min(max(a,b),c));
     *
     * @param high - inclusive (use n-1 or p)
     */
    private int partition(int[] data, int low, int high) {

        count++;
        int mid = (low + high) / 2;
        if (data[mid] < data[low]) SortUtil.swap(data, mid, low);
        if (data[mid] > data[high]) SortUtil.swap(data, mid, high);
        if (data[low] < data[mid]) SortUtil.swap(data, mid, low); // reverse

        int pivot = data[low];
        int i = low - 1, j = high + 1;

        // This loop is more efficient, no comparator
        // Need to make sure i and j are updated regardless of condition
        while (true) {

            while (data[++i] < pivot) ;
            while (data[--j] > pivot) ;
            if (i >= j) return j;
            SortUtil.swap(data, i, j);
        }
    }

    static long count = 0;

    private int partition1(int[] data, int low, int high) {

        count++;
        int i = low;
        int pivot = data[i];
        for (int j = low + 1; j < high; j++) {
            if (data[j] < pivot) {
                SortUtil.swap(data, j, ++i);
            }
        }
        SortUtil.swap(data, low, i);
        return i;
    }

    public static void main(String[] args) {
        // Initializing array
//		int data[] = { 9, 4, 8, 3, 1, 2, 5 };
        int[] data = {1, 2, 3, 4, 5, 8, 9};

        int size = 1 << 27;
        int[] dataLarge = new int[size];
        for (int i = size - 1, j = 0; i >= 0; i--, j++) {
            dataLarge[j] = i;
        }

        QuickSort alg = new QuickSort();

        long start = System.currentTimeMillis();
        alg.sort(dataLarge, dataLarge.length);
        System.out.println("duration " + (System.currentTimeMillis() - start));

        for (int i = 1; i < size; i++) {
            if (dataLarge[i] < dataLarge[i - 1]) {
                System.out.println("FAILED " + dataLarge[i - 1] + " " + dataLarge[i]);
                break;
            }
        }

        System.out.println("Count " + count);
        //Utility.print(dataLarge);

    }
}
