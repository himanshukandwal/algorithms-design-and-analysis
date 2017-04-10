package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Program to find the largest lexicographic rotation in a string. 
 * 
 * @author Hxkandwal
 */
public class LargestLexicographicRotation  extends AbstractCustomTestRunner {
	
	private static LargestLexicographicRotation _instance = new LargestLexicographicRotation();

	public static String _combine (String x, String y) {
		return new StringBuilder().append(rotate(x)).append(rotate(y)).toString();
	}
	
	private static String rotate (String str) {
		String max = str;
		for (int i = 0; i < str.length(); i ++) {
			str = str.substring(1) + str.charAt(0);
			max = (str.compareTo(max) > 0) ? str : max;
		}
		return max;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("rotate", "it", "terotati");
	}

	public void runTest(final String x, final String y, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { x, y });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
