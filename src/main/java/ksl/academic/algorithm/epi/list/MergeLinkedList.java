package ksl.academic.algorithm.epi.list;

public class MergeLinkedList {

    public static void main(String[] args) {

        Node l1 = buildNode(5, 7, 9, 10, 33);
        Node l2 = buildNode(2, 3, 8, 14, 16, 18, 19);

        System.out.println(print(l1));
        Node l3 = mergeList(l1, l2);
        System.out.println(print(l3));

    }

    static Node mergeList(Node l1, Node l2) {

        Node x = new Node();
        Node head = x;
        while (l1 != null && l2 != null) {
            if (l1.getData() <= l2.getData()) {
                x.next = l1;
                l1 = l1.next;
            } else {
                x.next = l2;
                l2 = l2.next;
            }
            x = x.next;
        }

        x.next = (l1 == null) ? l2 : l1;
        return head.next;
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
