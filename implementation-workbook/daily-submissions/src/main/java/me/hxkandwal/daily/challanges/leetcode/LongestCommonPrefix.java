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
		StringBuilder prefix = new StringBuilder();
		
		if (strs.length > 0) {
			int mIdx = 0, idx = strs.length;
			
			// check if we have looped completely or not.
			while ((idx == strs.length) && mIdx < strs [0].length()) {
				char ch = strs [0].charAt(mIdx);
				
				for (idx = 1; idx < strs.length && (mIdx < strs [idx].length()); idx ++)
					if (strs [idx].charAt(mIdx) != ch) break;
				
				if (idx == strs.length) prefix.append(strs [0].charAt(mIdx ++));
			}
		}
		
		return prefix.toString();
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
