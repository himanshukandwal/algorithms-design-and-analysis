package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertEquals;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 438. Find All Anagrams in a String
 * 
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will 
 * not be larger than 20,100.
 * 
 * The order of output does not matter.
 * 
 * Example 1: 
 * 		Input: s: "cbaebabacd" p: "abc"
 * 		Output: [0, 6]
 * 		Explanation:
 * 			The substring with start index = 0 is "cba", which is an anagram of "abc".
 * 			The substring with start index = 6 is "bac", which is an anagram of "abc".
 * 
 * Example 2:
 * 		Input: s: "abab" p: "ab"
 * 		Output: [0, 1, 2]
 * 		Explanation:
 * 			The substring with start index = 0 is "ab", which is an anagram of "ab".
 * 			The substring with start index = 1 is "ba", which is an anagram of "ab".
 * 			The substring with start index = 2 is "ab", which is an anagram of "ab".
 * 
 * @author Hxkandwal
 *
 */
public class FindAllAnagramsInAString extends AbstractCustomTestRunner {
	
	private static FindAllAnagramsInAString _instance = new FindAllAnagramsInAString();
	
	private FindAllAnagramsInAString() {}
	
	public List<Integer> _findAnagrams(String s, String p) {
		List<Integer> result = null;
		
		int[] alphabets = new int [26];
		for (int idx = 0; idx < p.length(); idx ++) 
			alphabets [p.charAt(idx) - 'a'] ++;
		
		for (int idx = 0; idx < s.length() - p.length(); idx ++) {
			
		}		
		
		return result;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("cbaebabacd", "abc", new int[] { 0, 6 });
		_instance.runTest("abab", "ab", new int[] { 0, 1, 2 });
	}
	
	public void runTest(final String s, final String p, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });
		
		for (Object answer : answers) {
			assertEquals(expectedOutput.length, ((List<Integer>) answer).size());
			
			int idx = 0;
			for (Integer item : (List<Integer>) answer)
				assertEquals(expectedOutput [idx ++], item.intValue());
		}
		
		System.out.println("ok!");
	}		
}
