package challenges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Find minimum sum such that one of every three consecutive elements is taken
 * 
 * Given an array of n non-negative numbers, the task is to find the minimum sum of elements 
 * (picked from the array) such that at least one element is picked out of every 3 consecutive elements 
 * in the array.
 * 
 * Examples:
 * 
 * 	Input : arr[] = {1, 2, 3}
 * 	Output : 1
 * 
 * 	Input : arr[] = {1, 2, 3, 6, 7, 1}
 * 	Output : 4
 * 
 * 	We pick 3 and 1  (3 + 1 = 4)
 * 	Note that there are following subarrays of three consecutive elements
 * 	{1, 2, 3}, {2, 3, 6}, {3, 6, 7} and {6, 7, 1}
 * 
 * 	We have picked one element from every subarray.
 * 
 * 	Input : arr[] = {1, 2, 3, 6, 7, 1, 8, 6, 2, 7, 7, 1}
 * 	Output : 7
 * 
 * 	The result is obtained as sum of 3 + 1 + 2 + 1
 * 
 * link : http://www.geeksforgeeks.org/find-minimum-sum-one-every-three-consecutive-elements-taken/
 * 
 * @author Hxkandwal
 *
 */
public class MinimumSumForOneOfEveryThreeConsecutiveElementsIsTaken extends AbstractCustomTestRunner {

	private static MinimumSumForOneOfEveryThreeConsecutiveElementsIsTaken _instance = new MinimumSumForOneOfEveryThreeConsecutiveElementsIsTaken();
	
	private MinimumSumForOneOfEveryThreeConsecutiveElementsIsTaken() {}
	
	/* method : this is becuase we have to consider all the possible window combinations. As whenever we add a 4th element, it will be sum of the min
	 * 			from the previous three elements.
	 * 
	 * 			At last, we have to take the min of the last three, as we adding first three blindly, so only propagation of that might yield incorrect
	 * 			answer at the last slot.
	 */
	public static int _findMinSum(int arr[]) {
		int[] sum = new int[arr.length];
		
		if (arr.length <= 3)
			return (arr.length == 0) ? -1 : (arr.length == 1) ? arr[0] : (arr.length == 2) ? 
					Math.max(arr[0], arr[1]) : Math.min(arr[0], Math.min(arr[1], arr[2]));
		else {
			sum [0] = arr [0];
			sum [1] = arr [1];
			sum [2] = arr [2];
			
			for (int idx = 3; idx < arr.length; idx ++) 
				sum [idx] = arr [idx] + Math.min(sum[idx - 1], Math.min(sum[idx - 2], sum[idx - 3]));
		}
		
		return Math.min(sum[arr.length - 1], Math.min(sum[arr.length - 2], sum[arr.length - 3]));
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 1, 2, 1}, 1);
		_instance.runTest(new int[] { 1, 2, 3, 6, 7, 1}, 4);
	}
	
	public void runTest(final int [] arr, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { arr });
	
		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
