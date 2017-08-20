package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

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

	public String longestCommonPrefix(String[] strs) {
		if (strs.length == 0) return "";
		StringBuilder ans = new StringBuilder();
		for (int idx = 0; idx < strs [0].length(); idx ++) {
			for (String str : strs) if (idx >= str.length() || str.charAt (idx) != strs [0].charAt (idx)) return ans.toString();
			ans.append (strs [0].charAt (idx));
		}
		return ans.toString();
	}
	
	public String _longestCommonPrefixBetter (String[] strs) {
        if (strs.length == 0) return "";
        StringBuilder ans = new StringBuilder (strs [0]);
        for (String str : strs) while (!str.startsWith (ans.toString())) ans.deleteCharAt (ans.length () - 1);
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
