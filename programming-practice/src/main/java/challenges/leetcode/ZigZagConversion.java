package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 6. ZigZag Conversion
 * 
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: 
 * (you may want to display this pattern in a fixed font for better legibility)
 * 
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 
 * And then read line by line: "PAHNAPLSIIGYIR"
 * 
 * Write the code that will take a string and make this conversion given a number of rows:
 * 
 * 	string convert(string text, int nRows);
 * 	
 * 	convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".
 * 
 * @author Hxkandwal
 *
 */
public class ZigZagConversion extends AbstractCustomTestRunner {
	
	private static ZigZagConversion _instance = new  ZigZagConversion();
	
	public String _convert(String s, int numRows) {
		if (numRows == 1) return s;
        StringBuilder ans = new StringBuilder ();
        int n = 0, sIdx = 0, jump = numRows + numRows - 2, mid = jump;
        while (n < numRows) {
            int idx = sIdx ++;
            while (idx < s.length()) {
                ans.append (s.charAt (idx));
                if (mid != jump && mid != 0 && idx + mid < s.length ()) ans.append (s.charAt (idx + mid));
                idx += jump;
            }
            n ++;
            mid -= 2;
        }
        return ans.toString();
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR");
		_instance.runTest("A", 2, "A");
		_instance.runTest("ABCDE", 4, "ABCED");
		_instance.runTest("ABCDEF", 5, "ABCDFE");
	}

	public void runTest(final String input, final int numRows, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, numRows });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
