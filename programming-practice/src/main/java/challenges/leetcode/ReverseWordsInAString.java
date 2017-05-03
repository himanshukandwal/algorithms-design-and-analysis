package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 151. Reverse Words in a String
 * 
 * Given an input string, reverse the string word by word.
 * 
 * For example,	Given s = "the sky is blue", return "blue is sky the".
 * 
 * @author Hxkandwal
 */
public class ReverseWordsInAString extends AbstractCustomTestRunner {
	
	private static ReverseWordsInAString _instance = new ReverseWordsInAString();

	public String _reverseWords(String s) {
		String [] words = s.split (" ");
		StringBuilder sb = new StringBuilder ();
		int end = words.length - 1;
		for (int i = 0; i <= end; i ++) {
			if (!words[i].isEmpty()) {
				sb.insert (0, words [i]);
				if (i < end) sb.insert (0, " ");
			}
		}
		return sb.toString();
	}
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("    ", "");
		_instance.runTest("the sky is blue", "blue is sky the");
	}

	public void runTest(final String s, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    
    
}
