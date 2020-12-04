package ksl.academic.algorithm.epi.list;

public class DetectCycle {

    public static void main(String[] args) {

        Node n = buildNode(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        n.next.next.next
                .next.next.next
                .next.next.next = n.next.next.next
                .next.next.next;

        System.out.println(hasCycle(n));
        System.out.println(getCycle(n));
        System.out.println(print(n, 20));

    }

    static boolean hasCycle(Node n) {
        if (n == null) return false;

        Node slow = n, fast = n;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) return true;
        }
        return false;
    }

    /**
     * Get the first node of the cycle.
     *
     * @param n - the root node
     * @return the first node of the cycle, if cycle exists, null otherwise
     */
    static Node getCycle(Node n) {

        if (n == null) return null;

        // Detect cycle
        Node s = n, f = n;
        boolean hasCycle = false;
        while (f != null && f.next != null) {
            s = s.next;
            f = f.next.next;

            if (s == f) {
                hasCycle = true;
                break;
            }
        }

        if (!hasCycle) return null;

        // Cycle exists, find the node where the cycle begins
        System.out.println("first slow :" + print(s, 1));

        // Reset the slow pointer to the beginning of the list.
        // Advance slow and fast pointers at the same pace.
        // The next node slow and fast meets, that node is the start of the cycle.
        s = n;
        while (s != f) {

            System.out.println("slow :" + print(s, 1));
            System.out.println("fast :" + print(f, 1));
            s = s.next;
            f = f.next;
        }
        return s;
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

    static class Node {
        private int data;
        private Node next;


        public String toString() {
            return String.valueOf(this.data);
        }
    }

}
