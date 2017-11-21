package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;

/**
 * 648. Replace Words
 *
 * In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor.
 * For example, the root an, followed by other, which can form another word another.
 *
 * Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it.
 * If a successor has many roots can form it, replace it with the root with the shortest length.
 *
 * You need to output the sentence after the replacement.
 *
 * Example 1:
 *
 *      Input: dict = ["cat", "bat", "rat"]
 *      sentence = "the cattle was rattled by the battery"
 *
 *      Output: "the cat was rat by the bat"
 *
 * Note:
 *  1) The input will only have lower-case letters.
 *  2) 1 <= dict words number <= 1000
 *  3) 1 <= sentence words number <= 1000
 *  4) 1 <= root length <= 100
 *  5) 1 <= sentence words length <= 1000
 */
public class ReplaceWords extends AbstractCustomTestRunner {

    class Trie {
        private char ch;
        private boolean terminal;
        private Trie [] children = new Trie [256];

        public Trie (char ch) { this.ch = ch; }

        public void add (String word) {
            Trie t = this;
            for (char c : word.toCharArray()) {
                if (t.children [c] == null) t.children [c] = new Trie (c);
                t = t.children [c];
            }
            t.terminal = true;
        }

        public String search (String word) {
            String ret = null;
            Trie t = this;
            for (int idx = 0; idx < word.length(); idx ++) {
                char c = word.charAt(idx);
                if (t.children [c] == null) break;
                t = t.children [c];
                if (t.terminal) { ret = word.substring (0, idx + 1); break; }
            }
            return ret;
        }
    }

    public String replaceWords(List<String> dict, String sentence) {
        Trie root = new Trie (' ');
        for (String word : dict) root.add (word);
        StringBuilder ans = new StringBuilder ();
        for (String str : sentence.split ("\\s+")) {
            String wroot = root.search (str);
            ans.append (wroot == null ? str : wroot).append (" ");
        }
        return ans.toString().trim();
    }

}
