package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Funny String
 * 
 * Suppose you have a String, S, of length N that is indexed from 0 to N-1. You also have some String, R, 
 * that is the reverse of String S. S is funny if the condition S[i] - S[i-1] == R[i] - R [i-1] is true for 
 * every character i from 1 to N-1.
 * 
 * Example:
 * a.		Input:	acxz
 * 			Output:	Funny
 * 
 * b.		Input:	bcxz
 * 			Output:	Not Funny
 * 
 * @author Hxkandwal
 *
 */
public class FunnyString extends AbstractCustomTestRunner {
	
	private static FunnyString _instance = new FunnyString();
	
	private FunnyString() {}
	
	public static boolean _isFunny (String str) {
		boolean isEqual = true;
		for (int idx = 0; idx < str.length()/2; idx ++)
			isEqual = isEqual && (Math.abs(str.charAt(idx) - str.charAt(idx + 1)) == 
								 (Math.abs(str.charAt(str.length() - idx - 1) - str.charAt(str.length() - idx - 2))));
		
		return isEqual;
	}
	
    // driver method
    public static void main(String[] args) {
        _instance.runTest("acxz", true);
        _instance.runTest("bcxz", false);
    }

    public void runTest(final String input, final boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
