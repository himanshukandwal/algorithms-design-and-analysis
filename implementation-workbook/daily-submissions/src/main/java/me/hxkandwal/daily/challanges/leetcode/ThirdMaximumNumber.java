package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 414. Third Maximum Number
 * 
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the maximum number. The time complexity must be in O(n).
 * 
 * Example 1:
 * 		Input: [3, 2, 1]
 * 		Output: 1
 * 
 * 		Explanation: The third maximum is 1.
 * 
 * Example 2:
 * 		Input: [1, 2]
 * 		Output: 2
 * 
 * 		Explanation: The third maximum does not exist, so the maximum (2) is returned instead.
 * 
 * Example 3:
 * 		Input: [2, 2, 3, 1]
 * 		Output: 1
 * 
 * 		Explanation: Note that the third maximum here means the third maximum distinct number.
 * 					 Both numbers with value 2 are both considered as second maximum.
 * 
 * @author Hxkandwal
 */
public class ThirdMaximumNumber extends AbstractCustomTestRunner {
	
	private static ThirdMaximumNumber _instance = new ThirdMaximumNumber();
	
	private ThirdMaximumNumber() {}
	
    public int _thirdMax(int[] nums) {
    	Integer max = null, smax = null, tmax = null;
        for (int num : nums) {
            if (max == null || num > max) {
                tmax = smax; smax = max; max = num;
            } else if (num < max && (smax == null || num > smax)) {
                tmax = smax; smax = num;
            } else if (smax != null && num < smax && (tmax == null || num > tmax)) tmax = num;
        }
        return (tmax == null) ? max : tmax;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 3, 2, 1 }, 1);
		_instance.runTest(new int [] { 1, 2 }, 2);
		_instance.runTest(new int [] { 2, 2, 3, 1 }, 1);
	}

	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
    
}
