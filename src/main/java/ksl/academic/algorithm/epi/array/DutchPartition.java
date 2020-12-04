package ksl.academic.algorithm.epi.array;

import ksl.academic.algorithm.sort.SortUtil;

public class DutchPartition {

    public static void main(String[] args) {
        int[] data = {3, 3, 1, 4, 4, 1};

        partition(data, 1);
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + " ");
        }

        System.out.println();
        System.out.println();


        boolean[] keys = {true, false, true, false, true, true, false};
        partition(keys);
        for (int i = 0; i < keys.length; i++) {
            System.out.print(keys[i] + " ");
        }

    }

    static void partition(int[] data, int p) {

        // validate input
        int pivot = data[p];
        int min = 0;
        int max = data.length - 1;

        int i = 0;
        while (i <= max) {
            if (data[i] < pivot)
                SortUtil.swap(data, i++, min++);
            else if (data[i] > pivot)
                SortUtil.swap(data, i, max--);
            else
                i++;
        }
    }

    static void partition(boolean[] keys) {

        int n = keys.length;
        int f = n - 1;
        int t = f - 1;
        for (int i = n - 1; i >= 0; i--) {
            while (f >= 0 && keys[f]) f--;
            while (t >= 0 && !keys[t]) t--;
            if (t >= 0 && f >= 0) {
                SortUtil.swap(keys, t, f);
            }
        }
    }
}

class Holder {
    boolean key;
    String value;

    public String toString() {
        char c = (key) ? '1' : '0';
        return c + " " + value;
    }
}
