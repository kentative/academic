package ksl.academic.algorithm.epi.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SearchMaze {

	public static void main(String[] args) {
		
		boolean[][] grid = {
				{false, true, true, true, true},
				{true,  true,  false, true, true},
				{true,  true,  true, false, false}
		};
		
		System.out.println(searchMaze(grid, new Coord(2, 0), new Coord(0, 4)));
	}
	
	static boolean[][] visited;
	static List<Coord> path;
	
	static List<Coord> searchMaze(boolean[][] grid, Coord s, Coord t) {

		// initialization
		path = new ArrayList<>();
		visited = new boolean[grid.length][grid[0].length];
		
		// Visit the entrance
		path.add(s);
//		visited[s.x][s.y] = true;
		grid[s.x][s.y] = false;
		
		// Walk the graph
		if (!walk(grid, s, t)) path.remove(s);
		return path;
	}

	private static boolean walk(boolean[][] grid, Coord s, Coord t) {
		
		if (s.equals(t)) return true;
		
		int[][] dir = {{0, 1},  {0,-1}, {1,0}, {-1,0}};		
		for (int[] d : dir) {
		
			Coord next = new Coord(s.x+d[0], s.y+d[1]);
			
			if (isFeasible(grid, next)) {
				path.add(next);
//				visited[s.x][s.y] = true;
				grid[s.x][s.y] = false;
				if (walk(grid, next, t)) return true;
				path.remove(next);
			}
		}
		
		return false;
	}

	private static boolean isFeasible(boolean[][] grid, Coord s) {
		
		return (s.x >= 0 && s.x < grid.length && 
				s.y >= 0 && s.y < grid[0].length && 
				grid[s.x][s.y]); // && !visited[s.x][s.y]);
	}
}

class Coord {
	
	int x;
	int y;
	
	Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public String toString() {
		return "(" + x + "," + y +")";
	}
	
	public boolean equals(Object o) {
		if (o == this) return true;
		if (getClass() != o.getClass()) return false;
		Coord c = (Coord) o;
		return (x == c.x && y == c.y); 
	}
	
	public int hashCode() {
		return Objects.hash(x, y);
	}
}

