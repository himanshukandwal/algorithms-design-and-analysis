package me.hxkandwal.daily.challanges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Fair Cut
 * 
 * Li and Lu have n integers, a1, a2, ... an, that they want to divide fairly between the two of them. 
 * They decide that if Li gets integers with indices I = { i1, i2, ..., ik } (which implies that Lu gets 
 * integers with indices ), then the measure of unfairness of this division is:
 * 
 * 		f (I) = Summation(i) Summation(j) |ai - aj|  
 * 
 * Find the minimum measure of unfairness that can be obtained with some division of the set of integers where 
 * Li gets exactly k integers.
 * 
 * Example:
 * a.		a: { 4, 3, 1, 2 }
 * 			k:	2
 * 			Answer : 6
 * 
 * 			One possible solution for this input is I = { 2, 4 }; J = { 1, 3 }
 * 				|a2 - a1| + |a3 - a2| + |a4 - a1| + |a4 - a3|
 * 			= 	1 + 2 + 2 + 1 
 * 			= 	6 
 * 
 * b.		a: { 3, 3, 3, 1 }
 * 			k:	1
 * 			Answer : 2
 *
 * 			One possible solution for this input is I = { 1 }; J = { 2, 3, 4 }
 * 
 * link : https://www.hackerrank.com/challenges/fair-cut
 * 
 * @author Hxkandwal
 *
 */
public class FairCut extends AbstractCustomTestRunner {
	
	private static FairCut _instance = new FairCut();
	
	private FairCut() {}

	public static int _getFairCut(int[] a, int k) {
		int[][] diffMatrix = new int [a.length][a.length];
		
		List<Integer> previousKSolutions = new ArrayList<Integer>();
		
		for (int row = 0; row < diffMatrix.length; row++) {
			int rowSum = 0;
			for (int col = 0; col < diffMatrix.length; col++) 
				rowSum += diffMatrix [row][col] = Math.abs(a [row] - a [col]);
			
			previousKSolutions.add(rowSum);
		}
		
		int minValue = Integer.MAX_VALUE;
		
		for (int kIdx = 1; kIdx <= k; kIdx ++) {
			
		}
				
		return 0;
	}

    // driver method
    public static void main(String[] args) {
        _instance.runTest(0, 1, 5, "5");
    }

    public void runTest(final int t1, final int t2, final int n, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { t1, t2, n });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
