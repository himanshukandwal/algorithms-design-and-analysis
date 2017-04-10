package challenges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Find first unique char in a string
 * 
 * Given a string, find the first non-repeating character in it. For example, if the input string is 
 * “GeeksforGeeks”, then output should be ‘f’ and if input string is “GeeksQuiz”, then output should be 
 * ‘G’.
 * 
 * link : http://www.geeksforgeeks.org/given-a-string-find-its-first-non-repeating-character/
 * 
 * @author Hxkandwal
 *
 */
public class FindFirstUniqueCharInString extends AbstractCustomTestRunner {
	
	private static FindFirstUniqueCharInString _instance = new FindFirstUniqueCharInString();
	
	private FindFirstUniqueCharInString() {}
	
	public static Character _getFirstUniqueCharacter(String input) {
		Character result = null;
		
		int[] alphabets = new int[26];
		
		// record occurences
		for (int idx = 0; idx < input.length(); idx++)
			alphabets [input.charAt(idx) - 'a'] ++;
		
		for (int idx = 0; idx < input.length(); idx ++)
			if (alphabets [input.charAt(idx) - 'a'] == 1) {
				result = input.charAt(idx);
				break;
			}
		
		return result;
	}
	
	// driver method
    public static void main(String[] args) throws FileNotFoundException {
    	_instance.runTest("ab", 'a');
    	_instance.runTest("abc", 'a');
    	_instance.runTest("abac", 'b');
    	_instance.runTest("abacdefb", 'c');
    	_instance.runTest("abaaacdefbcd", 'e');
    	_instance.runTest("xxyzzzxyzy", null);
    	_instance.runTest("hqghumeaylnlfdxfircvscxggbwkfnqduxwfnfozv", 'm');	
    }

	public void runTest(final String s, final Character expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((Character) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
