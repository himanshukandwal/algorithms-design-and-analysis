package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * First Missing Integer
 * 
 * Given an unsorted integer array, find the first missing positive integer.
 * 
 * Example:
 * 		Given [1,2,0] return 3,
 * 
 * 		Given [3,4,-1,1] return 2,
 * 	
 * 		Given [-8, -7, -6] returns 1.
 * 
 * Your algorithm should run in O(n) time and use constant space.
 * 
 * @author Hxkandwal
 */
public class FirstMissingInteger extends AbstractCustomTestRunner {
	
	private static FirstMissingInteger _instance = new FirstMissingInteger();
	
	public int _firstMissingPositive(List<Integer> a) {
	    if (a.size () == 0) return 1;
	    for (int ai : a) {
	        while (ai > 0 && ai <= a.size() && ai != a.get (ai - 1)) {
	            int val = a.get (ai - 1);
	            a.set (ai - 1, ai);
	            ai = val;
	        }
	    }
	    
	    for (int idx = 0; idx < a.size (); idx ++) if (a.get (idx) != idx + 1) return idx + 1;
	    return a.size() + 1;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(3, 4, -1, 1), 2);
		_instance.runTest(Arrays.asList(-8, -7, -6), 1);
		_instance.runTest(Arrays.asList(0, 1, 2), 3);
		_instance.runTest(Arrays.asList(1), 2);
		_instance.runTest(Arrays.asList(1, 1, 1), 2);
	}

	public void runTest(final List<Integer> a, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
