package ksl.academic.algorithm.epi.string;

import java.util.ArrayList;
import java.util.List;

class NewMonic {
	
	public static void main(String[] args) {
		
		NewMonic mne = new NewMonic();
		List<String> sequences = mne.sequence("5368526", 0);
		System.out.println(sequences.contains("KENTLAM"));
		System.out.println('C' + "Test") ;
	}
	
	String[] lookup = { "0",
		"1", "ABC", "DEF",
		"GHI", "JKL", "MNO",
		"PQRT", "TUV", "WXYZ"
	}; 
	
	List<String> sequence(String digits, int d) {
		
		String alpha = lookup[digits.charAt(d)-'0'];
		
		if(d == digits.length()-1) {
			List<String> last = new ArrayList<String>();
			for (int i = 0; i < alpha.length(); i++) {
				last.add(String.valueOf(alpha.charAt(i)));
			}
			return last;
		}
	
		List<String> result = new ArrayList<>();
		for (String ss : sequence(digits, d+1)) {
			for (int i = 0; i < alpha.length(); i++) {
				result.add(alpha.charAt(i) + ss);
			}
		}
		
		return result;
	}
} 