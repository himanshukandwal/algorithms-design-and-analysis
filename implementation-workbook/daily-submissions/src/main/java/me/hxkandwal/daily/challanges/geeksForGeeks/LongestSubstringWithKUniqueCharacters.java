package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Longest substring with k unique characters in a given string.
 * 
 * Given a string you need to print longest possible substring that has exactly M unique characters. If there are more than one substring of longest possible length, 
 * then print any one of them.
 * 
 * Examples:
 * 	1. "aabbcc", k = 1
 * 		Max substring can be any one from {"aa" , "bb" , "cc"}.
 * 
 *  2. "aabbcc", k = 2
 *  	Max substring can be any one from {"aabb" , "bbcc"}.
 *  
 *  3. "aabbcc", k = 3
 *  	There are substrings with exactly 3 unique characters
 *  	{"aabbcc" , "abbcc" , "aabbc" , "abbc" }
 *  	
 *  	Max is "aabbcc" with length 6.
 *  
 *  4. "aaabbb", k = 3
 *  	There are only two unique characters, thus show error message. 
 * 
 * link: http://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
 * 
 * @author Hxkandwal
 */
public class LongestSubstringWithKUniqueCharacters extends AbstractCustomTestRunner {
	
	private static LongestSubstringWithKUniqueCharacters _instance = new LongestSubstringWithKUniqueCharacters();
	
	private LongestSubstringWithKUniqueCharacters() {}
	
	public static int _longestSubstring(String s, int k) {
		int start = 0, maxlength = 0;
		
		Map<Character, Integer> locations = new HashMap<>();
		
		for (int idx = 0; idx < s.length(); idx ++) {
			char ch = s.charAt(idx);
			
			if (locations.size() >= k && !locations.containsKey(ch)) {
				char sch = s.charAt(start);
				start = locations.remove(sch) + 1;
			}
			
			locations.put(ch, idx);
			maxlength = Math.max(maxlength, idx - start + 1);
		} 
		
		return (locations.size() == k ? maxlength : 0);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("aabbcc", 1, 2);
		_instance.runTest("aabbcc", 2, 4);
		_instance.runTest("aabbcc", 3, 6);
		_instance.runTest("aaabbb", 3, 0);
	}

	public void runTest(final String input, final int k, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, k });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}		

}
