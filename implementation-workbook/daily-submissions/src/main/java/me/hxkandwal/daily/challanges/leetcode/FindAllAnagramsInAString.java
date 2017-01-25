package me.hxkandwal.daily.challanges.leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
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
		List<Integer> result = new ArrayList<>();
		
		int[] alphabets = new int [26];
		for (int idx = 0; idx < p.length(); idx ++) 
			alphabets [p.charAt(idx) - 'a'] ++;
		
		boolean found = false;
		for (int idx = 0; idx < s.length() - p.length() + 1; idx ++) {
			if (found) { 
				if (s.charAt(idx + p.length() - 1) == s.charAt(idx - 1))
					result.add(idx);
				else {
					found = false;
					if (alphabets [s.charAt(idx + p.length() - 1) - 'a'] == 0)
						idx += (p.length() - 1);
				}
			} else {
				int[] localAlphabets = new int [26];
				for (int innerIdx = 0; innerIdx < p.length(); innerIdx ++) {
					if (alphabets [s.charAt(idx + innerIdx) - 'a'] == 0)
						break;
					else
						localAlphabets [s.charAt(idx + innerIdx) - 'a'] ++;
				}
				
				if (isEqual(alphabets, localAlphabets)) 
					found = result.add(idx);
			}
		}		
		
		return result;
    }
	
	public List<Integer> _findAnagrams2(String s, String p) {
	    List<Integer> list = new ArrayList<>();
	    
	    if (s == null || s.length() == 0 || p == null || p.length() == 0) return list;
	    
	    int[] hash = new int[256]; //character hash
	    
	    //record each character in p to hash
	    for (char c : p.toCharArray()) hash[c]++;
	    
	    //two points, initialize count to p's length
	    int left = 0, right = 0, count = p.length();
	    
	    while (right < s.length()) {
	        //move right everytime, if the character exists in p's hash, decrease the count
	        //current hash value >= 1 means the character is existing in p
	        if (hash[s.charAt(right++)]-- >= 1) count--; 
	        
	        //when the count is down to 0, means we found the right anagram
	        //then add window's left to result list
	        if (count == 0) list.add(left);
	    
	        //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
	        //++ to reset the hash because we kicked out the left
	        //only increase the count if the character is in p
	        //the count >= 0 indicate it was original in the hash, cuz it won't go below 0
	        if (right - left == p.length() && hash[s.charAt(left++)]++ >= 0) count++;
	    }
	    
	    return list;
	}
	
	private static boolean isEqual(int[] pattern, int[] detected) {
		for (int idx = 0; idx < detected.length; idx ++) 
			if (pattern [idx] != detected [idx])
				return false;
		
		return true;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("cbaebabacd", "abc", new int[] { 0, 6 });
		_instance.runTest("abab", "ab", new int[] { 0, 1, 2 });
		_instance.runTest("abacbabc", "abc", new int[] { 1, 2, 3, 5 });
	}
	
	@SuppressWarnings("unchecked")
	public void runTest(final String s, final String p, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, p });
		
		for (Object answer : answers) {
			assertEquals(expectedOutput.length, ((List<Integer>) answer).size());
			
			int idx = 0;
			for (Integer item : (List<Integer>) answer)
				assertEquals(expectedOutput [idx ++], item.intValue());
		}
		
		System.out.println("ok!");
	}		
}
