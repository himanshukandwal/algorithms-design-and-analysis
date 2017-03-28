package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 43. Multiply Strings
 * 
 * Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2. 
 * 
 * Note:
 * 		The length of both num1 and num2 is < 110.
 * 		Both num1 and num2 contains only digits 0-9.
 * 		Both num1 and num2 does not contain any leading zero.
 * 
 * You must not use any built-in BigInteger library or convert the inputs to integer directly.
 * 
 * @author Hxkandwal
 */
public class MultiplyStrings extends AbstractCustomTestRunner {
	
	private static MultiplyStrings _instance = new MultiplyStrings();
	
    public static String _multiply(String s1, String s2) {
    	StringBuilder ans = new StringBuilder();
        
        int idx2 = s2.length() - 1;
        while (idx2 >= 0) {
            int ch2 = s2.charAt(idx2) - '0';
            
            int idx1 = s1.length() - 1, localcarry = 0;
            StringBuilder localProduct = new StringBuilder();
            
            while (idx1 >= 0) {
                int ch1 = s1.charAt(idx1 --) - '0';
                int innerProduct = ch1 * ch2 + localcarry;
                localcarry = innerProduct / 10;
                innerProduct %= 10;
                localProduct.append (String.valueOf(innerProduct));
            }
            
            if (localcarry > 0) localProduct.append (String.valueOf(localcarry));
            
            if (ans.length() == 0) 
                ans.append (localProduct.toString());
            else {
                localcarry = 0;
                int iidx1 = s2.length() - idx2 - 1, iidx2 = 0;
                
                while (iidx1 < ans.length() && iidx2 < localProduct.length()) {
                    int cch1 = ans.charAt(iidx1 ++) - '0';
                    int cch2 = localProduct.charAt(iidx2 ++) - '0';
                    int sum = cch1 + cch2 + localcarry;
                    localcarry = sum / 10;
                    sum %= 10;
                    ans.setCharAt (iidx1 - 1, (char) (sum + '0'));
                }
                
                while (iidx2 < localProduct.length()) {
                    int sum = localProduct.charAt(iidx2 ++) - '0' + localcarry;
                    localcarry = sum / 10;
                    sum %= 10;
                    ans.append (sum);
                }
                
                if (localcarry > 0) ans.append (localcarry);
            }
            idx2 --;
        }
        
        return (Long.valueOf(ans.toString()) == 0) ? "0" : ans.reverse().toString();
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("1", "1", "1");
		_instance.runTest("1", "0", "0");
		_instance.runTest("10", "0", "0");
		_instance.runTest("1", "2", "2");
		_instance.runTest("2", "1", "2");
		_instance.runTest("2", "2", "4");
		_instance.runTest("9", "99", "891");
		_instance.runTest("123", "456", "56088");
		_instance.runTest("999", "999", "998001");
		_instance.runTest("123456789", "987654321", "121932631112635269");
	}

	public void runTest(final String num1, String num2, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num1, num2 });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
