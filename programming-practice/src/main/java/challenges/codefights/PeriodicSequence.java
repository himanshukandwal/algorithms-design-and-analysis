package challenges.codefights;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Periodic Sequence
 *
 * A periodic sequence s is defined as follows:
 *
 *   s[0], a, b and m are all given positive integers;
 *   s[i] for i > 0 is equal to (a * s[i - 1] + b) mod m.
 *
 * Find the period of s, i.e. the smallest integer T such that for each i > k (for some integer k): s[i] = s[i + T].
 *
 * Example:
 *      For s0 = 11, a = 2, b = 6, and m = 12, the output should be periodicSequence(s0, a, b, m) = 2.
 *      The sequence would look like this: 11, 4, 2, 10, 2, 10, 2, 10, 2, 10....
 *
 *      For s0 = 1, a = 2, b = 3, and m = 5, the output should be periodicSequence(s0, a, b, m) = 4.
 *      The sequence would look like this: 1, 0, 3, 4, 1, 0, 3, 4, 1, 0, 3, 4....
 *
 * link: https://app.codesignal.com/skill-test/rYiNGN925SRvbTmec
 *
 * @author Hxkandwal
 */
public class PeriodicSequence extends AbstractCustomTestRunner {

    int periodicSequence(int s0, int a, int b, int m) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(s0, 0);
        for (int idx = 1, l = s0; idx < Integer.MAX_VALUE; idx ++) {
            l = (a * l + b) % m;
            if (map.containsKey(l)) return idx - map.get(l);
            map.put (l, idx);
        }
        return 0;
    }
}
