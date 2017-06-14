package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * Beautiful Pairs
 * 
 * Determine and print the maximum possible number of pairwise disjoint beautiful pairs.
 * 
 * A pair of indices (i, j) is beautiful if the ith element of array A is equal to the jth element of 
 * array B. In other words, pair (i, j) is beautiful if and only if Ai = Bj.
 * 
 * Change exactly 1 element in B so that the resulting number of pairwise disjoint beautiful pairs is maximal, 
 * and print this maximal number to stdout.
 * 
 * link : https://www.hackerrank.com/challenges/beautiful-pairs
 *  
 * @author Hxkandwal
 */
public class BeautifulPairs extends AbstractCustomTestRunner {
	
	private static BeautifulPairs _instance = new BeautifulPairs();
	
	// O (n) solution
	public int _beautifulPairBetter(int[] A, int[] B) {
		int n = A.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int idx = 0; idx < n; idx ++) map.put (A [idx], map.getOrDefault (A [idx], 0) + 1);
        for (int idx = 0; idx < n; idx ++) map.put (B [idx], map.getOrDefault (B [idx], 0) - 1);
        
        int nonsim = 0;
        for (int val : map.values()) if (val != 0) nonsim += Math.abs (val);
        nonsim >>= 1;
        return (nonsim == 0 ? n - 1 : n - nonsim + 1); 
	}
		
	// O (nlogn) solution
	public int _beautifulPair(int[] A, int[] B) {
		int n = A.length;

		Arrays.sort(A);
		Arrays.sort(B);

		int sim = 0, ai = 0, bi = 0;
		while (ai < n && bi < n) {
			if (A[ai] > B[bi]) bi ++;
			else if (A[ai] < B[bi]) ai ++;
			else { sim ++; ai ++; bi ++; }
		}

		return (sim == n ? n - 1 : sim + 1);
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1 }, new int[] { 1 }, 0);
		_instance.runTest(new int[] { 1, 2, 2 }, new int[] { 1, 2, 3 }, 3);
	}

	public void runTest(final int[] A, final int [] B, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { A, B });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
