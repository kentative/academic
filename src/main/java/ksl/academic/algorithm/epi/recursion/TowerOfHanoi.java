package ksl.academic.algorithm.epi.recursion;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Given T1(source), T2(destination), T3
 * move n-1 disks to T3 using T2 as buffer
 * move nth disk to T2
 * move n-1 disks to T2 using T3 as buffer
 * <p>
 * move n-1 to buffer
 * move last to destination
 * move n-1 to destination
 */
public class TowerOfHanoi {

    public static void main(String[] args) {

        int disks = 4;

        @SuppressWarnings("unchecked")
        Deque<Integer>[] towers = new Deque[3];
        for (int i = 0; i < towers.length; i++) {
            towers[i] = new ArrayDeque<Integer>(disks);
        }

        for (int i = 0; i < disks; i++) {
            towers[0].push(disks - i);
        }

        move(towers, disks, 0, 1, 2);
//		System.out.println();
//		moveItr(towers, disks, 0, 1, 2);
    }

    public static void move(Deque<Integer>[] towers, int n, int from, int to, int buffer) {

        if (n > 0) {
            move(towers, n - 1, from, buffer, to);
            transfer(towers, from, to);
            move(towers, n - 1, buffer, to, from);
        }
    }

    private static void transfer(Deque<Integer>[] towers, int from, int to) {

        int disk = towers[from].pop();
        towers[to].push(disk);
        System.out.println("Moving D" + disk + " from T" + from + " to T" + to);

    }

    private static class Param {
        int from;
        int to;
        int use;
        int disk;
        boolean transfer;

        Param(int from, int to, int use, int disk, boolean transfer) {
            this.from = from;
            this.to = to;
            this.use = use;
            this.disk = disk;
            this.transfer = transfer;
        }
    }


}
