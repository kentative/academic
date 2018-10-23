package ksl.academic.algorithm.epi.string;

public class Interconvert {

	public static void main(String[] args) {
		System.out.println(convert(0));
		
		System.out.println(convert("-12454"));
	}
	
	static String convert(int x) {

		if (x==0) return "0";
		
		boolean isNeg = false;
		if (x < 0) {
			x = -x;
			isNeg = true;
		}

		// 100 = 2
		int n = (int) Math.log10(x) + 1;
		int base = 10;

		char[] buffer = new char[n];
		for (int i = 0; x > 0; i++) {
			buffer[n - 1 - i] = (char) (x % base + '0');
			x /= base;
		}

		if (isNeg) return "-" + String.valueOf(buffer);
		return String.valueOf(buffer);
	}

	static int convert(String x) {

		int n = x.length(), i = 0;
		boolean isNeg = false;
		if (x.charAt(0) == '-') {
			isNeg = true;
			n--;
			i++;
		}

		int base = 10;
		int msb = (int) Math.pow(10, n - 1);
		int result = 0;
		while (msb > 0) {
			result += msb * (x.charAt(i++) - '0');
			msb /= base;
		}

		if (isNeg) return -result;
		return result;
	}
	
	static int convert2(String x) {

		int n = x.length(), i = 0;
		boolean isNeg = false;
		if (x.charAt(0) == '-') {
			isNeg = true;
			n--;
			i++;
		}

		int base = 10;
		int result = 0;
		while (i < n) {
			result = result * base + (x.charAt(i++) - '0');			
		}
		
		for (int j = 0; j< 10; j++) {
			System.out.println(j);
		}
		if (isNeg) return -result;
		return result;
	}

}
