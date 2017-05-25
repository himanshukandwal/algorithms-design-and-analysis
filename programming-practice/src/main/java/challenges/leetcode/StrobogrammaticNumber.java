package challenges.leetcode;

import java.util.HashMap;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * Strobogrammatic Number
 * 
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * Write a function to determine if a number is strobogrammatic. The number is represented as a string.
 * 
 * For example, the numbers "69", "88", and "818" are all strobogrammatic.
 * 
 * @author Hxkandwal
 */
public class StrobogrammaticNumber extends AbstractCustomTestRunner {

	public boolean _isStrobogrammaticBetter(String num) {
	    for (int i=0, j=num.length()-1; i <= j; i++, j--)
	        if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
	            return false;
	    return true;
	}
	
	public boolean _isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<>();
        map.put ('8', '8'); map.put ('1', '1'); map.put ('6', '9');  map.put ('9', '6'); map.put ('0', '0');
        int start = 0, end = num.length() - 1;
        while (start <= end) {
            if (map.containsKey (num.charAt (start)) && map.get (num.charAt (start)) == num.charAt (end)) { start ++; end --; }
            else return false;
        }
        return true;
    }
	
}
