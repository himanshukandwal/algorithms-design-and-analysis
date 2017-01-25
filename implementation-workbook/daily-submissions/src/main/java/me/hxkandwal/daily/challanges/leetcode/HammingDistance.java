package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 461. Hamming Distance
 * 
 * The Hamming distance between two integers is the number of positions at which the corresponding bits 
 * are different. Given two integers x and y, calculate the Hamming distance.
 * 
 * Input: x = 1, y = 4
 * Output: 2
 * 
 * Explanation:
 * 		1   (0 0 0 1)
 * 		4   (0 1 0 0)
 * 		       ↑   ↑
 * 
 * The above arrows point to positions where the corresponding bits are different.
 * 
 * @author Hxkandwal
 *
 */
public class HammingDistance extends AbstractCustomTestRunner {
	
	private static HammingDistance _instance = new HammingDistance();
	
	private HammingDistance() {}

	public static int _hammingDistance(int x, int y) {
		int result = 0;
		
        if (x != y) { 
        	while (x > 0 || y > 0) {
        		result += (x % 2 == y % 2 ? 0 : 1);
        		
        		if (x > 0)
        			x /= 2;
        		
        		if (y > 0)
        			y /= 2;
        	}
        }
        
        return result;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(1, 1, 0);
		_instance.runTest(1, 4, 2);
	}
	
	public void runTest(final int x, final int y, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { x, y });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
