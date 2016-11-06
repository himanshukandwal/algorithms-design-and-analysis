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

	// method : DP approach (importance of reverse diagonal, finding converging palindromes within limiting column wall)
	public static String _longestPalindromicSubstringDP(String input) {
		
		// dp build up (needs in-lining)
		int[][] dp = new int [input.length() + 1][input.length() + 1];
		
		int maxRow = 0, maxLen = 0;
		
		// standing on row value and trying to converge/curve-in (sub problem, previously curved if any) if there is a match.
		for (int row = 0; row < input.length(); row ++) 
			for (int col = 0; col <= row; col ++) 
				if (input.charAt(row) == input.charAt(col)) {
					dp [row + 1][col + 1] = (row == col) ? 1 : ((row - col == 1 || dp [row][col + 2] > 0) ? dp [row][col + 2] + 2 : 0);
					if (dp [row + 1][col + 1] > maxLen) {
						maxLen = dp [row + 1][col + 1];
						maxRow = row + 1;
					}
				}
		
		// string extraction
		char[] answerArr = new char [maxLen];
		int idx = 0;
		while (maxLen >= 0) {
			answerArr [answerArr.length - idx -1] = answerArr [idx] = input.charAt(maxRow - idx - 1);
			maxLen -= 2;
			idx ++;
		}
		
		return String.valueOf(answerArr);
	}
	
	// method : naive approach, how we generally visualized it.
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
    	_instance.runTest("abcba", "abcba");	
    	_instance.runTest("forgeeksskeegfor", "geeksskeeg");	
    	_instance.runTest("sabbaerd", "abba");	
    	_instance.runTest("sabaerd", "aba");
    	_instance.runTest("ayaxzfbjbkrxiri", "aya");
    	_instance.runTest("abcda", "a");	
    }

	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
