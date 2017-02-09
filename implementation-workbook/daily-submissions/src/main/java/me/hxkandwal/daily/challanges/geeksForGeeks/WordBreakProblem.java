package me.hxkandwal.daily.challanges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Dynamic Programming | Set 32 (Word Break Problem)
 * 
 * Given an input string and a dictionary of words, find out if the input string can be segmented into a space-separated sequence of dictionary words. 
 * See following examples for more details.
 * 
 * This is a famous Google interview question, also being asked by many other companies now a days.
 * Consider the following dictionary
 * 
 *  { i, like, sam, sung, samsung, mobile, ice, cream, icecream, man, go, mango }
 *  
 *  Eaxmple 1: Input:  ilike
 *  		   Output: Yes
 *  				   The string can be segmented as "i like".
 *  
 *  Eaxmple 1: Input:  ilikesamsung
 *  		   Output: Yes
 *  				   The string can be segmented as "i like samsung" or "i like sam sung".
 * 
 * link: http://www.geeksforgeeks.org/dynamic-programming-set-32-word-break-problem/
 * @author Hxkandwal
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class WordBreakProblem extends AbstractCustomTestRunner {
	
	private static WordBreakProblem _instance = new WordBreakProblem();
	
	private WordBreakProblem() {}

	public static boolean _wordBreak(Set<String> dict, String s) {
		boolean[] f = new boolean[s.length() + 1];
		f[0] = true;

		for (int i = 1; i <= s.length(); i ++) {
			for (int j = i - 1; j >= 0; j --) {
				if (f[j] && dict.contains(s.substring(j, i))) {
					f[i] = true;
					break;
				}
			}
		}

		return f[s.length()];
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new HashSet() {{ add("ap"); add("ple"); }}, "apple", true);
		_instance.runTest(new HashSet() {{ add("ale"); add("apple"); add("monkey"); add("plea"); }}, "apple", true);
		_instance.runTest(new HashSet() {{ add("ale"); add("apple"); add("monkey"); add("plea"); }}, "banana", false);
		_instance.runTest(new HashSet() {{ add("i"); add("like"); add("sam"); add("sung"); 
										   add("samsung"); add("mobile"); add("ice"); add("go");
										   add("cream"); add("icecream"); add("man"); add("mango"); }}, "ilikesamsung", true);
		_instance.runTest(new HashSet() {{ add("i"); add("like"); add("sam"); add("sung"); 
										   add("samsung"); add("mobile"); add("ice"); add("go");
										   add("cream"); add("icecream"); add("man"); add("mango"); }}, "ilikesamsungOne", false);		
	}

	public void runTest(final Set<String> dictionary, final String input, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { dictionary, input });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
		
}
