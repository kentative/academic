package ksl.academic.algorithm.amzn;

import java.util.Arrays;

import com.google.common.collect.Comparators;

public class Sort {

	public static void main(String[] args) {
		String[] words = {
				"drove",
				"splash",
				"scratch",
				"whine",
				"maze",
				"chant",
				"wide",
				"baked",
				"state"
		};

		String[] word1 = {
				"ribbon",
				"umbrella",
				"wind",
				"fairy",
				"rainbow",
				"piano",
				"candy",
				"moon",
				"special",
				"magical",
				"feathers",
				"fire"
		};
		
		print(word1);
	}
	
	private static void print(String[] words) {
		Arrays.sort(words);
		int i = 1;
		for (String w : words) {
			System.out.println(String.format(" %1$2d. %2$s", i++, w));
		}
	}
}
