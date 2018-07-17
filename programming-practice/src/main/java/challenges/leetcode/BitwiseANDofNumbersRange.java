package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 201. Bitwise AND of Numbers Range
 *
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 *
 * Example 1:
 *      Input: [5,7]
 *      Output: 4
 *
 * Example 2:
 *      Input: [0,1]
 *      Output: 0
 *
 * @author hxkandwal
 */
public class BitwiseANDofNumbersRange extends AbstractCustomTestRunner {

    // concept is to find the same MSBs in both m and n
    // m = 5, n = 7
    //  101
    //  111
    //  ---
    //  100
    public int _rangeBitwiseAnd(int m, int n) {
        String ml = "", nl = "";
        while (m > 0) { ml = (m % 2) + ml;  m = m >> 1; }
        while (n > 0) { nl = (n % 2) + nl;  n = n >> 1; }

        if (ml.length() != nl.length()) return 0;
        int ans = 0;

        for (int idx = 0; idx < ml.length(); idx ++) {
            if (ml.charAt (idx) == nl.charAt(idx)) ans = ans | (ml.charAt(idx) - '0') << (ml.length() - idx - 1) ;
            else break;
        }
        return ans;
    }

    // similar concept, this also reduced m = 5, n = 7 first to 1 and then moves the m (or n, now same) to the nonMatching spaces to the left.
    // m = 101 ==>  10 ==>  1
    // n = 111 ==>  11 ==>  1
    // nm = 1  ==>  10 ==> 100
    public int rangeBitwiseAnd(int m, int n) {
        if (m == 0) return 0;
        int nonMatching = 1;
        while (m != n) {
            m = m >> 1;
            n = n >> 1;
            nonMatching = nonMatching << 1;
        }
        return m * nonMatching;
    }

}
