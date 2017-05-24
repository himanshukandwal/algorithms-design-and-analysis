package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * Check Sum Sub-array
 * 
 * You are given an array of integers arr and a target k. Your task is to figure out whether you can 
 * pick a subarray of arr such that its elements add up to k.
 * 
 * Example:
 * 		For arr = [0, 1, 2, 3, 4, 5] and k = 7, the answer should be checkSum(arr, k) = true.
 * 
 * 		The sum of the elements of the subarray [3, 4] is exactly 7, so the answer is true.
 * 
 * @author Hxkandwal
 */
public class CheckSumSubarray extends AbstractCustomTestRunner {
	
	private static CheckSumSubarray _instance = new CheckSumSubarray();

	public boolean _checkSumSub(int[] arr, int k) {
		int sum = 0;
		Set<Integer> set = new HashSet<>();
		for (int e : arr)
			if (set.add(sum += e) && set.remove(sum - k) || sum == k) return true;
		return false;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 0, 1, 2, 3, 4, 5 }, 7, true);
	}

	public void runTest(final int[] arr, final int k, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { arr, k });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	

}
