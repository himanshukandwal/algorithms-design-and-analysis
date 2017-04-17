package challenges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Example:
 * 
 * Input  : arr[] = { 3, 10, 2, 1, 20 } 
 * Output : Length of LIS = 3
 * The longest increasing subsequence is { 3, 10, 20 }
 * 
 * Input  : arr[] = { 3, 2 }
 * Output : Length of LIS = 1
 * The longest increasing subsequences are { 3 } and { 2 }
 * 
 * Input : arr[] = { 50, 3, 10, 7, 40, 80 }
 * Output : Length of LIS = 4
 * The longest increasing subsequence is { 3, 7, 40, 80 }
 * 
 * Link : http://www.geeksforgeeks.org/dynamic-programming-set-3-longest-increasing-subsequence/
 * 
 * @author Hxkandwal
 *
 */
public class LongestIncreasingSubSequence extends AbstractCustomTestRunner {
	
	private static LongestIncreasingSubSequence _instance = new LongestIncreasingSubSequence();
	
	public static int[] _longestSubsequence(int[] array) {
		int[] memoizedArray = new int [array.length];
		
		for (int idx = 0; idx < array.length; idx++)
			memoizedArray [idx] = 1;
		
		int maxLength = 1, maxIndex = 0;
		for (int idx = 0; idx < array.length; idx ++) {
			for (int innerIdx = idx + 1; innerIdx < memoizedArray.length; innerIdx ++) {
				if (array [idx] < array [innerIdx]) {
					memoizedArray [innerIdx] = Math.max (memoizedArray [innerIdx], memoizedArray [idx] + 1);
					maxLength = Math.max(maxLength, memoizedArray [innerIdx]);
					maxIndex = (memoizedArray [innerIdx] == maxLength ? innerIdx : maxIndex);
				}
			}
		}
		
		int[] result = new int [maxLength];
		int resultIdx = maxLength - 1;
		result [resultIdx --] = array [maxIndex --];
		
		for (int idx = maxIndex; resultIdx >= 0; idx --) {
			if (memoizedArray [idx] == (resultIdx + 1))
				result [resultIdx --] = array [idx];
		}
		
		return result;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 3, 10, 2, 1, 20 }, new int[] { 3, 10, 20 });
		_instance.runTest(new int[] { 3, 10, 2, 1, 20, 2, 3, 4, 5 }, new int[] { 1, 2, 3, 4, 5 });
		_instance.runTest(new int[] { 50, 3, 10, 7, 40, 80 }, new int[] { 3, 7, 40, 80 });
	}

	public void runTest(final int[] input, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
