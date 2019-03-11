package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * 247. Strobogrammatic Number II
 * 
 * A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).
 * 
 * Find all strobogrammatic numbers that are of length = n.
 * 
 * For example, Given n = 2, return ["11","69","88","96"].
 * 
 * @author Hxkandwal
 */
public class StrobogrammaticNumberII extends AbstractCustomTestRunner {
	
	private static StrobogrammaticNumberII _instance = new StrobogrammaticNumberII();

	public List<String> findStrobogrammaticCleaner(int n) {
		return dfs (n, n);
    }
    
	// working our way outwards. Post order stitching. backtracking.
    public List<String> _findStrobogrammatic(int n) {
        return dfs (n, n);
    }

    private List<String> dfs (int len, int n) {
        if (len == 0) return Arrays.asList("");
        if (len == 1) return Arrays.asList("0", "1", "8");

        List<String> ans = new ArrayList<>();
        for (String s : dfs(len - 2, n)) {
            if (len != n) ans.add ('0' + s + '0');
            ans.add ('1' + s + '1');
            ans.add ('6' + s + '9');
            ans.add ('8' + s + '8');
            ans.add ('9' + s + '6');
        }

        return ans;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(2, Arrays.asList("11", "69", "88", "96"));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final int n, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	

}
