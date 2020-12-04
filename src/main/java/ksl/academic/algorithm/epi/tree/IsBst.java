package ksl.academic.algorithm.epi.tree;

import java.util.Stack;

import ksl.academic.structure.tree.Node;
import ksl.academic.structure.tree.TreeUtil;

public class IsBst {

    public static void main(String[] args) {

        Node<Integer> root = new Node<>(26);
        add(root, 17, 41);
        add(root.left, 14, 21);
        add(root.right, 30, 47);

        inorder(root);

        System.out.println(TreeUtil.printTree(root));
        System.out.println(isBinearySearchTree(root));
    }

    static boolean isBinearySearchTree(Node<Integer> node) {

        Stack<Node<Integer>> stack = new Stack<>();
        int previous = Integer.MIN_VALUE;
        while (!stack.isEmpty() || node != null) {

            // Always exhaust left branch first
            if (node != null) {
                stack.push(node);
                node = node.left;
                continue;
            }

            node = stack.pop();
            if (previous > node.data) return false;
            previous = node.data;
            node = node.right;
        }

        return true;
    }

    static void inorder(Node<Integer> n) {

        if (n == null) return;
        inorder(n.left);
        System.out.println(n);
        inorder(n.right);
    }

    private static void add(Node<Integer> node, int left, int right) {
        node.left = new Node<Integer>(left);
        node.right = new Node<Integer>(right);
    }


}
