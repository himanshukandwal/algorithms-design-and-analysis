package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 737. Sentence Similarity II
 *
 * Given two sentences words1, words2 (each represented as an array of strings), and a list of similar word pairs pairs, determine if two sentences are similar.
 * For example,
 *              words1 = ["great", "acting", "skills"] and
 *              words2 = ["fine", "drama", "talent"] are similar,
 *              if the similar word pairs are pairs = [["great", "good"], ["fine", "good"], ["acting","drama"], ["skills","talent"]].
 *
 * Note that the similarity relation is transitive. For example, if "great" and "good" are similar, and "fine" and "good" are similar, then "great" and "fine" are similar.
 * Similarity is also symmetric. For example, "great" and "fine" being similar is the same as "fine" and "great" being similar.
 *
 * Also, a word is always similar with itself. For example, the sentences words1 = ["great"], words2 = ["great"], pairs = [] are similar, even though there are no specified
 * similar word pairs.
 *
 * Finally, sentences can only be similar if they have the same number of words. So a sentence like words1 = ["great"] can never be similar to words2 = ["doubleplus","good"].
 *
 * Note:
 *  The length of words1 and words2 will not exceed 1000.
 *  The length of pairs will not exceed 2000.
 *  The length of each pairs[i] will be 2.
 *  The length of each words[i] and pairs[i][j] will be in the range [1, 20].
 *
 * @author Hxkandwal
 */
public class SentenceSimilarityII extends AbstractCustomTestRunner {

    public boolean _areSentencesSimilarTwo(String[] words1, String[] words2, String[][] pairs) {
        if (words1 == null || words2 == null || words1.length != words2.length) return false;

        Map<String, Set<String>> map = new HashMap<>();
        for (String[] p : pairs) {
            map.computeIfAbsent(p [0], k -> new HashSet<>()).add (p [1]);
            map.computeIfAbsent(p [1], k -> new HashSet<>()).add (p [0]);
        }

        // floyd warshall
        for (String k : map.keySet()) {
            for (String a : map.get (k)) {
                for (String b : map.get (k)) {
                    map.get (a).add(b);
                    map.get (b).add(a);
                }
            }
        }

        for (int idx = 0; idx < words1.length; idx ++) {
            String a = words1 [idx], b = words2 [idx];
            if (!(a.equals(b) || map.containsKey(a) && map.get(a).contains(b))) return false;
        }
        return true;
    }

}
