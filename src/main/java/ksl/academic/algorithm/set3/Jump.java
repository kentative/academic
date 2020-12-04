package ksl.academic.algorithm.set3;

import java.util.ArrayList;
import java.util.List;

public class Jump {
    public static void main(String[] args) {

        List<Integer> a = new ArrayList<>();
        a.add(3);
        a.add(4);
        a.add(1);
        a.add(2);
        a.add(5);
        a.add(6);
        a.add(9);
        a.add(0);
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(1);

        System.out.println(jump_over_numbers(a));

    }

    static int jump_over_numbers(List<Integer> list) {
        if (list == null || list.isEmpty()) return -1;

        int jump = 0;
        int position = 0;

        while (position < list.size()) {
            int next = list.get(position);
            if (next == 0) return -1;
            position += next;
            System.out.println("position " + position);
            jump++;
        }

        return jump;
    }
}
