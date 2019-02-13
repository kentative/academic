package ksl.academic.algorithm.amzn;

import ksl.academic.algorithm.Utility;

public class Sudoku {

	public static void main(String[] args) {
		
		char[][] board = new char[][] {
		  {'5','3','.','.','7','.','.','.','.'},
		  {'6','.','.','1','9','5','.','.','.'},
		  {'.','9','8','.','.','.','.','6','.'},
		  {'8','.','.','.','6','.','.','.','3'},
		  {'4','.','.','8','.','3','.','.','1'},
		  {'7','.','.','.','2','.','.','.','6'},
		  {'.','6','.','.','.','.','2','8','.'},
		  {'.','.','.','4','1','9','.','.','5'},
		  {'.','.','.','.','8','.','.','7','9'}
		};
		
		System.out.println(Utility.toString(board));
		System.out.println(isValidSudoku(board));
	}

	public static boolean isValidSudoku(char[][] board) {
		
		int size = 9, b = 3;
		for (int i = 0; i < size; i++) {

			boolean[][] cache = new boolean[3][size+1];
			for (int j = 0; j < size; j++) {
				if (hasDuplicate(board[i][j], cache[0])) return false;
				if (hasDuplicate(board[j][i], cache[1])) return false;
				if (hasDuplicate(
						board[((i/b)* b) + j/b]
							 [((i%b)* b) + j%b], cache[2])) return false;
			}
		}
		return true;   
    }

	private static boolean hasDuplicate(char c, boolean[] cache) {
		
		if (c == '.') return false; 
		int index = c-'0';
		if (cache[index]) return true;
		cache[index] = true;
		return false;
	}


}
