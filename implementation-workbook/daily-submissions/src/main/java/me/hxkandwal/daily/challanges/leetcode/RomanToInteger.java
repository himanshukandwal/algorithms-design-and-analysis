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
	
    public int _romanToInt(String s) {
    	Map<Character, Integer> romans = new HashMap<Character, Integer>();
        romans.put('I', 1);
        romans.put('V', 5);
        romans.put('X', 10);
        romans.put('L', 50);
        romans.put('C', 100);
        romans.put('D', 500);
        romans.put('M', 1000);
        char[] chArr = s.toCharArray();
        
        int ans = 0;
        for (int idx = 0; idx < chArr.length; idx ++) {
            int value = romans.get (chArr [idx]);
            if (idx == chArr.length - 1 || romans.get (chArr [idx + 1]) <= value) ans += value;
            else ans -= value;
        }
        return ans;
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
