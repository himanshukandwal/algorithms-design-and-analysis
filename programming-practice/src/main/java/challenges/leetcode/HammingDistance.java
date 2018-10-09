package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

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

	public static int _hammingDistance(int x, int y) {
		int ans = 0;
		for (int idx = 31; idx >= 0; idx --) ans += ((x >> idx & 1) ^ (y >> idx & 1));
		return ans;
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
