package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

/**
 * Palindrome Index
 *
 * link: https://www.hackerrank.com/challenges/palindrome-index/problem
 */
public class PalindromeIndex extends AbstractCustomTestRunner {

    public int _palindromeIndex(String s) {
        for (int idx = 0; idx < s.length(); idx ++) if (checkPalindrome(s, idx)) return idx;
        return -1;
    }

    public boolean checkPalindrome(String s, int idx) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (i == idx) i ++;
            if (j == idx) j --;
            if (s.charAt (i) != s.charAt (j)) break;
            i ++; j --;
        }
        return i >= j;
    }

}
