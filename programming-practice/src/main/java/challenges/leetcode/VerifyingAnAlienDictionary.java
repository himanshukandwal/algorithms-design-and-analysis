package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 953. Verifying an Alien Dictionary
 *
 * In an alien language, surprisingly they also use english lowercase letters, but possibly in a different order. The order of the alphabet is some permutation of
 * lowercase letters. Given a sequence of words written in the alien language, and the order of the alphabet, return true if and only if the given words are sorted
 * lexicographicaly in this alien language.
 *
 * Example 1:
 *      Input: words = ["hello","leetcode"], order = "hlabcdefgijkmnopqrstuvwxyz"
 *      Output: true
 *      Explanation: As 'h' comes before 'l' in this language, then the sequence is sorted.
 *
 * Example 2:
 *      Input: words = ["word","world","row"], order = "worldabcefghijkmnpqstuvxyz"
 *      Output: false
 *      Explanation: As 'd' comes after 'l' in this language, then words[0] > words[1], hence the sequence is unsorted.
 *
 * Example 3:
 *      Input: words = ["apple","app"], order = "abcdefghijklmnopqrstuvwxyz"
 *      Output: false
 *      Explanation: The first three characters "app" match, and the second string is shorter (in size.) According to lexicographical rules "apple" > "app", because 'l' > '∅',
 *                   where '∅' is defined as the blank character which is less than any other character (More info).
 *
 * Note:
 *  1 <= words.length <= 100
 *  1 <= words[i].length <= 20
 *  order.length == 26
 *  All characters in words[i] and order are english lowercase letters.
 *
 * @author Hxkandwal
 */
public class VerifyingAnAlienDictionary extends AbstractCustomTestRunner {

    public boolean _isAlienSorted(String[] words, String order) {
        int [] meta = new int [256];
        for (int idx = 0; idx < order.length(); idx ++) meta [order.charAt(idx)] = idx + 1;
        for (int idx = 0; idx < words.length; idx ++) {
            inner: for (int jdx = idx - 1; jdx >= 0; jdx --) {
                for (int i = 0; i < Math.min(words [idx].length(), words [jdx].length()); i ++) {
                    if (meta [words[idx].charAt(i)] < meta [words[jdx].charAt(i)]) return false;
                    else if (meta [words[idx].charAt(i)] > meta [words[jdx].charAt(i)]) continue inner;
                }
                if (words [idx].length() < words [jdx].length()) return false;
            }
        }
        return true;
    }

}
