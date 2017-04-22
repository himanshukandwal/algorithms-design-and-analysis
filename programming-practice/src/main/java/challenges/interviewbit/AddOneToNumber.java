package challenges.interviewbit;

import java.util.ArrayList;

import challenges.AbstractCustomTestRunner;

/**
 * Add One To Number
 * 
 * Given a non-negative number represented as an array of digits, add 1 to the number (increment the number represented by the digits).
 * The digits are stored such that the most significant digit is at the head of the list.
 * 
 * Example:
 * 	If the vector has [1, 2, 3] the returned vector should be [1, 2, 4]
 * 	as 123 + 1 = 124.
 * 
 * link: https://www.interviewbit.com/problems/add-one-to-number/ 
 * 
 * @author Hxkandwal
 */
public class AddOneToNumber extends AbstractCustomTestRunner {

	public ArrayList<Integer> plusOne(ArrayList<Integer> a) {
	    int carry = 0;
	    for (int idx = a.size() - 1; idx >= 0; idx --) {
	        int curr = a.get (idx) + (idx == a.size() - 1 ? 1 : 0) + carry;
	        carry = (curr / 10);
	        a.set (idx, curr % 10);
	        if (carry == 0) break;
	    }    
	    
	    if (carry > 0) a.add (0, carry);
	    return a;
	}
	
}
