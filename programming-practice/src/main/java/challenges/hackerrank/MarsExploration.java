/**
 * 
 */
package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Mars Exploration
 * 
 * Sami's spaceship crashed on Mars! She sends n sequential SOS messages to Earth for help.
 * 
 * Letters in some of the SOS messages are altered by cosmic radiation during transmission. 
 * Given the signal received by Earth as a string, S, determine how many letters of Sami's SOS 
 * have been changed by radiation.
 * 
 * Example:
 * a.		Input:	SOSSPSSQSSOR
 * 			Output:	3
 * 
 * b.		Input:	SOSSOT
 * 			Output:	1
 * 
 * 
 * link: https://www.hackerrank.com/challenges/mars-exploration
 * 
 * @author Hxkandwal
 */
public class MarsExploration extends AbstractCustomTestRunner {
	
	private static MarsExploration _instance = new MarsExploration();
	
	public MarsExploration() {}
	
	public static int _getChangedLetters(String s) {
		// array sequence which we know as meta information.
		char [] positionalCharArray = new char [] { 'S', 'O', 'S' };
		
		int err = 0;
		for (int idx = 0; idx < s.length(); idx ++)
			if (s.charAt(idx) != positionalCharArray [idx % positionalCharArray.length])
				err ++;
		
		return err;
	}

    // driver method
    public static void main(String[] args) {
        _instance.runTest("SOSSPSSQSSOR", 3);
        _instance.runTest("SOSSOT", 1);
        _instance.runTest("SSSSSS", 2);
        _instance.runTest("OOOOOO", 4);
        _instance.runTest("SSOSOO", 3);
        _instance.runTest("SOSSOS", 0);
    }

    public void runTest(final String s, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { s });

        for (Object answer : answers)
            assertThat((int) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }	
}
