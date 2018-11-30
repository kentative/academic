package ksl.academic.algorithm.epi.array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class UniqueChar.
 */
public class UniqueChar {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		System.out.println(findUniqueChar("ABCCaabc"));
	}
	
	/**
	 * Find unique char.
	 *
	 * @param s the s
	 * @return the string
	 */
	private static String findUniqueChar(String s) {
		
		char[] buffer = s.toCharArray();
		Set<Character> unique = new HashSet<>();
		for (char c : buffer) unique.add(c);
		return Arrays.toString(unique.toArray());
	}
	
}
