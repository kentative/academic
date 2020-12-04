package ksl.academic.algorithm.epi.list;

public class RotateList {

    public static void main(String[] args) {

        Node n = buildNode(1, 2, 3, 4, 5);
        System.out.println(print(rotate(n, 3)));
    }

    static Node rotate(Node list, int k) {

        if (list == null) return null;

        // find size, create cycle
        int size = 1;
        Node x = list, head = list;
        while (x.next != null) {
            size++;
            x = x.next;
        }
        x.next = list;

        // find head of rotated array
        int offset = size - k;
        while (offset > 1) {
            offset--;
            head = head.next;
        }
        Node hRotated = head.next;
        head.next = null;

        return hRotated;
    }


    private static Node buildNode(int... data) {

        Node head = new Node();
        head.setData(data[0]);

        Node list = head;
        for (int i = 1; i < data.length; i++) {
            Node next = new Node();
            next.setData(data[i]);
            list.setNext(next);
            list = next;

        }
        return head;
    }

    private static String print(Node n) {

        StringBuilder sb = new StringBuilder();
        Node x = n;
        while (x != null) {
            sb.append(x.getData()).append(" ");
            x = x.getNext();
        }
        return sb.toString();
    }

    static class Node {
        private int data;
        private Node next;


        public String toString() {
            return String.valueOf(this.data);
        }

        public int getData() {
            return data;
        }

        public void setData(int data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }

}
