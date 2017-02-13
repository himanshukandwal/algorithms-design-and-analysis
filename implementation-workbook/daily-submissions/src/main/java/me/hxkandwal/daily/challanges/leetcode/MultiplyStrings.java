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
	
	private MultiplyStrings() {}
	
    public static String _multiply(String num1, String num2) {
    	String first = num1, second = num2;
    	
    	if (num2.length() > num1.length()) { first = num2; second = num1; }
    	int idx2 = second.length() - 1, shift = 0;
    	
    	StringBuilder answer = null;
    	while (idx2 >= 0) {
    		int idx1 = first.length() - 1;
    		StringBuilder localAnswer = new StringBuilder();
    		int product = 0, sum = 0, carry = 0;
    		
    		while (idx1 >= 0) {
    			product = (first.charAt(idx1 --) - '0') * (second.charAt(idx2) - '0') + carry;
    			carry = (product >= 10) ? product / 10 : 0;
    			product = (product >= 10) ? product % 10 : product;
    			
    			localAnswer.append(product);
    		}
    		
    		if (carry > 0) localAnswer.append(carry);
    		localAnswer.reverse();
    		
    		if (shift == 0)
				answer = localAnswer;
    		else {
    			StringBuilder additiveAnswer = new StringBuilder();
    			int mIdx = answer.length() - 1;
    			
    			for (int idx = 0; idx < shift; idx ++) additiveAnswer.append(answer.charAt(mIdx - idx));
    			mIdx -= shift; carry = 0;
    			
    			int lIdx = localAnswer.length() - 1;
    			
    			while (lIdx >= 0 || mIdx >= 0) {
    				if (lIdx >= 0 && mIdx >= 0)
    					sum = (answer.charAt(mIdx --) - '0') + (localAnswer.charAt(lIdx --) - '0') + carry;
    				else if (lIdx >= 0)
    					sum = (localAnswer.charAt(lIdx --) - '0') + carry;
    				else
    					sum = (answer.charAt(mIdx --) - '0') + carry;
    				
    				carry = (sum >= 10) ? sum / 10 : 0;
    				sum = (sum >= 10) ? sum % 10 : sum;
    				additiveAnswer.append(sum);
    			}
    			
    			if (carry > 0) additiveAnswer.append(carry);
    			
    			answer = additiveAnswer.reverse();
    		}
    		
    		idx2 --;
    		shift ++;
    	}
    	
    	for (idx2 = 0; idx2 < answer.length(); idx2 ++)
			if (answer.charAt(idx2) != '0') break;
    	
    	return (idx2 == answer.length() ? "0" : answer.toString());
    }	
    
    /**
     * Amazing solution.
     * 
     * Read more: https://discuss.leetcode.com/topic/30508/easiest-java-solution-with-graph-explanation
     */
    public static String _multiplyOptimal(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        int [] pos = new int [m + n];
       
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0'); 
                int p1 = i + j, p2 = i + j + 1;
                int sum = mul + pos[p2];

                pos[p1] += sum / 10;
                pos[p2] = (sum) % 10;
            }
        }  
        
        StringBuilder sb = new StringBuilder();
        for (int p : pos) if (!(sb.length() == 0 && p == 0)) sb.append(p);
        
        return sb.length() == 0 ? "0" : sb.toString();
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
