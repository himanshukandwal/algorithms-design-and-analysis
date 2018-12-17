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
        char val;
        boolean seen;
        List<Node> children = new ArrayList<>();

        public Node (char val) { this.val = val; }
    }

    // Note: Stitching within words does not make sense, only thing to note is across words. Example: [ab, ba], now ba this doesn't means a is smaller than b.
    // start comparing with index 0, then if the position at index 0 is same, the start comparing index 1 and so on.
    // [Initial commit is wrong but shows initial approach.]
    public String _alienOrder(String[] words) {
        Map<Character, Node> map = new HashMap<>();
        for (char c : words [0].toCharArray()) map.putIfAbsent (c, new Node (c));

        for (int idx = 1; idx < words.length; idx ++) {
            String wx = words [idx - 1], wy = words [idx];

            for (char c : wx.toCharArray()) map.putIfAbsent (c, new Node (c));
            for (char c : wy.toCharArray()) map.putIfAbsent (c, new Node (c));

            int length = Math.min(wx.length(), wy.length());
            for (int k = 0; k < length; k ++) {
                if (wx.charAt(k) != wy.charAt(k)) {
                    map.get(wx.charAt(k)).children.add(map.get (wy.charAt(k)));
                    break;
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        for (Character k : map.keySet()) if (!topologicalOrder(new HashSet<>(), map.get (k), ans)) return "";
        return ans.reverse().toString();
    }

    private boolean topologicalOrder (Set<Node> path, Node n, StringBuilder ans) {
        if (n == null || n.seen) return true;
        if (path.contains (n)) return false;
        path.add (n);
        for (Node c : n.children) if (!topologicalOrder(path, c, ans)) return false;
        ans.append (n.val);
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
