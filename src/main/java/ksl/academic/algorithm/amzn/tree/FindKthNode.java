package ksl.academic.algorithm.amzn.tree;

public class FindKthNode {

    public static void main(String[] args) {
        // Sorted balanced tree
        Node root = new Node(12);
        Node n6 = root.addLeft(6);
        Node n18 = root.addRight(18);

        Node n3 = n6.addLeft(3);
        n3.addLeft(2);
        n3.addRight(4);

        Node n9 = n6.addRight(9);
        n9.addLeft(8);
        n9.addRight(10);

        Node n15 = n18.addLeft(15);
        n15.addLeft(14);
        n15.addRight(16);

        findKth(root, 5);
        System.out.println(result);
    }

    public static int findKth(Node node, int k) {

        int result = -1;
        postOrder(node);
        return result;
    }

    static int k = 5;
    static int result = -1;

    private static void postOrder(Node node) {

        if (node == null)
            return;
        if (k == 0) {
            System.out.println(node.data);
            result = node.data;
        }

        k--;
        postOrder(node.right);
        postOrder(node.left);

//		System.out.println(node.data + " " + k);
    }

}
