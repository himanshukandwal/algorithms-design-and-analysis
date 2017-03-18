package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 186. Reverse Words in a String II
 * 
 * Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.
 * 
 * The input string does not contain leading or trailing spaces and the words are always separated by a single space.
 * 
 * For example,
 * 		Given s = "the sky is blue",
 * 		return "blue is sky the".
 * 
 * Could you do it in-place without allocating extra space?
 * 
 * @author Hxkandwal
 */
public class ReverseWordsInAStringII extends AbstractCustomTestRunner {
	
	private static ReverseWordsInAStringII _instance = new ReverseWordsInAStringII();

	public String _reverseWords(char[] s) {
        reverse (s, 0, s.length);
        for (int fIdx = 0, idx = 0; idx <= s.length; idx ++) {
            if (idx == s.length || s [idx] == ' ') { 
                reverse (s, fIdx, idx); 
                fIdx = idx + 1; 
            }
        }
        return String.valueOf(s);
    }
    
    private void reverse (char[] s, int from, int to) {
        for (int idx = 0; idx < (to - from) >>> 1; idx ++) {
            char ch = s [from + idx];
            s [from + idx] = s [to - 1 - idx];
            s [to - 1 - idx] = ch;
        }
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("the sky is blue".toCharArray(), "blue is sky the");
	}

	public void runTest(final char[] s, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
