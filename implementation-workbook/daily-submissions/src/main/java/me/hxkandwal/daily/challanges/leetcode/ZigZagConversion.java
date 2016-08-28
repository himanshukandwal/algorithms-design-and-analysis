package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	private ZigZagConversion() {}
	
	public String _convert(String s, int numRows) {
		if (numRows <= 1 || s.length() <= numRows)
			return s;
		
		char[] result = new char [s.length()];
		
		boolean toggle = false; 	// to toggle between lower and upper wave. (false : lower, true : upper)
		int idx = 0, mover = 0;
		
		for (int row = 0; row < numRows && idx < s.length(); row ++, toggle = false, mover = row) {
			result [idx ++] = s.charAt(mover);
			
			int delta = 0;
			while (mover < s.length()) {
				if (toggle = !toggle) // automatic computation + evaluation
					delta = 2 * (numRows - row - 1);
				else
					delta = (row + row);
				
				if (delta > 0) {
					mover += delta;
					if (mover < s.length())
						result [idx ++] = s.charAt(mover);
				}
			}
		}
		
		return String.valueOf(result);
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("PAYPALISHIRING", 3, "PAHNAPLSIIGYIR");
		_instance.runTest("A", 2, "A");
		_instance.runTest("ABCDE", 4, "ABCED");
		_instance.runTest("ABCDEF", 5, "ABCDFE");
		
		System.out.println("ok!");
	}

	public void runTest(final String input, final int numRows, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { input, numRows });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
	}

}
