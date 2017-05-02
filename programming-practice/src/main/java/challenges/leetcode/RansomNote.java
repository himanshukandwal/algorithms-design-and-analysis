package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 383. Ransom Note
 * 
 * Given an arbitrary ransom note string and another string containing letters from all the magazines, 
 * write a function that will return true if the ransom note can be constructed from the magazines ; 
 * otherwise, it will return false.
 * 
 * Example :
 * 	a) canConstruct("a", "b") -> false
 *	b) canConstruct("aa", "ab") -> false
 * 	c) canConstruct("aa", "aab") -> true
 * 
 * @author Hxkandwal
 *
 */
public class RansomNote extends AbstractCustomTestRunner {

	private static RansomNote _instance = new RansomNote();

    public boolean _canConstruct(String ransomNote, String magazine) {
    	int [] hash = new int [256];
        for (char ch : ransomNote.toCharArray()) hash [ch] ++;
        for (char ch : magazine.toCharArray()) if (hash [ch] > 0) hash [ch] --;
        for (int h : hash) if (h > 0) return false;
        return true;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("", "", true);
		_instance.runTest("", "abc", true);
		_instance.runTest("a", "b", false);
		_instance.runTest("aa", "ab", false);
		_instance.runTest("aa", "aab", true);
	}
	
	public void runTest(final String ransomNote, final String magazine, final Boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { ransomNote, magazine });
		
		for (Object answer : answers) 
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
    
}