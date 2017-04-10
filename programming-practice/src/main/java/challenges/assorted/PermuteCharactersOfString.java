package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * program that creates permutations of the characters of a string and prints and returns the count of all.
 * 
 * @author Hxkandwal
 *
 */
public class PermuteCharactersOfString extends AbstractCustomTestRunner {
	
	private static PermuteCharactersOfString _instance = new PermuteCharactersOfString();
	
	private PermuteCharactersOfString() {}
	
	public static int _permute(String input) {
		if (input.length() == 0)
			return 0;
		
		return permuteInner("", input);
	}
	
	private static int permuteInner(String soFar, String remaining) {
		if (remaining.length() == 0) {
			System.out.println(" > " + soFar);
			return 1;
		}
		
		int count = 0;
		for (int idx = 0; idx < remaining.length(); idx ++) {
			String permutedString = soFar + String.valueOf(remaining.charAt(idx));
			count += permuteInner(permutedString, remaining.substring(0, idx) + remaining.substring(idx + 1));
		}
		
		return count;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("", 0);
		_instance.runTest("12", 2);
		_instance.runTest("123", 6);
	}

	public void runTest(final String input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
