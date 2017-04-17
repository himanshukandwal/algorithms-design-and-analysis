package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

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

	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<> ();
        for (String word : wordList) set.add (word);
        Queue <String> queue = new LinkedList();
        queue.offer (beginWord);
        
        int layer = 1;
        boolean found = false;
        while (!queue.isEmpty() && !found) {
            int size = queue.size();
            while (size -- > 0 ) {
                String word = queue.poll ();
                for (int idx = 0; idx < word.length(); idx ++) {
                    for (int iidx = 0; iidx < 26; iidx ++) {
                        char ch = ((char) ('a' + iidx));
                        if (word.charAt (idx) != ch) {
                            String transformed = word.substring (0, idx) + ch + word.substring (idx + 1);        
                            if (transformed.equals (endWord)) return layer + 1;
                            if (set.contains (transformed)) queue.offer (transformed);
                        }
                    }
                }
            }
            layer ++;
        }
        return 0;
    }
	

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 2, 1 }, 1, true);
	}

	public void runTest(final int[] nums, final int target, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, target });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
}
