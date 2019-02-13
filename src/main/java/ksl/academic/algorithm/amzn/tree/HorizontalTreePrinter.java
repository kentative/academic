package ksl.academic.algorithm.amzn.tree;

public class HorizontalTreePrinter {

	public static String print(Node node) {
		return printHTree(node, 1, new StringBuilder()).toString();
	}
	
	private static StringBuilder printHTree(Node node, int level, StringBuilder buffer) {

		if (node == null) return null;
		printHTree(node.right, level + 1, buffer);
		
		for (int i = 0; i < level *4; i++) buffer.append(" ");
		
		buffer.append(String.valueOf(node.data) + "\n");
		
		printHTree(node.left, level + 1, buffer);
		
		return buffer;
	}

	public static String prettyPrint(Node node) {
		return printHTree(node, true, new StringBuilder(), new StringBuilder()).toString();
	}

	/**
	 * 
	 *  	│           ┌── 22
	 *		│       ┌── 21
	 *		│       │   └── 20
	 *		│   ┌── 18
	 *		│   │   │   ┌── 16
	 *		│   │   └── 15
	 *		│   │       └── 14
	 *		└── 12
	 *		    │       ┌── 10
	 *		    │   ┌── 9
	 *		    │   │   └── 8
	 *		    └── 6
	 *		       │   ┌── 4
	 *		        └── 3
	 *		            └── 2
	 * @param node
	 * @param isTail
	 * @param prefix
	 * @param sb
	 * @return
	 */
	private static StringBuilder printHTree(Node node, boolean isTail, StringBuilder prefix, StringBuilder sb) {

		String connector;
		if (node.right != null) {
			connector = isTail ? "│   " : "    ";
			printHTree(node.right, false, new StringBuilder(prefix + connector), sb);
		}

		connector = isTail ? "└── " : "┌── ";
		sb.append(prefix).append(connector + node.data + "\n");

		if (node.left != null) {
			connector = isTail ? "    " : "│   ";
			printHTree(node.left, true, new StringBuilder(prefix + connector), sb);
		}
		return sb;
	}
	
	public static void main(String[] args) {
		System.out.println((char) 65);
		System.out.println((int) '─');
		System.out.println((char) 0x2571);
	}
}
