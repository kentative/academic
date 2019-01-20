package ksl.academic.algorithm.amzn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Given a grid size by (row, col) of values {0, 1, 9}: 
 * find the shortest path from source to destination.
 *  - source      = cell at (0,0)
 *  - destination = cell with value of 9, only 1 per grid
 *  - valid path  = cells with value of 1
 *  output: path length
 *  
 * @author Kent
 *
 */
public class ShortestPath {

	
	public static void main(String[] args) {
		
		int row = 4, col = 4;
		int[][] grid = {
				{1, 1, 1, 0}, 
				{1, 0, 1, 0},
				{1, 1, 1, 0},
				{9, 1, 0, 0}
		};
		
		List<List<Integer>> area = new ArrayList<>();		
		for (int r = 0; r < row; r++) {
			area.add(r, new ArrayList<Integer>(col));
			for (int c = 0; c < col; c++) {
				area.get(r).add(grid[r][c]);
			}
		}
		
		System.out.println(findShortestPath(row, col, area));
	}
	
	public static int findShortestPath(int row, int col, List<List<Integer>> area) {

		// init algorithm specific data
		boolean[][] visited = new boolean[row][col];
		int[][] distance = new int[row][col];			
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < row; c++) {
				distance[r][c] = Integer.MAX_VALUE;
			}
		}
		
		// create graph to encapsulate input data
		Graph g = new Graph(row, col, area);
		Map<int[], int[]> path = new HashMap<>(row * col);
		int[] source = {0, 0};
		int[] target = null;
		
		int length = 0;
		
		List<int[]> queue = new LinkedList<>();
		queue.add(source);
		while (!queue.isEmpty()) {
			
			int[] x = queue.remove(0);
			if (g.isDestination(x)) {
				target = x;
				break;
			}
			
			int r = x[0], c = x[1];
			if (visited[r][c]) continue;
			
			for (int[] adj : g.getAdjacent(r, c)) {
				int d = distance[r][c] + 1;
				if (d < distance[adj[0]][adj[1]]){
					distance[adj[0]][adj[1]] = d;
					path.put(adj, x);
					queue.add(adj);
					length++;
				}
			}
			visited[r][c] = true;
		}
		
		if (target == null) return -1;
		
		List<int[]> result = new LinkedList<>();
		for (int[] x = target; x != null; x = path.get(x)) {
			result.add(0, x);
			System.out.println(Arrays.toString(x));
		}
		
		System.out.println("Length: " + length);
		return result.size();
	}
		
	// Encapsulates data into this structure.
	// This makes the algorithm easier to implement
	static class Graph {
	
		private List<List<Integer>> area;
		private int row;
		private int col;
		
		// use to traverse the grid
		private int[][] dir;
		
		public Graph(int row, int col, List<List<Integer>> area) {
			this.row = row;
			this.col = col;
			this.area = area;
			this.dir = new int[][] { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		}
		
		public List<int[]> getAdjacent(int r, int c) {
			
			List<int[]> adjCells = new ArrayList<>(4);
			for (int[] d : dir) {
				int rAdj = r + d[0];
				int cAdj = c + d[1];
				if (isValidCell(rAdj, cAdj)) {
					int type = area.get(rAdj).get(cAdj);
					if (type != 0) {
						adjCells.add(new int[] {rAdj, cAdj});
					}
				}
			}
			return adjCells;
		}
		
		private boolean isValidCell(int r, int c) {
			return (r >= 0 && r < row) && (c >= 0 && c < col);
		}
		
		public boolean isDestination(int[] x) {
			return area.get(x[0]).get(x[1]) == 9;
		}
	}
}
