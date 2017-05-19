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
        for (int idx = 0; idx < strs.length; idx ++) {
            String reverse = new StringBuilder(strs [idx]).reverse().toString();
            if (strs [idx].compareTo (reverse) < 0) strs [idx] = reverse;
        }
        StringBuilder ans = new StringBuilder ();
        for (int idx = 0; idx < strs.length; idx ++) ans.append (strs [idx]);
        
        String max = ans.toString(), local = max;
        for (int idx = 0; idx < ans.length(); idx ++) {
            local = local.substring (1) + local.charAt (0);
            if (max.compareTo (local) < 0) max = local;
        }
        return max;
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
