package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Given a string, find the longest substring which is palindrome. 
 * 
 * For example, if the given string is “forgeeksskeegfor”, the output should be “geeksskeeg”.
 * 
 * link : http://www.geeksforgeeks.org/longest-palindrome-substring-set-1/
 * 
 * @author Hxkandwal
 *
 */
public class LongestPalindromicSubstring extends AbstractCustomTestRunner {
	
	private static LongestPalindromicSubstring _instance = new LongestPalindromicSubstring();
	
	private LongestPalindromicSubstring() {}
	
	public static String _longestPalindromicSubstring(String input) {
		
		return null;
	}
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest("forgeeksskeegfor", "geeksskeeg");	
    }

	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
