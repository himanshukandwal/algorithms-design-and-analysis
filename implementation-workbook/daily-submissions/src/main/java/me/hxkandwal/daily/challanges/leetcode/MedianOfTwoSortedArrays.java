package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 4. Median of Two Sorted Arrays
 * 
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * Example 1: nums1 = [1, 3] 	nums2 = [2]
 * 
 * 			  The median is 2.0
 * 
 * Example 2: nums1 = [1, 2] 	nums2 = [3, 4]
 * 
 * 			  The median is (2 + 3)/2 = 2.5
 *  
 * @author Hxkandwal
 *
 */
public class MedianOfTwoSortedArrays extends AbstractCustomTestRunner {

	private static MedianOfTwoSortedArrays _instance = new MedianOfTwoSortedArrays();

	private MedianOfTwoSortedArrays() {
	}

	public double _findMedianSortedArrays(int[] nums1, int[] nums2) {
		
		return 0d;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, null, 0);
		_instance.runTest(new int[] {}, new int[] {}, 0);
		_instance.runTest(new int[] { 1, 3 }, new int[] { 2 }, 2.0d);
		_instance.runTest(new int[] { 1, 2 }, new int[] { 3, 4 }, 2.5d);
	}

	public void runTest(final int[] nums1, final int[] nums2, final double expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums1, nums2 });

		for (Object answer : answers)
			assertThat((Double) answer).isWithin(expectedOutput);

		System.out.println("ok!");
	}

}
