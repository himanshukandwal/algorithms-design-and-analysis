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

	public boolean _isPresent (int [] nums, int sum) {
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
