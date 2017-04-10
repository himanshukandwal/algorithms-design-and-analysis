package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 268. Missing Number
 * 
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * 
 * For example,
 * 		Given nums = [0, 1, 3] return 2.
 * 
 * Note: Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 *  
 * @author Hxkandwal
 */
public class MissingNumber extends AbstractCustomTestRunner {
	
	private static MissingNumber _instance = new MissingNumber();
	
	public MissingNumber() {}
	
    public int _missingNumber(int[] nums) {
    	int sum = nums.length * (nums.length + 1)/2;
    	for (int num : nums) sum -= num;
    	return sum;
    }	
    
    public int _missingNumberXOR(int[] nums) {
        int xor = 0, i = 0;
    	for (i = 0; i < nums.length; i++) xor = xor ^ i ^ nums[i];
    	return xor ^ i;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 0, 1, 3 }, 2);
		_instance.runTest(new int [] { 0 }, 1);
	}

	public void runTest(final int[] nums, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    

}
