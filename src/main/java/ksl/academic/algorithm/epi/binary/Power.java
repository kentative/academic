package ksl.academic.algorithm.epi.binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Power {

	private static Logger logger = LoggerFactory.getLogger(Power.class);
	
	public static void main(String[] args) {
		
		logger.info(String.valueOf(Math.pow(2, -3)));
		logger.info(String.valueOf(power(2, 3)));
		logger.info(String.valueOf(power2(2, -3)));
		
	}
	
	
	/**
	 * Returns x to the power of y. 2^3 = 8;
	 * 
	 * @param x - the base
	 * @param y - the power
	 * @return base ^ power
	 */
	static double power(double x, int y) {
		double result = 1;
		for (int i = 0; i < y; i++) {
			result *= x;
		}
		return result;
	}

	/**
	 * Compute power using divide and conquer
	 * @param x - base
	 * @param y - exponent
	 * @return power of (base ^ exponent)
	 */
	static double power2(double x, int y) {

		if (y < 0) {
			// negate y, and inverse x
			y *= -1;
			x = 1.0/x;
		}
		
		double result = 1.0;
		while (y != 0) {
			if ((y & 1) == 1) {
				result *= x;
			}			
			x *= x;  
			y >>= 1;			
		}
		return result;
	}

	// @OneNote
	static double power3(double x, int y) {
		if (y < 0) {
			y *= -1;
			x = 1.0/x;
		}
		
		double result = 1.0;
		while (y != 0) {
			if ( (y & 1) == 1 ) {
				result *= x;
			}
			x *= x;
			y >>= 1;
		}
		return result;
	}
}
