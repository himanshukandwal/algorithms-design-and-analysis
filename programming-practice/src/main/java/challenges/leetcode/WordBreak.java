package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 139. Word Break
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated 
 * sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.
 * 
 * For example, 
 * 		given s = "leetcode", dict = ["leet", "code"].
 * 		Return true because "leetcode" can be segmented as "leet code".
 * 
 * @author Hxkandwal
 */
public class WordBreak extends AbstractCustomTestRunner {
	
	private static WordBreak _instance = new WordBreak();

	public boolean wordBreak(String s, List<String> wordDict) {
		boolean [] f = new boolean [s.length() + 1];
		f [0] = true;
		for (int idx = 0; idx < s.length(); idx ++) {
			if (f [idx])
				for (String word : wordDict)
					if (s.substring (idx).startsWith(word))
						f [idx + word.length()] = true;
		}
		return f [s.length()];
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("apple", Arrays.asList ("ap", "ple"), true);
		_instance.runTest("leetcode", Arrays.asList ("leet", "code"), true);		
	}

	public void runTest(final String s, final List<String> wordDict, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, wordDict });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
    
}
