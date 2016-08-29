package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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

	/* method 1 : using dynamic magazine creation, using a helper function which creates a new string by removing 
				  the detected position index character. */
    public boolean _canConstruct(String ransomNote, String magazine) {
    	if (magazine != null && !magazine.isEmpty() && (ransomNote == null || ransomNote.isEmpty())) 
    		return true;
    	
    	if (ransomNote != null && !ransomNote.isEmpty() && (magazine == null || magazine.isEmpty())) 
    		return false;
    	
    	if ((ransomNote == null || ransomNote.isEmpty()) && (magazine == null || magazine.isEmpty())) 
    		return true;
    	
    	int idx = 0;
    	
    	for (; idx < magazine.length(); idx ++)
    		if (magazine.charAt(idx) == ransomNote.charAt(0))
    			break;
    	
    	return (idx == magazine.length()) ? false : _canConstruct(ransomNote.substring(1), filtredString(magazine, idx));
    }
    
    private String filtredString (String string, int pos) {
    	char[] chArr = new char [string.length() - 1];
    	
    	int otherIndex = 0;
    	for (int idx = 0; idx < string.length(); idx ++) {
    		if (idx != pos) 
    			chArr [otherIndex ++] = string.charAt(idx);
    	}
    	
    	return String.valueOf(chArr);
    }
    
	/* method 2 : using a simple and single alphabet character array. */
    public boolean _canConstructUsingNativeComponents(String ransomNote, String magazine) {
    	if ((ransomNote == null || ransomNote.isEmpty()) && (magazine == null || magazine.isEmpty())) 
    		return true;
    	
        int[] cnt = new int[26];
        for (int i = 0; i < magazine.length(); i++) cnt[magazine.charAt(i) - 97]++;
		for (int i = 0; i < ransomNote.length(); i++) if (--cnt[ransomNote.charAt(i) - 97] < 0) return false;
		return true;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(null, null, true);
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