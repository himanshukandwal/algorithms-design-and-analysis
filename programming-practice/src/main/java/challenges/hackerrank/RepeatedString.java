package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Repeated String
 * 
 * Lilah has a string, s, of lower-case English letters that she repeated infinitely many times.
 * 
 * Given an integer, n, find and print the number of letter a's in the first n letters of Lilah's infinite string.
 * 
 * Example :
 * 	a)			aba 10
 * 	Result : 	7
 * 	
 * 	The first 10 letters of the infinite string are 'abaabaabaa'. 
 * 	Because there are  a's, we print 7 on a new line.
 * 
 * 	b)			a	1000000000000
 * 	Result : 	1000000000000
 * 
 * 	Because all of the first 1000000000000 letters of the infinite string are a, 
 * 	we print 1000000000000 on a new line.
 * 
 * @author Hxkandwal
 *
 */
public class RepeatedString extends AbstractCustomTestRunner {
	
	private static RepeatedString _instance = new RepeatedString();
	
	public long _getAcount(String input, long repeatedLength) {
		long count = 0;
		for (int idx = 0; idx < Math.min(input.length(), repeatedLength); idx ++)
			if (input.charAt(idx) == 'a')
				count ++;
		
		if (input.length() < repeatedLength) {
			count *= (repeatedLength / input.length());

			for (int idx = 0; idx < (repeatedLength % input.length()); idx++)
				if (input.charAt(idx) == 'a')
					count++;
		}
		
		return count;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("a", 1000000000000l, 1000000000000l);
		_instance.runTest("aba", 10, 7);
	}

	public void runTest(final String input, final long repeatedLength, final long expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, repeatedLength });

		for (Object answer : answers)
			assertThat((Long) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
