package ksl.academic.algorithm.amzn.graph;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ShortestPathPractice {

	public static void main(String[] args) {
		
		int row = 4, col = 4;
		int[][] grid = {
				{1, 1, 1, 0}, 
				{0, 0, 1, 1},
				{0, 0, 0, 1},
				{9, 1, 1, 1}
		};
		
		List<List<Integer>> area = new ArrayList<>();		
		for (int r = 0; r < row; r++) {
			area.add(r, new ArrayList<Integer>(col));
			for (int c = 0; c < col; c++) {
				area.get(r).add(grid[r][c]);
			}
		}
		
		System.out.println(shortest(row, col, area));
	}

	
	/**
	 *  
	 * @param grid
	 * @return
	 */
	public static int shortest(int row, int col, List<List<Integer>> grid) {
		
		boolean[][] visited = new boolean[row][col];
		// Init cost array
		int[][] cost = new int[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				cost[r][c] = Integer.MAX_VALUE;
			}
		}
		
		Graph g = new Graph(row, col, grid);		
		Map<Coord, Coord> path = new HashMap<>();
		Coord start = new Coord(0, 0);
		Deque<Coord> queue = new ArrayDeque<>();
		queue.offer(start);
		Coord target = null;
		while (!queue.isEmpty()) {
			Coord x = queue.poll();
			
			if (visited[x.r][x.c]) continue;
			if (g.isDestination(x)) {
				target = x;
				break;
			}
			
			visited[x.r][x.c] = true; 
			for (Coord adj : g.getAdjList(x)) {
				int adjCost = cost[x.r][x.c]+1;
				if (adjCost < cost[adj.r][adj.c]) {
					cost[adj.r][adj.c]= adjCost;
					path.put(adj, x);
					queue.offer(adj);
				}
			}
			
		}
		
		List<Coord> result = new LinkedList<>();
		for (Coord x = target; x != null; x = path.get(x)) {
			result.add(0, x);
		}
		return result.size();
	}
	
	static class Graph {
		
		private int row;
		private int col;
		private List<List<Integer>> grid;
		private int[][] dir;
		
		public Graph(int row, int col, List<List<Integer>> grid) {
			
			this.row = row; 
			this.col = col;
			this.grid = grid;
			this.dir = new int[][] {{0,1}, {0,-1}, {1,0}, {-1,0}};
		}

		public List<Coord>getAdjList(Coord x) {
			
			List<Coord> result = new LinkedList<>();
			for (int[] d : dir) {
				Coord coord = new Coord(x.r + d[0], x.c + d[1]);
				if (isValid(coord)) {
					if (grid.get(coord.r).get(coord.c) != 0) {
						result.add(coord);
					}
				}
			}
			return result;
		}

		public boolean isDestination(Coord x) {
			return isValid(x) && grid.get(x.r).get(x.c) == 9;
		}

		private boolean isValid(Coord x) {
			return x.r >= 0 && x.r < row && x.c >= 0 && x.c < col;
		}
	}
	
	static class Coord {
		int r;
		int c;
		
		public Coord(int r, int c) {
			this.r = r; 
			this.c = c;			
		}
	}
}
