package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 555. Split Concatenated Strings
 * 
 * Given a list of strings, you could concatenate these strings together into a loop, where for each string you could choose 
 * to reverse it or not. Among all the possible loops, you need to find the lexicographically biggest string after cutting the 
 * loop, which will make the looped string into a regular one.
 * 
 * Specifically, to find the lexicographically biggest string, you need to experience two phases:
 * -	Concatenate all the strings into a loop, where you can reverse some strings or not and connect them in the same order as 
 * 		given.
 * 
 * -	Cut and make one breakpoint in any place of the loop, which will make the looped string into a regular one starting from 
 * 		the character at the cutpoint.
 * 
 * And your job is to find the lexicographically biggest one among all the possible regular strings.
 * 
 * Example:	Input: "abc", "xyz"
 * 			Output: "zyxcba"
 * 
 * 			Explanation: You can get the looped string "-abcxyz-", "-abczyx-", "-cbaxyz-", "-cbazyx-",
 * 						 where '-' represents the looped status.
 * 
 *  					 The answer string came from the fourth looped one, where you could cut from the middle character 
 *  					 'a' and get "zyxcba".
 *  
 * Note:
 * -	The input strings will only contain lowercase letters.
 * -	The total length of all the strings will not over 1,000.
 * 
 * @author Hxkandwal
 */
public class SplitConcatenatedStrings extends AbstractCustomTestRunner {
	
	private static SplitConcatenatedStrings _instance = new SplitConcatenatedStrings();

	public String _splitLoopedString(String[] strs) {
		String stot = "";
	    for (int i = 0; i < strs.length; i++) {
	        String rever = new StringBuffer(strs[i]).reverse().toString();
	        if (rever.compareTo(strs[i]) > 0) strs[i] = rever;
	        stot = stot + strs[i];
	    }
	    int start = 0;
	    String sol = stot;
	    char maxch = 'a';
	    for (int i = 0; i < strs.length; i++) {
	        int n = strs[i].length();
	        start += n;
	        String rever = new StringBuffer(strs[i]).reverse().toString();
	        String other_strs = stot.substring(start) + stot.substring(0, start - n);
	        for (int j = 0; j < n; j++) {
	            if (strs[i].charAt(j) - maxch >= 0) {
	                maxch = strs[i].charAt(j);
	                String s1 = strs[i].substring(j) + other_strs + strs[i].substring(0, j);
	                String s2 = rever.substring(n-1-j) + other_strs + rever.substring(0, n-1-j);
	                if (s1.compareTo(sol) > 0) sol = s1;
	                if (s2.compareTo(sol) > 0) sol = s2;
	            }
	        }
	    }
	    return sol;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String [] { "abc", "xyz" }, "zyxcba");
		_instance.runTest(new String[] { "yzy", "aba" }, "zyabay");
		_instance.runTest(new String[] { "lc", "evol", "cdy" }, "ylclovecd");
	}

	public void runTest(final String[] strs, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { strs });

		for (Object answer : answers)
				assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}  	
}
