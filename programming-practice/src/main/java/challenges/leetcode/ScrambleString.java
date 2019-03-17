package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 87. Scramble String
 *
 * Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.
 * Below is one possible representation of s1 = "great":
 *
 *                                  great
 *                                 /    \
 *                               gr    eat
 *                              / \    /  \
 *                             g   r  e   at
 *                                        / \
 *                                       a   t
 *
 * To scramble the string, we may choose any non-leaf node and swap its two children.
 * For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".
 *
 *                                  rgeat
 *                                 /    \
 *                               rg    eat
 *                              / \    /  \
 *                             r   g  e   at
 *                                        / \
 *                                       a   t
 *
 * We say that "rgeat" is a scrambled string of "great".
 * Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".
 *
 *                                  rgtae
 *                                 /    \
 *                               rg    tae
 *                              / \    /  \
 *                             r   g  ta  e
 *                                   / \
 *                                  t   a
 *
 * We say that "rgtae" is a scrambled string of "great".
 * Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.
 *
 * Example 1:
 *              Input: s1 = "great", s2 = "rgeat"
 *              Output: true
 *
 * Example 2:
 *              Input: s1 = "abcde", s2 = "caebd"
 *              Output: false
 *
 * @author Hxkandwal
 */
public class ScrambleString extends AbstractCustomTestRunner {

    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        if (s1.length() != s2.length()) return false;
        int [] arr = new int [256];
        for (char c : s1.toCharArray()) arr [c] ++;
        for (char c : s2.toCharArray()) arr [c] --;
        for (int idx : arr) if (idx != 0) return false;

        for (int idx = 1; idx < s1.length(); idx ++) {
            if (
                    (isScramble (s1.substring(0, idx), s2.substring(0, idx)) &&
                            isScramble (s1.substring(idx), s2.substring(idx)))
                            ||
                            (isScramble (s1.substring(0, idx), s2.substring(s2.length() - idx)) &&
                                    isScramble (s1.substring(idx), s2.substring(0, s2.length() - idx)))
            ) return true;
        }
        return false;
    }
}
