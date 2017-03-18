package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 125. Valid Palindrome
 * 
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * 
 * For example,
 * 		"A man, a plan, a canal: Panama" is a palindrome.
 * 		"race a car" is not a palindrome.
 * 
 * Note:
 * 		Have you consider that the string might be empty? This is a good question to ask during an interview.
 * 		For the purpose of this problem, we define empty string as valid palindrome.
 * 
 * @author Hxkandwal
 */
public class ValidPalindrome extends AbstractCustomTestRunner {
	
	private static ValidPalindrome _instance = new ValidPalindrome();

	public boolean _isPalindrome(String s) {
        s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        for (int idx = 0; idx < s.length()/2; idx ++)
            if (s.charAt(idx) != s.charAt(s.length() - 1 - idx)) return false;
        return true;        
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("A man, a plan, a canal: Panama", true);
		_instance.runTest("race a car", false);
		_instance.runTest("0P", false);
	}

	public void runTest(final String s, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
