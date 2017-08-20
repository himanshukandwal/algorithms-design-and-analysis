package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

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

    public String _countAndSay(int n) {
        if (n == 0) return "";
        String count = "1";
        while (n -- > 1) {
            String ans = "";
            for (int idx = 0, c = 0; idx <= count.length(); idx ++) {
                if (idx == 0 || (idx < count.length () && count.charAt (idx) == count.charAt (idx - 1))) c ++;
                else {
                    ans += (c + "" + count.charAt (idx - 1));
                    c = 1;
                }
            }
            count = ans;
        }
        return count;
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
