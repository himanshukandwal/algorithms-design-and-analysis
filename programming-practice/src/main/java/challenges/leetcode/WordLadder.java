package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 127. Word Ladder
 * 
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence 
 * from beginWord to endWord, such that:
 * 	1.	Only one letter can be changed at a time.
 * 	2.	Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * 
 * For example,
 * 	Given: beginWord = "hit"  endWord = "cog"
 * 		   wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 *  As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 *  
 * Note:
 * 	- Return 0 if there is no such transformation sequence.
 * 	- All words have the same length.
 * 	- All words contain only lowercase alphabetic characters.
 * 	- You may assume no duplicates in the word list.
 * 	- You may assume beginWord and endWord are non-empty and are not the same.
 * 
 * @author Hxkandwal
 */
public class WordLadder extends AbstractCustomTestRunner {
	
	private static WordLadder _instance = new WordLadder();

	public int _ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<String> set = new HashSet<>();
        for (String word : wordList) set.add (word);
        if (!set.contains (endWord)) return 0;
        Queue <String> queue = new LinkedList<>();
        queue.offer (beginWord);
        
        int dist = 1;
        while (!queue.isEmpty()) {
            dist ++;
            int size = queue.size();
            while (size -- > 0) {
                String word = queue.poll ();
                for (int idx = 0; idx < word.length (); idx ++) {
                    for (char ch = 'a'; ch <= 'z'; ch ++) {
                        if (ch == word.charAt (idx)) continue;
                        String transformed = word.substring (0, idx) + ch + word.substring (idx + 1);
                        if (endWord.equals (transformed)) return dist;
                        if (set.contains (transformed)) { queue.offer (transformed); set.remove (transformed); }
                    }
                }
            }
        }
        return 0;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest("hit", "cog", Arrays.asList("hot","dot","dog","lot","log"), 0);
		_instance.runTest("hit", "cog", Arrays.asList("hot","dot","dog","lot","log","cog"), 5);
	}

	public void runTest(final String beginWord, final String endWord, final List<String> wordList, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { beginWord, endWord, wordList });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
}
