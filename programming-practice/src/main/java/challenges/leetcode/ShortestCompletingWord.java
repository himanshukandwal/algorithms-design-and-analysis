package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * 748. Shortest Completing Word
 *
 * Find the minimum length word from a given dictionary words, which has all the letters from the string licensePlate. Such a word is said to complete the given
 * string licensePlate.
 * Here, for letters we ignore case. For example, "P" on the licensePlate still matches "p" on the word.
 * It is guaranteed an answer exists. If there are multiple answers, return the one that occurs first in the array.
 *
 * The license plate might have the same letter occurring multiple times. For example, given a licensePlate of "PP", the word "pair" does not complete the licensePlate,
 * but the word "supper" does.
 *
 * Example 1:
 *  Input: licensePlate = "1s3 PSt", words = ["step", "steps", "stripe", "stepple"]
 *  Output: "steps"
 *  Explanation: The smallest length word that contains the letters "S", "P", "S", and "T".
 *               Note that the answer is not "step", because the letter "s" must occur in the word twice.
 *               Also note that we ignored case for the purposes of comparing whether a letter exists in the word.
 *
 * Example 2:
 *  Input: licensePlate = "1s3 456", words = ["looks", "pest", "stew", "show"]
 *  Output: "pest"
 *  Explanation: There are 3 smallest length words that contains the letters "s".
 *               We return the one that occurred first.
 *
 * Note:
 *  1. licensePlate will be a string with length in range [1, 7].
 *  2. licensePlate will contain digits, spaces, or letters (uppercase or lowercase).
 *  3. words will have a length in the range [10, 1000].
 *  4. Every words[i] will consist of lowercase letters, and have length in range [1, 15].
 *
 * @author Hxkandwal
 */
public class ShortestCompletingWord extends AbstractCustomTestRunner {

    public String _shortestCompletingWord(String licensePlate, String[] words) {
        Map<Character, Integer> map = new HashMap<>();
        String ans = "";

        fill (map, licensePlate);
        for (String w : words) {
            String buffer = "";
            for (char c : w.toLowerCase().toCharArray()) {
                if (map.containsKey(c)) {
                    buffer += c;
                    if (map.put (c, map.get(c) - 1) == 1) map.remove(c);
                }

                if (map.isEmpty() && (w.length() < ans.length() || ans.length() == 0)) ans = w;
            }
            fill (map, buffer);
        }
        return ans;
    }

    private void fill(Map<Character, Integer> map, String s) {
        for (char c : s.toLowerCase().toCharArray())
            if (c >= 'a' && c <= 'z') map.put (c, map.getOrDefault(c, 0) + 1);
    }

    public String _shortestCompletingWordOther(String licensePlate, String[] words) {
        int[] target = count(licensePlate);
        String ans = "";
        for (String word: words)
            if ((word.length() < ans.length() || ans.length() == 0) &&
                    dominates(count(word.toLowerCase()), target))
                ans = word;
        return ans;
    }

    public boolean dominates(int[] count1, int[] count2) {
        for (int i = 0; i < 26; ++i)
            if (count1[i] < count2[i])
                return false;
        return true;
    }

    public int[] count(String word) {
        int[] ans = new int[26];
        for (char letter: word.toCharArray()){
            int index = Character.toLowerCase(letter) - 'a';
            if (0 <= index && index < 26)
                ans[index]++;
        }
        return ans;
    }
}
