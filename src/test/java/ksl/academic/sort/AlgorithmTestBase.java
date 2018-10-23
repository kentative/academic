package ksl.academic.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;

import ksl.academic.algorithm.sort.SortingAlgorithm;


/**
 * 
 * Category
 * Time
 * Space
 * Stability
 * Adaptability
 * 
 * @since June 30 2018
 */
public class AlgorithmTestBase {
	
	protected int MAX_COUNT = 10;
	
	/**
	 * The data array to be sorted
	 */
	protected int[] data;
	
	/**
	 * The sorted array
	 */
	protected List<Integer> dataList;
	
	@Before
	public void setupTestBase() {
		Random random = new Random();
		
		data = new int[MAX_COUNT];
		
		dataList = new ArrayList<>();
		for (int i = 0; i < MAX_COUNT; i++) {
			data[i] = random.nextInt(MAX_COUNT);
			dataList.add(data[i]);
		}
		
		System.out.println("----------------------------------------");
		System.out.println("Size " + MAX_COUNT);
	}
	
	protected long sortArray(SortingAlgorithm algorithm) {
		
		long start = System.currentTimeMillis();
		algorithm.sort(data, data.length);
		long stop = System.currentTimeMillis();
		long duration = stop - start;

		System.out.println(algorithm.getClass().getSimpleName() + " array: " + duration);
		return duration;
	}
	
	protected long sortList(SortingAlgorithm algorithm) {

		long start = System.currentTimeMillis();
		algorithm.sort(dataList, data.length);
		long stop = System.currentTimeMillis();
		
		long duration = stop - start;
		System.out.println(algorithm.getClass().getSimpleName() + " list: " + duration);
		return duration;
	}
	
	
	protected void verify() {
		for (int i = 0; i < MAX_COUNT-1; i++) {
			Assert.assertTrue("Index: " + i, data[i] <= data[i+1]);
		}
	}
	
	
	protected void print() {
		if (MAX_COUNT > 100) return;
		for (int i = 0; i < MAX_COUNT; i++) {
			System.out.println(data[i]);
		}
	}
}
