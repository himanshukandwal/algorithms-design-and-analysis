package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

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
		pi [0] = 0;
		
		for (int idx = 1, j = 0; idx < pattern.length(); idx ++) {
			if (pattern.charAt(idx) == pattern.charAt(j)) pi [idx] = ++ j;
			else {
				while (j > 0 && pattern.charAt(idx) != pattern.charAt(j)) j = pi [j - 1];
				pi [idx] = (j == 0) ? 0 : ++ j;
			}
		}
		
		int piIdx = 0;
		for (int idx = 0; idx < line.length(); idx ++) {
			char ch = line.charAt(idx);
			
			while (piIdx >= 0) {
				if (ch == pattern.charAt(piIdx)) {
					if (piIdx + 1 == pi.length) { 
						ans ++; 
						piIdx = pi [piIdx];     // <<<<<<<<<<<<< very important point.
					} else piIdx ++;
					
					break;
				} else { 
					if (piIdx - 1 >= 0) piIdx = pi [piIdx - 1];
					else break;
				}
			}
		}
		
		return ans;
	}	

	// driver method
	public static void main(String[] args) {
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
