package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * You are given an array of positive integers a. You may choose some integer X and update a several times, 
 * where to update means to perform the following operations:
 * 
 *    pick a contiguous subarray of length not greater than the given k;
 *    replace all elements in the picked subarray with the chosen X.
 *    What is the minimum number of updates required to make all the elements of the array the same?
 *    
 * Example:
 * 
 * 		For a = [1, 2, 2, 1, 2, 1, 2, 2, 2, 1, 1, 1] and k = 2, the output should be
 *    	arrayEqualization(a, k) = 4.
 *    
 *    	Here's how a will look like after each update:
 *    
 *     	[ 1, 2, 2, 1, 2, 1, 2, 2, 2, 1, 1, 1 ] ->
 *     	[ 1, 1, 1, 1, 2, 1, 2, 2, 2, 1, 1, 1 ] ->
 *     	[ 1, 1, 1, 1, 1, 1, 2, 2, 2, 1, 1, 1 ] ->
 *     	[ 1, 1, 1, 1, 1, 1, 1, 1, 2, 1, 1, 1 ] ->
 *     	[ 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 ]
 *    
 * @author Hxkandwal
 *
 */
public class ArrayEqualizationToOnes extends AbstractCustomTestRunner {
	
	private static ArrayEqualizationToOnes _instance = new ArrayEqualizationToOnes();
	
	public ArrayEqualizationToOnes() {}
	
	public static int _arrayEqualization(int[] array, int k) {
		int steps = 0;

		for (int idx = 0; idx < array.length; idx ++) {
			if (array [idx] != 1) {
				steps ++;
				for (int kIdx = 0; kIdx < k && idx + kIdx < array.length; kIdx ++)
					if (array [idx + kIdx] != 1)
						array [idx + kIdx] = 1;
			}
		}
		
		return steps;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 2, 2, 1, 2, 1, 2, 2, 2, 1, 1, 1 }, 2, 4);
		_instance.runTest(new int [] { 1, 2, 2, 1, 1, 1, 2, 2, 2, 1, 1, 1 }, 9, 1);
		_instance.runTest(new int [] { 5, 2, 3, 5, 2, 2, 3, 5, 1, 2, 5, 1, 2, 5, 3 }, 7, 3);
	}

	public void runTest(final int[] array, final int k, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { array, k });

		for (Object answer : answers)
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
