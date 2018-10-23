package ksl.academic.algorithm.leet;

public class FindKMaxInTree {

	public static void main(String[] args) {
		int n = 1 << 17;
		System.out.println(n);
		
		//find 453
		
		int k = 453;
		
		int lo = 0, hi = n;
		
		// If tree is balanced
		while (lo <= hi) {
			int m = (lo + hi)/2;
			if (m < k) {
				lo = m+1;
				System.out.println("Left");
			} else if (m > k) {
				hi = m-1;
				System.out.println("Right");
			} else break;
		}
	}
}
