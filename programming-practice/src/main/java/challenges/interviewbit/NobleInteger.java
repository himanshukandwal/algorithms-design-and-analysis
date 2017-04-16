package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Noble Integer
 * 
 * Given an integer array, find if an integer p exists in the array such that the number of 
 * integers greater than p in the array equals to p.
 * 
 * If such an integer is found return 1 else return -1.
 * 
 * @author Hxkandwal
 */
public class NobleInteger extends AbstractCustomTestRunner {
	
	private static NobleInteger _instance = new NobleInteger();

	public int _solve (List<Integer> A) {
        Collections.sort (A);
        for (int idx = 0; idx < A.size(); idx ++)
            if (A.get (idx) == A.size () - lastIndexOf (A, A.get (idx))) return 1;
        return -1;
    }
    
    private int lastIndexOf (List<Integer> A, int val) {
        int low = 0, high = A.size () - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (A.get (mid) <= val) low = mid + 1;
            else high = mid - 1;
        }
        return low;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(5, 6, 2), 1);
	}

	public void runTest(final List<Integer> A, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { A });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
