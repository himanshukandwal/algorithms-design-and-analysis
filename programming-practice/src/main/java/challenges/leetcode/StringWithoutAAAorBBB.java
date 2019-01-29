package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 984. String Without AAA or BBB
 *
 * Given two integers A and B, return any string S such that:
 *      S has length A + B and contains exactly A 'a' letters, and exactly B 'b' letters;
 *      The substring 'aaa' does not occur in S;
 *      The substring 'bbb' does not occur in S.
 *
 * Example 1:
 *      Input: A = 1, B = 2
 *      Output: "abb"
 *      Explanation: "abb", "bab" and "bba" are all correct answers.
 *
 * Example 2:
 *      Input: A = 4, B = 1
 *      Output: "aabaa"
 *
 * Note:
 *  0 <= A <= 100
 *  0 <= B <= 100
 *  It is guaranteed such an S exists for the given A and B.
 *
 * @author Hxkandwal
 */
public class StringWithoutAAAorBBB extends AbstractCustomTestRunner {

    public String strWithout3a3b(int A, int B) {
        StringBuilder ans = new StringBuilder();
        while (A + B > 0) {
            boolean a = false;
            int len = ans.length();

            if (len >= 2 && ans.charAt(len - 1) == ans.charAt(len - 2))
                a = ans.charAt(len - 1) == 'b';
            else
                a = A > B;

            if (a) { ans.append ('a'); A --; }
            else { ans.append ('b'); B --; }
        }
        return ans.toString();
    }

}
