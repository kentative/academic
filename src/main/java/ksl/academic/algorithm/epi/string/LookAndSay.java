package ksl.academic.algorithm.epi.string;

public class LookAndSay {

	public static void main(String[] args) {
		System.out.println(say(8)); //1113213211
		                            //1113213211
	}

	static String say(int k) {

		if (k < 1)
			throw new IllegalArgumentException("invalid value: " + k);

		// base case: k ==1
		if (k == 1) {
			return "1";
		}

		return describe2(say(k - 1));
	}

	static String describe(String digit) {

		int counter = 0;

		StringBuilder sb = new StringBuilder();
		int prev = -1, cur = prev;
		for (int i = 0; i < digit.length(); i++) {
			cur = digit.charAt(i) - '0';
			if (cur != prev && prev != -1) {
				sb.append(counter + String.valueOf(prev));
				counter = 0;
			}
			prev = cur;
			counter++;
		}

		sb.append(counter + String.valueOf(cur));
		return sb.toString();
	}

	static String describe2(String digit) {

		StringBuilder sb = new StringBuilder();
		
		int prev = digit.charAt(0) - '0', cur = prev;
		int counter = 1;
		for (int i = 1; i < digit.length(); i++) {
			cur = digit.charAt(i) - '0';
			if (cur != prev) {
				sb.append(counter).append(prev);
				counter = 0;
				prev = cur;
			}
			counter++;
		}

		sb.append(counter).append(cur);
		return sb.toString();
	}
}
