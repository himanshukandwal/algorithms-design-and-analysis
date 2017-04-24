package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Sorted Permutation Rank
 * 
 * Given a string, find the rank of the string amongst its permutations sorted lexicographically. 
 * Assume that no characters are repeated.
 * 
 * Example :
 * 		Input : 'acb'
 * 		Output : 2
 * 
 * 		The order permutations with letters 'a', 'c', and 'b' :
 * 		abc
 * 		acb
 * 		bac
 * 		bca
 * 		cab
 * 		cba
 * 
 * The answer might not fit in an integer, so return your answer % 1000003.
 * 
 * @author Hxkandwal
 */
public class SortedPermutationRank extends AbstractCustomTestRunner {
	
	private static SortedPermutationRank _instance = new SortedPermutationRank();
	
	public int _findRank(String a) {
		long [] factorials = new long [a.length() + 1];
		factorials [0] = factorials [1] = 1;
		for (int idx = 2; idx <= a.length(); idx ++) factorials [idx] = factorials [idx - 1] * idx;
		
		int [] charCount = new int [256];
        for (int idx = 0; idx < a.length(); idx ++) charCount [a.charAt(idx)] ++;
         
        long rank =  1;
        for (int idx = 0; idx < a.length(); idx ++) {
            // find number of characters smaller than S[i] still left. 
            int less = 0;
            for (int ch = 0; ch < a.charAt(idx); ch ++) less += charCount[ch];
            
            rank = (rank + (factorials [a.length() - idx - 1] * less)) % 1000003;
            
            // remove the current character from the set. 
            charCount[a.charAt(idx)] --;
        }
		return (int) rank;
	}

	public static int findSmallerInRight(String A, int low, int high) {
		int countRight = 0;
		for (int idx = low + 1; idx <= high; idx ++) if (A.charAt(idx) < A.charAt(low)) countRight ++;
		return countRight;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("ZCSFLVHXRYJQKWABGT", 318057);
		_instance.runTest("view", 15);
		_instance.runTest("acb", 2);
	}

	public void runTest(final String a, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
