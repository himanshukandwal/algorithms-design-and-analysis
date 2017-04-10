package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 242. Valid Anagram
 * 
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * 
 * For example,
 * 		s = "anagram", t = "nagaram", return true.
 * 		s = "rat", t = "car", return false.
 * 
 * Note:
 * 		You may assume the string contains only lowercase alphabets.
 * 
 * Follow up:
 * 		What if the inputs contain unicode characters? How would you adapt your solution to such case?
 *  
 * @author Hxkandwal
 */
public class ValidAnagram extends AbstractCustomTestRunner {
	
	private static ValidAnagram _instance = new ValidAnagram();
	
	private ValidAnagram() {}
	
    public boolean _isAnagram(String s, String t) {
    	if (s.length() != t.length()) return false;
        int [] hash = new int [256]; 
        
        for (char ch : s.toCharArray()) ++ hash [ch];
        for (char ch : t.toCharArray()) -- hash [ch];
        for (int val : hash) if (val > 0) return false;
        return true;
    }	
    
    // driver method
	public static void main(String[] args) {
		_instance.runTest("cat", "tac", true);
		_instance.runTest("cat", "bac", false);
	}

	public void runTest(final String s, final String t, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
