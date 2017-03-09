package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Subset Sum Problem
 * 
 * https://www.youtube.com/watch?v=s6FhG--P7z0&index=4&list=PLrmLmBdmIlpsHaNTPP_jHHDx_os9ItYXr
 * 
 * @author Hxkandwal
 */
public class SubsetSum extends AbstractCustomTestRunner {
	
	private static SubsetSum _instance = new SubsetSum();
	
	// optimal dynamic approach.
	public boolean isPresentOptimalDP (int [] nums, int sum) {
		boolean [] dp = new boolean [sum + 1];
		
		dp [0] = true;
		
		for (int num : nums)
			for (int col = dp.length - 1; col >= 0; col --)
				if (col >= num) 
					dp [col] = dp [col] || dp [col - num];
		
		return dp [sum];
	}
	
	// dynamic approach.
	public boolean _isPresentDP (int [] nums, int sum) {
		boolean [][] dp = new boolean [nums.length] [sum + 1];
		
		for (int row = 0; row < nums.length; row ++) dp [row][0] = true;
		
		for (int row = 0; row < nums.length; row ++) {
			for (int col = 1; col <= sum; col ++) {
				if (row == 0) { dp [row][col] = nums [row] == col ? true : false; continue; }
				if (nums [row] > col) 
					dp [row][col] = dp [row - 1][col];
				else 
					dp [row][col] = dp [row - 1][col] || dp [row - 1][col - nums [row]];
			}
		}
		
		return dp [nums.length - 1][sum];
	}
	
	// recursive approach
	public boolean isPresent (int [] nums, int sum) {
		return isPresentAt (nums, 0, 0, sum);
	}
	
	private boolean isPresentAt (int[] nums, int start, int rsum, int sum) {
		if (start == nums.length) return  (rsum == sum);
		boolean res = isPresentAt (nums, start + 1, rsum, sum);
		if (res) return res;
		res = res || isPresentAt(nums, start + 1, rsum + nums [start], sum);
		return res;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 2, 3, 7, 8, 10 }, 11, true);
		_instance.runTest(new int[] { 2, 3, 7, 8, 10 }, 5, true);
		_instance.runTest(new int[] { 2, 3, 7, 8, 10 }, 12, true);
		_instance.runTest(new int[] { 2, 3, 7, 8, 10 }, 14, false);
	}

	public void runTest(final int[] nums, final int s, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, s });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
