package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 166. Fraction to Recurring Decimal
 * 
 * Given two integers representing the numerator and denominator of a fraction, 
 * return the fraction in string format.
 * 
 * If the fractional part is repeating, enclose the repeating part in parentheses.
 * 
 * For example,
 * 		Given numerator = 1, denominator = 2, return "0.5".
 * 		Given numerator = 2, denominator = 1, return "2".
 * 		Given numerator = 2, denominator = 3, return "0.(6)".
 * 
 * @author Hxkandwal
 */
public class FractionToRecurringDecimal extends AbstractCustomTestRunner {
	
	private static FractionToRecurringDecimal _instance = new FractionToRecurringDecimal();
	
	public String _fractionToDecimal(int numerator, int denominator) {
        
		return "";
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("0", "0", "0");
		_instance.runTest("1", "1", "10");
		_instance.runTest("11", "1", "100");
		_instance.runTest("101111", "10", "110001");
		_instance.runTest("110010", "10111", "1001001");
	}

	public void runTest(final String a, final String b, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a, b });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
