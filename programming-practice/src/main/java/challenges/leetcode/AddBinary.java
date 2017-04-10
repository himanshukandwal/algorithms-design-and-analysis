package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 67. Add Binary
 * 
 * Given two binary strings, return their sum (also a binary string).
 * 
 * For example,
 * 		a = "11"
 * 		b = "1"
 * 	
 * Return "100".
 * 
 * @author Hxkandwal
 *
 */
public class AddBinary extends AbstractCustomTestRunner {
	
	private static AddBinary _instance = new AddBinary();
	
	private AddBinary() {}
	
    public String _addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();
        
        int idx = 0, carry = 0;
        while (idx < a.length() || idx < b.length()) {
            int ach = (idx < a.length() ? a.charAt(a.length() - idx - 1) - '0' : -1);
            int bch = (idx < b.length() ? b.charAt(b.length() - idx - 1) - '0' : -1);
            idx ++;
            
            if (ach >= 0 && bch >= 0) {
                sb.append(carry > 0 ? ach ^ bch ^ carry : ach ^ bch);
                carry = (carry > 0 ? (ach | bch) & carry : ach & bch);
            } else {
                if (ach >= 0) {
                    sb.append(carry > 0 ? ach ^ carry : ach);
                    carry = (carry > 0 ? ach & carry : 0);
                } else {
                    sb.append(carry > 0 ? bch ^ carry : bch);
                    carry = (carry > 0 ? bch & carry : 0);
                }
            }
        }
        
        if (carry > 0)
            sb.append(carry);
        
        return sb.reverse().toString();
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
