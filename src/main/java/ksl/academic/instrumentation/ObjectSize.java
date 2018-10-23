package ksl.academic.instrumentation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ObjectSize {

	private static Logger logger = LoggerFactory.getLogger(ObjectSize.class);

	public static void main(String[] args) {
		
		boolean bit = true;
		printSize(bit, "boolean");
		
		printSize(new boolean[1], "boolean[1]");
		printSize(new boolean[2], "boolean[2]");
		printSize(new boolean[1][1], "boolean[1][1]");
		
		printSize(new boolean[1024], "boolean[1024]");
		printSize(new boolean[1024][1024], "boolean[1024][1024]");
		
		printSize(new boolean[12][12], "boolean[12][12]");
		printSize(new boolean[11][11], "boolean[11][11]");
		printSize(new boolean[10][10], "boolean[11][11]");
		printSize(new boolean[128][128], "boolean[128][128]");
		
		printSize(2, "integer");
		printSize(Integer.valueOf(3), "Integer");
		printSize(Long.valueOf(3), "Long");
		printSize(Double.valueOf(3), "Double");
		
		int row = 128;
		int col = 128;
		boolean[][] matrix = new boolean[row][col];
		
		for (int r = 0; r < row; r++) {
			for (int c = 0; c < col; c++) {
				matrix[r][c] = (r+c)%2 == 0;
			}
		}
		printSize(matrix, "boolean[128][128] set to true");
	}

	/**
	 * Print basic details including size of provided object to standard output.
	 * 
	 * @param object Object whose value and size are to be printed to standard output.
	 */
	public static void printSize(final Object object, String tag) {
		logger.info(tag + " of type '" + object.getClass() + "' has size of "
				+ InstrumentationAgent.getObjectSize(object) + " bytes.");
	}
}
