package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Maximum Absolute Difference
 * 
 * You are given an array of N integers, A1, A2 ,…, AN. Return maximum value of f(i, j) for all 1 ≤ i, j ≤ N.
 * f(i, j) is defined as |A[i] - A[j]| + |i - j|, where |x| denotes absolute value of x.
 * 
 * For example,
 * 		A = [1, 3, -1]
 * 		
 * 		f(1, 1) = f(2, 2) = f(3, 3) = 0
 * 		f(1, 2) = f(2, 1) = |1 - 3| + |1 - 2| = 3
 * 		f(1, 3) = f(3, 1) = |1 - (-1)| + |1 - 3| = 4
 * 		f(2, 3) = f(3, 2) = |3 - (-1)| + |2 - 3| = 5
 * 
 * So, we return 5.
 * 
 * link: https://www.interviewbit.com/problems/maximum-absolute-difference/
 * 
 * @author Hxkandwal
 */
public class MaximumAbsoluteDifference extends AbstractCustomTestRunner {
	
	private static MaximumAbsoluteDifference _instance = new MaximumAbsoluteDifference();

	public int _maxArr(List<Integer> A) {
		int minIdx = A.get(0) > A.get(1) ? 1 : 0;
		int maxIdx = (minIdx == 0) ? 1 : 0;
			
	    int max = Math.max (Integer.MIN_VALUE, A.get (maxIdx) - A.get (minIdx) + Math.abs (maxIdx - minIdx))    ;
	    
	    for (int idx = 2; idx < A.size(); idx ++) {
	        max = Math.max (max, Math.abs (A.get (idx) - A.get (minIdx)) + Math.abs (idx - minIdx));
	        max = Math.max (max, Math.abs (A.get (idx) - A.get (maxIdx)) + Math.abs (idx - maxIdx));
	        if (A.get (maxIdx) <= A.get (idx) && (A.get (idx) - A.get (maxIdx)) > (idx - maxIdx)) maxIdx = idx;
	        if (A.get (minIdx) >= A.get (idx) && (A.get (minIdx) - A.get (idx)) > (idx - minIdx)) minIdx = idx;
	    }
	        
	    return max;
	}
		
	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(55, -8, 43, 52, 8, 59, -91, -79, -18, -94), 158);
	}
	
	public void runTest(final List<Integer> A, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { A });
	
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
	
		System.out.println("ok!");
	}
}
