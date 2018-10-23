package ksl.academic.algorithm.epi.tree;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ksl.academic.structure.Node;
import ksl.academic.structure.Stack;

public class DepthFirstTraversal {

	static Logger logger = LoggerFactory.getLogger(Node.class);
	
	public static void main(String[] args) {
		Node<String> root = new Node<>("4");
		Node<String> d1n1 = root.setLeft(new Node<>("2"));
		d1n1.setLeft(new Node<>("1"));
		d1n1.setRight(new Node<>("3"));
		
		Node<String> d1n2 = root.setRight(new Node<>("6"));
		d1n2.setLeft(new Node<>("5"));
		d1n2.setRight(new Node<>("7"));
		
		logger.info("----------------------------");
		logger.info("RECURSIVE pre order: ");
		preOrder(root);
		logger.info("ITR ------------------------");		
		preOrderItr(root);
		
		logger.info("---------------------------");
		logger.info("---------------------------");
		logger.info("RECURSIVE in order: ");
		inOrder(root);
		logger.info("ITR ------------------------");
		inOrderItr(root);
		
		
		logger.info("---------------------------");
		logger.info("---------------------------");
		logger.info("RECURSIVE post order: ");
		postOrder(root);
		logger.info("ITR ------------------------");
		postOrderItr(root);
	}

	private static void preOrder(Node<String> node) {
		if (node == null) return; 
		logger.info(node.data);
		preOrder(node.getLeft());
		preOrder(node.getRight());		
	}
	
	private static void preOrderItr(Node<String> node) {
		
		if (node == null) return;
		Stack<Node<String>> s = new Stack<>();
		
		s.push(node);
		while (!s.isEmpty()) {
			node = s.pop();
			logger.info(node.data);
			s.push(node.right);    // push reverse order
			s.push(node.left);
		}		
	}
	
	private static void inOrder(Node<String> node) {
		if (node == null) return; 
		inOrder(node.getLeft());
		logger.info(node.data);
		inOrder(node.getRight());		
	}
	
	private static void inOrderItr(Node<String> node) {
		
		Stack<Node<String>> s = new Stack<>();
		while (!s.isEmpty() || node != null) {
			if (node != null) {
				// walk left all the way down
				s.push(node);
				node = node.left;				
			} else {
				node = s.pop();
				logger.info(node.data);
				
				// walk right
				node = node.right;
			}
		}
	}
	
	private static void postOrder(Node<String> node) {
		if (node == null) return; 
		postOrder(node.getLeft());
		postOrder(node.getRight());		
		logger.info(node.data);
	}
	
	private static void postOrderItr(Node<String> node) {
		
		Stack<Node<String>> s = new Stack<>();
		Node<String> last = null;
		while (!s.isEmpty() || node != null) {
			if (node != null) {
				// walk left all the way down
				s.push(node);	
				node = node.left;
			} else {
				// if right exists and walking
				Node<String> peek = s.peek();
				if (peek.right != null && last != peek.right) {
					node = peek.right;
				} else {
					logger.info(peek.data);
					last = s.pop();
				}
			}
		}
	}
}
