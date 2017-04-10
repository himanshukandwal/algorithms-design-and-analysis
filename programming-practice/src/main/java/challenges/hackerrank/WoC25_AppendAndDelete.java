package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * You have a string, s, of lowercase English alphabetic letters. You can perform two types of operations on s :
 * 
 * 	a. Append a lowercase English alphabetic letter to the end of the string.
 * 	b. Delete the last character in the string. Performing this operation on an empty string results in an empty string.
 * 
 * Given an integer, k, and two strings, s and t, determine whether or not you can convert s to t by performing exactly k of the above operations on s. 
 * If it's possible, print Yes; otherwise, print No.
 * 
 * Example :
 * a.		s: hackerhappy
 * 			t: hackerrank
 * 			k: 9
 * 			
 * 			Answer : Yes
 * 					 We perform 5 delete operations to reduce string s to 'hacker'. 
 * 					 Next, we perform  append operations (i.e., r, a, n, and k), to get hackerrank. 
 * 					 Because we were able to convert s to t by performing exactly k = 9 operations, we print Yes.
 * 
 * b.		s: aba
 * 			t: aba
 * 			k: 7
 * 			
 * 			Answer : Yes
 * 					 We perform 4 delete operations to reduce string s to the empty string 
 * 					 (recall that, though the string will be empty after 3 deletions, we can still perform a delete operation on an empty string to get the empty string). 
 * 					 Next, we perform 3 append operations (i.e., a, b, and a). Because we were able to convert s to t by performing exactly 7 operations, we print Yes.
 * 
 * link : https://www.hackerrank.com/contests/w25/challenges/append-and-delete
 * 
 * @author Hkandwal
 *
 */
public class WoC25_AppendAndDelete extends AbstractCustomTestRunner {
	
	private static WoC25_AppendAndDelete _instance = new WoC25_AppendAndDelete();
	
	private WoC25_AppendAndDelete() {}
	
	// logic : go by the question description.
	public static boolean _canConvert(String s, String t, int k) {
		int steps = 0, interimCount = 0, slen = s.length(), tlen = t.length();
		
		while (slen > tlen) { 
			steps ++;
			slen --;
		}
		
		while (tlen > slen) { 
			steps ++;
			tlen --;
		}
		
		int idx = slen - 1;
		for (; idx >= 0; idx --) {
			if (s.charAt(idx) == t.charAt(idx)) 
				interimCount ++;
			else {
				steps += (2 * ++ interimCount);		// pre-increment to consider the current element too. 
				interimCount = 0;
			}	
		}
		
		boolean isConvertible = (steps == k);
		if (steps < k)
			if ((k - steps) >= 2 * interimCount || (k - steps) % 2 == 0)
				isConvertible = true;
		
		return isConvertible;
	}

	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest("hackerhappy", "hackerrank", 9, true);
    	_instance.runTest("hackerhappy", "hackerrank", 6, false);
    	_instance.runTest("aba", "aba", 7, true);
    	_instance.runTest("aba", "aba", 5, false);
    	_instance.runTest("a", "b", 1, false);
    	_instance.runTest("a", "ab", 1, true);
    	_instance.runTest("", "ab", 1, false);
    	_instance.runTest("", "ab", 2, true);
    	_instance.runTest("c", "ab", 3, true);
    	_instance.runTest("", "", 2, true);
    	_instance.runTest("", "", 1, true);
    	_instance.runTest("", "", 0, true);
    	_instance.runTest("a", "a", 1, false);
    	_instance.runTest("a", "a", 2, true);
    	_instance.runTest("a", "a", 3, true);
    	_instance.runTest("abcdefgh", "abc", 5, true);
    	_instance.runTest("ajcdefgh", "abc", 9, true);
    	_instance.runTest("ajcdefgh", "abc", 6, false);
    	_instance.runTest("apple", "apple", 3, false);
    	_instance.runTest("apple", "apple", 5, false);
    	_instance.runTest("apple", "apple", 6, true);
    	_instance.runTest("abcd", "abcef", 4, false);
    	_instance.runTest("abcd", "abcef", 5, true);
    	_instance.runTest("abc", "add", 7, true);
    	_instance.runTest("abc", "add", 5, false);
    }

	public void runTest(final String s, final String t, final int k, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, t, k });
		
		for (Object answer : answers) 
			assertThat((boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
