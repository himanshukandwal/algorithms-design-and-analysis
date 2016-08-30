package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 58. Length of Last Word
 * 
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.
 * 
 * If the last word does not exist, return 0.
 * 
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * 
 * For example,
 * 		Given s = "Hello World", return 5.
 * 
 * @author Hxkandwal
 *
 */
public class LengthOfLastWord extends AbstractCustomTestRunner {

	private static LengthOfLastWord _instance = new LengthOfLastWord();
	
	private LengthOfLastWord() {}
	
    public int _lengthOfLastWord(String s) {
        s = s.trim();
        int followingIdx = -1;
        
        for (int idx = 0; idx < s.length(); idx ++) 
            if (idx > 0 && s.charAt(idx -1) == ' ')
                followingIdx = idx;
                
        return (followingIdx == -1) ? (s.length() > 0 ? s.length() : 0) : (s.length() - followingIdx) ; 
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("", 0);
		_instance.runTest("   ", 0);
		_instance.runTest("Hello World", 5);
		_instance.runTest("a", 1);
		_instance.runTest("a ", 1);
	}
	
	public void runTest(final String s, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
