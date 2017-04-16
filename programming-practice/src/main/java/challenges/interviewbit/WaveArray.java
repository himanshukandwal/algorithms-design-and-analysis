package challenges.interviewbit;

import java.util.Collections;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Wave Array
 * 
 * Given an array of integers, sort the array into a wave like array and return it, 
 * In other words, arrange the elements into a sequence such that a1 >= a2 <= a3 >= a4 <= a5.....
 * 
 * Example:
 * 		Given [1, 2, 3, 4]
 * 		One possible answer : [2, 1, 4, 3]
 * 		Another possible answer : [4, 1, 3, 2]
 * 
 * @author Hxkandwal
 */
public class WaveArray extends AbstractCustomTestRunner {

	public List<Integer> wave(List<Integer> a) {
	    Collections.sort (a);
	    for (int idx = 0; idx < a.size() - 1; idx += 2) {
	        int val = a.get (idx);
	        a.set (idx, a.get (idx + 1));
	        a.set (idx + 1, val);
	    }
	    return a;
	}
	
}
