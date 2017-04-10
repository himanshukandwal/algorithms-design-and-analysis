package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 504. Base 7
 * 
 * Given an integer, return its base 7 string representation.
 * 
 * Example 1: 
 * 		Input: 100
 * 		Output: "202"
 * 
 * Example 2:
 * 		Input: -7
 * 		Output: "-10"
 * 
 * Note: The input will be in range of [-1e7, 1e7].
 * 
 * @author Hxkandwal
 */
public class Base7 extends AbstractCustomTestRunner {
	
	private static Base7 _instance = new Base7();
	
	private Base7() {}
	
    public String _convertToBase7(int num) {
    	boolean isNegative = (num < 0);
    	StringBuilder ans = new StringBuilder();
    	num = (isNegative) ? -num : num;
    	
        while (num > 0) {
        	ans.append(num % 7);
			num /= 7;
		}
        return (isNegative ? "-" : "") + (ans.length() > 0 ? ans.reverse().toString() : "0");
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(100, "202");
		_instance.runTest(-7, "-10");
	}

	public void runTest(final int area, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { area });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
