package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 422. Valid Word Square
 * 
 * Given a sequence of words, check whether it forms a valid word square.
 * A sequence of words forms a valid word square if the kth row and column read the exact same string, 
 * where 0 ≤ k < max(numRows, numColumns).
 * 
 * Note:
 * 	-	The number of words given is at least 1 and does not exceed 500.
 * 	-	Word length will be at least 1 and does not exceed 500.
 * 	-	Each word contains only lowercase English alphabet a-z.
 * 
 * Example 1:
 * 		Input: [
 * 					"abcd",
 * 					"bnrt",
 * 					"crmy",
 * 					"dtye"
 * 			   ]
 * 		Output:	true
 * 
 * Example 2:
 * 		Input:	[
 * 					"abcd",
 * 					"bnrt",
 * 					"crm",
 * 					"dt"
 * 				]
 * 		Output:	true
 * 
 * @author Hxkandwal
 */
public class ValidWordSquare extends AbstractCustomTestRunner {
	
	private static ValidWordSquare _instance = new ValidWordSquare();

	public boolean _validWordSquare(List<String> words) {
		if (words.size () == 0) return true;
        int [] size = new int [words.size()];
        for (int idx = 0; idx < words.size(); idx ++) {
            if (words.get (idx).length () > words.size()) return false;
            size [idx] += words.get (idx).length ();
            for (int i = 0; i < words.get (idx).length (); i ++) size [i] --;
        }
        for (int val : size) if (val != 0) return false;
        
        int idx = -1;
        while ( ++ idx < words.size ()) 
            for (int i = idx; i < words.get (idx).length (); i ++)
                if (words.get (idx).charAt (i) != words.get (i).charAt (idx)) return false;
        return true;
    }

	// driver method
	public static void main(String[] args) throws FileNotFoundException {
		_instance.runTest(Arrays.asList("abcd", "bnrt", "crmy", "dtye"), true);
		_instance.runTest(Arrays.asList("abcd", "bnrt", "crm", "dt"), true);
		_instance.runTest(Arrays.asList("ball", "asee", "lett", "le"), false);
	}
	
	public void runTest(final List<String> words, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { words });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
