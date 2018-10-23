package ksl.academic.algorithm.epi.binary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ColumnEnc {

	private static Logger logger = LoggerFactory.getLogger(ColumnEnc.class);
	
	public static void main(String[] args) {

		logger.info(String.valueOf(colunEnc("AAZ")));
		
	}
	
	static int colunEnc(String id) {
		
		int base = 26;
		int result = 0;
		for (int i = 0; i < id.length(); i++) {
			result = (result * base) + getValue(id.charAt(i));
		}
		return result;
	}

	static int getValue(char c) {
		if (c >= 'A' && c <= 'Z') return c - 'A' +1;
		return -1;
	}
	
}
