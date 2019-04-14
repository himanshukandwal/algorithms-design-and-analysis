package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 884. Uncommon Words from Two Sentences
 *
 * We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 *
 * Return a list of all uncommon words. You may return the list in any order.
 *
 * Example 1:
 *              Input: A = "this apple is sweet", B = "this apple is sour"
 *              Output: ["sweet","sour"]
 *
 * Example 2:
 *              Input: A = "apple apple", B = "banana"
 *              Output: ["banana"]
 *
 * Note:
 *  0 <= A.length <= 200
 *  0 <= B.length <= 200
 *  A and B both contain only spaces and lowercase letters.
 *
 * @author Hxkandwal
 */
public class UncommonWordsFromTwoSentences extends AbstractCustomTestRunner {

    public String[] _uncommonFromSentences(String A, String B) {
        Map<String, Integer> map = new HashMap<>();
        for (String w : A.split(" ")) map.put(w, map.getOrDefault(w, 0) + 1);    // regex does slows down. " " -> 3ms, "\\s+" -> 7ms
        for (String w : B.split(" ")) map.put(w, map.getOrDefault(w, 0) + 1);
        List<String> ans = new ArrayList<>();
        for (String k : map.keySet()) if (map.get(k) == 1) ans.add (k);
        return ans.toArray(new String [0]);
    }
}
