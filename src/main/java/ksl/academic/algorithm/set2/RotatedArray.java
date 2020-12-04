package ksl.academic.algorithm.set2;

/**
 * A much more elegant solution. O(log n)
 * <p>
 * Performs binary search with rotated mid!
 *
 * @author Kent
 */
public class RotatedArray {

    public enum Row {A, B, C, D, E}

    public enum Direction {Left, Top, Right, Bottom}

    public static void main(String[] args) {

        int[][] dataSet = new int[6][6];

        dataSet[0] = new int[]{1, 2, 3, 4, 5, 6};
        dataSet[1] = new int[]{2, 3, 4, 5, 6, 1};
        dataSet[2] = new int[]{3, 4, 5, 6, 1, 2};
        dataSet[3] = new int[]{4, 5, 6, 1, 2, 3};
        dataSet[4] = new int[]{5, 6, 1, 2, 3, 4};
        dataSet[5] = new int[]{6, 1, 2, 3, 4, 5};

        System.out.println(search(dataSet[2], 6, 6));
    }

    /**
     * Perform binary search on a sorted, but rotated array.
     *
     * @param data the sorted but rotated list of numbers
     * @param n    - the size of the list, not really needed
     * @param x    - the number to search for
     * @return - the index containing x, -1 if not found.
     */
    public static int search(int[] data, int n, int x) {

        int lo = 0, hi = n - 1;
        // find the index of the smallest value using binary search.
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (data[mid] > data[hi])
                lo = mid + 1; // right side
            else
                hi = mid; // left side - only case where we can have infinite loop
        }


        // lo == hi is the index of the smallest value and also the number of places rotated.
        int offset = lo;

        lo = 0;
        hi = n - 1;
        // The usual binary search and accounting for rotation.
        while (lo <= hi) {
            int mid = (lo + hi) / 2;
            int realmid = (mid + offset) % n;
            if (x > data[realmid])
                lo = mid + 1;
            else if (x < data[realmid])
                hi = mid - 1;
            else
                return realmid;
        }
        return -1;
    }
}
