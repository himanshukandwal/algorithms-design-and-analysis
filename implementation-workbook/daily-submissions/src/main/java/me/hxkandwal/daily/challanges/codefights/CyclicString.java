package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Cyclic String
 * 
 * You're given a substring s of some cyclic string. What's the length of the smallest possible string that can be 
 * concatenated to itself many times to obtain this cyclic string?
 * 
 * Example:
 * 		For s = "cabca", the output should be cyclicString(s) = 3.
 * 		
 * 		"cabca" is a substring of a cycle string "abcabcabcabc..." that can be obtained by concatenating "abc" to itself. 
 * 		Thus, the answer is 3.
 * 
 * link: https://codefights.com/tournaments/6unnTXbS3WCj4Zt5B/C
 * 
 * @author Hxkandwal
 *
 */
public class CyclicString extends AbstractCustomTestRunner {
	
	private static CyclicString _instance = new CyclicString();
	
	private CyclicString() {}
	
	// using KMP pattern building algorithm (https://www.youtube.com/watch?v=GTJr8OvyEVQ)
	public static int _cyclicString (String s) {
		int [] prefix = new int [s.length()];
		
		for (int i = 1, j = 0; i < s.length(); i ++) {
			if (s.charAt(j) != s.charAt(i))
				while (j > 0 && s.charAt(j = prefix [j - 1]) != s.charAt(i));
			
			if (s.charAt(j) == s.charAt(i))
				prefix [i] = ++ j;
		}
		
		int length = 0;
		for (int idx = prefix.length - 1; idx >= 0; idx --)
			if (prefix [idx] != 0) length ++;
			else break;
			
		return prefix.length - length;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("cabca", 3);
		_instance.runTest("abab", 2);
		_instance.runTest("aaa", 1);
		_instance.runTest("abcabc", 3);
	}

	public void runTest(final String s, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
