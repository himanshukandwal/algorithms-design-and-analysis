package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Design an algorithm and write code to remove the duplicate characters in a string without using any 
 * additional buffer. 
 * 
 * NOTE: One or two additional variables are fine. An extra copy of the array is not.
 * 
 * link: Gayle Laakmann (Cracking the coding interview)
 * 
 * @author Hxkandwal
 */
public class RemoveDuplicateCharactersInString extends AbstractCustomTestRunner {
	
	private static RemoveDuplicateCharactersInString _instance = new RemoveDuplicateCharactersInString();
	
	private RemoveDuplicateCharactersInString() {}
	
	public static String _removeDuplicates(String input) {
		int uniqueIndex = -1;
		char[] charArr = input.toCharArray();
		
		for (int idx = 0; idx < input.length(); idx ++) {
			boolean found = false;
			
			for (int innerIdx = 0; innerIdx <= uniqueIndex; innerIdx ++)
				if (input.charAt(innerIdx) == input.charAt(idx)) {
					found = true;
					break;
				}
			
			if (!found)
				charArr [++ uniqueIndex] = charArr [idx];
		}
		
		return String.valueOf(Arrays.copyOf(charArr, uniqueIndex + 1));
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("FOLLOW UP", "FOLW UP");
	}

	public void runTest(final String input, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
