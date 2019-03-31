package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

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

    // cyclic fpass, spass
	/**
	 * numRows = 5
	 * fpass spass
	 *   8	   0
	 *   6	   2
	 *   4	   4
	 *   2	   6
	 *   0	   8
	 */
	public String _convertBetter(String s, int numRows) {
		if (numRows == 1 || numRows > s.length()) return s;
		int jump = 2 * numRows - 2;

		StringBuilder ans = new StringBuilder();
		for (int idx = 0; idx < numRows; idx ++) {
			int fpass = jump - 2 * idx, spass = 2 * idx;

			ans.append (s.charAt(idx));
			int j = idx;
			while (j < s.length()) {
				int next = j + fpass;
				if (next >= s.length()) break;
				if (next != j) { j = next; ans.append (s.charAt(j)); }

				next = j + spass;
				if (next >= s.length()) break;
				if (next != j) { j = next; ans.append (s.charAt(j)); }
			}
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
