package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * The string s contains dashes that split it into groups of characters. You are given an integer k that represents 
 * the number of characters in groups that your output should have. Your goal is to return a new string that breaks s 
 * into groups with a length of k by placing dashes at the correct intervals. 
 * 
 * If necessary, the first group of characters can be shorter than k. It is guaranteed that there are no consecutive 
 * dashes in s.
 * 
 * Example:
 * 		For s = "2-4a0r7-4k" and k = 4, the output should be stringReformatting(s, k) = "24a0-r74k";
 * 		The input string "2-4a0r7-4k" is split into three groups with lengths of 1, 5 and 2. 
 * 		Since k = 4, you need to split the string into two groups of 4 characters each, making the output 
 * 			string "24A0-R74k".
 * 
 * 		For s = "2-4a0r7-4k" and k = 3, the output should be stringReformatting(s, k) = "24-a0r-74k".
 * 		Given the same input string and k = 3, split the string into groups of 2, 3, and 3 characters to get the output 
 * 			string of "24-a0r-74k".
 * 
 * @author Hxkandwal
 */
public class StringReformatting extends AbstractCustomTestRunner {
	
	private static StringReformatting _instance = new StringReformatting();

	public String _stringReformatting(String s, int k) {
	    s = s.replaceAll("-", "");
	    StringBuilder ans = new StringBuilder();
	    for (int idx = s.length() - 1; idx >= 0; idx --)
	    	ans.append (ans.length() % (k + 1) == k ? "-" : "").append (s.charAt(idx));
	    return ans.reverse().toString();
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("2-4a0r7-4k", 3, "24-a0r-74k");
		_instance.runTest("2-4a0r7-4k", 4, "24a0-r74k");
	}

	public void runTest(final String s, final int k, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s, k });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
