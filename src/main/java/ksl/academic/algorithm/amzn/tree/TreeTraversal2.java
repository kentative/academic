package ksl.academic.algorithm.amzn.tree;

import ksl.academic.structure.tree.Node;

public class TreeTraversal2 {

    // 1 2 3
    //
    public static void main(String... args) {

        NNode root = new NNode(10);
        NNode n2 = root.addNode(new NNode(20));
        NNode n3 = root.addNode(new NNode(30));
        NNode n4 = root.addNode(new NNode(40));

        n2.addNode(new NNode(21));
        n2.addNode(new NNode(22));
        n2.addNode(new NNode(23));

        n3.addNode(new NNode(31));
        n3.addNode(new NNode(32));
        n3.addNode(new NNode(33));

        n4.addNode(new NNode(41));
        n4.addNode(new NNode(42));
        n4.addNode(new NNode(43));

        dfs(root);

    }

    private static void inOrder(Node<String> node) {
        if (node == null) return;
        inOrder(node.left);
        System.out.println(node.data);
        inOrder(node.right);
    }


    public static void dfs(NNode node) {


        if (node == null) return;
        for (NNode n : node.nodes) {
            dfs(n);
        }
        System.out.println(node.data);
    }


    public static String bfs(NNode root) {

        return "";
    }
}
