package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 567. Permutation in String
 * 
 * Given two strings s1 and s2, write a function to return true if s2 contains the permutation of s1. In other words, one of 
 * the first string's permutations is the substring of the second string.
 * 
 * Example 1:
 * 		Input : s1 = "ab" s2 = "eidbaooo"
 * 		Output: True
 * 		Explanation	: s2 contains one permutation of s1 ("ba").
 * 
 * Example 2:
 * 		Input :	s1= "ab" s2 = "eidboaoo"
 * 		Output: False
 * 
 * Note:
 * 	-	The input strings only contain lower case letters.
 * 	-	The length of both given strings is in range [1, 10,000].
 * 
 * @author Hxkandwal
 */
public class PermutationInString extends AbstractCustomTestRunner {
	
	private static PermutationInString _instance = new PermutationInString();

	public boolean _checkInclusion(String s1, String s2) {
        int [] map = new int [256];
        for (char ch : s1.toCharArray()) map [ch] ++;
        for (int idx = 0, start = 0; idx < s2.length(); idx ++) {
            char ch = s2.charAt (idx);
            if (-- map [ch] < 0) while (map [ch] != 0) map [s2.charAt (start ++)] ++;
            else if (idx - start + 1 == s1.length()) return true;
        }
        return false;
    }	

	// driver method
	public static void main(String[] args) {
		_instance.runTest("adc", "dcda", true);
		_instance.runTest("hello", "ooolleoooleh", false);
	}
	
	public void runTest(final String s1, String s2, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s1, s2 });
		
		for (Object answer : answers) 
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
		
}
