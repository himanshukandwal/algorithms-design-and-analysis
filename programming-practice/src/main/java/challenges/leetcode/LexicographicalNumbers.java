package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 386. Lexicographical Numbers
 * 
 * Given an integer n, return 1 - n in lexicographical order.
 * For example, given 13, return: [1,10,11,12,13,2,3,4,5,6,7,8,9].
 * 
 * Please optimize your algorithm to use less time and space. The input size may be as large as 5,000,000.
 * 
 * @author Hxkandwal
 */
public class LexicographicalNumbers extends AbstractCustomTestRunner {
	
	private static LexicographicalNumbers _instance = new LexicographicalNumbers();

	public List<Integer> _lexicalOrder(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int idx = 1; idx <= n; idx ++) ans.add (idx);
        Collections.sort (ans, (a, b) -> {
            String astr = String.valueOf(a), bstr = String.valueOf(b);
            if (astr.length() == bstr.length()) return a - b;
            else {
                int minLen = Math.min (astr.length(), bstr.length());
                for (int idx = 0; idx < minLen; idx ++) {
                    if (astr.charAt (idx) > bstr.charAt (idx)) return 1;
                    else if (astr.charAt (idx) < bstr.charAt (idx)) return -1;
                }
                return 1;
            }
        });
        return ans;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(13, Arrays.asList(1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final int n, final List<Integer> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((List<Integer>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
