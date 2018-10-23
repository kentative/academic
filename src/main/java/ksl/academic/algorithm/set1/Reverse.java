package ksl.academic.algorithm.set1;

public class Reverse {

	
	public Reverse(String...data) {
		
	}
	
	/**
	 * Reverses the specified string
	 * @param s - the string to be reversed
	 * @return the reversed string
	 */
	public static String reverse(String s) {

		if (s == null || s.length() < 1) {
			return "";
		}
				
		StringBuilder sb = new StringBuilder();
		for (int i = s.length() -1; i >= 0; i--) {
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
	
	/**
	 * 10 (0 - 9) = 0 , 1, 2, 3, 4 -- 5, 6, 7, 8 9
	 * 11 (0 - 10) = 0. 1, 2, 3, 4 -- 5 -- 6, 7, 8, 9 10
	 * 
	 * Reverses the specified string
	 * @param s - the string to be reversed
	 * @return the reversed string
	 */
	public static String reverseInPlace(String s) {

		if (s == null || s.length() < 1) {
			return "";
		}
		
		byte[] data = s.getBytes();
		for (int i = 0; i < data.length/2; i++) {
			int j = data.length -1 - i;
			System.out.println("Swapping: " + i + " - " + j);
			data[i] = (byte) (data[i] ^ data[j]);
			data[j] = (byte) (data[j] ^ data[i]);
			data[i] = (byte) (data[i] ^ data[j]);
		}
		return new String(data);
	}
	
	public static void main(String[] args) {
		
		String data = "This is a test String";
		String reverse = Reverse.reverseInPlace(data);
		System.out.println(reverse);
		
		System.out.println(Math.floorDiv(-7, 8));
		System.out.println(-7/8);
		
	}
	
}
