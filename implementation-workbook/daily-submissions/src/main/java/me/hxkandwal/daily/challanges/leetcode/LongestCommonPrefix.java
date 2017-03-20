package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 14. Longest Common Prefix
 * 
 * Write a function to find the longest common prefix string amongst an array of strings.
 * 
 * @author Hxkandwal
 *
 */
public class LongestCommonPrefix extends AbstractCustomTestRunner {
	
	private static LongestCommonPrefix _instance = new LongestCommonPrefix();

	public String _longestCommonPrefixFast(String[] strs) {
		StringBuilder ans = new StringBuilder();
        int minLen = Integer.MAX_VALUE;
        for (String str : strs) minLen = Math.min (minLen, str.length());
        for (int idx = 0; idx < minLen; idx ++) {
            Character ch = null;
            for (String str : strs) {
                if (ch == null) ch = str.charAt (idx);
                else if (ch != str.charAt (idx)) { ch = null; break; }
            }
            if (ch == null) break;
            else ans.append (ch);
        }
        return ans.toString();
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String[] {}, "");
		_instance.runTest(new String[] { "", "" }, "");
		_instance.runTest(new String[] { "", "" }, "");
		_instance.runTest(new String[] { "c", "c" }, "c");
		_instance.runTest(new String[] { "c", "cd" }, "c");
		_instance.runTest(new String[] { "cd" }, "cd");
		_instance.runTest(new String[] { "a", "b" }, "");
	}

	public void runTest(final String[] strs, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { strs });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
