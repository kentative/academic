package ksl.academic.algorithm.sim;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

// https://www.interviewcake.com/question/java/merging-ranges
public class MergeMeeting {

	public static void main(String[] args) {
		Meeting[] meetings = new Meeting[] { 
				new Meeting(0, 1), 
				new Meeting(3, 5), 
				new Meeting(4, 8),
				new Meeting(10, 12), 
				new Meeting(9, 10) 
			};

		Meeting[] mergeRange = mergeRange(meetings);
		for (Meeting m : mergeRange) {
			System.out.println(m);
		}
	}

	public static Meeting[] mergeRange(Meeting[] meetings) {
		int n = meetings.length;

		if (n == 0 || n == 1) return meetings;
		List<Meeting> mergedList = new ArrayList<>(n);
		
		Set<Meeting> visited = new HashSet<>(); 

		for (int i = 0; i < n; i++) {
			Meeting m = meetings[i];
			if (visited.contains(m)) continue;

			List<Meeting> olList = getOverlapping(m, meetings);
			while (!olList.isEmpty()) {
				Meeting ol = olList.remove(0);
				visited.add(ol);
				m = merge(m, ol);
			}
			mergedList.add(m);
		}
		
		return mergedList.toArray(new Meeting[mergedList.size()]);
	}
	
	private static List<Meeting> getOverlapping(Meeting m, Meeting[] meetings) {
		
		List<Meeting> overlapped = new LinkedList<>();
		for (int i = 0; i < meetings.length; i++) {
			if (isOverlapping(m, meetings[i])) {
				overlapped.add(meetings[i]);
			}
		}
		return overlapped;
	}

	// This is the incorrect version, on White Board
	public static Meeting[] mergeRangeWB(Meeting[] meetings) {
		int n = meetings.length;

		if (n == 0 || n == 1) return meetings;
		List<Meeting> mergedList = new ArrayList<>(n);
		
		Set<Meeting> visited = new HashSet<>(); 

		for (int i = 0; i < n-1; i++) {
			Meeting m = meetings[i];
			
			if (visited.contains(m)) {
				continue;
			}

			for (int j = 0; j < n; j++) {
				if (isOverlapping(m, meetings[j])) {
					m = merge(m, meetings[j]);
				}
			}
			if (!mergedList.contains(m))
			mergedList.add(m);
		}
		
		return mergedList.toArray(new Meeting[mergedList.size()]);
	}

	
	private static Meeting merge(Meeting m1, Meeting m2) {
		Meeting m = new Meeting(0, 0);
		m.startTime = Math.min(m1.startTime, m2.startTime);
		m.endTime = Math.max(m1.endTime, m2.endTime);
		return m;
	}

	private static boolean isOverlapping(Meeting m1, Meeting m2) {
		return m1.startTime <= m2.endTime && m1.startTime >= m2.startTime
				|| m1.endTime >= m2.startTime && m1.endTime <= m2.endTime;
	}

	public static class Meeting {

		private int startTime;
		private int endTime;

		public Meeting(int startTime, int endTime) {
			// number of 30 min blocks past 9:00 am
			this.startTime = startTime;
			this.endTime = endTime;
		}

		public int getStartTime() {
			return startTime;
		}

		public void setStartTime(int startTime) {
			this.startTime = startTime;
		}

		public int getEndTime() {
			return endTime;
		}

		public void setEndTime(int endTime) {
			this.endTime = endTime;
		}
		
		public int hashCode() {
			return Objects.hash(startTime, endTime);
		}
		
		public boolean equals(Object obj) {
			if (obj == this) return true;
			if (obj == null) return false;
			if (obj.getClass() != this.getClass()) return false;
			
			Meeting other = (Meeting) obj;
			return (startTime ==  other.startTime) && (endTime == other.endTime);
		}
		
		public String toString() {
			return "(" + startTime + ", " + endTime +")";
		}
	}

}
