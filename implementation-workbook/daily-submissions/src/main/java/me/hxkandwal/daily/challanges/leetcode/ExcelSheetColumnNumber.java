package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 171. Excel Sheet Column Number
 * 
 * Related to question Excel Sheet Column Title.
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * 
 * For example:
 * 		A -> 1
 * 		B -> 2
 * 		C -> 3
 * 		...
 * 		Z -> 26
 * 		AA -> 27
 * 		AB -> 28
 * 
 * @author Hxkandwal
 */
public class ExcelSheetColumnNumber extends AbstractCustomTestRunner {
	
	private static ExcelSheetColumnNumber _instance = new ExcelSheetColumnNumber();
	
	private ExcelSheetColumnNumber() {}
	
	public int _titleToNumber(String s) {
		int result = 0;
	    for (int i = 0 ; i < s.length(); i ++) 
	    	result = result * 26 + (s.charAt(i) - 'A' + 1);
	    return result;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("A", 1);
		_instance.runTest("AA", 27);
		_instance.runTest("AB", 28);
		_instance.runTest("BA", 53);
		_instance.runTest("AJHX", 24568);
	}

	public void runTest(final String s, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
