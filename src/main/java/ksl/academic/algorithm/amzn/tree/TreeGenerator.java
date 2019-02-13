package ksl.academic.algorithm.amzn.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeGenerator {

	public static void main(String[] args) {
		
		int n = 5;
		
//		TreeUtil.print(generateByCount(n, 0));
		
		List<Node> generateTrees = generateAll(n);
		System.out.println("Forest size: " + generateTrees.size());
		for (Node node : generateTrees) {
			TreeUtil.print(node);
		}
		System.out.println("Number of invocations: " + invokeCounter);
		
		int depth = 31 - Integer.numberOfLeadingZeros(n);

		Node node = generateByCount(n, depth);
		TreeUtil.print(node);
		TreeUtil.print(generateByDepth(depth));
		
	}
	
	static int counter = 1;
	public static Node generateByDepth(int depth) {
		
	    if (depth < 0) return null;
	    
	    Node node = new Node(counter++);
	    if (depth > 0) {
	        node.left = generateByDepth(depth-1);
	        node.right = generateByDepth(depth-1);
	    }
	    return node;
	}
	
	/**
	 * Generate a complete binary with n nodes
	 * @param n - the number of nodes
	 * @return the tree
	 */
	private static Node generateByCount(int n, int i) {
		
		if (i >= n) return null;

		Node node = new Node(i);
		node.left  = generateByCount(n, 2*i +1);
		node.right = generateByCount(n, 2*i +2);
		
		return node;
	}
	
	
	/**
	 * 
	 * @param n
	 * @return
	 */
	public static List<Node> generateAll(int n) {
		int depth = 31 - Integer.numberOfLeadingZeros(n);
		System.out.println("depth: " + depth);
		
        List<Node> helper = helper(1, n);
        return helper.stream().filter(x -> getDepth(x) <= depth+1)
        		.collect(Collectors.toList());
    }
    
	
	static Map<Integer, List<Node>> cache = new HashMap<>();
	static int invokeCounter = 0;
	/**
     * Helper function to generate complete binary tree
     * @param start
     * @param end
     * @return
     */
    private static List<Node> helper(int start, int end) {
    	
    	// memoization
    	int key = (start << 5) + end;
    	if (cache.containsKey(key)) {
    		return cache.get(key);
    	}
    	
    	invokeCounter++;
    	List<Node> result = new ArrayList<Node>();
        if (start > end) {
            result.add(null);
            return result;
        }

        for (int i = start; i <= end; i++) {
            List<Node> left = helper(start, i - 1);
            List<Node> right = helper(i + 1, end);
            
            for (Node l : left) {
            	for (Node r : right) {
            		Node root = new Node(i);
            		root.left = l;
            		root.right = r;
            		result.add(root);
            	}
            }
        }

        cache.put(key, result);
        return result;
    }

	private static int getDepth(Node root) {
		if (root == null) return 0;
		
		return Math.max(getDepth(root.left), getDepth(root.right)) + 1;
	}
}
