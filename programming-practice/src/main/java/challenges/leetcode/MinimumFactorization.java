package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 625. Minimum Factorization
 * 
 * Given a positive integer a, find the smallest positive integer b whose multiplication of each 
 * digit equals to a.
 * 
 * If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.
 * 
 * Example 1
 * 		Input:	48
 * 		Output:	68
 * 
 * Example 2
 * 		Input:	15
 * 		Output:	35
 * 
 * @author Hxkandwal
 */
public class MinimumFactorization extends AbstractCustomTestRunner {
	
	private static MinimumFactorization _instance = new MinimumFactorization();
	
	public int _smallestFactorization(int a) {
        if (a <= 1) return a;
        long ans = Long.MAX_VALUE;
        
        boolean [] map = new boolean [10];
        Arrays.fill (map, true);
        map [0] = map [1] = false;
        
        for (int idx = 9; idx > 1; idx --) 
            if (map [idx] && a % idx == 0) {
            	int val = _smallestFactorization (a/idx);
                if (val > 0) ans = Math.min (ans, val == 1 ? idx : 10l * val + idx);
                
                for (int jdx = 2; jdx < idx; jdx ++) if (map [jdx]) map [jdx] = idx % jdx != 0;
            }
        
        return (ans == Long.MAX_VALUE || ans > Integer.MAX_VALUE) ? 0 : (int) ans;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(1, 1);
		_instance.runTest(3, 3);
		_instance.runTest(6, 6);
		_instance.runTest(48, 68);
		_instance.runTest(15, 35);
		_instance.runTest(128, 288);
		_instance.runTest(22, 0);
		_instance.runTest(3000000, 355555588);
		_instance.runTest(18000000, 0);
		_instance.runTest(134217728, 888888888);
		_instance.runTest(150994944, 888888889);
	}

	public void runTest(final int a, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { a });

		for (Object answer : answers)
			assertThat((int) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
