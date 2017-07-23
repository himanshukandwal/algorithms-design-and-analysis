package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 645. Set Mismatch
 * 
 * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers in the set got 
 * duplicated to another number in the set, which results in repetition of one number and loss of another number.
 * 
 * Given an array nums representing the data status of this set after the error. Your task is to firstly find the number occurs twice 
 * and then find the number that is missing. Return them in the form of an array.
 * 
 * Example 1:
 * 		Input: nums = [1,2,2,4]
 * 		Output: [2,3]
 * 
 * Note:
 * 	-	The given array size will in the range [2, 10000].
 * 	-	The given array's numbers won't have any order.
 * 
 * @author Hxkandwal
 */
public class SetMismatch extends AbstractCustomTestRunner {
	
	private static SetMismatch _instance = new SetMismatch();

	/*
	 *  put each element k to the k-1th position unless the k-1th is already occupied by k. 
	 *  In that case we know k is a duplicate. In a second pass, we look for the ith position 
	 *  where its value is not i+1, we know i+1 is the missing value.
	 */
	public int[] _findErrorNums(int[] nums) {
		for (int i = 0; i < nums.length; i ++) 
			while (nums [nums [i] - 1] != nums [i]) 
				swap (nums, i, nums[i] - 1);
		
        for (int i = 0; i < nums.length; i ++) 
			if (nums[i] != i + 1) return new int[] { nums [i], i + 1 };
        
        return null;
    }
	
	private void swap (int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2, 2, 4 }, new int[] { 2, 3 });
		_instance.runTest(new int[] { 2, 2 }, new int[] { 2, 1 });
		_instance.runTest(new int[] { 4, 3, 2, 2, 1 }, new int[] { 2, 5 });
	}

	public void runTest(final int [] nums, final int [] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((int []) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
