package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

/**
 * Palindrome Index
 *
 * link: https://www.hackerrank.com/challenges/palindrome-index/problem
 */
public class PalindromeIndex extends AbstractCustomTestRunner {

    public int _palindromeIndex(String s) {
        if (checkPalindrome(s)) return -1;
        int ret = 0, i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt (i) != s.charAt (j)) return checkPalindrome(s.substring(0, j)) ? j : i;
            i ++; j --;
        }
        return ret;
    }

    public boolean checkPalindrome(String s) {
        int i = 0, j = s.length() - 1;
        while (i < j) {
            if (s.charAt (i) != s.charAt (j)) break;
            i ++; j --;
        }
        return i >= j;
    }

}
