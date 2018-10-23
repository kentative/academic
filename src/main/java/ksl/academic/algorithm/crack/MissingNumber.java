package ksl.academic.algorithm.crack;

import com.google.common.base.Preconditions;

public class MissingNumber {
	public static void main(String[] args) {

		int[] data = {0, 1, 2, 3, 4, 6, 7, 8, 9};
		
		System.out.println(findMissingNumber(data));
	}
	
	static int findMissingNumber(int[] data) {
		Preconditions.checkNotNull(data);
		
		int n = data.length;
		for (int i = 0; i < n; i++) {
			if ((i ^ data[i]) != 0) {
				return i;
			}
		}
		return -1;
	}
}
