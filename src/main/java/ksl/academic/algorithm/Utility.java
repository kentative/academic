package ksl.academic.algorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {

	private static Logger logger = LoggerFactory.getLogger(Utility.class);
	
	
	public static String toBinaryString(String x) throws Exception {
		
		// US-ASCII or UTF-8
		byte[] bytes = x.getBytes("UTF-8");
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			sb.append(String.format("%8s", Integer.toBinaryString(bytes[i])));
			sb.append(" ");
					
		}
		return sb.toString();
	}
	
	/**
	 * 0    Sign bit (leading bit)
	 * -000 0000 MSB (high order bit)
	 * 0000 0000 24
	 * 0000 0000 16
	 * 0000 0011  8
	 * 
	 * @param x
	 * @return
	 */
	public static String toBinaryString(int x) {

		final char PAD = ' ';
		
		char[] buffer = new char[32+8];
		int leadingZero = Integer.numberOfLeadingZeros(x);

		// Create a 32-bit representation with leading zeroes
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < leadingZero; i++) br.append("0");		
		br.append(Integer.toBinaryString(x));
		String binary = br.toString();
		
		// Add spacing to every 4 bit and the sign bit
		int k = 0;
		buffer[1] = PAD;
		for (int i = 0; i < buffer.length; i++) {
			if (i > 0 && (i)%5 ==0) buffer[i] = PAD;
			if (buffer[i] != PAD) {
				buffer[i] = binary.charAt(k++);
			}
		}
		
		return String.valueOf(buffer);
	}
	
	public static String toBinaryString(long x) {

		final char PAD = ' ';
		
		char[] buffer = new char[64+8];
		int leadingZero = Long.numberOfLeadingZeros(x);

		// Create a 32-bit representation with leading zeroes
		StringBuilder br = new StringBuilder();
		for (int i = 0; i < leadingZero; i++) br.append("0");		
		br.append(Long.toBinaryString(x));
		String binary = br.toString();
		
		// Add spacing to every 4 bit and the sign bit
		int k = 0;
		buffer[1] = PAD;
		for (int i = 0; i < buffer.length; i++) {
			if (i > 0 && (i)%9 == 0) buffer[i] = PAD;
			if (buffer[i] != PAD) {
				buffer[i] = binary.charAt(k++);
			}
		}
		
		return String.valueOf(buffer);
	}

	public static void print(int[] data) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			sb.append(data[i] + " ");
		}
		logger.info(sb.toString());
	}
	
	public static void print(char[] data) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < data.length; i++) {
			sb.append(data[i] + " ");
		}
		logger.info(sb.toString());
	}
	
	public static String toString(int[][] grid) {
		StringBuilder sb = new StringBuilder();
		for(int r = 0; r < grid.length; r++) {
			sb.append("Row: " + r + " [");
			for (int c = 0; c < grid[r].length; c++) {
				if (c > 0) sb.append(" ");
				sb.append(grid[r][c]);
			}
			sb.append("]");
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
	
	public static String print(int[] data, int lo, int hi) {
		StringBuilder sb = new StringBuilder();
		sb.append("[");
		for (int k = lo; k < hi; k++) {
			sb.append(data[k] + ", ");
		}
		sb.append(data[hi] + "]");
		return sb.toString();
	}
}
