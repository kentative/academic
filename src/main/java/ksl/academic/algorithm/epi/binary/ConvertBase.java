package ksl.academic.algorithm.epi.binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConvertBase {

	private static Logger logger = LoggerFactory.getLogger(ConvertBase.class);
	
	public static void main(String[] args) {

		logger.info(convert("34324", 10, 2));
		
	}
	
	static String convert(String s1, int b1, int b2) {
		
		// value of x1
		int x1 = 0;
		for (int i = 0; i <  s1.length(); i++) {
			x1 *= b1;
			x1 += getValue(s1.charAt(i));
		}
		
		char[] buffer = new char[8];
		int count = 0;
		while (x1 > 0) {

			if (count == buffer.length) {
				char[] nb = new char[buffer.length*2];
				for (int i = 0; i < buffer.length; i++) {
					nb[i] = buffer[i];
				}
				buffer = nb;
			}
			
			int r = x1 % b2;
			buffer[count] = getChar(r);
			x1 /= b2;
			count++;
		}		
		
		// Reverse buffer
		for (int i = 0; i < count/2; i++) {
			char t = buffer[i];
			buffer[i] = buffer[count-1 -i];
			buffer[count-1 -i] = t;
		}
		
		return String.valueOf(buffer);
	}

	
	static char getChar(int x) {
		if (x >=0 && x <= 9) return (char) (x + '0');
		if (x >= 10 && x <= 16) return (char) (x - 10 + 'A');
		return '0';
	}


	private static int getValue(char c) {
		if (c >= '0' && c <= '9') return c - '0';
		if (c >= 'A' && c <= 'F') return c - 'A' + 10;
		return 0;
	}
}
