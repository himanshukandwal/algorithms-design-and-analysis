package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 4. Median of Two Sorted Arrays
 * 
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * 
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 * 
 * Example 1: nums1 = [1, 3] 	nums2 = [2],
 * 			  The median is 2.0
 * 
 * Example 2: nums1 = [1, 2] 	nums2 = [3, 4]
 * 			  The median is (2 + 3)/2 = 2.5
 *  
 * @author Hxkandwal
 */
public class MedianOfTwoSortedArrays extends AbstractCustomTestRunner {

	private static MedianOfTwoSortedArrays _instance = new MedianOfTwoSortedArrays();

	public double _findMedianSortedArrays(int[] nums1, int[] nums2) {
		int n = nums1.length, m = nums2.length;
    	int mid1 = (n + m + 1) / 2, mid2 = (n + m + 2) / 2;
    	return (getKth (nums1, 0, m, nums2, 0, n, mid1) + getKth (nums1, 0, m, nums2, 0, n, mid2)) / 2;  
    }
    
    private int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
//    	if (end1 > end2) return getKth (nums2, start2, end2, nums1, start1, end1, k);
        if (end1 == 0) return nums2 [start2 + k - 1];
        if (k == 1) return Math.min (nums1 [start1], nums2 [start2]);
        
        int i = Math.min (end2, k / 2), j = Math.min (end1, k / 2);
        if (nums1 [start1 + i - 1] < nums2 [start2 + j - 1]) {
            return getKth(nums1, start1 + i, end1 - i, nums2, start2, end2, k - i);
        } else {
            return getKth(nums1, start1, end1, nums2, start2 + j, end2 - j, k - j);
        }
    }

	// driver method
	public static void main(String[] args) {
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
