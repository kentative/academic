package ksl.academic.algorithm.amzn.tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VerticalTreePrinter {



	public static void print(Node root) {
		int maxLevel = findMaxLevel(root);
		printVTree(Collections.singletonList(root), 1, maxLevel);
	}
	
	private static void printVTree(List<Node> nodes, int level, int maxLevel) {
		
		if (nodes == null || nodes.isEmpty() || level > maxLevel) return;
		
		int floor     = 1 + maxLevel - level;
		int indent    = (1 << floor) - 1;
		int spaces    = 1 << (floor + 1);
		int edgeLines = 1 << (floor - 1);

		int offset = String.valueOf(nodes.get(0).data).length()/2;
		printWhitespaces(indent - offset);
		List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.data);
                newNodes.add(node.left);
                newNodes.add(node.right);
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            offset = String.valueOf(node.data).length();
            printWhitespaces(spaces - offset);
        }
        System.out.println("");

        for (int i = 1; i <= edgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                printWhitespaces(indent - i);
                if (nodes.get(j) == null) {
                    printWhitespaces(edgeLines + edgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).left != null)
                    System.out.print((char) 0x2571);
                else
                    printWhitespaces(1);

                printWhitespaces(i + i - 1);

                if (nodes.get(j).right != null)
                    System.out.print((char) 0x2572);
                else
                    printWhitespaces(1);

                printWhitespaces(edgeLines + edgeLines - i);
            }

            System.out.println("");
        }

        printVTree(newNodes, level + 1, maxLevel);
	}

	private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    }
	
	public static int findMaxLevel(Node root) {

		if (root == null) return 0;
		return Math.max(findMaxLevel(root.left), findMaxLevel(root.right)) + 1;
	}
}
