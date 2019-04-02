package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.LinkedList;
import java.util.List;

/**
 * 842. Split Array into Fibonacci Sequence
 *
 * Given a string S of digits, such as S = "123456579", we can split it into a Fibonacci-like sequence [123, 456, 579].
 * Formally, a Fibonacci-like sequence is a list F of non-negative integers such that:
 *  0 <= F[i] <= 2^31 - 1, (that is, each integer fits a 32-bit signed integer type);
 *  F.length >= 3;
 *  and F[i] + F[i+1] = F[i+2] for all 0 <= i < F.length - 2.
 *
 * Also, note that when splitting the string into pieces, each piece must not have extra leading zeroes, except if the piece is the number 0 itself.
 * Return any Fibonacci-like sequence split from S, or return [] if it cannot be done.
 *
 * Example 1:
 *              Input: "123456579"
 *              Output: [123,456,579]
 *
 * Example 2:
 *              Input: "11235813"
 *              Output: [1,1,2,3,5,8,13]
 *
 * Example 3:
 *              Input: "112358130"
 *              Output: []
 *              Explanation: The task is impossible.
 *
 * Example 4:
 *              Input: "0123"
 *              Output: []
 *              Explanation: Leading zeroes are not allowed, so "01", "2", "3" is not valid.
 *
 * Example 5:
 *              Input: "1101111"
 *              Output: [110, 1, 111]
 *              Explanation: The output [11, 0, 11, 11] would also be accepted.
 *
 * Note:
 *  1 <= S.length <= 200
 *  S contains only digits.
 *
 * @author Hxkandwal
 */
public class SplitArrayintoFibonacciSequence extends AbstractCustomTestRunner {

    public List<Integer> _splitIntoFibonacci(String s) {
        LinkedList<Integer> ans = new LinkedList<>();
        long a = 0;
        outer: for (int idx = 0; idx < s.length(); idx ++) {
            a = 10l * a + (s.charAt (idx) - '0');
            if (a > Integer.MAX_VALUE || (idx > 0 && s.charAt(0) == '0')) break;

            long b = 0;
            for (int jdx = idx + 1; jdx < s.length(); jdx ++) {
                b = 10l * b + (s.charAt (jdx) - '0');
                if (b > Integer.MAX_VALUE) break;

                if (dfs (ans, s, jdx + 1, a, b) && ans.size() >= 1) {
                    ans.addFirst ((int) b);
                    ans.addFirst ((int) a);
                    break outer;
                }
            }
        }
        return ans;
    }

    private boolean dfs (LinkedList<Integer> ans, String s, int index, long a, long b) {
        if (index == s.length()) return true;
        long c = a + b, num = 0;

        int idx = index;
        while (idx < s.length() && (c == 0 || num < c)) {
            num = 10l * num + (s.charAt(idx ++) - '0');
            if (idx > index && s.charAt(index) == '0') break;
        }
        if (num != c || num > Integer.MAX_VALUE) return false;

        if (dfs (ans, s, idx, b, c)) {
            ans.addFirst ((int) c);
            return true;
        }
        return false;
    }
}
