package challenges.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 12. Integer to Roman
 * 
 * Given an integer, convert it to a roman numeral.
 * Input is guaranteed to be within the range from 1 to 3999.
 * 
 * @author Hxkandwal
 */
public class IntegerToRoman extends AbstractCustomTestRunner {

	public String intToRoman(int num) {
        Map<Integer, String> dict = new LinkedHashMap<>();
        dict.put(1000, "M"); dict.put(900, "CM"); dict.put(500, "D");
        dict.put(400, "CD"); dict.put(100, "C"); dict.put(90, "XC");
        dict.put(50, "L"); dict.put(40, "XL"); dict.put(10, "X");
        dict.put(9, "IX"); dict.put(5, "V"); dict.put(4, "IV");
        dict.put(1, "I"); 

        StringBuilder ans = new StringBuilder();
        for (Map.Entry<Integer, String> entry : dict.entrySet()) {
            for (int idx = 0; idx < num / entry.getKey(); idx ++) ans.append (entry.getValue());
            num %= entry.getKey();
        }
        return ans.toString();
    }
	
}
