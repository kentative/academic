package ksl.academic.algorithm.amzn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Initial implementation (whiteboard version)
 * @author Kent
 *
 */
public class ShortestPath0 {

	
	public static void main(String[] args) {
		
		int row = 4, col = 4;
		int[][] grid = {
				{1, 1, 1, 0}, 
				{1, 0, 1, 0},
				{0, 1, 1, 0},
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

		boolean[][] visited = new boolean[row][col];
		int[][] distance = new int[row][col];
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < row; c++) {
				distance[r][c] = Integer.MAX_VALUE;
			}
		}
		Map<Coord, Coord> path = new HashMap<>(row * col);
		Coord source = new Coord(0, 0, 1);
		distance[0][0] = 0;
		Coord target = null;
		
		List<Coord> queue = new LinkedList<>();
		queue.add(source);
		while (!queue.isEmpty()) {
			
			Coord x = queue.remove(0);
			
			if (x.type == 9) {
				target = new Coord(x.r, x.c, 9);
				break;
			}
			
			if (visited[x.r][x.c]) continue;
			visited[x.r][x.c] = true;
			
			for (Coord adj : getAdjacent(x, row, col, area)) {
				int d = distance[x.r][x.c] + 1;
				if (d < distance[adj.r][adj.c]) {
					distance[adj.r][adj.c] = d;
					System.out.println("adding " + adj.r + ", " + adj.c + " distance "+ d);
					path.put(adj, x);
					queue.add(adj);
				}
			}
		}
		
		if (target == null) return -1;
		
		
		List<Coord> result = new ArrayList<>();
		for (Coord x = target; x != null; x = path.get(x)) {
			result.add(0, x);
		}
		return result.size();
	}
	
	
	private static List<Coord> getAdjacent(Coord x, int row, int col, List<List<Integer>> area) {
		
		List<Coord> adjCells = new ArrayList<>(4);
		
		int[][] dir = { {1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		for (int[] d : dir) {
			int r = x.r + d[0];
			int c = x.c + d[1];
			if (isValidCell(r, c, row, col)) {
				int type = area.get(r).get(c);
				if (type != 0) {
					adjCells.add(new Coord(r, c, type));
				}
			}
		}
		return adjCells;
	}


	private static boolean isValidCell(int r, int c, int row, int col) {
		return (r >= 0 && r < row) && (c >= 0 && c < col);
	}


	static class Coord {
		
		int r;
		int c;
		int type;
		
		public Coord(int r, int c, int type) {
			this.r = r; 
			this.c = c;
			this.type = type;
		}
		
		public int hashCode() {
			return Objects.hash(r, c);
		}
		
		public boolean equals(Object obj) {
			if (this == obj) return true;
			
			if (!(obj instanceof Coord)) return false;
			
			Coord coord = (Coord) obj;
			return this.r == coord.r && this.c == coord.c;
		}
		
	}
}
