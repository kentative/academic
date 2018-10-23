package ksl.academic.algorithm.set2;

public class Set2RotatedArray2 {

	public enum Row { A, B, C, D, E }
	public enum Direction { Left, Top, Right, Bottom }

	
	
	public static void main(String[] args) {
		
		int[][] dataSet = new int[6][6];
		
		dataSet[0] = new int[] {1, 2, 3, 4, 5, 6};
		dataSet[1] = new int[] {2, 3, 4, 5, 6, 1};
		dataSet[2] = new int[] {3, 4, 5, 6, 1, 2};
		dataSet[3] = new int[] {4, 5, 6, 1, 2, 3};
		dataSet[4] = new int[] {5, 6, 1, 2, 3, 4};
		dataSet[5] = new int[] {6, 1, 2, 3, 4, 5};

		System.out.println(search(dataSet[2], 6, 6));

//		System.out.println(calcAngle(3, 30));
		
	}
	
	public static int search(int data[], int n, int x) {
        
		int lo = 0, hi = n-1;
        // find the index of the smallest value using binary search.
		while (lo < hi) {
			int mid = (lo + hi) / 2;
			if (data[mid] > data[hi])
				lo = mid + 1; // right side
			else
				hi = mid; // left side - only case where we can have infinite loop
		}
        
        
        // lo == hi is the index of the smallest value and also the number of places rotated.
        int offset = lo;
        
        lo=0;hi=n-1;        
        // The usual binary search and accounting for rotation.
        while(lo<=hi){
			int mid = (lo + hi) / 2;
			int realmid = (mid + offset) % n;
			if (x > data[realmid])
				lo = mid + 1;
			else if (x < data[realmid])
				hi = mid - 1;
			else
				return realmid;
        }
        return -1;
    }
	
	
	public static int binarySearch(int[] data, int n, int x) {
		int lo = 0, hi = n-1;
		
		while (lo <= hi) {
			int mid = (lo + hi)/2;
			if (x == data[mid]) 	return mid;
			else if (x < data[mid]) hi = mid-1;
			else lo = mid+1;
		}
		
		return -1;
	}
	
	public static int bsearch(int[] data, int lo, int hi, int x) {

		if (data.length < 1) return -1;
		while (lo <= hi) {
			int m = (lo + hi)/2;
			if (x < data[m]) hi = m-1;
			else if (x > data[m]) lo = m+1;
			else return m;
		}
		
		return -1;
	}
	
	public static double calcAngle(int h, int m) {
		double hAngle = (h%12)/12.0 * 360 + (0.5*(m%60));
		double mAngle = (m%60)/60.0 * 360;
		
		System.out.println(hAngle);
		System.out.println(mAngle);
		System.out.println((-2+5)%5);
		return ((hAngle - mAngle) +360)% 360;
	}
}
