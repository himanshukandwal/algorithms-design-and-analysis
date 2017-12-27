package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Super Reduced String
 * 
 * Shil has a string, S, consisting of N lowercase English letters. In one operation, 
 * he can delete any pair of adjacent letters with same value. 
 * 
 * For example, string "aabcc" would become either "aab" or "bcc" after 1 operation.
 * 
 * Shil wants to reduce  as much as possible. To do this, he will repeat the above operation as many times 
 * as it can be performed. Help Shil out by finding and printing 's non-reducible form!
 * 
 * Example :
 * 		a) aaabccddd
 * 			Output : abd
 * 
 * 		b) baab
 * 			Output : Empty String
 * 
 * 		b) aa
 * 			Output : Empty String
 *  
 * @author Hxkandwal
 *
 */
public class SuperReducedString extends AbstractCustomTestRunner {
	
	private static SuperReducedString _instance = new SuperReducedString();
	
	public static String _superReduce(String s) {
		StringBuilder ans = new StringBuilder();
		for (char ch : s.toCharArray()) {
			if (ans.length() == 0 || ans.charAt (ans.length() - 1) != ch) ans.append (ch);
			else ans.deleteCharAt(ans.length() - 1);
		}
		return ans.length() > 0 ? ans.toString() : "Empty String";
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("aaabccddd", "abd");
		_instance.runTest("baab", "Empty String");
		_instance.runTest("aa", "Empty String");
	}

	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}