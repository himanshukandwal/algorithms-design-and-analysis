package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 349. Intersection of Two Arrays
 * 
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2] return [2].
 * 
 * @author Hxkandwal
 *
 */
public class IntersectionOfTwoArrays extends AbstractCustomTestRunner {
	
	private static IntersectionOfTwoArrays _instance = new IntersectionOfTwoArrays();

	public int[] _intersection(int[] nums1, int[] nums2) {
		Arrays.sort (nums1);
        Arrays.sort (nums2);
        int count = 0;
        for (int idx1 = 0; idx1 < nums1.length; idx1 ++)
            if (idx1 == 0 || nums1 [idx1 - 1] != nums1 [idx1]) 
                if (Arrays.binarySearch (nums2, nums1 [idx1]) >= 0) count ++;
        
        int [] ans = new int [count];
        for (int idx1 = 0, idx = 0; idx1 < nums1.length; idx1 ++)
            if (idx1 == 0 || nums1 [idx1 - 1] != nums1 [idx1]) 
                if (Arrays.binarySearch (nums2, nums1 [idx1]) >= 0) ans [idx ++] = nums1 [idx1];
        return ans;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {1, 2, 2, 1}, new int[] {2, 2}, new int[] {2});
		_instance.runTest(new int[] {1, 2, 3, 1}, new int[] {2, 3}, new int[] {2, 3});
		_instance.runTest(new int[] {}, new int[] {}, new int[] {});
	}
	
	public void runTest(final int[] num1, final int[] num2, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num1, num2 });
		
		for (Object answer : answers) 
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
