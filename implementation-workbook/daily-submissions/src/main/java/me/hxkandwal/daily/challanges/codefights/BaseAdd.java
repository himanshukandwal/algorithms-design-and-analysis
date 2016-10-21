package me.hxkandwal.daily.challanges.codefights;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * You are given two non-negative integers, number1 and number2, given in bases base1 and base2, 
 * respectively. Each base can be in range from Binary (2) to Hexatridecimal (36).
 * 
 * Your task is to sum the given numbers and return them in one of the given bases. Here's how the base 
 * of the returned value is determined:
 * a) if number1 ≠ number2, the base of the largest number should be chosen;
 * b) if number1 = number2, the largest base from the given should be chosen.
 * 
 * Note, that characters used in bases greater than 10 are given in the lowercase.
 * 
 * Example:
 * 
 * a) 	BaseAdd("11", 2, "10", 10) = "13"
 * 		112 = 310, thus the sum equals 3 + 10 = 1310. Since 112 < 1010, base 10 should be used in the answer.
 *
 * b) 	BaseAdd("11111100000", 2, "7e0", 16) = "fc0".
 * 		111111000002 = 2016 base 10, and 7e016 = 2016 base 10 as well. Thus the sum equals 2016 + 2016 = 4032 base 10. 
 * 		
 * 		number1 = number2, the largest base should be used in the answer, which is 16. 
 * 		So, the answer is 403210 = fc016.
 *  
 * @author Hxkandwal
 *
 */
public class BaseAdd extends AbstractCustomTestRunner {
	
	private static BaseAdd _instance = new BaseAdd();
	
	private BaseAdd() {}
	
	public static String _addBase(String number1, int base1, String number2, int base2) {
		int n1 = Integer.parseInt(number1, base1), n2 = Integer.parseInt(number2, base2);
	    
	    if (n1 == n2)
	        return Integer.toString(n1 + n2, (base1 > base2 ? base1 : base2));
	    else
	        return (n1 > n2) ? Integer.toString(n1 + n2, base1) : Integer.toString(n1 + n2, base2);
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("11", 2, "10", 10 ,"13");
		_instance.runTest("11111100000", 2, "7e0", 16, "fc0");
		_instance.runTest("123", 10, "12", 11, "136");
	}

	public void runTest(final String number1, final int base1, final String number2, final int base2, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { number1, base1, number2, base2 });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	
	
}
