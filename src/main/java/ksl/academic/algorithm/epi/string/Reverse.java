package ksl.academic.algorithm.epi.string;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Reverse {

	public static void main(String[] args) {
		
		String sentence = "Alice is a girl";
		System.out.println("Original:        " + sentence);
		System.out.println("reverse:         " + reverse(sentence));
		System.out.println("reverseByteSwap: " + reverseByteSwap(sentence));
		System.out.println("reverse1:        " + reverse1(sentence));
		
	}
	
	static String reverse(String s) {
		
		String[] words = s.split(" ");

		int n = words.length;
		String[] reverse = new String[n];
		for (int i = 0; i < n; i++) {
			reverse[n-1-i] = words[i];
		}
		
		StringBuilder sb = new StringBuilder();
		for (String w : reverse) {
			sb.append(w + " ");
		}
		return sb.toString();
	}
	
	static String reverse1(String s) {
		
		final String delim = " ";
		String[] words = s.split(delim);
		
		StringBuilder sb = new StringBuilder();
		for (int i = words.length-1; i >=0; i--) sb.append(words[i] + delim);
		sb.setLength(sb.length()-1);
		return sb.toString();
	}
	
	// Reverse a string using byte swapping
	static String reverseByteSwap(String data) {

		byte[] bytes = data.getBytes();
		int n = bytes.length;
		
		// Swap all
		swapRange(bytes, 0, n-1);
		
		// Swap each word
		int s = 0; // beginning of a word
		for (int i = 0; i < n; i++) {
			if (bytes[i] == ' ') {
				// swap letters within a word
				swapRange(bytes, s, i-1); 
				s = i+1;                  
			}
		}
		
		// The last word doesn't have a space
		swapRange(bytes, s, n-1); // [j, i]
		return new String(bytes);		
	}
	
	static void swapRange(byte[] b, int lo, int hi) {
		while (lo < hi) {
			b[lo] ^= b[hi];
			b[hi] ^= b[lo];
			b[lo] ^= b[hi];
			lo++; hi--;
		}
	}
		
}
