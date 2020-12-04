package ksl.academic.algorithm.crack;

import java.util.Collections;
import java.util.PriorityQueue;

public class ContinuousMedian {


    public static void main(String[] args) {

        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        System.out.println(maxQ.peek());
    }
}
