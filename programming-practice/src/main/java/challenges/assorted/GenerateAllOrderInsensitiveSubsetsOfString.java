package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Generate all order-insensitive subsets of a string.
 * 
 * "abc" has subsets "a", "b", "ab", "ac", ...
 * 
 * Order doesn't matter. "ab" is same as "ba"
 * 
 * link : https://www.youtube.com/watch?v=NdF1QDTRkck
 * 
 * @author Hxkandwal
 *
 */
public class GenerateAllOrderInsensitiveSubsetsOfString extends AbstractCustomTestRunner {
	
	private static GenerateAllOrderInsensitiveSubsetsOfString _instance = new GenerateAllOrderInsensitiveSubsetsOfString();
	
	private GenerateAllOrderInsensitiveSubsetsOfString() {}
	
	// method : very powerful and simple algorithm implementation.
	public static int _generateSubsets(String input) {
		if (input.length() == 0)
			return 0;
		
		return generateSubsetsInner("", input);
	}

	private static int generateSubsetsInner(String soFar, String remaining) {
		if (remaining.length() == 0) {
			if (soFar.length() > 0) {
				System.out.println(" > " + soFar);
				return 1;
			} else 
				return 0;
		}
		
		int count = 0;
		
		// with the first character at string index 0.
		count += generateSubsetsInner (soFar + remaining.charAt(0), remaining.substring(1));
		
		// without the first character at string index 0.
		count += generateSubsetsInner (soFar, remaining.substring(1));
		
		return count;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("", 0);
		_instance.runTest("12", 3);
		_instance.runTest("123", 7);
		_instance.runTest("1234", 15);
	}

	public void runTest(final String input, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
