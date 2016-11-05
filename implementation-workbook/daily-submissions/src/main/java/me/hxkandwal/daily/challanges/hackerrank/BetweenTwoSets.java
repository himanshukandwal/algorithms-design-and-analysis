package me.hxkandwal.daily.challanges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Between Two Sets
 * 
 * Consider two sets of positive integers, A = { a0, a1, ..., an } and B = {b0, b1, ... , bn }. 
 * We say that a positive integer, x , is between sets A and B if the following conditions are satisfied:
 * 
 * a)	All elements in A are factors of x.
 * b) 	x is a factor of all elements in B.
 * 
 * Example:
 * 		A = { 2, 4 }
 * 		B = { 16, 32, 96 }
 * 		
 * 		Answer : 3
 * 
 * 		Explanation: The integers that are between A = { 2, 4 } and B = { 16, 32, 96 } are 4, 8, and 16.
 * 
 * link : https://www.hackerrank.com/contests/w25/challenges/between-two-sets
 * 
 * @author Hxkandwal
 *
 */
public class BetweenTwoSets extends AbstractCustomTestRunner {
	
	private static BetweenTwoSets _instance = new BetweenTwoSets();
	
	private BetweenTwoSets() {}
	
	// euclid algorithm
	private static int gcd (int a, int b) {
		if (b == 0) 
			return a;
		else
			return gcd(b, a % b);
	}
	
	private static int arrayGcd (int[] array) {
		Integer gcd = null;
		if (array.length >= 2) {
			for (int idx = 0; idx < array.length; idx ++) 
				if (gcd == null)
					gcd = gcd (array [idx], array [idx + 1]);
				else
					gcd = gcd (gcd, array [idx]);			
		} else
			gcd = (array.length == 0) ? 0 : array [0];
		
		return gcd;
	}
	
	/* logic : 	build gcd engine, operate on B, resulting answer save as bgcd.
	 *			find lcm (need gcd engine again) of A, and then build up to bgcd. 
	 */
	public static int _getCountBetweenTwoSets(int[] A, int[] B) {
		int bgcd = arrayGcd (B);
		
		List<Integer> factors = new ArrayList<>();
		for (int idx = 1; idx <= bgcd; idx ++)
			if (bgcd % idx == 0) 
				factors.add(idx);
		
		int count = 0;
		for (int factor : factors) {
			boolean isDivisible = true;
			for (int idx = 0; idx < A.length; idx++) 
				isDivisible = isDivisible && (factor % A [idx] == 0);
			count += (isDivisible) ? 1 : 0;
		}
		
		return count;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 2, 4 }, new int[] { 16, 32, 96 }, 3);
	}

	public void runTest(final int[] A, final int[] B, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { A, B });

		for (Object answer : answers)
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
