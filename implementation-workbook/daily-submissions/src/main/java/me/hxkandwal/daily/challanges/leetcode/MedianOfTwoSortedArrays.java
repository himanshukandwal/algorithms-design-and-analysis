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
		if ((nums1 == null && nums2 == null) || (nums1.length == 0 && nums2.length == 0))
			return 0;

		int sIdx1 = (nums1.length > 0) ? 0 : -1;
		int eIdx1 = (nums1.length > 0) ? nums1.length - 1 : 0;

		int sIdx2 = (nums2.length > 0) ? 0 : -1;
		int eIdx2 = (nums2.length > 0) ? nums2.length - 1 : -1;

		int start = (sIdx1 >= 0 ? (sIdx2 >= 0 ? (nums2[0] < nums1[0] ? nums2[0] : nums1[0]) : nums1[0]) : nums2[0]);
		int end = (eIdx1 >= 0
				? (eIdx2 >= 0 ? (nums2[eIdx2] > nums1[eIdx1] ? nums2[eIdx2] : nums1[eIdx1]) : nums1[eIdx1])
				: nums2[eIdx2]);

		boolean even = (nums1.length + nums2.length) % 2 == 0;

		int mid = (start + end) >>> 1;
		int mIdx1 = -1, mIdx2 = -1;

		if (sIdx1 >= 0 && eIdx1 >= 0 && nums1[eIdx1] >= mid && nums1[sIdx1] <= mid) {
			mIdx1 = (sIdx1 + eIdx1) >>> 1;

			while (sIdx1 <= eIdx1) {
				if ((nums1[mIdx1] == mid) || (nums1[mIdx1] < mid
						&& (mIdx1 + 1 >= nums1.length || (mIdx1 + 1 < nums1.length || nums1[mIdx1 + 1] > mid)))) {
	

					if (nums1[mIdx1] < mid)
						sIdx1 = mIdx1 + 1;
					else
						eIdx1 = mIdx1 - 1;

					mIdx1 = (sIdx1 + eIdx1) >>> 1;
				}
			}
		}

		if (sIdx2 >= 0 && eIdx2 >= 0 && nums2[eIdx2] > mid) {
			mIdx2 = (sIdx2 + eIdx2) >>> 1;

			while ((nums2[mIdx2] != mid)
					|| !(nums2[mIdx2] < mid && (mIdx2 + 1 < nums2.length || nums2[mIdx2 + 1] > mid))) {

				if (nums2[mIdx2] < mid)
					sIdx2 = mIdx2 + 1;
				else
					eIdx2 = mIdx2 - 1;

				mIdx2 = (sIdx2 + eIdx2) >>> 1;
			}
		}

		int median = 0;
		if (even) {
			if (mIdx1 >= 0) {
				if (mIdx1 + 1 < nums1.length) {
					median = (nums1[mIdx1] + nums1[mIdx1 + 1]) / 2;
				} else {
					median = (nums1[mIdx1] + nums2[mIdx2]) / 2;
				}
			} else {
				if (mIdx2 + 1 < nums2.length) {
					median = (nums2[mIdx2] + nums2[mIdx2 + 1]) / 2;
				} else {
					median = (nums1[mIdx1] + nums2[mIdx2]) / 2;
				}
			}
		} else {
			median = (mIdx1 >= 0 ? nums1[mIdx1] : nums2[mIdx2]);
		}

		return median * 1d;
	}

	// driver method
	public static void main(String[] args) {
//		_instance.runTest(null, null, 0);
//		_instance.runTest(new int[] {}, new int[] {}, 0);
		_instance.runTest(new int[] { 1, 3 }, new int[] { 2 }, 2.0d);
//		_instance.runTest(new int[] { 1, 2 }, new int[] { 3, 4 }, 2.5d);
	}

	public void runTest(final int[] nums1, final int[] nums2, final double expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums1, nums2 });

		for (Object answer : answers)
			assertThat((Double) answer).isWithin(expectedOutput);

		System.out.println("ok!");
	}

}
