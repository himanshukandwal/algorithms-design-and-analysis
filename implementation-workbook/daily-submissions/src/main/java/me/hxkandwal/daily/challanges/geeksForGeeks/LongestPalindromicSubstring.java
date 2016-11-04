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
		String maxPalindrome = null;
		
		for (int idx = 0; idx < input.length(); idx ++) {
			StringBuilder localPalindromeOdd = new StringBuilder();
			circulate (input, idx, 0, false, localPalindromeOdd);
			
			StringBuilder localPalindromeEven = new StringBuilder();
			circulate (input, idx, 0, true, localPalindromeEven);
			
			if (maxPalindrome == null || maxPalindrome.length() < Math.max(localPalindromeEven.length(), localPalindromeOdd.length()))
				maxPalindrome = (localPalindromeEven.length() > localPalindromeOdd.length()) ? localPalindromeEven.toString() : localPalindromeOdd.toString();
		}
			
		return maxPalindrome;
	}
	
	private static void circulate (String input, int center, int radius, boolean even, StringBuilder collector) {
		if (((even && center + radius + 1 < input.length()) || (!even && center + radius < input.length())) && center - radius >= 0) {
			if (even) {
				if (input.charAt(center + radius + 1) == input.charAt(center - radius)) {
					collector.append(String.valueOf(input.charAt(center + radius + 1)));
					collector.insert(0, String.valueOf(input.charAt(center - radius)));
					
					circulate(input, center, radius + 1, even, collector);
				}
			} else {
				if (input.charAt(center + radius) == input.charAt(center - radius)) {
					collector.append(String.valueOf(input.charAt(center + radius)));
					if (center + radius != center - radius) 
						collector.insert(0, String.valueOf(input.charAt(center - radius)));
					
					circulate(input, center, radius + 1, even, collector);
				}
			}
		}
	}

	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest("forgeeksskeegfor", "geeksskeeg");	
    	_instance.runTest("sabbaerd", "abba");	
    	_instance.runTest("sabaerd", "aba");
    	_instance.runTest("ayaxzfbjbkrxiri", "aya");	
    }

	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
