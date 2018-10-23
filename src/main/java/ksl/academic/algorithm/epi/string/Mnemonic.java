package ksl.academic.algorithm.epi.string;

import java.util.ArrayList;
import java.util.List;

public class Mnemonic {

	public static void main(String[] args) {
		
		Mnemonic mne = new Mnemonic();
		List<String> sequences = mne.sequence("5368526", 0);
		System.out.println(sequences.contains("KENTLAM"));
	}
	
	private String[] lookup;
	
	public Mnemonic() {
		this.lookup = new String[] {"0",
				"1","ABC","DEF",
				"GHI","JKL","MNO",
				"PQR","STU","WXYZ"};
	}
	
	List<String> sequence (String digit, int d) {
		
		// Base case
		if (d == digit.length() - 1) {
			String x = lookup[digit.charAt(d) - '0'];
			List<String> arrayList = new ArrayList<>();
			for (int i = 0; i < x.length(); i++) {
				arrayList.add(String.valueOf(x.charAt(i)));
			}
			return arrayList;			
		}
		
		String x = lookup[digit.charAt(d) - '0'];
		List<String> arrayList = new ArrayList<>();
		for (int i = 0; i < x.length(); i++) {
			List<String> sub = sequence(digit, d+1);			
			for (String s : sub) {
				arrayList.add(String.valueOf(x.charAt(i)) + s);
			}
		}
		return arrayList;
	}
}


// recursing - increasing elements