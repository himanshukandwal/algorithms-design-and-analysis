package challenges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Given a non-negative integer number, remove all of its odd digits (if all of the digits are removed, return zero).
 * 
 * @author Hxkandwal
 *
 */
public class NoOddDigits extends AbstractCustomTestRunner {
	
	private static NoOddDigits _instance = new NoOddDigits();
	
	private NoOddDigits() {}

	public static int _noOddDigits(int n) {
	    StringBuilder sb = new StringBuilder("" + n);
	    int idx = 0;
	    
	    while (idx < sb.length()) {
	    	if (Character.getNumericValue(sb.charAt(idx)) % 2 != 0)
	    		sb.deleteCharAt(idx);
	    	else
	    		idx ++;
	    }

	    return sb.length() == 0 ? 0 : Integer.valueOf(sb.toString());
	}
	
	// faster method, elegant and powerful
	public static int _recursiveNoOddDigits(int n) {
		if (n == 0)  return 0; 
	    return (n % 10) % 2 == 1 ? _recursiveNoOddDigits(n / 10) : _recursiveNoOddDigits(n / 10) * 10 + n % 10;
	}

	// driver method
	public static void main(String[] args) {
		_instance.runTest(0, 0);
		_instance.runTest(100, 0);
		_instance.runTest(123, 2);
		_instance.runTest(12345, 24);
		_instance.runTest(299752, 22);
	}

	public void runTest(final int number, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { number });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
