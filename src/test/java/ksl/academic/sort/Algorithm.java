package ksl.academic.sort;

import org.junit.Test;

import ksl.academic.algorithm.sort.InsertionSort;
import ksl.academic.algorithm.sort.MergeSort2;
import ksl.academic.algorithm.sort.QuickSort;
import ksl.academic.algorithm.sort.QuickSort2;
import ksl.academic.algorithm.sort.QuickSort3;
import ksl.academic.algorithm.sort.SortingAlgorithm;

/**
 * 
 * Category Time Space Stability Adaptability
 * 
 * @since June 30 2018
 */
public class Algorithm extends AlgorithmTestBase {

	@Test
	public void insertion() throws Exception {

		if (MAX_COUNT < 100000) {
			SortingAlgorithm algorithm = new InsertionSort();
			sortArray(algorithm);
			verify();
		}

		// sortList(algorithm);

	}

	@Test
	public void quick() throws Exception {
		sortArray(new QuickSort());
		verify();
		print();
	}
	
	@Test
	public void quick2() throws Exception {
		sortArray(new QuickSort2());
		verify();
		print();
	}
	
	@Test
	public void quick3() throws Exception {
		sortArray(new QuickSort3());
		print();
		verify();
	}

	@Test
	public void merge() {
		SortingAlgorithm algorithm = new MergeSort2();
		sortArray(algorithm);
		print();
		verify();
	}

}
