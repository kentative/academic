package ksl.academic.algorithm.leet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

import ksl.academic.algorithm.epi.tree.Trie;

/**
 * https://leetcode.com/problems/word-search-ii/description/
 * 
 * Non-recursive solution
 * 
 * @author Kent
 *
 */
public class FindWords {
	
	public static void main(String[] args) {
		
		String[] words = {"one", "oat", "oath", "tone", "tea", "teal", "pea", "more", "mole", "moto"};
		
		char[][] board = {
				  {'o','a','t','h'},
				  {'n','a','e','e'},
				  {'e','l','p','t'},
				  {'r','o','m','o'}
				};
		
		System.out.println(findWords(board, words));

	}
	
	public static List<String> findWords(char[][] board, String[] words) {

		Trie trie = new Trie();
		for (String word : words) trie.insert(word);
		
		List<String> result = new ArrayList<>(words.length);
		int row = board.length, col = (row > 0) ?board[0].length :0;
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				result.addAll(dfs(new Param(r, c), board, trie));			
			}
		}
		return result;
		
	}
	
	private static List<String> dfs(Param param, char[][] board, Trie dict) {

		// Initialization
		List<String> result = new ArrayList<>();
		int row = board.length, col = (row > 0) ?board[0].length :0;
		
		Deque<Param> queue = new ArrayDeque<>();
		queue.add(param);
		
		// traversal loop 
		int[][] dir = { {1,0}, {0,1}, {-1,0}, {0,-1}};// , {1,1}, {-1,-1}, {1, -1}, {-1, 1}};
		while (!queue.isEmpty()) {
			
			Param x = queue.poll();
			if (x.path.contains(new Coord(x.r, x.c)))	continue; // if already visited
			
			x.buffer.append(board[x.r][x.c]);
			String prefix = x.buffer.toString();
			if (dict.search(prefix)) {
				result.add(prefix);
			}
			
			for (int[] d : dir) {
				Param adj = new Param(x.r + d[0], x.c + d[1]);
				adj.buffer = new StringBuilder(x.buffer.toString());
				adj.path = new LinkedList<>(x.path);
				adj.path.add(new Coord(x.r, x.c));
				
				if (isWalkable(adj, row, col) && dict.startsWith(prefix + board[adj.r][adj.c])) {
					queue.add(adj);
				}
			}
		}
		return result;
		
	}

	private static boolean isWalkable(Param x, int row, int col) {
		return (x.r >= 0 && x.r < row) &&
			   (x.c >= 0 && x.c < col);
	}

	private static class Param {
		
		int r;
		int c;
		StringBuilder buffer;
		List<Coord> path;
		
		Param(int r, int c) {
			this.r = r;
			this.c = c;
			this.buffer = new StringBuilder();
			this.path = new LinkedList<>();
		}
	}
	
	private static class Coord {
		int r;
		int c;
		
		public Coord(int r, int c) {
			this.r = r;
			this.c = c;
		}
		
		public boolean equals(Object obj) {
			
			if (this == obj) return true;
			
			if (!(obj instanceof Coord)) return false;
			
			Coord coord = (Coord) obj;
			return r == coord.r && c == coord.c;
		}
		
		public int hashCode() {
			return Objects.hash(r, c);
		}
	}
}
