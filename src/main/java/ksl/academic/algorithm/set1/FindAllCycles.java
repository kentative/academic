package ksl.academic.algorithm.set1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * @author Kent
 * @since 10.17.2018
 */
public class FindAllCycles {

    public static void main(String[] args) {

        int n = 100;
        int[] data = new int[n];
        for (int i = 0; i < data.length; i++) {
            data[i] = i;
        }


        // Shuffle 10!=10*9*8*7*6...*1
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            int k = r.nextInt(i + 1);
            swap(data, i, k);
        }

        int[] test = data; //{3, 5, 2, 6, 9, 7, 4, 1, 0, 8};

//		System.out.println(Arrays.toString(test));
        List<Set<Integer>> cycles = findAllCycles(test);
        cycles.forEach(x -> System.out.println(x));
    }

    private static void swap(int[] data, int i, int k) {
        int t = data[i];
        data[i] = data[k];
        data[k] = t;
    }


    static List<Set<Integer>> findAllCycles(int[] data) {

        List<Set<Integer>> result = new ArrayList<>();

        int n = data.length;
        boolean[] isVisited = new boolean[n];
        for (int i = 0; i < n; i++) {
            Set<Integer> cycle = findCycle(data, i, isVisited);
            if (cycle != null) {
                result.add(cycle);
            }
        }

        return result;
    }

    private static Set<Integer> findCycle(int[] data, int i, boolean[] globalVisit) {

        int n = data.length;
        boolean[] localVisit = new boolean[n];

        Set<Integer> cycle = new HashSet<>();
        int x = data[i]; // starting index
        while (true) {

            if (!localVisit[x]) {

                // Duplicate cycle
                if (globalVisit[x]) {
                    return null;
                }

                localVisit[x] = true;
                globalVisit[x] = true;
                cycle.add(x);

            } else {
                return cycle;
            }
            x = data[x];
        }
    }

    static boolean isCompleteCycle(int[] data) {

        int n = data.length;
        boolean[] visited = new boolean[n];

        int x = 0;
        for (int i = 0; i < n; i++) {
            x = (x + data[x]) % n;
            if (x < 0) x += n;

            if (!visited[x]) visited[x] = true;
            else return false;
        }

        return true;
    }

}
