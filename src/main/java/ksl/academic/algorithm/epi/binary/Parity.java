package ksl.academic.algorithm.epi.binary;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ksl.academic.algorithm.Utility;

public class Parity {

	private static Logger logger = LoggerFactory.getLogger(Parity.class);
	
	public static void main (String...args) throws Exception {
		
		String x = "Kent Lam";
		logger.info(Utility.toBinaryString(x));
		logger.info(String.valueOf(parity(x)));
		logger.info(String.valueOf(parity2(x)));
		
		
		int value = 11;
		logger.info(Utility.toBinaryString(value));
		logger.info(String.valueOf(parity(value)));
		
		logger.info(Utility.toBinaryString(value));
		logger.info(String.valueOf(parity2(value)));
		
	}

	// @OneNote
	static boolean parity(int x) {
		
		boolean parity = true;
		while (x !=0) {
			parity ^= (x & 1) == 0;
			x >>=1;
		}
		return parity;
	}
	
	// @OneNote
	static boolean parity2(int x) {
		boolean parity = false;
		while (x != 0) {
			parity ^= (x & 1) == 0;
			x = x & (x-1);
		}
		return parity;
	}

	/**
	 * Determine parity by dropping lowest set bits instead of left-shifting 1 bit.
	 * 
	 * @param x
	 * @return
	 */
	static boolean parity2(String x) {
		
		byte[] bytes = x.getBytes();
		boolean parity = false;
		for (int i = 0; i < bytes.length; i++) {
			int n = bytes[i];			
			while (n != 0) {
				// toggle parity based on (if last bit is 0) --even toggle = parity
				parity ^= (n & 1) == 0;
				n &= (n-1); // drop the lowest set bit
		
			}
			
		}
		return parity;
	}
	
	/**
	 * Determine parity by brute force.
	 * <pre>
	 * 1. Initialize the parity flag to true
	 * 2. Check the last bit of the byte if 1, toggle the parity flag.
	 * 3. Iterate through all bit by shifting left by 1 each time
	 * 4. return parity flag
	 * </pre>
	 * 
	 * @param x - the string to check
	 * @return true if the bytes in this string has parity
	 * 
	 * @throws UnsupportedEncodingException
	 */
	static boolean parity(String x) throws UnsupportedEncodingException {
		
		byte[] bytes = x.getBytes();
		boolean parity = true;
		for (int i = 0; i < bytes.length; i++) {
			int n = bytes[i];			
			while (n != 0) {
				// toggle parity based on (if last bit is 0) --even toggle = parity
				parity ^= (n & 1) == 0;
				n >>= 1;
		
			}
			
		}
		return parity;
	}
	
	static void bitWiseOperator() {
		
		logger.info(Utility.toBinaryString(4));
		logger.info(Utility.toBinaryString(7));
		logger.info(Utility.toBinaryString(4&7));
		logger.info(Utility.toBinaryString(~4));
		
	}
	
	static void shiftLeft() {
		int x = 1024;
		logger.info("Value:                 " + Utility.toBinaryString(x) + " decimal: " + x);
		logger.info("Left arithmetic shift: " + Utility.toBinaryString(x << 4) + " decimal: " + (x << 4));
	}
	
	
	static void shiftRight() {
		int x = -1024;
		logger.info("Value:                  " + Utility.toBinaryString(x) + " decimal: " + x);
		logger.info("Right arithmetic shift: " + Utility.toBinaryString(x >> 4) + " decimal: " + (x >> 4));
		logger.info("Right logical shift:    " + Utility.toBinaryString(x >>> 4)+ " decimal: " + (x >>> 4));
	}
	
}
