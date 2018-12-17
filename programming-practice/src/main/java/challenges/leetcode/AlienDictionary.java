package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

/**
 * 269. Alien Dictionary
 *
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from
 * the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
 *
 * Example 1:
 *      Input:  [ "wrt", "wrf", "er", "ett", "rftt" ]
 *      Output: "wertf"
 *
 * Example 2:
 *      Input:  [ "z", "x" ]
 *      Output: "zx"
 *
 * Example 3:
 *      Input: [ "z", "x", "z" ]
 *      Output: ""
 *
 * Explanation: The order is invalid, so return "".
 *
 * Note:
 *  -   You may assume all letters are in lowercase.
 *  -   You may assume that if a is a prefix of b, then a must appear before b in the given dictionary.
 *  -   If the order is invalid, return an empty string.
 *  -   There may be multiple valid order of letters, return any one of them is fine.
 *
 * @author Hxkandwal
 */
public class AlienDictionary extends AbstractCustomTestRunner {

    private static AlienDictionary _instance = new AlienDictionary();

    class Node {
        char c;
        boolean seen;
        Set<Node> children = new HashSet<>();
        Node (char c) { this.c = c; }
    }
    // Note: Stitching within words does not make sense, only thing to note is across words. Example: [ab, ba], now ba this doesn't means a is smaller than b.
    // start comparing with index 0, then if the position at index 0 is same, the start comparing index 1 and so on.
    // topological order also doesn't make sense as, we want things to be weighted like : [wrt, wrf], now this makes chain as w -> r -> t, now this doesn't explains
    //                                                                                                                             |--> f
    // the occurrence of t before f. We can make it weighted by assigning more weight to t, but that possibly won't work in bigger inputs like how the weights should vary
    // and how the order should be computed later on. (how to assign equal weights).
    // Simple idea: use a map int [26] (lowercase letter), keep marking the char (idx) with increasing  value (starting with 1) as we encounter number. Read and insert in
    // StringBuilder answer.
    // [Initial commit is wrong but shows initial approach.]
    public String _alienOrder(String[] words) {
        Set<String> set = new HashSet<>();
        for (int idx = 0; idx < words.length; idx ++) {
            if (idx > 0 && words [idx - 1].equals (words [idx])) continue;
            if (set.contains(words [idx])) return "";
            set.add (words [idx]);
        }

        Map<Character, Node> map = new HashMap<>();
        for (int idx = 0; idx < words.length; idx ++) {
            if (idx > 0 && words [idx - 1].equals (words [idx])) continue;

            for (int jdx = 0; jdx < words [idx].length(); jdx ++) {
                char c = words [idx].charAt(jdx);
                map.putIfAbsent(c, new Node (c));
                Node nc = map.get (c);

                if (jdx == 0) { // stitching across words
                    if (idx > 0 && words [idx - 1].charAt(0) != words [idx].charAt(0)) {
                        if (nc.children.contains(words [idx - 1].charAt(0))) return "";  // cycle - break
                        map.get(words [idx - 1].charAt(0)).children.add (nc);
                    }
                } else { // stitching within words
                    if (words [idx].charAt(jdx - 1) != c) {
                        if (map.get(c).children.contains(words [idx].charAt(jdx - 1))) return "";  // cycle - break
                        map.get(words [idx].charAt(jdx - 1)).children.add (nc);
                    }
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for (Character c : map.keySet())
            if (!topologicalOrder (new HashSet<>(), map.get(c), ans)) return "";
        return ans.reverse().toString();
    }

    private boolean topologicalOrder(Set<Node> path, Node n, StringBuilder ans) {
        if (n == null || n.seen) return true;
        if (path.contains(n)) return false;  // cycle - break
        path.add(n);
        for (Node c : n.children) if (!topologicalOrder(path, c, ans)) return false;
        ans.append(n.c);
        return n.seen = true;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new String[] { "wrt", "wrf", "er", "ett", "rftt" }, "wertf");
    }

    public void runTest(final String[] words, final String expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { words });

        for (Object answer : answers)
            assertThat((String) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
