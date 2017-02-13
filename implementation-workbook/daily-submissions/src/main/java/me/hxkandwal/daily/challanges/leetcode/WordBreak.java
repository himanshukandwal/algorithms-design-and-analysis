package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Word Break II
 * 
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated 
 * sequence of one or more dictionary words. You may assume the dictionary does not contain duplicate words.
 * 
 * For example, 
 * 		given s = "leetcode", dict = ["leet", "code"].
 * 		Return true because "leetcode" can be segmented as "leet code".
 * 
 * @author Hxkandwal
 *
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class WordBreak extends AbstractCustomTestRunner {
	
	private static WordBreak _instance = new WordBreak();
	
	public WordBreak() {}

    public static boolean _wordBreak(Set<String> hash, String s) {
    	boolean[] f = new boolean [s.length() + 1];
    	f [0] = true;
    	
    	for (int idx = 1; idx < f.length; idx ++) 
    		for (int innerIdx = idx - 1; innerIdx >= 0; innerIdx --)
    			if (f [idx] = (f [innerIdx] && hash.contains(s.substring (innerIdx, idx))))
    				break;
		
		return f [f.length - 1];    
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
