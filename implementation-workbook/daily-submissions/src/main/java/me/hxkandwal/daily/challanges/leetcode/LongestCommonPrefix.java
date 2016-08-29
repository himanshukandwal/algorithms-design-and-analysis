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
	
	public String _longestCommonPrefix(String[] strs) {
		String prefix = "";

		if (strs.length > 0) {
			int endIndex = 0;
			boolean isPresent = true;
			String incrementedPrefix = "";
			
			while (isPresent) {
				prefix = incrementedPrefix;
				endIndex ++;
				
				if (endIndex <= strs[0].length())
					incrementedPrefix = strs[0].substring(0, endIndex);
				else
					break;

				for (int idx = 0; idx < strs.length; idx++) {
					if (endIndex > strs[idx].length() || !strs[idx].substring(0, endIndex).equalsIgnoreCase(incrementedPrefix)) {
						isPresent = false;
						break;
					}
				}
			}
		}

		return prefix;
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
