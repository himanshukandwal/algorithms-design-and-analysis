package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 13. Roman to Integer
 * 
 * Given a roman numeral, convert it to an integer.
 * 
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * @author Hxkandwal
 *
 */
public class RomanToInteger extends AbstractCustomTestRunner {

	private static RomanToInteger _instance = new RomanToInteger();
	
	private RomanToInteger() {}

    // method 1 : crude and complex.
    public int _romanToInt(String s) {
    	int icount = 0;
    	for (; icount < s.length() && s.charAt(icount) == 'I'; icount ++);
    	
    	if (icount > 0) {
    		if (s.length() == icount)
    			return icount;
    		else
    			return (s.charAt(icount) == 'X') ? 9 : 4;
    	} else {
    		if (s.startsWith("XC"))
    			return 90 + (s.length() > 2 ? _romanToInt(s.substring(2)) : 0);
    		if (s.startsWith("CM"))
    			return 900 + (s.length() > 2 ? _romanToInt(s.substring(2)) : 0);
    		if (s.startsWith("CD"))
    			return 400 + (s.length() > 2 ? _romanToInt(s.substring(2)) : 0);
    		if (s.startsWith("XL"))
    			return 40 + (s.length() > 2 ? _romanToInt(s.substring(2)) : 0);
    		if (s.charAt(0) == 'V')
    			return 5 + (s.length() > 1 ? _romanToInt(s.substring(1)) : 0);
    		if (s.charAt(0) == 'X')
    			return 10 + (s.length() > 1 ? _romanToInt(s.substring(1)) : 0);
    		if (s.charAt(0) == 'L')
    			return 50 + (s.length() > 1 ? _romanToInt(s.substring(1)) : 0);
    		if (s.charAt(0) == 'C')
    			return 100 + (s.length() > 1 ? _romanToInt(s.substring(1)) : 0);
    		if (s.charAt(0) == 'D')
    			return 500 + (s.length() > 1 ? _romanToInt(s.substring(1)) : 0);
    		if (s.charAt(0) == 'M')
    			return 1000 + (s.length() > 1 ? _romanToInt(s.substring(1)) : 0);
    	}
    	
    	return 0;
    }
    
    // method 2 : cleaner and short.
    public int _romanToInt2(String s) {
        Map<Character, Integer> romans = new HashMap<Character, Integer>();
        romans.put('I', 1);
        romans.put('V', 5);
        romans.put('X', 10);
        romans.put('L', 50);
        romans.put('C', 100);
        romans.put('D', 500);
        romans.put('M', 1000);
        char[] cs = s.toCharArray();
        int num = 0;
        int val;
        for (int i = 0; i < cs.length; i++) {
            val = romans.get(cs[i]);
            
            // very nice logic, saved multiple if else condition with the clause that 
            // if (value of cs[i + 1]) >  (value of cs[i]), then remove else add.
            if (i == cs.length - 1 || romans.get(cs[i + 1]) <= val) {
                num += val;
            } else {
                num -= val;
            }
        }
        return num;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("I", 1);
		_instance.runTest("II", 2);
		_instance.runTest("III", 3);
		_instance.runTest("IV", 4);
		_instance.runTest("V", 5);
		_instance.runTest("VI", 6);
		_instance.runTest("VII", 7);
		_instance.runTest("VIII", 8);
		_instance.runTest("IX", 9);
		_instance.runTest("X", 10);
		_instance.runTest("L", 50);
		_instance.runTest("LX", 60);
		_instance.runTest("LXX", 70);
		_instance.runTest("LXXX", 80);
		_instance.runTest("XC", 90);
		_instance.runTest("C", 100);
		_instance.runTest("XCIV", 94);
		_instance.runTest("DCXXI", 621);
		_instance.runTest("MCMXCVI", 1996);
		_instance.runTest("MCDLXXVI", 1476);
		_instance.runTest("MMMXLV", 3045);
	}
	
	public void runTest(final String str, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { str });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
