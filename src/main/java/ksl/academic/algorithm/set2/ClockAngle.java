package ksl.academic.algorithm.set2;

public class ClockAngle {

	/**
	 * Calculates the angle between the hour and minute hand of a clock
	 * 
	 * @param h - the hour    [0 - 12)
	 * @param m - the minute  [0 - 60)
	 * @return
	 */
	public static double calcAngle(int h, int m) {
		double hAngle = 
				(h%12)/12.0 * 360    // every 1 hour, moves by 30 degrees (360/12), up to 12 hours
				+ (0.5*(m%60));      // every 1 minutes, hour moves by 0.5 degree, up to 60 minutes
		double mAngle = (m%60)/60.0 * 360;
		
		System.out.println("hour: "   + hAngle);
		System.out.println("minute: " + mAngle);
		return Math.abs((hAngle - mAngle))% 360;
	}
	
	public static void main(String[] args) {
		
		System.out.println(calcAngle(12, 00)); // 0
		System.out.println(calcAngle(12, 30)); // 0
		System.out.println();
		System.out.println(calcAngle(1, 0));   // 30
		System.out.println();
		
		System.out.println(calcAngle(2, 0));   // 60
		System.out.println();
		
		System.out.println(calcAngle(3, 0));   // 90
		System.out.println();
		
	}
}
