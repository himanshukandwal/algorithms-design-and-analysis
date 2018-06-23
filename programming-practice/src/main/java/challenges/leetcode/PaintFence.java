package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * 276. Paint Fence
 *
 * There is a fence with n posts, each post can be painted with one of the k colors.
 * You have to paint all the posts such that no more than two adjacent fence posts have the same color.
 *
 * Return the total number of ways you can paint the fence.
 *
 * Note: n and k are non-negative integers.
 *
 * Example:
 *
 *      Input: n = 3, k = 2
 *      Output: 6
 *      Explanation: Take c1 as color 1, c2 as color 2. All possible ways are:
 *
 *                      post1  post2  post3
 *          -----      -----  -----  -----
 *             1         c1     c1     c2
 *             2         c1     c2     c1
 *             3         c1     c2     c2
 *             4         c2     c1     c1
 *             5         c2     c1     c2
 *             6         c2     c2     c1
 *
 * @author hxkandwal
 */
public class PaintFence extends AbstractCustomTestRunner {

    private static PaintFence _instance = new PaintFence();

    // https://leetcode.com/problems/paint-fence/discuss/71156/O(n)-time-java-solution-O(1)-space
    public int _numWaysBetter(int n, int k) {
        if (n == 0) return 0;
        if (n == 1) return k;

        int sameColorCounts = k, diffColorCounts = k * (k - 1);
        for(int i = 2; i < n; i ++) {
            int temp = diffColorCounts;
            diffColorCounts = (diffColorCounts + sameColorCounts) * (k - 1);
            sameColorCounts = temp;
        }
        return diffColorCounts + sameColorCounts;
    }

    public int _numWays(int n, int k) {
        Map<String, Integer> dp = new HashMap<>();
        if (n > 0) dfs (dp, new int [n], n, k, 0);
        return dp.getOrDefault("-1:-1:0", 0);
    }

    private int dfs (Map<String, Integer> dp, int[] c, int n, int k, int start) {
        if (start == n) return 1;

        String key = (start - 2 < 0 ? -1 : c [start - 2]) + ":" +
                     (start - 1 < 0 ? -1 : c [start - 1]) + ":" +
                      start;
        if (dp.containsKey(key)) return dp.get(key);

        int val = 0;
        for (int idx = 1; idx <= k; idx ++) {
            if (start > 1 && c [start - 1] == idx && c [start - 2] == idx) continue;
            c [start] = idx;
            val += dfs (dp, c, n, k, start + 1);
        }
        dp.put(key, val);
        return val;
    }

    // driver method
    public static void main(String[] args) throws FileNotFoundException {
        _instance.runTest(3, 2, 6);
        _instance.runTest(4, 2, 10);
        _instance.runTest(2, 46340, 2147395600);
    }

    public void runTest(final int n, final int k, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { n, k });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
