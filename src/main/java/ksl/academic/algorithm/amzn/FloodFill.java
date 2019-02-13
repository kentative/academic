package ksl.academic.algorithm.amzn;

import java.util.Arrays;
import java.util.*;


public class FloodFill {

public static void main(String[] args) {
		
		int[][] bitmap = new int[][] {
			{1, 2, 1, 4}, 
			{1, 1, 1, 4},
			{0, 2, 1, 1},
			{1, 1, 1, 4}
		};
		
		// Before
		for (int[] row : bitmap) System.out.println(Arrays.toString(row));
		fill(bitmap, 0, 0, 3);
		
		// After
		System.out.println();
		for (int[] row : bitmap) System.out.println(Arrays.toString(row));
		
	}

	// 
	private static void fill(int[][] bitmap, int x, int y, int target) {
		
		int[][] dir = new int[][] { {0,1}, {1,0}, {0,-1}, {-1,0} }; 
		
		int row = bitmap.length, col = bitmap[0].length;
		
		boolean[][] visited = new boolean[row][col];
		Deque<int[]> stack = new ArrayDeque<>();
		stack.push(new int[] {x, y});
		
		int source = bitmap[x][y];
		while (!stack.isEmpty()) {

			int[] coord = stack.pop();
			int r = coord[0], c = coord[1];

			if (visited[r][c]) continue;
			
			// Visit cell, color it
			bitmap[r][c] = target;
			visited[r][c] = true;
			
			for (int[] d : dir) {
				int adjR = r + d[0], adjC = c + d[1];
				
				if (isWalkable(row, col, adjR, adjC) && 
						isValid(bitmap, adjR, adjC, source)) {
					
					stack.push(new int[] {adjR, adjC});
				}
			}			
		}
	}
	
	// check if r and c is within the boundary of the grid
	private static boolean isWalkable(int row, int col, int r, int c) {
		return (r >=0 && r < row && c >= 0 && c < col);
	}
	
	// check if the cell is valid for fill --the value is the same as the source
	private static boolean isValid(int[][] bitmap, int r, int c, int source) {
		return (bitmap[r][c] == source);
	}

}
