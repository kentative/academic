package ksl.academic.algorithm.set4;

import java.util.LinkedList;
import java.util.List;

import ksl.academic.structure.tree.Node;
import ksl.academic.structure.tree.TreeUtil;

/**
 * The Class CountTreeNodes.
 */
public class CountTreeNodes {

    /**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
        Node<Integer> root = new Node<Integer>(26);
        TreeUtil.add(root, 17, 41);
        TreeUtil.add(root.left, 17, 21, true);
        TreeUtil.add(root.right, 30, 47);
        TreeUtil.add(root.left.left, 31, 42);

        System.out.println(count(root));
        System.out.println(countItr(root));
    }


    /**
     * Count.
     * <p>
     * Analysis:
     * h = log(n)
     * Time  = worst case recurse at every level, but 1 fewer height
     * = h + (h-1) + (h-2)
     * = 1 + 2 + 3 + h
     * = h(h-1)/2
     * = (h^2 - h)/2 = O(h^2)
     * <p>
     * Space = call stack
     * = left tree + right tree
     * = O(h + h) = O(h)
     *
     * @param node the node
     */
    public static int count(Node<Integer> node) {

        // traverse both subtree and count depth while right != null
        int h = 0;
        Node<Integer> left = node;
        Node<Integer> right = node;
        while (right != null) {
            h++;
            left = left.left;
            right = right.right;
        }

        if (left == null) {
            System.out.println("balanced");
            return (1 << h) - 1;
        } else {
            System.out.println("left heavy");
            return 1 + count(node.left) + count(node.right);
        }
    }

    // Iterative approach
    public static int countItr(Node<Integer> node) {

        int count = 0;
        List<Node<Integer>> queue = new LinkedList<>();
        queue.add(node);

        while (!queue.isEmpty()) {

            Node<Integer> x = queue.remove(0);
            Node<Integer> left = x;
            Node<Integer> right = x;

            int h = 0;
            while (right != null) {
                h++;
                left = left.left;
                right = right.right;
            }

            if (left == null) {
                count += (1 << h) - 1;
            } else {
                queue.add(x.left);
                queue.add(x.right);
                count++;
            }
        }
        return count;
    }

}
