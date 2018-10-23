package ksl.academic.algorithm.epi.binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Palindrome {

	private static Logger logger = LoggerFactory.getLogger(Palindrome.class);
	
	public static void main(String[] args) {

		int x = 1111;
		System.out.println(x);
		
		logger.info(String.valueOf(palindrome(x)));
		logger.info(String.valueOf(numeric_palindrome(x)));
	}
	
	static boolean palindrome(int x) {
		
		int base = 10;
		int digits = (int) Math.log10(x) + 1;
		
		// value of most significant base
		int msb = (int) Math.pow(base, digits-1);
		
		// remaining value with least significant digit
		int r = x;
		for (int i = 0; i < digits/2; i++) {			

			int lsd = r%base; 
			int msd = x/msb;
			
			System.out.println(msd + " ? " + lsd);
			if (msd != lsd) return false;

			// trim right
			System.out.println(x + " % " + msb + " = " + x%msb);
			x %= msb;  
			msb /= base;
			
			// trim left
			r /= base; 
		}
		return true;
	}
	
	public static boolean numeric_palindrome(long number) {
		
		int base = 10;
		
		// log10(100) = 2, want 3 digits
		int digit = (int) Math.log10(number) +1;
		
		// the base and value of the most significant digit
		long msb = (long) Math.pow(base, digit-1);
		long msv = number;
		
		for (int i = 0; i < digit/2; i++) {
			
			if (msv/msb != number%base) return false;
			
			msv %= msb;        // remove left most digit
			msb /= base;       // move most significant base left
			number /= base;    // remove right most digit
		}
		return true;
	}
}
