package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 358. Rearrange String k Distance Apart
 *
 * Given a non-empty string s and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 *
 * Example 1:
 *              Input: s = "aabbcc", k = 3
 *              Output: "abcabc"
 *              Explanation: The same letters are at least distance 3 from each other.
 *
 * Example 2:
 *              Input: s = "aaabc", k = 3
 *              Output: ""
 *              Explanation: It is not possible to rearrange the string.
 *
 * Example 3:
 *              Input: s = "aaadbbcc", k = 2
 *              Output: "abacabcd"
 *              Explanation: The same letters are at least distance 2 from each other.
 *
 * @author Hxkandwal
 */
public class RearrangeStringKDistanceApart extends AbstractCustomTestRunner {

    // Note: Next version of Task Scheduler. (Task Scheduler II)
    public String _rearrangeString(String s, int k) {
        StringBuilder ans = new StringBuilder();
        int [][] arr = new int [26][2];     // frequency + last seen pair
        for (int idx = 0; idx < 26; idx ++) arr [idx][1] = -1; // last seen at -1 index.
        for (char c : s.toCharArray()) arr [c - 'a'][0] ++;

        int len = s.length();
        for (int idx = 0; idx < len; idx ++) {
            int maxIdx = -1;
            for (int j = 0; j < 26; j ++) {
                if (arr [j][0] > 0 &&
                        (maxIdx < 0 || arr [maxIdx][0] < arr [j][0]) &&
                        (arr [j][1] < 0 || (idx - arr [j][1] >= k))) {
                    maxIdx = j;
                }
            }

            if (maxIdx < 0) return "";
            ans.append ((char) ('a' + maxIdx));
            arr [maxIdx][0] --;
            arr [maxIdx][1] = idx;
        }
        return ans.length() != len ? "" : ans.toString();
    }
}
