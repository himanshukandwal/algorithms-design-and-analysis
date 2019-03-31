package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Stack;

/**
 * 758. Bold Words in String
 *
 * Given a set of keywords words and a string S, make all appearances of all keywords in S bold. Any letters between <b> and </b> tags become bold.
 * The returned string should use the least number of tags possible, and of course the tags should form a valid combination.
 *
 * For example, given that words = ["ab", "bc"] and S = "aabcd", we should return "a<b>abc</b>d". Note that returning "a<b>a<b>b</b>c</b>d" would use more tags,
 * so it is incorrect.
 *
 * Note:
 *  words has length in range [0, 50].
 *  words[i] has length in range [1, 10].
 *  S has length in range [0, 500].
 *  All characters in words[i] and S are lowercase letters.
 *
 * @author Hxkandwal
 */
public class BoldWordsInString extends AbstractCustomTestRunner {

    class Trie {
        public char ch;
        public Trie[] children = new Trie [256];
        public boolean terminal;

        public Trie(char ch) { this.ch = ch; }

        public void add(String w) {
            Trie t = this;
            for (char c : w.toCharArray()) {
                if (t.children [c] == null) t.children [c] = new Trie(c);
                t = t.children [c];
            }
            t.terminal = true;
        }

        public int find (String w, int start) {
            Trie t = this;
            int ans = -1;
            for (int idx = start; idx < w.length(); idx ++) {
                if (t.children [w.charAt (idx)] == null) break;
                t = t.children [w.charAt (idx)];
                if (t.terminal) ans = idx;
            }
            return ans;
        }
    }

    public String _boldWords(String[] words, String S) {
        Stack<int[]> stack = new Stack<>();
        Trie r = new Trie('_');
        for (String w : words) r.add (w);

        int lastEnd = -1;
        for (int idx = 0; idx < S.length(); idx ++) {
            int eIdx = r.find (S, idx);
            if (eIdx < 0) continue;
            if (eIdx > lastEnd) {
                if (lastEnd >= 0 && lastEnd >= idx - 1) stack.peek() [1] = eIdx;
                else stack.push (new int [] { idx, eIdx });
                lastEnd = eIdx;
            }
        }

        StringBuilder ans = new StringBuilder (S);
        while (!stack.isEmpty()) {
            int s = stack.peek() [0], e = stack.pop() [1];
            ans.insert(e + 1, "</b>");
            ans.insert(s, "<b>");
        }
        return ans.toString();
    }

}
