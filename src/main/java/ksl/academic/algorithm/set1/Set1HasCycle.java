package ksl.academic.algorithm.set1;

import java.util.HashSet;

/**
 * @author Kent
 * @since 07.26.2018
 */
public class Set1HasCycle {

    public static void main(String[] args) {

        int[] data = {2, 2, -1};
        System.out.println(hasCycleNoCache(data));

        System.out.println(isCompleteCycle(data));

        System.out.println(-8 % 6);
    }

    public static boolean hasCycle(int[] data) {

        HashSet<Integer> visited = new HashSet<>();
        int x = 0;
        for (int i = 0; i < data.length; i++) {
            x = ((x + data[x]) % data.length); // relative index
            if (x < 0) x += data.length;
            if (visited.contains(x)) {
                return false;
            } else {
                visited.add(x); // add the index of the array, not the value
            }
        }
        return (x == 0);

    }

    public static boolean hasCycleNoCache(int[] data) {
        int x = 0;
        int n = data.length;
        for (int i = 0; i < n; i++) {
            x = ((x + data[x]) % n); // relative index
            if (x < 0) x += n;       // catch negative value from modulo

            if (x == 0 && i < n - 1) {
                return false;
            }
        }
        return (x == 0);
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
