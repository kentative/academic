package ksl.academic.algorithm.epi.array;

import ksl.academic.algorithm.Utility;

public class Spiral {
	
	
	public static void main(String[] args) {
		Spiral s = new Spiral();
		
		int[][] grid = new int[6][5];
		grid[0] = new int[] { 1,  2,  3,  4, 5};
		grid[1] = new int[] { 6,  7,  8,  9, 10};
		grid[2] = new int[] {11, 12, 13, 14, 15};
		grid[3] = new int[] {16, 17, 18, 19, 20};
		grid[4] = new int[] {21, 22, 23, 24, 25};
		grid[5] = new int[] {26, 27, 28, 29, 30};
		
		s.spiral2(grid);
		Utility.toString(grid);
		
		s.spiral3(3, 3);
	}
	
	private int[] result;
	private int counter;
	
	int[] spiral(int[][] data) {
		
		// square 2D array
		int n = data.length;
		int rMin = 0, cMin = 0;
		int rMax = n-1, cMax = rMax;
		
		counter = 0;
		result = new int[n*n];
		while (counter < n * n) {
			
			traverse(data, cMin, cMax, 'R', rMin);
			traverse(data, rMin+1, rMax, 'D', cMax);
			traverse(data, cMin, cMax-1, 'L', rMax);			
			traverse(data, rMin+1, rMax-1, 'U', cMin);
			cMax--; cMin++; rMin++; rMax--;
		}
		return result;
	}
	
	// Non square
	int[] spiral2(int[][] data) {
		
		// square 2D array
		int row = data.length, col = data[0].length;
		int rMin = 0, cMin = 0;
		int rMax = row-1, cMax = col-1; 
		
		counter = 0;
		result = new int[row*col];
		while (counter < row * col) {
			
			traverse(data, cMin, cMax, 'R', rMin);
			traverse(data, rMin+1, rMax, 'D', cMax);
			traverse(data, cMin, cMax-1, 'L', rMax);			
			traverse(data, rMin+1, rMax-1, 'U', cMin);
			cMax--; cMin++; rMin++; rMax--;
		}
		return result;
	}
			
	// dir (R=right, L=left, U=up, D=down)
	void traverse(int[][] data, int min, int max, char dir, int x) {
		
		if (counter == data.length * data.length) return;
		
		if (dir == 'R' || dir == 'D') {
			for (int i = min; i <= max; i++) {
				if (dir == 'R') result[counter++] = data[x][i];
				else result[counter++] = data[i][x];
			}
		}
		
		if (dir == 'L' || dir == 'U') {
			for (int i = max; i >= min; i--) {
				if (dir == 'L') result[counter++] = data[x][i];
				else result[counter++] = data[i][x];
			}
		}
		
		Utility.print(result);
	}
	
	void spiral3(int X, int Y) {

		int x, y, dx, dy;
		x = y = dx = 0;
		dy = -1;
		
		int t;
		int steps = X * Y;
		for (int i = 0; i < steps; i++) {
			System.out.println("(" + x + ", " + y + ")");
			
			// turn when x=y (TR, BL), x =-y(TL, BR) , x == 1-y
			if ((x == y) || ((x < 0) && (x == -y)) || ((x > 0) && (x-1 == -y))) {
				t = dx;
				dx = -dy;
				dy = t;
			}
			x += dx;
			y += dy;
		}		
	}
}
