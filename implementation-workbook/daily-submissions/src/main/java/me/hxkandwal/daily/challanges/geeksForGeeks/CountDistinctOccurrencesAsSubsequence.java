package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Count distinct occurrences as a subsequence
 * 
 * Given a two strings S and T, find count of distinct occurrences of T in S as a subsequence.
 * 
 * Examples:
 * 
 * a)		Input  : S = banana, T = ban
 * 			Output : 3
 * 			
 * 			T appears in S as below three subsequences.
 * 			[ban], [ba  n], [b   an]
 * 
 * b) 		Input  : S = geeksforgeeks, T = ge
 * 			Output : 6
 * 			
 * 			T appears in S as below three subsequences.
 * 			[ge], [     ge], [g e], [g    e] [g     e] and [     g e] 
 * 
 * link: http://www.geeksforgeeks.org/count-distinct-occurrences-as-a-subsequence/
 * 
 * @author Hxkandwal
 *
 */
public class CountDistinctOccurrencesAsSubsequence extends AbstractCustomTestRunner {
	
	private static CountDistinctOccurrencesAsSubsequence _instance = new CountDistinctOccurrencesAsSubsequence();
	
	private CountDistinctOccurrencesAsSubsequence() {}
	
	/**
	 * 	0 1 2 3 4 5 
	 * "b a n a n a"
	 * 
	 *   		[b = 0, a = 0, n = 0]
	 *  i = 0,	[b = 1, a = 0, n = 0]
	 *  i = 1,	[b = 1, a = 1, n = 0]   a is a's current value + all value of b  
	 *  i = 2,	[b = 1, a = 1, n = 1]   n is a's current value + all value of a (as n has to associated with all the paths in a)
	 *  i = 3,	[b = 1, a = 2, n = 1]
	 *  i = 4,	[b = 1, a = 2, n = 3]	every occurrence of first variable means increment and for rest means to merge the result for that index from the one previous index.  
	 *  i = 5,	[b = 1, a = 3, n = 3]
	 *  
	 *  answer is resulted from the last pattern variable (completed pattern count)
	 */
	public static int _findSubsequenceCount(String input, String pattern) {
		int[] pattern_count = new int [pattern.length()];
		
		for (int idx = 0; idx < input.length(); idx ++) {
			char ch = input.charAt(idx);
			
			if (ch == pattern.charAt(0))
				pattern_count[0] ++;
			else {
				for (int innerIdx = 1; innerIdx < pattern.length(); innerIdx ++) 
					if (ch == pattern.charAt(innerIdx)) {
						pattern_count [innerIdx] += pattern_count [innerIdx - 1];
						break;
					}
			}
		}
		
		return pattern_count [pattern_count.length - 1];
	}
	
	// driver method
	public static void main(String[] args) {
//		_instance.runTest("banana", "ban", 3);
//		_instance.runTest("geeksforgeeks", "ge", 6);
		_instance.runTest("wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxpklorellnmpapqfwkhopkmco", "wlrbbmqbhcdarzowkkyhiddqscdxrjmowfrxsjybldbefsarcbynecdyggxxp", 4);
	}

	public void runTest(final String input, final String pattern, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, pattern });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
