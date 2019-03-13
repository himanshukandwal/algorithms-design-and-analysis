package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

/**
 * 676. Implement Magic Dictionary
 *
 * Implement a magic directory with buildDict, and search methods.
 * For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
 *
 * For the method search, you'll be given a word, and judge whether if you modify exactly one character into another character in this word, the modified word is
 * in the dictionary you just built.
 *
 * Example 1:
 *    Input: buildDict(["hello", "leetcode"]), Output: Null
 *    Input: search("hello"), Output: False
 *    Input: search("hhllo"), Output: True
 *    Input: search("hell"), Output: False
 *    Input: search("leetcoded"), Output: False
 *
 * Note:
 *  You may assume that all the inputs are consist of lowercase letters a-z.
 *  For contest purpose, the test data is rather small by now. You could think about highly efficient algorithm after the contest.
 *  Please remember to RESET your class variables declared in class MagicDictionary, as static/class variables are persisted across multiple test cases. Please see here for more details.
 *
 * @author Hxkandwal
 */
public class ImplementMagicDictionary extends AbstractCustomTestRunner {

  Map<String, Set<String>> map;

  /** Initialize your data structure here. */
  public ImplementMagicDictionary() {
    map = new HashMap<>();
  }

  /** Build a dictionary through a list of words */
  public void buildDict(String[] dict) {
    for (String s : dict)
      for (int idx = 0; idx < s.length(); idx ++) {
        String key = s.substring(0, idx) + "*" + s.substring(idx + 1);
        map.computeIfAbsent(key, k -> new HashSet<>()).add(s);
      }
  }

  /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
  public boolean search(String word) {
    for (int idx = 0; idx < word.length(); idx ++) {
      String key = word.substring(0, idx) + "*" + word.substring(idx + 1);
      if (map.containsKey(key) && (!map.get (key).contains(word) || map.get (key).size() > 1)) return true;
    }
    return false;
  }

  // other solution
  class MagicDictionary {
    Map<Integer, ArrayList<String>> buckets;

    public MagicDictionary() {
      buckets = new HashMap<>();
    }

    public void buildDict(String[] words) {
      for (String word: words) {
        buckets.computeIfAbsent(word.length(), x -> new ArrayList<>()).add(word);
      }
    }

    public boolean search(String word) {
      if (!buckets.containsKey(word.length())) return false;
      for (String candidate: buckets.get(word.length())) {
        int mismatch = 0;
        for (int i = 0; i < word.length(); ++i) {
          if (word.charAt(i) != candidate.charAt(i)) {
            if (++mismatch > 1) break;
          }
        }
        if (mismatch == 1) return true;
      }
      return false;
    }
  }

  // driver method
  public static void main(String[] args) {
    ImplementMagicDictionary magicDictionary = new ImplementMagicDictionary();
    magicDictionary.buildDict(new String[] { "hello", "hallo", "leetcode" });

    System.out.println(magicDictionary.search("hello"));
    System.out.println(magicDictionary.search("hhllo"));
  }
}
