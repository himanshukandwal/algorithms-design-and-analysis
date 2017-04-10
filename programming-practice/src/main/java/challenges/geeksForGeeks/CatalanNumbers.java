package challenges.geeksForGeeks;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Catalan Numbers
 * 
 * link: https://en.wikipedia.org/wiki/Catalan_number
 * 		 https://www.youtube.com/watch?v=eoofvKI_Okg
 * 
 * @author Hxkandwal
 *
 */
public class CatalanNumbers extends AbstractCustomTestRunner {
	
	private static CatalanNumbers _instance = new CatalanNumbers();
	
	private CatalanNumbers() {}
	
	/**
	 * DP Solution
	 * 
	 * Hint to find : There will be two actions (stroking, handshaking, pairing, parenthesis), all will be
	 * 				  non-overlapping.
	 * 
	 * Here, N is the count for each of the two operations. 
	 * example, 1 means 1 left parenthesis and 1 right parenthesis so, in total 2.
	 * 			1 upward stroke and 1 downward stroke.
	 * 			Handshake is a single action requiring 2 ppl so, we just double it.
	 * 
	 * Result is the number of Dykes paths or unique ways/counts.
	 */
	public static int _getNthCatalanNumber(int n) {
		if (n <= 1) 
			return 1;
		
		int[] catalan_memo = new int [n + 1];
		catalan_memo [1] = catalan_memo [0] = 1;
		
		for (int idx = 2; idx <= n; idx ++) {
			
			/* finding catalan sequence for idx:
			 * 
			 * Cn = Cn−1*C0 + Cn−2*C1 +···+ C1*Cn−2 + C0*Cn−1
			 */
			for (int i = 0; i < idx; i++)
				catalan_memo [idx] += catalan_memo[i] * catalan_memo[idx - i - 1];		// calculation of the binomial coefficients.
		} 
		
		return catalan_memo[n];
	}
	
	// recursion tree solution.
	public static int _getNthCatalanNumberRecursively(int n) {
	    if (n <= 1) 
	    	return 1;
	 
	    int res = 0;
	    for (int i=0; i<n; i++)
	        res += _getNthCatalanNumberRecursively(i) * _getNthCatalanNumberRecursively(n-i-1);
	 
	    return res;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(1, 1);
		_instance.runTest(2, 2);
		_instance.runTest(3, 5);
		_instance.runTest(4, 14);
		_instance.runTest(5, 42);
	}

	public void runTest(final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}
