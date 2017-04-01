package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		if (numerator == 0) return "0";
		
		StringBuilder ans = new StringBuilder();
		ans.append(((numerator > 0) ^ (denominator > 0)) ? "-" : "");
		long numer = Math.abs((long) numerator), denom = Math.abs((long) denominator);
		
        ans.append (numer / denom);
        long remainder = numer % denom;       
        
        if (remainder > 0) ans.append(".");
        
        Map<Long, Integer> remainderIndex = new HashMap<>();
        while (!remainderIndex.containsKey (remainder) && remainder > 0) {
            remainderIndex.put (remainder, ans.length());
            numer = remainder * 10l;
            ans.append (numer / denom);
            remainder = (numer % denom);
        }
        
	    if (remainder != 0) { ans.append (")"); ans.insert (remainderIndex.get (remainder).intValue(), '('); }
        return ans.toString();
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(1, 2, "0.5");
		_instance.runTest(-1, -2, "0.5");
		_instance.runTest(-1, 2, "-0.5");
		_instance.runTest(2, 1, "2");
		_instance.runTest(2, 3, "0.(6)");
	}

	public void runTest(final int numerator, int denominator, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { numerator, denominator });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
