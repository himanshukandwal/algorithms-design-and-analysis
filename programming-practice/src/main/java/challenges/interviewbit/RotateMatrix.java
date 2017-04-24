package challenges.interviewbit;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Rotate Matrix
 * 
 * You are given an n x n 2D matrix representing an image.
 * Rotate the image by 90 degrees (clockwise).
 * 
 * You need to do this in place.
 * Note that if you end up using an additional array, you will only receive partial score.
 * 
 * Example:
 * 		If the array is
 * 		
 * 		[
 * 			[1, 2],
 * 			[3, 4]
 * 		]
 * 
 * Then the rotated array becomes:
 * 
 * 		[
 * 			[3, 1],
 * 			[4, 2]
 * 		]
 * 
 * @author Hxkandwal
 */
public class RotateMatrix extends AbstractCustomTestRunner {
	
	private static RotateMatrix _instance = new RotateMatrix();

	public void rotate(List<List<Integer>> a) {
	    int layer = 0, n = a.size();
	    while (n - 2 * layer > 1) {
	        for (int idx = 0; idx < n - 2 * layer - 1; idx ++) {
	           int val = a.get (layer + idx).get (n - layer - 1);
	           a.get (layer + idx).set (n - layer - 1, a.get (layer).get (layer + idx));
	           a.get (layer).set (layer + idx, a.get (n - layer - 1 - idx).get (layer));
	           a.get (n - layer - 1 - idx).set (layer, a.get (n - layer - 1).get (n - layer - 1 - idx));
	           a.get (n - layer - 1).set (n - layer - 1 - idx, val);
	        }
	        layer ++;
	    }
	}
	
	// driver method
	public static void main(String[] args) {
		List<List<Integer>> a = Arrays.asList(Arrays.asList(1, 2), Arrays.asList(3, 4));
		_instance.rotate(a);
		
		for (List<Integer> ai : a) System.out.println(ai);
	}

}
