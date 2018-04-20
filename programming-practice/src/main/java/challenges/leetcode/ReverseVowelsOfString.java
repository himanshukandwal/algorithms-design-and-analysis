package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

/**
 * 345. Reverse Vowels of a String
 * 
 * Write a function that takes a string as input and reverse only the vowels of
 * a string.
 * 
 * Example 1: Given s = "hello", return "holle". Example 2: Given s =
 * "leetcode", return "leotcede".
 * 
 * @author Hxkandwal
 *
 */
@SuppressWarnings("serial")
public class ReverseVowelsOfString extends AbstractCustomTestRunner {
	
	public static ReverseVowelsOfString _instance = new ReverseVowelsOfString();
	
	public String _reverseVowels(String s) {
		int l = 0, r = s.length() - 1;
		char [] ca = s.toCharArray();
		String v = "aeiouAEIOU";

		while (l < r) {
			if (!v.contains(String.valueOf(ca [l]))) l ++;
			else if (!v.contains(String.valueOf(ca [r]))) r --;
			else {
				char t = ca [l];
				ca [l] = ca [r];
				ca [r] = t;
				l ++; r --;
			}
		}
		return String.valueOf(ca);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("hello", "holle");
		_instance.runTest("", "");
		_instance.runTest("a", "a");
		_instance.runTest("b", "b");
		_instance.runTest("leetcode", "leotcede");
		_instance.runTest("aA", "Aa");
	}
	
	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });
		
		for (Object answer : answers) 
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
