package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

/**
 * 760. Find Anagram Mappings
 *
 * Given two lists Aand B, and B is an anagram of A. B is an anagram of A means B is made by randomizing the order of the elements in A.
 * We want to find an index mapping P, from A to B. A mapping P[i] = j means the ith element in A appears in B at index j.
 * These lists A and B may contain duplicates. If there are multiple answers, output any of them.
 *
 * For example, given
 *
 *      A = [12, 28, 46, 32, 50]
 *      B = [50, 12, 32, 46, 28]
 *
 * We should return [1, 4, 3, 2, 0]
 * as P[0] = 1 because the 0th element of A appears at B[1], and P[1] = 4 because the 1st element of A appears at B[4], and so on.
 *
 * Note:
 *  A, B have equal lengths in range [1, 100].
 *  A[i], B[i] are integers in range [0, 10^5].
 *
 * @author Hxkandwal
 */
public class FindAnagramMappings extends AbstractCustomTestRunner {

    public int[] _anagramMappings(int[] A, int[] B) {
        // using sorted index array
        Integer [] aIdxs = new Integer [A.length];
        Integer [] bIdxs = new Integer [B.length];
        for (int idx = 0; idx < A.length; idx ++) aIdxs [idx] = idx;
        for (int idx = 0; idx < B.length; idx ++) bIdxs [idx] = idx;

        Arrays.sort(aIdxs, (a, b) -> A [a] - A [b]);
        Arrays.sort(bIdxs, (a, b) -> B [a] - B [b]);

        int [] ans = new int [A.length];
        for (int idx = 0; idx < ans.length; idx ++)
            ans [aIdxs [idx]] = bIdxs [idx];
        return ans;
    }

    /**
     * Note: map.computeIfAbsent() slows down.
     *  - with computeIfAbsent: 31ms
     *  - without computeIfAbsent: 1ms
     * */

    // using Map of value and location information (Queue)
    public int[] _anagramMappingsFaster(int[] A, int[] B) {
        Map<Integer, Queue<Integer>> map = new HashMap<>();
        for (int idx = 0; idx < B.length; idx ++) {
            if (!map.containsKey(B [idx])) map.put(B [idx], new LinkedList<>());
            map.get (B [idx]).add (idx);
        }

        int [] ans = new int [A.length];
        for (int idx = 0; idx < A.length; idx ++)
            ans [idx] = map.get (A [idx]).poll();
        return ans;
    }
}
