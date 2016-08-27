package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 303. Range Sum Query - Immutable
 * 
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * 
 * Example:
 * 
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * 		
 * 		sumRange(0, 2) -> 1 
 * 		sumRange(2, 5) -> -1 
 * 		sumRange(0, 5) -> -3
 * 
 * Note: a) You may assume that the array does not change. b) There are many
 * calls to sumRange function.
 * 
 * @author Hxkandwal
 *
 */
public class RangeSumQuery extends AbstractCustomTestRunner {

	private int[] nums;

	// Your NumArray object will be instantiated and called as such:
	// NumArray numArray = new NumArray(nums);
	// numArray.sumRange(0, 1);
	// numArray.sumRange(1, 2);

	public RangeSumQuery(int[] nums) {
		this.nums = nums;
	}

	public int _sumRange(int i, int j) {
		int result = -1;

		if ((0 <= i)  && i <= j && j < nums.length) {
			int smallerSum, fullSum;
			smallerSum = fullSum = 0;

			for (int idx = 0; idx <= j; idx++) {
				fullSum += nums[idx];

				if (idx == (i - 1))
					smallerSum = fullSum;
			}

			result = fullSum - smallerSum;
		}

		return result;
	}

	// driver method
	public static void main(String[] args) {
		new RangeSumQuery(new int[] {1, 2}).runTest(0, -1, -1);
		new RangeSumQuery(new int[] {1, 2}).runTest(-1, -1, -1);
		new RangeSumQuery(new int[] {1, 2}).runTest(0, 0, 1);
		new RangeSumQuery(new int[] {1, 2, 3, 4}).runTest(3, 1, -1);
		new RangeSumQuery(new int[] {-2, 0, 3, -5, 2, -1}).runTest(0, 2, 1);
		new RangeSumQuery(new int[] {-2, 0, 3, -5, 2, -1}).runTest(2, 5, -1);
		new RangeSumQuery(new int[] {-2, 0, 3, -5, 2, -1}).runTest(0, 5, -3);
		new RangeSumQuery(new int[] {-4, -5}).runTest(0, 0, -4);
		new RangeSumQuery(new int[] {-4, -5}).runTest(1, 1, -5);
		new RangeSumQuery(new int[] {-4, -5}).runTest(0, 1, -9);
		
		System.out.println("ok!");
	}

	public void runTest(final int i, final int j, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { i, j });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {
		Integer answer = null;

		try {
			answer = (Integer) method.invoke(this, externalVariables);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
			return null;
		}

		return answer;
	}

}
