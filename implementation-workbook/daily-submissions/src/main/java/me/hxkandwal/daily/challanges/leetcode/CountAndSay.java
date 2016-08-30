package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 38. Count and Say
 * 
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 	
 * 	1, 11, 21, 1211, 111221, ...
 * 
 * 	1 is read off as "one 1" or 11.
 * 	11 is read off as "two 1s" or 21.
 * 	21 is read off as "one 2, then one 1" or 1211.
 * 
 * Given an integer n, generate the nth sequence.
 * 
 * Note: The sequence of integers will be represented as a string.
 * 
 * @author Hxkandwal
 *
 */
public class CountAndSay extends AbstractCustomTestRunner {
	
	private static CountAndSay _instance = new CountAndSay();
	
	private CountAndSay() {}
	
    public String _countAndSay(int n) {
        String value = "1";
        
        for (int num = 0; num < (n - 1); num ++) {
            StringBuilder sbInner = new StringBuilder();    
            
            Character previousCh = null;
            int count = 0;
            for (int idx = 0; idx < value.length(); idx ++) {
                Character ch = value.charAt(idx);
                
                if (previousCh == null) {
                    count = 1;
                    previousCh = ch;
                } else if (previousCh == ch) {
                    count ++;    
                } else {
                    sbInner.append(count + String.valueOf(previousCh));
                    previousCh = ch;
                    count = 1;
                }
            }
            value = sbInner.append(count + String.valueOf(previousCh)).toString();
        }
        
        return value;
    }
	
    public static void main(String[] args) {
    	_instance.runTest(1, "1");
    	_instance.runTest(2, "11");
    	_instance.runTest(3, "21");
    	_instance.runTest(4, "1211");
    	_instance.runTest(5, "111221");
	}
    
	public void runTest(final int n, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });
		
		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
			
		System.out.println("ok!");
	}
	
}
