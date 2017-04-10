package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Grid Challenge
 * 
 * Given a squared sized grid G of size N in which each cell has a lowercase letter. 
 * Denote the character in the ith row and in the jth column as G [i][j].
 * 
 * @author Hxkandwal
 */
public class GridChallenge extends AbstractCustomTestRunner {
	
	private static GridChallenge _instance = new GridChallenge();
	
	public boolean _isPossible(String[] input) {
		int maxLen = 0, size = input.length;
		for (int idx = 0; idx < size; idx ++) { 
            char[] strArr = input [idx].toCharArray();
            Arrays.sort (strArr);
            input [idx] = String.valueOf(strArr);
            maxLen = Math.max (maxLen, input [idx].length());
        }
		
        if (size == 0) return true;
        else {
            for (int col = 0; col < maxLen; col ++) {
                char ch = input [0].charAt (col);
                for (int row = 1; row < size; row ++) {
                    char nch = input [row].charAt (col);
                    if (nch >= ch) ch = nch;
                    else return false;
                }
            }
            return true;
        }
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String [] { "ebacd", "fghij", "olmkn", "trpqs", "xywuv" }, true);
		_instance.runTest(new String [] { "ppp", "ypp", "wyw" }, true);
		_instance.runTest(new String [] { "lyivr", "jgfew", "uweor", "qxwyr", "uikjd" }, false);
		_instance.runTest(new String [] { "1" }, true);
	}

	public void runTest(final String[] input, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	

}
