package challenges.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Largest Number
 * 
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * 
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * 
 * Note: The result may be very large, so you need to return a string instead of an integer.
 * 
 * @author Hxkandwal
 */
public class LargestNumber extends AbstractCustomTestRunner {

	public String largestNumber(int[] nums) {
        List<String> strs = new ArrayList<>();
		for (int num : nums) strs.add (String.valueOf (num));
		Collections.sort(strs, (x, y) -> (y + x).compareTo(x + y));
		
		StringBuilder ans = new StringBuilder ();
	    for (String str : strs) ans.append(str);
	    return (ans.toString().matches ("0+")) ? "0" : ans.toString();
    }
	
}
