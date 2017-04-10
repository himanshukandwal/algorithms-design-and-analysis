package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 169. Majority Element
 * 
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 * 
 * @author Hxkandwal
 */
public class MajorityElement extends AbstractCustomTestRunner {
	
	private static MajorityElement _instance = new MajorityElement();
	
	private MajorityElement() {}

	// O(n) solution (Boyer-Moore algorithm)
	public int _majorityElementFaster(int[] nums) {
		int count = 0, major = nums [0];
		for (int num : nums) {
			if (num == major) count ++;
			else if (count == 0) { major = num; count ++; }
			else count --;
		}
		return major;
    }
	
	// O(nlogn) solution
    public int _majorityElement(int[] nums) {
    	Arrays.sort(nums);
    	return nums [nums.length/2];
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 1, 1, 2 }, 1);
		_instance.runTest(new int[] { 1, 1, 1, 2, 2 }, 1);
		_instance.runTest(new int[] { 1, 1, 1, 1, 2, 3, 2 }, 1);
		_instance.runTest(new int[] { 8, 8, 7, 7, 7 }, 7);
		_instance.runTest(new int[] { -1, 100, 2, 100, 100, 4, 100 }, 100);
	}

	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	    
   
}
