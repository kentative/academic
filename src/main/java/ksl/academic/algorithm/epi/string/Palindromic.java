package ksl.academic.algorithm.epi.string;

public class Palindromic {

	public static void main(String[] args) {

		System.out.println(palindromic("...abc12@#121 cba"));
	}

	static boolean palindromic(String s) {

		int lo = 0;
		int hi = s.length() - 1;

		while (lo < hi) {
			lo = nextLow(s, lo, hi);
			hi = nextHigh(s, hi, lo);

			if (lo < 0 || hi < 0)
				return true;
			if (s.charAt(lo) != s.charAt(hi))
				return false;
			lo++;
			hi--;
		}
		return true;
	}

	static int nextLow(String s, int x, int max) {
		for (int i = x; i <= max; i++) {
			if (isValid(s.charAt(i)))
				return i;
		}
		return -1;
	}

	static int nextHigh(String s, int x, int min) {
		for (int i = x; i >= min; i--) {
			if (isValid(s.charAt(i)))
				return i;
		}
		return -1;
	}

	static boolean isValid(char c) {
		return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
	}
}
