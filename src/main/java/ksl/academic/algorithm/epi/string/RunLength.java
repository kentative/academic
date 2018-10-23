package ksl.academic.algorithm.epi.string;

public class RunLength {

	public static void main(String[] args) {
		
		System.out.println(encode("aaabbcdddd"));
		
		System.out.println(decode("14a2b1c"));
	}
	
	static String encode(String text) {
		
		char[] buffer = text.toCharArray();
		
		int count = 1;
		char prev = buffer[0];
		int n = buffer.length;
		StringBuilder sb = new StringBuilder(buffer.length);
		for (int i = 1; i < n; i++, count++) {
			
			if (prev != buffer[i]) {
				sb.append(count).append(prev);
				count = 0;
				prev = buffer[i];
			}
		}
		
		// last element
		sb.append(count).append(prev);
		
		return sb.toString();
	}
	
	
	static String decode(String code) {		
		int n = code.length();
		StringBuilder sb = new StringBuilder(n*2);
		
		int count = 0;
		for (int i = 0; i < n; i++) {
			char x = code.charAt(i);
			if (x >= '0' && x <='9') {
				count = (count * 10) + (int) (x - '0');
			} else {
				for (int j = 0; j < count; j++) {
					sb.append(x);
				}
				count = 0;
			}
		}
		
		return sb.toString();
	}
}
