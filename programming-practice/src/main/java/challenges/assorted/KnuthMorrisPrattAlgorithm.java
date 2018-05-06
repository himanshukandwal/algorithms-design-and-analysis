package challenges.assorted;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Knuth-Morris-Pratt (KMP) Algorithm Implementation.
 * 
 * link: http://keithschwarz.com/interesting/code/?dir=knuth-morris-pratt
 * 
 * @author Hxkandwal
 */
public class KnuthMorrisPrattAlgorithm extends AbstractCustomTestRunner {

	private static KnuthMorrisPrattAlgorithm _instance = new KnuthMorrisPrattAlgorithm();

	public int _search (String pattern, String line) {
		int ans = 0;
		int[] pi = new int [pattern.length()];

		for (int idx = 1, j = 0; idx < pattern.length(); idx ++) {
			while (j > 0 && pattern.charAt(idx) != pattern.charAt(j)) j = pi [j - 1];
			if (pattern.charAt(idx) == pattern.charAt(j)) pi [idx] = ++ j;
		}

		for (int idx = 0, j = 0; idx < line.length(); idx ++) {
			while (j > 0 && line.charAt(idx) != pattern.charAt(j)) j = pi [j - 1];
			if (line.charAt(idx) == pattern.charAt(j)) {
				j ++;
				if (j == pattern.length()) {
					ans ++;
					j = pi [j - 1];
				}
			}
		}
		
		return ans;
	}	

	// driver method
	public static void main(String[] args) {
		_instance.runTest("abc", "asabc", 1);
		_instance.runTest("abc", "abcba", 1);
		_instance.runTest("aba", "ababa", 2);
		_instance.runTest("abc", "dabasdasabc", 1);
		_instance.runTest("abc", "jdsalkjdlakjl;dkja;k", 0);
		_instance.runTest("abc", "dabcabc", 2);
		_instance.runTest("aaba", "aabaacaadaabaaba", 3);
		_instance.runTest("abcdabcy", "abcxabcdabxabcdabcdabcy", 1);
		_instance.runTest("aabaabaaa", "a", 0);
	}

	public void runTest(final String pattern, final String line, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { pattern, line });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
