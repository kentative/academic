package ksl.academic.algorithm.leet;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

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
		
		String[] words = {"oath","pea","eat","rain", "tea", "teal"};
		
		char[][] board = {
				  {'o','a','a','n'},
				  {'e','t','a','e'},
				  {'a','h','k','r'},
				  {'l','f','l','v'}
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
		
		// Search loop 
		StringBuilder confirmed = new StringBuilder();
		int[][] dir = { {1,0}, {0,1}, {-1,0}, {0,-1}};
		while (!queue.isEmpty()) {
			
			Param x = queue.poll();
			
			confirmed.append(board[x.r][x.c]);
			String prefix = confirmed.toString();
			if (dict.search(prefix)) {
				result.add(prefix);
			}
			
			for (int[] d : dir) {
				Param adj = new Param(x.r + d[0], x.c + d[1]);
				if (isWalkable(adj, row, col) && dict.startsWith(prefix + board[adj.r][adj.c])) {
					// Safe traversal, no need to track visited
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
		
		Param(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}
}
