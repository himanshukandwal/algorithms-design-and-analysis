package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

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
	
	/**
	 * We try to decompose the problem in form of a tree, starting from the input number that can be branched 
	 * in 2-9 ways (as the branch with value 1 means the input number itself). Our goal is to find the shortest 
	 * path from the root to the leaf, while generating the minimal number. 
	 * We try branching from the highest number first, as then we will construct the shortest path.
	 *
	 *	      48
	 *	     /|\  
	 *	    8 6 4.. 2  
	 *	   /  |  \
	 *	  6   8  6
	 *           |
	 *           2
	 * 
	 * Also assuming that if a number is divisible by 8, then no need to divide further by 4 and 2 (factors) and 
	 * this is done by maintaining information about all the previous numbers seen, using a seen variable.
	 */
	public int _smallestFactorization(int a) {
		if (a <= 1) return a;
        long ans = Long.MAX_VALUE;
            
        int seen = 1;
        for (int idx = 9; idx > 1; idx --) 
            if (seen % idx != 0 && a % idx == 0) {
                seen *= idx;
                int val = _smallestFactorization (a/idx);
                if (val > 0) ans = Math.min (ans, val == 1 ? idx : 10l * val + idx);
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
