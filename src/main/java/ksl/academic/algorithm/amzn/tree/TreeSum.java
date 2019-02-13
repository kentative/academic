package ksl.academic.algorithm.amzn.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeSum {

	public static void main(String[] args) {
		// Sorted balanced tree
		Node root = new Node(12);
		Node n6 = root.addLeft(6);
		Node n18 = root.addRight(18);

		Node n3 = n6.addLeft(3);
		n3.addLeft(2);
		n3.addRight(4);

//		Node n9 = n6.addRight(9);
//		n9.addLeft(8);
//		n9.addRight(10);

//		Node n15 = n18.addLeft(15);
//		n15.addLeft(14);
//		n15.addRight(16);
//
//		Node n21 = n18.addRight(21);
//		n21.addLeft(20);
//		n21.addRight(22);
		
		System.out.println(HorizontalTreePrinter.prettyPrint(root));
		
		for (List<Node> nodes : lessThanPath(root, 26)) {
			for (Node n : nodes) {
				System.out.print(n.data + " ");
			}
			System.out.println();
		}
		
	}

	public static List<List<Node>> lessThanPath(Node root, int sum) {

		List<List<Node>> paths = new ArrayList<>();
		visit(root, 0, new HashMap<Integer, Node>(), sum, paths);
		return paths;
	}

	private static void visit(Node node, int length, Map<Integer, Node> path, int sum, List<List<Node>> validPath) {
		if (node == null)
			return;

		path.put(length, node);
		length++;
		if (node.left == null && node.right == null) {
			List<Node> pathNode = new ArrayList<>();
			int total = 0;
			for (int i = 0; i < length; i++) {
				Node n = path.get(i);
				total += n.data;
				pathNode.add(n);
			}
			if (total < sum) {
				validPath.add(pathNode);
			}
			
		} else {
			visit(node.left, length, path, sum, validPath);
			visit(node.right, length, path, sum, validPath);
		}
	}
}
