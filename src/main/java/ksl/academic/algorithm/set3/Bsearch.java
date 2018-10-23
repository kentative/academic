package ksl.academic.algorithm.set3;

public class Bsearch {

	public static void main(String[] args) {
		int[] data = {1, 3, 5, 7, 8};
		
		System.out.println(search(data, 8));
		
	}
	
	static int search(int[] data, int x) {
		return bsearch(data, 0, data.length-1, x);
	}
	
	static int bsearch(int[] data, int lo, int hi, int x) {
		
		while (lo <= hi) {
			int m = (lo+hi)/2;
			if (x < data[m]) hi = m-1;
			else if (x > data[m]) lo = m+1;
			else return m;
		}
		return -1;
				
	}
}
