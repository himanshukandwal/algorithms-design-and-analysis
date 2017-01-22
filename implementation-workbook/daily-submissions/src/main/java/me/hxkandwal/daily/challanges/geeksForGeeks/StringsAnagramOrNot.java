package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Check whether two strings are anagram of each other.
 * 
 * Write a function to check whether two given strings are anagram of each other or not. An anagram of a string 
 * is another string that contains same characters, only the order of characters can be different. 
 * 
 * For example, "abcd" and "dabc" are anagram of each other.
 * 
 * link: http://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
 * 
 * @author Hxkandwal
 *
 */
public class StringsAnagramOrNot extends AbstractCustomTestRunner {
	
	private static StringsAnagramOrNot _instance = new StringsAnagramOrNot();
	
	public StringsAnagramOrNot() {}
	
	public static boolean _areAnagram(String string1, String string2) {
		int[] alphabets = new int ['z' - 'A' + 1];
		
		for (int idx = 0; idx < string1.length(); idx ++) 
			alphabets ['z' - string1.charAt(idx)] ++;
		
		for (int idx = 0; idx < string2.length(); idx ++) 
			alphabets ['z' - string2.charAt(idx)] --;
		
		for (int idx = 0; idx < alphabets.length; idx ++) 
			if (alphabets [idx] != 0)
				return false;
		
		return true;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("abcd", "dabc", true);
	}
	
	public void runTest(final String input1, final String input2, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input1, input2 });
		
		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
