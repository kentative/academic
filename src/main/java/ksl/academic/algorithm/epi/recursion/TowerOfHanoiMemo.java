package ksl.academic.algorithm.epi.recursion;

/**
 * Example of trade-offs: the Towers of Hanoi with memoization For each of the
 * six permutation of (src,dst,tmp) and for each n, save the steps for
 * hanoi(n,src,dst,tmp).
 *
 * The time complexity goes down significantly --- each method call for (n, src,
 * dst, tmp) is only computed once, and after that the string of steps is
 * returned. The cross-over between the time for the memoized solution and the
 * recursive is between n=7 and n=8.
 *
 * The space complexity, however, is nasty: the table contains only (n+1)*6
 * strings --- with three numbers, there are six permutations. Each string,
 * however, contains the full solution for the problem of a given value of n ---
 * pow(2, n) - 1 disk movements. Below the level n (single permutation) and
 * level (n-1) (two permutations), the lower levels settle into a pattern of
 * requiring three of the six strings --- except for level 0 with all empty
 * strings.
 *
 * Perpetrator: Timothy Rolfe
 */
public class TowerOfHanoiMemo {

	static String[][] memo = null;
	// Look-up vector for permutation index.
	// Done as strings because 012 is OCTAL 12, not decimal 12
	static String[] lookup = { "012", "021", "102", "120", "201", "210" };

	// Allow access to the memoization table.
	static String[][] table() {
		return memo;
	}

	// Clear table for next problem
	static void clear() {
		memo = null;
	}

	static String hanoi(int n, int src, int dst, int tmp) {
		String triple;
		int index;
		if (memo == null) {// Allow for hanoi(0...) up through hanoi(n...)
			memo = new String[n + 1][6];
			// Empty strings to concatenate in generating move sequences
			memo[0][0] = "";
			for (int k = 1; k < 6; k++)
				memo[0][k] = memo[0][0];
		}
		triple = String.format("%1d%1d%1d", src, dst, tmp);
		// If inappropriate src,dst,tmp sent, let this blow up
		index = 0;
		// Take advantage of the String overload to equals
		while (!triple.equals(lookup[index]))
			index++;
		if (memo[n][index] == null) {
			String result;
			result = hanoi(n - 1, src, tmp, dst);
			result += String.format("%d %d\n", src, dst);
			result += hanoi(n - 1, tmp, dst, src);
			memo[n][index] = result;
		}
		return memo[n][index];
	}

	static String recHanoi(int n, int src, int dst, int tmp) {
		if (n == 0)
			return "";
		return recHanoi(n - 1, src, tmp, dst) + String.format("%d %d\n", src, dst) + recHanoi(n - 1, tmp, dst, src);
	}

	public static void main(String[] args) {
		int size, row, col, text = 0;
		String path1, path2;
		long start, middle, finish;
		java.util.Scanner console = new java.util.Scanner(System.in);

		System.out.print("Number of disks:  ");
		if (args.length == 0)
			path1 = console.nextLine();
		else {
			path1 = args[0];
			System.out.println(path1);
		}
		size = Integer.parseInt(path1);
		start = System.nanoTime();
		path1 = hanoi(size, 0, 2, 1);
		middle = System.nanoTime();
		path2 = recHanoi(size, 0, 2, 1);
		finish = System.nanoTime();
		if (path1.equals(path2)) // String.equals overloads Object.equals
			System.out.println("Results agree");
		else
			System.out.println("Results disagree");
		System.out.printf("Memoized:  %3.3f milliseconds\nRecursive: %3.3f milliseconds\n", 1E-06 * (middle - start),
				1E-06 * (finish - middle));
		System.out.printf("   Ratio:  %3.3f\n", (double) (middle - start) / (finish - middle));
		System.out.println("\nTable of results actually computed:");
		System.out.print("      ");
		for (col = 0; col < 6; col++)
			System.out.printf("%4s", lookup[col]);
		System.out.println();
		for (row = size; row >= 0; row--) {
			System.out.printf("%3d: ", row);
			for (col = 0; col < 6; col++) {
				System.out.printf("%4c", memo[row][col] == null ? ' ' : 'X');
				if (memo[row][col] != null)
					text += memo[row][col].length();
			}
			System.out.println();
		}
		System.out.printf("\nTotal text:  %d characters\n", text);
		System.out.print("\nPress ENTER to exit:  ");
		console.nextLine();
	}
}
/*
 * Results of a run:
 * 
 * Results agree Memoized: 515.238 milliseconds Recursive: 12044.328
 * milliseconds Ratio: 0.043
 * 
 * Table of results actually computed: 012 021 102 120 201 210 20: X 19: X X 18:
 * X X X 17: X X X 16: X X X 15: X X X 14: X X X 13: X X X 12: X X X 11: X X X
 * 10: X X X 9: X X X 8: X X X 7: X X X 6: X X X 5: X X X 4: X X X 3: X X X 2: X
 * X X 1: X X X 0: X X X X X X
 * 
 * Total text: 14679812 characters
 */
