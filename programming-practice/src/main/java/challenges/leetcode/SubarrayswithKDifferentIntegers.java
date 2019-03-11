package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 992. Subarrays with K Different Integers
 *
 * Given an array A of positive integers, call a (contiguous, not necessarily distinct) subarray of A good if the number of different integers in that subarray is exactly K.
 * (For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.)
 *
 * Return the number of good subarrays of A.
 *
 * Example 1:
 *      Input: A = [1,2,1,2,3], K = 2
 *      Output: 7
 *      Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2].
 *
 * Example 2:
 *      Input: A = [1,2,1,3,4], K = 3
 *      Output: 3
 *      Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
 *
 * Note:
 *  1 <= A.length <= 20000
 *  1 <= A[i] <= A.length
 *  1 <= K <= A.length
 *
 * @author Hxkandwal
 */
public class SubarrayswithKDifferentIntegers extends AbstractCustomTestRunner {

    public int _subarraysWithKDistinct(int[] A, int K) {
        return subarraysWithAtMostKDistinct(A, K) - subarraysWithAtMostKDistinct(A, K - 1);
    }

    public int subarraysWithAtMostKDistinct(int[] A, int K) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int end = 0, start = 0; end < A.length; end ++) {
            int val = A [end];
            map.put (val, map.getOrDefault(val, 0) + 1);
            while (map.size() > K) {
                int sVal = A [start ++];
                if (map.put(sVal, map.get(sVal) - 1) == 1) map.remove(sVal);
            }
            ans += end - start + 1; // every new end at location n, will add (n) sub-arrays. [1, 2] .. now 3, will add 3 subarrays { [1, 2, 3], [2, 3], [3] }
                                    // This is also reason why we have n*(n+1)/2 sub-arrays (ways to chose 2 end points): 1 + 2 + 3 + 4 .... + n.
        }
        return ans;
    }

}
