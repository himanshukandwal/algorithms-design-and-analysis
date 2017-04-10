package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 405. Convert a Number to Hexadecimal
 * 
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
 * 
 * Note:
 * 		All letters in hexadecimal (a-f) must be in lowercase.
 * 		The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero 
 * 			character '0'; otherwise, the first character in the hexadecimal string will not be the zero character.
 * 		The given number is guaranteed to fit within the range of a 32-bit signed integer.
 * 		You must not use any method provided by the library which converts/formats the number to hex directly.
 * 
 * Example 1:
 * 		Input: 	26
 * 		Output: "1a"
 * 
 * Example 2:
 * 		Input: -1
 * 		Output:	"ffffffff"
 * 
 * @author Hxkandwal
 */
public class ConvertANumberToHexadecimal extends AbstractCustomTestRunner {
	
	private static ConvertANumberToHexadecimal _instance = new ConvertANumberToHexadecimal();
    
    public String _toHex(int num) {
    	if (num == 0) return "0";
    	char[] map = "0123456789abcdef".toCharArray();
        String result = "";
		while (num != 0) { result = map [(num & 15)] + result; num >>>= 4; }
        return result;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(26, "1a");
        _instance.runTest(Integer.MAX_VALUE, "7fffffff");
    }

    public void runTest(final int num, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { num });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }	
	
}
