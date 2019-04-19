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
        int[] arr = new int [26];
        for (int idx = 0; idx < order.length(); idx ++)
            arr [order.charAt(idx) - 'a'] = idx + 1;

        for (int idx = 1; idx < words.length; idx ++) {
            String a = words [idx - 1], b = words [idx];
            int ai = 0, bi = 0;
            while (ai < a.length() && bi < b.length()) {
                if (a.charAt(ai) == b.charAt(bi)) {
                    ai ++; bi ++;
                }
                else if (arr [a.charAt(ai) - 'a'] > arr [b.charAt(bi) - 'a'])
                    return false;
                else break;
            }
            if (ai < a.length() && bi == b.length()) return false;
        }
        return true;
    }

}
