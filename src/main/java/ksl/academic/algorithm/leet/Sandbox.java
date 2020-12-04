package ksl.academic.algorithm.leet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sandbox {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        list.add(4);
        list.add(1);
        list.add(5);
        Collections.sort(list);

        System.out.println(list);
    }

    static class Coord implements Comparable<Coord> {

        @Override
        public int compareTo(Coord o) {
            // TODO Auto-generated method stub

            double d1 = 0.0;
            double d2 = 2.2;
            return Double.compare(d1, d2);
        }

    }
}
