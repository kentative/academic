package ksl.academic.algorithm.amzn.tree;

public class Node {
	
	public Node left;
	public Node right;
	public int data;
	
	public Node(int x) {
		this.data = x;
	}
	
	public Node addLeft(int x) {
		left = new Node(x);
		return left;
	}
	
	public Node addRight(int x) {
		right = new Node(x);
		return right;
	}
	
	public StringBuilder print(StringBuilder prefix, boolean isTail, StringBuilder sb) {
	    
		if(right!=null) {
	        right.print(
	        		new StringBuilder(prefix)
	        		.append(isTail ? "│   " : "    "), false, sb);
	    }

		String connector = isTail? "└── " : "┌── ";
	    sb.append(prefix).append(connector + data + "\n");
	    
	    if(left!=null) {
	        left.print(
	        		new StringBuilder(prefix)
	        		.append(isTail ? "    " : "│   "), true, sb);
	    }
	    
	    return sb;
	}

	@Override
	public String toString() {
	    return this.print(
	    		new StringBuilder(), 
	    		true, 
	    		new StringBuilder()).toString();
	}
	
}