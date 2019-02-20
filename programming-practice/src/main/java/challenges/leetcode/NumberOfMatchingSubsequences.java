package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;

/**
 * 792. Number of Matching Subsequences
 *
 * Given string S and a dictionary of words words, find the number of words[i] that is a subsequence of S.
 *
 * Example :
 *      Input:  S = "abcde"
 *              words = ["a", "bb", "acd", "ace"]
 *      Output: 3
 *      Explanation: There are three words in words that are a subsequence of S: "a", "acd", "ace".
 *
 * Note:
 *  All words in words and S will only consists of lowercase letters.
 *  The length of S will be in the range of [1, 50000].
 *  The length of words will be in the range of [1, 5000].
 *  The length of words[i] will be in the range of [1, 50].
 *
 * @author Hxkandwal
 */
public class NumberOfMatchingSubsequences extends AbstractCustomTestRunner {

    public int _numMatchingSubseq(String S, String[] words) {
        return (int) Arrays.stream(words).filter(T -> {
            int tIdx = 0;
            for (int sIdx = 0; sIdx < S.length() && tIdx < T.length(); sIdx ++)
                if (S.charAt(sIdx) == T.charAt(tIdx)) tIdx ++;
            return tIdx == T.length();
        }).count();
    }

}
