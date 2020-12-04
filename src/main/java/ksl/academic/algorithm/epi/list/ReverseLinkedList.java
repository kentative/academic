package ksl.academic.algorithm.epi.list;

public class ReverseLinkedList {

    public static void main(String[] args) {

        Node n = buildNode(5, 7, 9, 10, 33);

        Node r = reverse(n);
        System.out.println(print(r));

    }

    static Node reverse(Node l) {

        Node x, n, p;

        p = null;
        x = l;
        while (x != null) {

            n = x.next;
            x.next = p;
            p = x;
            x = n;
        }
        return p;
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
