package challenges.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 126. Word Ladder II
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from 
 * beginWord to endWord, such that:
 * 
 * 	1. Only one letter can be changed at a time
 * 	2. Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * 
 * For example,
 * Given:
 * 		beginWord = "hit"
 * 		endWord = "cog"
 * 		wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * Return
 * 		[
 * 			["hit","hot","dot","dog","cog"],
 * 			["hit","hot","lot","log","cog"]
 * 		]
 * 
 * Note:
 * 	-	Return an empty list if there is no such transformation sequence.
 * 	-	All words have the same length.
 * 	-	All words contain only lowercase alphabetic characters.
 * 	-	You may assume no duplicates in the word list.
 * 	-	You may assume beginWord and endWord are non-empty and are not the same.
 * 
 * UPDATE (2017/1/20):
 * 	The wordList parameter had been changed to a list of strings (instead of a set of strings). Please reload the code definition to 
 * 	get the latest changes.
 *  
 * @author Hxkandwal
 */
public class WordLadderII extends AbstractCustomTestRunner {

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<List<String>>();
        Set<String> set = new HashSet<>();
        for (String w : wordList) set.add (w);
        List<String> build = new ArrayList<> ();
        build.add (beginWord);
        dfs (ans, build, set, endWord, beginWord);
        return ans;
    }
    
    private void dfs (List<List<String>> ans, List<String> build, Set<String> set, String endWord, String word) {
        if (word.equals (endWord)) {
            if (ans.size () == 0 || ans.get (0).size() >= build.size ()) {
                if (ans.size () > 0 && ans.get (0).size() > build.size ()) ans.clear ();
                ans.add (new ArrayList<>(build));
            }
        } else if (set.size () > 0) {
            for (int idx = 0; idx < word.length (); idx ++) {
                for (char ch = 'a'; ch <= 'z'; ch ++) {
                    if (word.charAt (idx) != ch) {
                        String next = word.substring (0, idx) + ch + word.substring (idx + 1);
                        if (set.contains (next)) {
                            build.add (next);
                            set.remove (next);
                            dfs (ans, build, set, endWord, next);
                            set.add (next);
                            build.remove (build.size () - 1);
                        }
                    }
                }
            }   
        }
    }
}
