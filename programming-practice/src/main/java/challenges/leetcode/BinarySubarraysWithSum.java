package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 930. Binary Subarrays With Sum
 *
 * In an array A of 0s and 1s, how many non-empty subarrays have sum S?
 * Example 1:
 *      Input: A = [1,0,1,0,1], S = 2
 *      Output: 4
 *      Explanation:
 *              The 4 subarrays are bolded below:
 *              [(1,0,1),0,1]
 *              [(1,0,1,0),1]
 *              [1,(0,1,0,1)]
 *              [1,0,(1,0,1)]
 * Note:
 * 1. A.length <= 30000
 * 2. 0 <= S <= A.length
 * 3. A[i] is either 0 or 1.
 *
 * @author Hxkandwal
 */
public class BinarySubarraysWithSum extends AbstractCustomTestRunner {

    public int _numSubarraysWithSum(int[] A, int S) {
        int[] pre = new int [A.length + 1];         // we need this as in the end we want to traverse through increasing order of prefixes. Not possible via HashMap (hashed entries)
        for (int idx = 0; idx < A.length; idx ++)
            pre [idx + 1] = pre [idx] + A [idx];    // this is because our logic is to find ending with entries. so there are some entries ending with pre[0] = 0, which is when we have A [idx] == S.

        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int p : pre) {                         // pre [A.length] = this is the biggest sum we can make.
            ans += map.getOrDefault(p, 0);
            map.put(p + S, map.getOrDefault(p + S, 0) + 1);  // try too put items back. (P[j] = P[i] + S << count of occurrences of this condition happening.
        }
        return ans;
    }
}
