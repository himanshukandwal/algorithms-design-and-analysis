package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
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
		if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0)
			return new int[] {};
		
		Arrays.sort(nums1);
		Arrays.sort(nums2);
		
		int idx1 = 0;
		int idx2 = 0;
		
		List<Integer> same = new ArrayList<Integer>();
		while (idx1 < nums1.length && idx2 < nums2.length) {
			if (nums1[idx1] > nums2 [idx2])
				idx2 ++;
			else if (nums1[idx1] < nums2 [idx2])
				idx1 ++;
			else {
				// check will work as the arrays are sorted. will keep unique things in the list. 
				if (same.isEmpty() || same.get(same.size() -1) != nums1[idx1])
					same.add(nums1[idx1]);
				
				idx1 ++;
				idx2 ++;
			}	
		}
		
		if (same.size() == 0)
			return new int[] {};
		else {
			int[] res = new int [same.size()];
			int idx = 0;
			for (Integer i : same) 
				res [idx ++] = i;
			
			return res;
		}
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {1, 2, 2, 1}, new int[] {2, 2}, new int[] {2});
		_instance.runTest(new int[] {1, 2, 3, 1}, new int[] {2, 3}, new int[] {2, 3});
		_instance.runTest(null, new int[] {2, 3}, new int[] {});
		_instance.runTest(null, null, new int[] {});
		_instance.runTest(new int[] {}, null, new int[] {});
		_instance.runTest(new int[] {}, new int[] {}, new int[] {});
	}
	
	public void runTest(final int[] num1, final int[] num2, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num1, num2 });
		
		for (Object answer : answers) 
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
