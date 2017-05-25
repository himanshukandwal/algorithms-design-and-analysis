package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

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
    private List<String> dfs (int len, int n) {
        if (len == 0) return Arrays.asList ("");
        if (len == 1) return Arrays.asList ("0", "1", "8");
        
        List<String> ans = new ArrayList<>();
        for (String num : dfs (len - 2, n)) {
            if (len != n) ans.add ("0" + num + "0");
            ans.add ("6" + num + "9");
            ans.add ("9" + num + "6");
            ans.add ("8" + num + "8");
            ans.add ("1" + num + "1");
        }
        return ans;
    }

	public List<String> _findStrobogrammatic(int n) {
        List<String> ans = new ArrayList<> ();
        dfs (ans, "", n, 0);
        return ans;
    }
    
    private void dfs (List<String> ans, String build, int n, int index) {
        if (build.length () == n) ans.add (build);
        else {
            if (build.length () + 2 <= n) {
                dfs (ans, build.substring (0, index) + '6' + "" + '9' + build.substring (index), n, index + 1);
                dfs (ans, build.substring (0, index) + '9' + "" + '6' + build.substring (index), n, index + 1);
                if (build.length() > 0) dfs (ans, build.substring (0, index) + '0' + "" + '0' + build.substring (index), n, index + 1);
                dfs (ans, build.substring (0, index) + '1' + "" + '1' + build.substring (index), n, index + 1);
                dfs (ans, build.substring (0, index) + '8' + "" + '8' + build.substring (index), n, index + 1);
            } else {
            	dfs (ans, build.substring (0, index) + '0' + build.substring (index), n, index + 1);
            	dfs (ans, build.substring (0, index) + '1' + build.substring (index), n, index + 1);
            	dfs (ans, build.substring (0, index) + '8' + build.substring (index), n, index + 1);
            }
        }
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(2, Arrays.asList("69", "96", "11", "88"));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final int n, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { n });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}	

}
