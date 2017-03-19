package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 541. Reverse String II
 * 
 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string. 
 * If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then 
 * reverse the first k characters and left the other as original.
 * 
 * Example:
 * 		Input: s = "abcdefg", k = 2
 * 		Output: "bacdfeg"
 *  
 * @author Hxkandwal
 */
public class ReverseStringII extends AbstractCustomTestRunner {
	
	private static ReverseStringII _instance = new ReverseStringII();

	public String _reverseStr(String s, int k) {
		char[] chArr = s.toCharArray(); 
        int idx = 0;
        while (idx < s.length()) { reverse (chArr, idx, Math.min(s.length() - 1, idx + k - 1)); idx += 2*k; }
        return String.valueOf(chArr);
    }
    
    private void reverse (char[] chArr, int from, int to) {
        for (int idx = 0; idx <= (to - from) >>> 1; idx ++) {
            char ch = chArr [from + idx];
            chArr [from + idx] = chArr [to - idx];
            chArr [to - idx] = ch;
        }
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("abcdefg", 2, "bacdfeg");
		_instance.runTest("abcdefg", 8, "gfedcba");
	}
	
	public void runTest(final String s, final int k, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, k });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
