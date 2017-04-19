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
        int m = nums1.length, n = nums2.length;
        if (n > m) return _findMedianSortedArrays (nums2, nums1);
        if (n == 0) return ((double) nums1 [(m - 1)/2] + (double) nums1 [m/2]) * 0.5d;
        
        int low = 0, high = 2 * n;
        while (low <= high) {
            int mid2 = (low + high)/2;
            int mid1 = m + n - mid2;
            
            double l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1 [(mid1 - 1)/2];
            double l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2 [(mid2 - 1)/2];
            double r1 = (mid1 == 2 * m) ? Integer.MAX_VALUE : nums1 [mid1/2];
            double r2 = (mid2 == 2 * n) ? Integer.MAX_VALUE : nums2 [mid2/2];
            
            if (l1 > r2) low = mid2 + 1;		
            else if (l2 > r1) high = mid2 - 1;	
            else return (Math.max(l1, l2) + Math.min(r1, r2)) / 2;
        }
        return -1;
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
