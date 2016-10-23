package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Count all increasing subsequences
 * 
 * We are given an array of digits (values lie in range from 0 to 9). The task is to count all the 
 * sub sequences possible in array such that in each subsequence every digit is greater than its previous 
 * digits in the subsequence.
 * 
 * Examples:
 * 
 * Input : 	arr[] = {1, 2, 3, 4}
 * 			Output: 15
 * 			
 * 			There are total increasing subsequences 
 * 				{1}, {2}, {3}, {4}, {1,2}, {1,3}, {1,4}, 
 * 				{2,3}, {2,4}, {3,4}, {1,2,3}, {1,2,4}, 
 * 				{1,3,4}, {2,3,4}, {1,2,3,4}
 * 
 * 
 * Input : 	arr[] = {4, 3, 6, 5}
 * 			Output: 8
 * 
 * 			Sub-sequences are 
 * 				{4}, {3}, {6}, {5}, 
 * 				{4,6}, {4,5}, {3,6}, {3,5}
 * 
 * Input : 	arr[] = {3, 2, 4, 5, 4}
 * 			Output : 14
 * 
 * 			Sub-sequences are 
 * 				{3}, {2}, {4}, {3,4},
 * 				{2,4}, {5}, {3,5}, {2,5}, {4,5}, {3,2,5}
 * 				{3,4,5}, {4}, {3,4}, {2,4}
 * 
 * 
 * link : http://www.geeksforgeeks.org/count-all-increasing-subsequences/ 
 * 
 * @author Hxkandwal
 *
 */
public class CountAllIncreasingSubsequences extends AbstractCustomTestRunner {
	
	private static CountAllIncreasingSubsequences _instance = new CountAllIncreasingSubsequences();
	
	private CountAllIncreasingSubsequences() {}
	
	// very powerful method. O(n2) solution
	public static int _countSubsequences(int[] array) {
		int length = array.length;
		int[] count = new int [length];
		
		for (int idx = 0; idx < array.length; idx ++) {
			count [idx] = 1;	// for the path starting from the element itself.

			for (int innerIdx = 0; innerIdx < idx; innerIdx ++) {
				// this path can be extended.
				if (array [innerIdx] < array [idx])
					count [idx] += count [innerIdx];
			}
		}
		
		int resultingCount = 0;
		for (int idx = 0; idx < count.length; idx++) 
			resultingCount += count [idx];
		
		return resultingCount;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {}, 0);
		_instance.runTest(new int[] { 1, 2, 3, 4 }, 15);
		_instance.runTest(new int[] { 4, 3, 6, 5 }, 8);
		_instance.runTest(new int[] { 3, 2, 4, 5, 4 }, 14);
		_instance.runTest(new int[] { 7, 4, 6, 8 }, 9);
	}

	public void runTest(final int[] array, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
