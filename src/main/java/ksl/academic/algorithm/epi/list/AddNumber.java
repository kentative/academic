package ksl.academic.algorithm.epi.list;

import static com.google.common.base.Preconditions.checkNotNull;

public class AddNumber {

    static int BASE = 10;

    public static void main(String[] args) {
        Node n1 = buildNode(1, 2, 3);
        Node n2 = buildNode(4, 5, 6, 7);
        Node sumLinkedList = sumLinkedList(n1, n2);
        System.out.println(print(sumLinkedList, 10));

    }

    public static Node sumLinkedList(Node n1, Node n2) {
        // Check inputs
        checkNotNull(n1);
        checkNotNull(n2);

        // Pad list to equal size
        int l1 = findLength(n1);
        int l2 = findLength(n2);
        int maxLength = Math.max(l1, l2);
        n1 = pad(n1, maxLength - l1);
        n2 = pad(n2, maxLength - l2);

        // Add and carry recursively
        PartialSum partial = add(n1, n2);

        if (partial.carry == 0) {
            return partial.sum;
        } else {
            return insertBefore(partial.sum, partial.carry);
        }
    }

    private static PartialSum add(Node n1, Node n2) {

        // Base case, beyond the last digit
        if (n1 == null) {
            return new PartialSum();
        }

        // Add recursively
        PartialSum partial = add(n1.next, n2.next);

        // Add digit
        int value = n1.data + n2.data + partial.carry;

        // insert in front
        Node result = insertBefore(partial.sum, value % BASE);
        partial.sum = result;
        partial.carry = value / BASE;
        return partial;
    }

    private static Node insertBefore(Node list, int data) {
        Node n = new Node();
        n.data = data;
        if (list != null) {
            n.next = list;
        }
        return n;
    }

    static class PartialSum {
        Node sum;
        int carry;
    }

    private static Node pad(Node n, int length) {

        if (length == 0) return n;

        for (int i = 0; i < length; i++) {
            n = insertBefore(n, 0);
        }
        return n;
    }

    private static int findLength(Node n) {

        int length = 0;
        while (n != null) {
            length++;
            n = n.next;
        }
        return length;
    }

    static class Node {
        private int data;
        private Node next;

        public String toString() {
            return String.valueOf(this.data);
        }
    }

    private static Node buildNode(int... data) {

        Node head = new Node();
        head.data = data[0];

        Node list = head;
        for (int i = 1; i < data.length; i++) {
            Node next = new Node();
            next.data = data[i];
            list.next = next;
            list = next;

        }
        return head;
    }

    private static String print(Node n, int max) {

        StringBuilder sb = new StringBuilder();
        Node x = n;
        int i = 0;
        while (x != null) {
            sb.append(x.data).append(" ");
            x = x.next;
            if (++i >= max) break;
        }
        return sb.toString();
    }
}
