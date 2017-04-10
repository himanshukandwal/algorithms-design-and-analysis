package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Write a method to decide if two strings are anagrams or not.
 * 
 * link: Gayle Laakmann (Cracking the coding interview)
 * 
 * @author Heman
 *
 */
public class ReplaceSpaceWithString extends AbstractCustomTestRunner {
	
	private static ReplaceSpaceWithString _instance = new ReplaceSpaceWithString();
	
	private ReplaceSpaceWithString() {}
	
	public static String _replace(String input, String replacement) {
		int spaceCount = 0;
		for (int idx = 0; idx < input.length(); idx ++) 
			if (input.charAt(idx) == ' ') 
				spaceCount ++;
		
		char[] output = new char [input.length() + spaceCount * (replacement.length() - 1)];
		
		for (int idx = 0, outIdx = 0; idx < input.length(); idx ++) {
			if (input.charAt(idx) == ' ') {
				for (int innerIdx = 0; innerIdx < replacement.length(); innerIdx ++)
					output [outIdx ++] = replacement.charAt(innerIdx);
			} else
				output [outIdx ++] = input.charAt(idx);
		}
		
		return String.valueOf(output);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("FOLLOW UP", " IT ", "FOLLOW IT UP");
	}

	public void runTest(final String input, final String replacement, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, replacement });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
