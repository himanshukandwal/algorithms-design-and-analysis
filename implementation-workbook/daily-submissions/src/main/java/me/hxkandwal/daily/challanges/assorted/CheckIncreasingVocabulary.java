package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 
 * @author Hxkandwal
 *
 */
public class CheckIncreasingVocabulary extends AbstractCustomTestRunner {
	
	private static CheckIncreasingVocabulary _instance = new CheckIncreasingVocabulary();
	
	private CheckIncreasingVocabulary() {}
	
	public static boolean _isEqual(String a, int a1, String b, int b1) {
		if (a1 == a.length() || b1 == b.length())
			return false;
		
		if (a.charAt(a1) == b.charAt(b1))
			return true;
		else {
			return _isEqual(a, a1 + 1, b, b1 + 1) || _isEqual(a, a1 + 1, b, b1) || _isEqual(a, a1, b, b1 + 1);
		}
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest("is", "bye", false);
		_instance.runTest("isb", "bye", true);
	}

	public void runTest(final String a, final String b, final Boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a, 0, b, 0 });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
