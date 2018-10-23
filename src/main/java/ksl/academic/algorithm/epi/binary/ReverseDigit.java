package ksl.academic.algorithm.epi.binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReverseDigit {

	private static Logger logger = LoggerFactory.getLogger(ReverseDigit.class);
	
	public static void main(String[] args) {

		logger.info(String.valueOf(reverse(-314)));
		
	}
	
	static int reverse(int x) {
		
		int base = 10;
		int y = 0;
		while (x != 0) {
			int r = x%10;
			y = y * base + r;
			x /= base;
		}
		return y;
	}
	
}
