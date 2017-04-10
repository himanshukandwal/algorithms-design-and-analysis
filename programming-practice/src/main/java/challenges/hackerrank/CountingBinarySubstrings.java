package challenges.hackerrank;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Counting Binary Substrings
 * 
 * link: https://www.hackerrank.com/tests/7hkdsapbj3f/questions/9fi0e7ck641
 *  
 * @author Hxkandwal
 */
public class CountingBinarySubstrings extends AbstractCustomTestRunner {
	
	private static CountingBinarySubstrings _instance = new CountingBinarySubstrings();
	
	private CountingBinarySubstrings() {}
	
	public static int _countSubstrings (String s) {
		int[] jump = new int[s.length()];
		int pairs = 0;
		
		for (int idx = 0; idx < s.length(); idx ++) {
			if (idx == 0) jump [idx] = 0;
			else {
				if (s.charAt(idx) != s.charAt(idx - 1)) {
					pairs ++;
					jump [idx] = 3;
				} else {
					if (jump [idx - 1] > 0 && (idx - jump [idx - 1] >= 0 && s.charAt(idx - jump [idx - 1]) != s.charAt(idx))) {
						pairs ++;
						jump [idx] = jump [idx - 1] + 2;
					}
				}
			}
		}

		return pairs;
	}
	
    // driver method
    public static void main(String[] args) {
        _instance.runTest("00110", 3);
        _instance.runTest("10101", 4);
        _instance.runTest("10001", 2);
    }

    public void runTest(final String input, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }	

}
