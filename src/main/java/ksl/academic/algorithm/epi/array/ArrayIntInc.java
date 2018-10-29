package ksl.academic.algorithm.epi.array;

import ksl.academic.algorithm.Utility;

/**
 * The Class ArrayIntInc.
 */
public class ArrayIntInc {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		int[] data = {-9,9,9,0};
		Utility.print(increment2(data));
		
		int[] data1 = {-9,9,9,0};
		Utility.print(incrementBoard(data1));
		
	}
	
	/**
	 * Increment.
	 *
	 * @param data the data
	 * @return the int[]
	 */
	static int[] increment(int[] data) {
		
		// check for valid input
		
		int base = 10;
		boolean carry = true; 
		for (int i = data.length-1; i >= 0 && carry; i--) {
			int x = (data[i]+1);
			carry = (x >= base);
			data[i] = x%base;
		}
		
		// overflow
		if (carry) {
			System.out.println(carry);
			int[] buffer = new int[data.length+1];
			buffer[0] = 1;
			data = buffer;
		}
		return data;
	}
	
	/**
	 * This version handles negative.
	 *
	 * @param data the data
	 * @return the int[]
	 */
	static int[] increment2(int[] data) {
		
		int base = 10;
		int inc = 1;
		
		// check for negative
		if (data[0] < 0) {
			inc = -1;
			data[0] *= -1;
		}
		
		boolean carry = true; 
		for (int i = data.length-1; i >= 0 && carry; i--) {
			int x = (data[i]+inc);
			if (inc > 0) {
				carry = (x >= base);
				data[i] = x%base;
			} else {
				carry = (x < 0);
				data[i] = (x + base)%base;
			}			
		}
		
		// overflow, need to return array
		if (carry) {
			int[] buffer = new int[data.length+1];
			buffer[0] = 1;
			data = buffer;
		}
		
		// account for negative
		if (inc < 0) data[0] *= -1;
		
		return data;
	}
	
	/**
	 * This version handles negative.
	 *
	 * @param data the data
	 * @return the int[]
	 */
	static int[] incrementBoard(int[] data) {
		
		int base = 10;
		int inc = 1;
		
		// check for negative
		if (data[0] < 0) {
			inc = -1;
			data[0] *= -1;
		}
		
		boolean carry = true; 
		for (int i = data.length-1; i >= 0 && carry; i--) {			
			data[i] = (data[i] + inc)%base;
			carry = (data[i] == 0);						
		}
		
		// overflow, need to return array
		if (carry) {
			int[] buffer = new int[data.length+1];
			buffer[0] = 1;
			data = buffer;
		}
		
		// account for negative
		if (inc < 0) data[0] *= -1;
		
		return data;
	}
}
