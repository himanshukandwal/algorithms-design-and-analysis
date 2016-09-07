package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * An element in a sorted array can be found in O(log n) time via binary search. But suppose we rotate an ascending order sorted 
 * array at some pivot unknown to you beforehand. 
 * 
 * So for instance, 1 2 3 4 5 might become 3 4 5 1 2. 
 * 
 * Devise a way to find an element in the rotated array in O(log n) time.
 * 
 * link : http://www.geeksforgeeks.org/search-an-element-in-a-sorted-and-pivoted-array/
 * 
 * @author Hxkandwal
 *
 */
public class SearchElementInSortedAndRotatedArray extends AbstractCustomTestRunner {
	
	private static SearchElementInSortedAndRotatedArray _instance = new SearchElementInSortedAndRotatedArray();
	
	private SearchElementInSortedAndRotatedArray() {}
	
	// method : binary search but with some optimization of  reusing similar subproblem (rotated sorted array) 
	public static boolean _search(int[] array, int value) {
		if (array == null || array.length == 0)
			return false;
		
		return searchInner(array, 0, array.length - 1, value);
	}

	private static boolean searchInner(int[] array, int start, int end, int value) {
		if (start < 0 || end >= array.length)
			return false;

		if (start > end)
			return false;
		
		int mid = (start + end) >>> 1;

		if (array[mid] == value)
			return true;

		// if array [start ... mid] is sorted correctly
		if (array [mid] > array [start]) {
			if (value > array [start] && value < array [mid])
				return searchInner (array, start, mid - 1, value);
			else
				return searchInner (array, mid + 1, end, value);

			// else the other array is sorted
		} else {
			if (value > array [mid] && value <= array [end])
				return searchInner (array, mid + 1, end, value);
			else
				return searchInner (array, start, mid - 1, value);
		}
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {}, 4, false);
		_instance.runTest(null, 4, false);
		_instance.runTest(new int[] { 3, 4, 5, 1, 2 }, 5, true);
		_instance.runTest(new int[] { 3, 4, 5, 1, 2 }, 1, true);
		_instance.runTest(new int[] { 4, 5, 1, 2, 3 }, 5, true);
		_instance.runTest(new int[] { 4, 5, 6, 7, 8, 1, 2, 3 }, 5, true);
		_instance.runTest(new int[] { 4, 5, 6, 7, 8, 1, 2, 3 }, 9, false);
	}

	public void runTest(final int[] array, final int value, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array, value });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
