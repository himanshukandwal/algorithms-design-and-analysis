package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 524. Longest Word in Dictionary through Deleting
 * 
 * Given a string and a string dictionary, find the longest string in the dictionary that can be formed by deleting some characters of the given 
 * string. If there are more than one possible results, return the longest word with the smallest lexicographical order. 
 * 
 * If there is no possible result, return the empty string.
 * 
 * Example 1:
 * 		Input: s = "abpcplea", d = ["ale","apple","monkey","plea"]
 * 		Output: "apple"
 * 		
 * Example 2:
 * 		Input: s = "abpcplea", d = ["a","b","c"]
 * 		Output: "a"
 * 
 * @author Hxkandwal
 */
public class LongestWordInDictionaryThroughDeleting extends AbstractCustomTestRunner {
	
	private static LongestWordInDictionaryThroughDeleting _instance = new LongestWordInDictionaryThroughDeleting();  

	// simpler and cleaner
	public String _findLongestWordBetter(String s, List<String> d) {
        String ans = "";
        for (String di : d) {
            int idx = 0;
            for (char c : s.toCharArray()) 
                if (idx < di.length() && c == di.charAt (idx)) idx ++;
                
            if (idx == di.length())
                if (ans.length() < di.length() || (di.length() == ans.length() && di.compareTo(ans) < 0))
                    ans = di;
        }
        
        return ans;
    }
	
	// this approach is working, however giving TLE and is not memory efficient.
	public String findLongestWord(String s, List<String> d) {
        int max = 0; 
        String ans = "";
        
        for (String di : d) {
            int val = longestCommonSubsequence (s, di);
            
            if (val > max) {
                max = val;
                ans = di;
            } else if (val == max && ans.compareTo(di) > 0)
            	ans = di;
        }
        
        return ans;
    }
    
    private int longestCommonSubsequence (String s, String o) {
        int [][] dp = new int [s.length() + 1][o.length() + 1];
        
        int max = 0;
        for (int row = 0; row < s.length(); row ++) {
            for (int col = 0; col < o.length(); col ++) {
                dp [row + 1][col + 1] = (s.charAt(row) == o.charAt (col)) ? dp [row][col] + 1 : Math.max (dp [row + 1][col], dp [row][col + 1]);
                max = Math.max (max, dp [row + 1][col + 1]);
            }
        }
        
        return max == o.length() ? max : 0;   // <<<<<<<<<<<<<<<< has to be a complete dictionary word.
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("aaa", Arrays.asList("aaa","aa","a"), "aaa");
		_instance.runTest("abpcplea", Arrays.asList("ale","apple","monkey","plea"), "apple");
		_instance.runTest("aewfafwafjlwajflwajflwafj", Arrays.asList("apple","ewaf","awefawfwaf","awef","awefe","ewafeffewafewf"), "ewaf");
	}

	public void runTest(final String s, List<String> d, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, d });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
	
}
