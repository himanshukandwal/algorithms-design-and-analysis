package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 115. Distinct Subsequences
 * 
 * Given a string S and a string T, count the number of distinct subsequences of S which equals T.
 * 
 * A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) 
 * of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence
 * of "ABCDE" while "AEC" is not).
 * 
 * Here is an example: S = "rabbbit", T = "rabbit". Return 3.
 * 
 * @author Hxkandwal
 */
public class DistinctSubsequences extends AbstractCustomTestRunner {
	
	private static DistinctSubsequences _instance = new DistinctSubsequences();

	public int _numDistinct(String s, String t) {
		int [][] dp = new int [t.length () + 1][s.length () + 1];
        for (int row = 0; row < t.length (); row ++) 
            for (int col = 0; col < s.length (); col ++)
                dp [row + 1][col + 1] = dp [row + 1][col] + (t.charAt (row) == s.charAt (col) ? (row == 0 ? 1 : dp [row][col]) : 0);
        return dp [t.length ()][s.length ()];
    }

	// driver method
	public static void main(String[] args) throws FileNotFoundException {
		_instance.runTest("rabbbit", "rabbit", 3);
	}
	
	public void runTest(final String s, final String t, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
}
