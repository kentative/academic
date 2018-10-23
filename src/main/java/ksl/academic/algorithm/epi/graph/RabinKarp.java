package ksl.academic.algorithm.epi.graph;

import java.util.Calendar;

public class RabinKarp {

//	public static void main(String[] args) {
//		String text = "This isn't a test";
//
//		System.out.println(brute("isn't", text));
//		System.out.println(rabinKarp("isn't", text));
//	}
	
	public static void main(String[] args) {
	    String test = "0".repeat(1000000) + "1";

	    String p = "0".repeat(1000) + "1";
	     long start  = Calendar.getInstance().getTimeInMillis();
	     for (int x = 0; x < 2; x++)
	         test.indexOf(p);
	     long end = Calendar.getInstance().getTimeInMillis();
	     end = end -start;
	     System.out.println("Standard Java Time->"+end);

	    start  = Calendar.getInstance().getTimeInMillis();
	    for (int x = 0; x < 2; x++)
	    	rabinKarp1(p, test);
	    end = Calendar.getInstance().getTimeInMillis();
	    end = end -start;
	    System.out.println("Rabin Karp time->"+end);
	    
	    
	    start  = Calendar.getInstance().getTimeInMillis();
	    for (int x = 0; x < 2; x++)
	    	brute(p, test);
	    end = Calendar.getInstance().getTimeInMillis();
	    end = end -start;
	    System.out.println("brute time->"+end);
	    
	    
	    RollingHash rh = new RollingHash(p.length());
	    start  = Calendar.getInstance().getTimeInMillis();
	    for (int x = 0; x < 2; x++)
	    	rh.search(p, test);
	    end = Calendar.getInstance().getTimeInMillis();
	    end = end -start;
	    System.out.println("rolling hash ->"+end);

	}

	static int brute(String pattern, String text) {

		int n = text.length();
		int m = pattern.length();
		boolean matched = true;
		for (int i = 0; i < (n - m); i++) {

			matched = true;
			for (int j = 0; j < m; j++) {
				if (pattern.charAt(j) != text.charAt(i + j)) {
					matched = false;
					break;
				}
			}

			if (matched) {
				return i;
			}
		}
		return -1;
	}

	static int rabinKarp1(String pattern, String text) {

		int n = text.length();
		int m = pattern.length();
		int hashPattern = pattern.hashCode();

		for (int i = 0; i <= (n - m); i++) {

			String substring = text.substring(i, i + m);
			int hashPart = substring.hashCode();

			if (hashPattern == hashPart) {
				if (pattern.equals(substring)) {
					return i;
					
				}
			}
		}
		return -1;
	}

}
