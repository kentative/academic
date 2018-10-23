package ksl.academic.algorithm.epi.tree;

public class Trie {
	
	public static void main(String[] args) {
		
		Trie trie = new Trie();
		trie.insert("kent");
		System.out.println(trie.search("kent"));
		System.out.println(trie.searchPrefix("kent"));
		
	}
	
	private Node root;
	
	public Trie() {
		root = new Node();
	}
	
	/**
	 * Adds a node if needed for each character. 
	 * Set the word flag on the last character of the word.
	 * 
	 * @param word - the word to be added
	 */
	public void insert(String word) {
		
		Node node = root;		
		char[] wordArray = word.toCharArray();
		for (char c : wordArray) {
			if (!node.containsKey(c)) {
				node.put(c, new Node());
			}
			node = node.get(c);
		}
		node.isEnd = true;
	}

	
	/**
	 * Scan through each char, if each has a corresponding node
	 * and the last char has the word flag set, return true. 
	 * Otherwise, returns false. 
	 * 
	 * @param word - the word to be searched
	 * @return true if the word is contained in this trie
	 */
	public boolean search(CharSequence word) {
		Node node = searchPrefix(word);
		return (node != null && node.isEnd); 
	}
	
	
	/**
	 * 
	 * @param prefix
	 * @return
	 */
	public Node searchPrefix(CharSequence prefix) {
		Node node = root;
		for (int i = 0; i < prefix.length(); i++) {
			char c = prefix.charAt(i);
			if (!node.containsKey(c)) return null;
			node = node.get(c);
		}
		
		// Node needs to have the endFlag set
		return node;
	}
	
	public boolean startsWith(CharSequence prefix) {
		 return searchPrefix(prefix) != null;
	}
	
	
	/**
	 * Internal representation of a Trie node 
	 */
	private static class Node {

		private static final int CHAR_MASK = 'a';
		private Node[] nodes;
		private boolean isEnd;

		// Lowercase English alphabet
		private final int RADIX = 26;

		public Node() {
			nodes = new Node[RADIX];
		}

		public boolean containsKey(char c) {
			return nodes[c - CHAR_MASK] != null;
		}

		public Node get(char c) {
			return nodes[c - CHAR_MASK];
		}

		public void put(char c, Node node) {
			nodes[c - CHAR_MASK] = node;
		}

	}
}
