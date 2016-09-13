package me.hxkandwal.daily.challanges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	private SuperReducedString() {}
	
	public static String _superReduce(String input) {
		StringBuilder sb = new StringBuilder();
	      
        int sbIdx = -1;
        for (int idx = 0; idx < input.length(); idx ++) {
          char ch = input.charAt(idx);
          
          if (sbIdx >= 0 && sb.charAt(sbIdx) == ch) {
            sb.delete(sbIdx, sbIdx + 1);
            sbIdx --;
          }
          else {
            sb.append(ch);  
            sbIdx ++;
          }
        }
      
        return (sbIdx > 0) ? sb.toString() : "Empty String";
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