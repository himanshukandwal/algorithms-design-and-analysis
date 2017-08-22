package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 243. Shortest Word Distance
 * 
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * 
 * For example,
 * 		Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 * 
 * @author Hxkandwal
 */
public class ShortestWordDistance extends AbstractCustomTestRunner {
	
	private static ShortestWordDistance _instance = new ShortestWordDistance();

	public int shortestDistance(String[] w, String w1, String w2) {
        int d = w.length;
        String prev = null;
        for (int idx = 0, start = -1; idx < w.length; idx ++) {
            if (w [idx].equals (w1) || w [idx].equals (w2)) {
                if (prev != null && !prev.equals (w [idx])) d = Math.min (d, idx - start);
                start = idx;
                prev = w [idx];
            }
        }
        return d;
	}

   	// driver method
   	public static void main(String[] args) {
		_instance.runTest(new String [] { "practice", "makes", "perfect", "coding", "makes" }, "coding", "practice", 3);
   	}

   	public void runTest(final String[] words, final String word1, final String word2, final int expectedOutput) {
   		List<Object> answers = runAll(getClass(), new Object[] { words, word1, word2 });

   		for (Object answer : answers)
   				assertThat((Integer) answer).isEqualTo(expectedOutput);

   		System.out.println("ok!");
   	}
    
}
