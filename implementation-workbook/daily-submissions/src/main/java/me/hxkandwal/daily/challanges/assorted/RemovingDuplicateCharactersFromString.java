package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * Write code to reverse C-Style string.
 * 
 * C-String means that “abcd” is represented as  ve characters, including the null character.
 * 
 * @author Hxkandwal
 *
 */
public class RemovingDuplicateCharactersFromString extends AbstractCustomTestRunner {
	
	private static RemovingDuplicateCharactersFromString _instance = new RemovingDuplicateCharactersFromString();
	
	private RemovingDuplicateCharactersFromString() {}
	
	public static String _reverseCStyle(String cstyleString) {
		char[] charArray = cstyleString.toCharArray();
		
		
		for (int idx = 0; idx < charArray.length - 1; idx ++) {
			
		}
		
		return null;
	}
	
	// driver method
	public static void main(String[] args) throws Exception {
		_instance.runTest("(AB) C((D E) F)/ SSS", "ABC(DEF)");
	}
	
	public void runTest(final String line, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { line });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
