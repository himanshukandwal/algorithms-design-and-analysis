package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 168. Excel Sheet Column Title
 * 
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * 
 * For example:
 * 		1 -> A
 * 		2 -> B
 * 		3 -> C
 * 		...
 * 		26 -> Z
 * 		27 -> AA
 * 		28 -> AB
 * 
 * @author Hxkandwal
 */
public class ExcelSheetColumnTitle extends AbstractCustomTestRunner {
	
	private static ExcelSheetColumnTitle _instance = new ExcelSheetColumnTitle();
	
	private ExcelSheetColumnTitle() {}
	
	/**
	 * when numbering starts from 1 instead of 0 like here we have 'A' as 1 instead of 0, 
	 * then its suggested that reduce the number by 1 to handle the range scenarios and use base 
	 * 26 instead of 27 (mode and division)
	 */
    public String _convertToTitle(int n) {
    	StringBuilder result = new StringBuilder();

        while (n > 0) {
            n --;
            result.append ((char)('A' + n % 26));          // <<<<<<<<<<<<<<<<<<, no need to do + 1 unlike as we did 'A' + s.charAt(idx) - 1
            n /= 26;
        }

        return result.reverse().toString();
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(1, "A");
		_instance.runTest(27, "AA");
		_instance.runTest(28, "AB");
		_instance.runTest(53, "BA");
		_instance.runTest(24568, "AJHX");
	}

	public void runTest(final int n, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	   
}
