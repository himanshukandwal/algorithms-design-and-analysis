package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Longest Repeated Substring
 * 
 * Given a text string, find Longest Repeated Substring in the text. If there are more than one 
 * Longest Repeated Substrings, get any one of them.
 * 
 * Example: 
 * a)	Longest Repeated Substring in GEEKSFORGEEKS is: GEEKS
 * b) 	Longest Repeated Substring in AAAAAAAAAA is: AAAAAAAAA
 * c) 	Longest Repeated Substring in ABCDEFG is: No repeated substring
 * d)	Longest Repeated Substring in ABABABA is: ABABA
 * e)	Longest Repeated Substring in ATCGATCGA is: ATCGA
 * f)	Longest Repeated Substring in banana is: ana
 * g) 	Longest Repeated Substring in abcpqrabpqpq is: ab (pq is another LRS here)		
 * 
 * @author Hxkandwal
 *
 */
public class LongestRepeatedSubstring extends AbstractCustomTestRunner {
	
	private static LongestRepeatedSubstring _instance = new LongestRepeatedSubstring();
	
	private LongestRepeatedSubstring() {}
	
	/* this is just an simpler case for LongestRepeatingNonOverlappingSubstring, as here we dont care about the parent 
	 * placeholder conditions.
	 * 
	 * There is another solution possible from suffix trees.
	 */
	public static String longestRepeatedSubstring(String input) {
		int[][] dp = new int [input.length()][input.length()];
		int position_index = -1, max_length = 0;
		
		for (int i = 0; i < input.length(); i ++) {
			for (int j = i + 1; j < input.length(); j++) {
				if (input.charAt(i) == input.charAt(j)) { 
					dp [i][j] = (i - 1 >= 0) ? dp [i - 1][j - 1] + 1 : 1;
					
					if (max_length < dp [i][j]) {
						max_length = dp [i][j];
						position_index = j;
					}
				}
			}
		}
		
		return input.substring(position_index - max_length + 1, position_index + 1);
	}
	
	// using simple suffix array (simple version, but still store full substrings)
	public static String _longestRepeatedSubstringSimple(String input) {
		String[] suffixArray = new String [input.length()];
		for (int idx = 0; idx < input.length (); idx ++)
			suffixArray [idx] = input.substring(idx);
		
		Arrays.sort(suffixArray);
		
		int max_length = 0; String ans = null;
		
		for (int idx = 0; idx < suffixArray.length - 1; idx ++) {
			int length = 0;
			while (length < suffixArray [idx].length())
				if (suffixArray [idx].charAt(length) == suffixArray [idx + 1].charAt(length)) length ++;
				else break;
			
			if (max_length < length) {
				max_length = length;
				ans = suffixArray [idx].substring(0, length);
			}
		}
		
		return ans == null ? "" : ans;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("GEEKSFORGEEKS", "GEEKS");
		_instance.runTest("AAAAAAAAAA", "AAAAAAAAA");
		_instance.runTest("ABCDEFG", "");
		_instance.runTest("ABABABA", "ABABA");
		_instance.runTest("ATCGATCGA", "ATCGA");
		_instance.runTest("banana", "ana");
		_instance.runTest("abcpqrabpqpq", "ab");
	}

	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
