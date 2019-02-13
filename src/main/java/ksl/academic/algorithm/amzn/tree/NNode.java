package ksl.academic.algorithm.amzn.tree;

import java.util.LinkedList;
import java.util.List;

/**
 * N-ary Node
 * @author Kent
 *
 */
public class NNode {

	int data;
	List<NNode> nodes;
	
	public NNode(int data) {
		this.data = data;
		nodes = new LinkedList<>();
	}
	
	public NNode addNode(NNode n) {
		nodes.add(n);
		return n;
	}
}
