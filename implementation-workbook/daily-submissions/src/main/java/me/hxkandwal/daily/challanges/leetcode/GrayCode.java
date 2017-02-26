package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 89. Gray Code
 * 
 * The gray code is a binary numeral system where two successive values differ in only one bit.
 * 
 * Given a non-negative integer n representing the total number of bits in the code, print the sequence of gray code. 
 * A gray code sequence must begin with 0.
 * 
 * For example, given n = 2, return [0,1,3,2]. Its gray code sequence is:
 * 		00 - 0
 * 		01 - 1
 * 		11 - 3
 * 		10 - 2
 * 
 * Note: For a given n, a gray code sequence is not uniquely defined.
 * 
 * For example, [0,2,3,1] is also a valid gray code sequence according to the above definition.
 * For now, the judge is able to judge based on one instance of gray code sequence. Sorry about that.
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class GrayCode extends AbstractCustomTestRunner {
	
	private static GrayCode _instance = new GrayCode();			
	
	// The idea is simple. G(i) = i^ (i/2).
	public List<Integer> _grayCodeFast(int n) {
	    List<Integer> result = new LinkedList<>();
	    for (int i = 0; i < 1<<n; i++) result.add(i ^ i>>1);
	    return result;
	}
	
    public List<Integer> _grayCode(int n) {
        List <Integer> ans = new ArrayList<>();
        if (n == 0) { ans.add (0); return ans; }
        for (String s : grayCodeStr (n))
            ans.add (Integer.parseInt(s, 2));
        return ans;
    }
    
    private List<String> grayCodeStr (int n) {
        List<String> ans = new ArrayList<>();
        if (n == 1) { ans.add ("0"); ans.add ("1"); }
        else {
             boolean jump = false;
             for (String a : grayCodeStr (n - 1)) {
            	 ans.add (jump ? a + "1" : a + "0"); ans.add (jump ? a + "0" : a + "1");
                 jump = !jump;
             }
        }
        return ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(2, new ArrayList() {{ add (0); add (1); add (3); add (2); }});
	}

	public void runTest(final int n, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}

}
