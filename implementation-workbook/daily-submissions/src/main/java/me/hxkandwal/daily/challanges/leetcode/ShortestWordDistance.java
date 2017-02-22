package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
    
	// more clean approach (always find difference and keep updating the index pointer with occurences, no late processing, then and there itself)
	public int shortestDistance(String[] words, String word1, String word2) {
	    int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;
	    
	    for (int i = 0; i < words.length; i++) {
	        if (words[i].equals(word1)) 
	            p1 = i;

	        if (words[i].equals(word2)) 
	            p2 = i;
	            
	        if (p1 != -1 && p2 != -1)
	            min = Math.min(min, Math.abs(p1 - p2));
	    }
	    
	    return min;
	}
	
    public int _shortestDistance(String[] words, String word1, String word2) {
    	int distance = Integer.MAX_VALUE;
        
        Map <String, List<Integer>> map = new HashMap<>();
        for (int idx = 0; idx < words.length; idx ++) {
            map.putIfAbsent (words [idx], new ArrayList<>());
            map.get(words [idx]).add(idx);
        }
        
        for (Integer a : map.get (word1))
            for (Integer b : map.get (word2))
                distance = Math.min (distance, Math.abs (a - b));
        return distance;
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
