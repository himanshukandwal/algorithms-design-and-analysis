package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Check if two strings are k-anagrams or not.
 * 
 * Given two strings of lowercase alphabets and a value k, the task is to find if two strings are K-anagrams of each other or not. 
 * Two strings are called k-anagrams if following two conditions are true.
 *  
 * a) Both have same number of characters.
 * b) Two strings can become anagram by changing at most k characters in a string.
 * 
 * Examples:
 * 
 * a) 	Input:  str1 = "anagram" , str2 = "grammar" , k = 3
 * 		Output:  Yes
 * 
 * 		Explanation: We can update maximum 3 values and it can be done in changing only 'r' to 'n'
 *  	and 'm' to 'a' in str2.
 *  
 *  b) 	Input:  str1 = "geeks", str2 = "eggkf", k = 1
 *  	Output:  No
 *  	
 *  	Explanation: We can update or modify only 1 value but there is a need of modifying 2 characters.
 *   	i.e. g and f in str 2.
 *   
 * link: http://www.geeksforgeeks.org/check-two-strings-k-anagrams-not/
 * 
 * @author Hxkandwal
 * 
 */
public class StringsKAnagramOrNot extends AbstractCustomTestRunner {

	private static StringsKAnagramOrNot _instance = new StringsKAnagramOrNot();
	
	private StringsKAnagramOrNot() {}
	
	public static boolean _areAnagram(String string1, String string2, int k) {
		int[] alphabets1 = new int ['z' - 'A' + 1];
		int[] alphabets2 = new int ['z' - 'A' + 1];
		
		for (int idx = 0; idx < string1.length(); idx ++) 
			alphabets1 ['z' - string1.charAt(idx)] ++;
		
		for (int idx = 0; idx < string2.length(); idx ++) 
			alphabets2 ['z' - string2.charAt(idx)] ++;
		
		int differences = 0;
		for (int idx = 0; idx < alphabets1.length && k >= 0; idx ++)
			differences += Math.abs(alphabets1 [idx] - alphabets2 [idx]);
		
		return (differences / 2 > k) ? false : true;
	}	
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("abcd", "dabc", 1, true);
		_instance.runTest("abcd", "dabc", 0, true);
		_instance.runTest("anagram", "grammar", 3, true);
		_instance.runTest("geeks", "eggkf", 1, false);
	}
	
	public void runTest(final String input1, final String input2, final int k, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input1, input2, k });
		
		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
}
