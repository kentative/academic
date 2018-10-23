package ksl.academic.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import ksl.academic.algorithm.sort.SortUtil;

public class Sandbox {
	public static void main (String...args) {

		List<int[]> queue;
		
		
		String[] test= {
				"word1", 
				"word2",
				"word4",
				"word5"
				};
		
		List<String> testList = new ArrayList<>(Arrays.asList(test));
		System.out.println("Is found " + Collections.binarySearch(testList, "word3"));
		
		int[][] data = new int[][] {
			{1, 2, 3}, 
			{1, 3, 2},
			{2, 1, 3},
			{2, 3, 1},
			{3, 1, 2},
			{3, 2, 1},
			};
			
		for (int i = 0; i < data.length; i++) {
			findMedian(data[i]);
		}
	}
	
	
	static void findMedian(int[] data) {
	
		if (data[1] < data[0]) SortUtil.swap(data, 1, 0);
		if (data[1] > data[2]) SortUtil.swap(data, 1, 2);
		if (data[0] > data[1]) SortUtil.swap(data, 1, 0);
		System.out.println(data[1]);
		
//		if (data[1] < data[0]) SortUtil.swap(data, 1, 0);
//		if (data[2] < data[0]) SortUtil.swap(data, 0, 2);
//		if (data[1] < data[2]) SortUtil.swap(data, 1, 2);
//		System.out.println(data[2]);
		
	}
	
	static class K {
		
	}
	
}
