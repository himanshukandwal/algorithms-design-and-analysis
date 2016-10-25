package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Longest repeating and non-overlapping substring
 * 
 * Given a string str, find the longest repeating non-overlapping substring in it. In other words find 2 identical 
 * substrings of maximum length which do not overlap. If there exists more than one such substring return any of them.
 * 
 * Examples:
 * a) 		Input : str = "geeksforgeeks"
 * 			Output : geeks
 * 
 * b) 		Input : str = "aab"
 * 			Output : a
 * 
 * c) 		Input : str = "aabaabaaba"
 * 			Output : aaba
 * 
 * d) 		Input : str = "aaaaaaaaaaa"
 * 			Output : aaaaa
 * 
 * e) 		Input : str = "banana"
 * 			Output : an 
 * 					or na
 * 
 * @author Hxkandwal
 *
 */
public class LongestRepeatingNonOverlappingSubstring extends AbstractCustomTestRunner {
	
	private static LongestRepeatingNonOverlappingSubstring _instance = new LongestRepeatingNonOverlappingSubstring();
	
	private LongestRepeatingNonOverlappingSubstring() {}
	
	public static String _longestRepeatedSubstring(String input) {
		
		return null;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("geeksforgeeks", "geeks");
		_instance.runTest("aab", "a");
		_instance.runTest("aabaabaaba", "aaba");
		_instance.runTest("aaaaaaaaaaa", "aaaaa");
		_instance.runTest("banana", "an");
	}

	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
