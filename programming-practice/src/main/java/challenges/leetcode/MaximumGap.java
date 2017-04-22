package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;
import java.util.TreeSet;

import challenges.AbstractCustomTestRunner;

/**
 * 164. Maximum Gap
 * 
 * Given an unsorted array, find the maximum difference between the successive elements in its sorted form.
 * 
 * Try to solve it in linear time/space.
 * 
 * Return 0 if the array contains less than 2 elements.
 * 
 * You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.
 * 
 * @author Hxkandwal
 */
public class MaximumGap extends AbstractCustomTestRunner {
	
	private static MaximumGap _instance = new MaximumGap();

	public int _maximumGap(int[] nums) {
		if (nums.length <= 1) return 0;
        int max = 0;
        TreeSet<Integer> tset = new TreeSet<>();
        for (int num : nums) tset.add (num);
        for (int num : tset) {
            max = Math.max (max, tset.floor (num - 1) == null ? max : Math.abs (tset.floor (num - 1) - num));
            max = Math.max (max, tset.ceiling (num + 1) == null ? max : Math.abs (tset.ceiling (num + 1) - num));
            tset.add (num);
        }
        return max;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1,10000000 }, 9999999);
	}

	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
