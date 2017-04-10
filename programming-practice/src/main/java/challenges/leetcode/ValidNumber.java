package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 65. Valid Number
 * 
 * Validate if a given string is numeric.
 * 
 * Some examples:
 * 	"0" => true
 * 	" 0.1 " => true
 * 	"abc" => false
 * 	"1 a" => false
 * 	"2e10" => true
 * 
 * Note: It is intended for the problem statement to be ambiguous. You should gather all requirements up front before implementing one.
 * 
 * @author Hxkandwal
 */
public class ValidNumber extends AbstractCustomTestRunner {
	
	private static ValidNumber _instance = new ValidNumber();

    public boolean _isNumber(String s) {
        int idx = 0, len = s.length();
        while (idx < len && s.charAt(idx) == ' ') idx ++;
        while (idx < len && (s.charAt(idx) == '+' || s.charAt(idx) == '-')) idx ++;
        boolean isNumeric = false;
        while (idx < len && (s.charAt(idx) >= '0' && s.charAt(idx) <= '9')) {
            isNumeric = true; idx ++;
        }
        if (idx < len && (s.charAt(idx) == '.')) {
            idx ++;
            while (idx < len && (s.charAt(idx) >= '0' && s.charAt(idx) <= '9')) {
                isNumeric = true; idx ++;
            }
        }
        if (idx < len && isNumeric && s.charAt(idx) == 'e') {
            isNumeric = false; idx ++;
            while (idx < len && s.charAt(idx) == ' ') idx ++;
            while (idx < len && (s.charAt(idx) == '+' || s.charAt(idx) == '-')) idx ++;
            while (idx < len && (s.charAt(idx) >= '0' && s.charAt(idx) <= '9')) {
                isNumeric = true; idx ++;
            }
        }
        while (idx < len && s.charAt(idx) == ' ') idx ++;
        return isNumeric && idx == len;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("-1.", true);
		_instance.runTest("1 ", true);
		_instance.runTest("e9", false);
	}

	public void runTest(final String s, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
	
}
