package ksl.academic.instrumentation;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Performance {

    public static void main(String[] args) {
        compareLinkedList();
    }


    /**
     * Compare LinkedList insertion with ArrayList
     */
    private static void compareLinkedList() {

        int maxSize = 1 << 16; // 2 ^ 16 = 65536
        System.out.println(maxSize);
        String testValue = "This is a test";
        List<String> linkedList = new LinkedList<>();
        List<String> arrayList = new ArrayList<>(maxSize);

        long start = System.currentTimeMillis();
        for (int i = 0; i < maxSize; i++) linkedList.add(0, testValue);
        System.out.println("LinkedList time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < maxSize; i++) arrayList.add(0, testValue);
        System.out.println("ArrayList time: " + (System.currentTimeMillis() - start));

        start = System.currentTimeMillis();
        for (int i = 0; i < maxSize; i++) arrayList.add(testValue);
        System.out.println("ArrayList last: " + (System.currentTimeMillis() - start));

    }
}
