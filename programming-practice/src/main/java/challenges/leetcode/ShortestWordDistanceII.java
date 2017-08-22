package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 244. Shortest Word Distance II
 * 
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your 
 * method will be called repeatedly many times with different parameters. How would you optimize it?
 * 
 * Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 
 * and return the shortest distance between these two words in the list.
 * 
 * For example,
 * 		Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * 		Given word1 = “coding”, word2 = “practice”, return 3.
 * 		Given word1 = "makes", word2 = "coding", return 1.
 * 
 * Note: You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 * 
 * @author Hxkandwal
 */
public class ShortestWordDistanceII extends AbstractCustomTestRunner {

	private Map<String, List<Integer>> map = new HashMap<>();

	public ShortestWordDistanceII (String[] words) {
		for (int idx = 0; idx < words.length; idx ++) map.computeIfAbsent (words [idx], k -> new ArrayList<>()).add (idx);
	}

	public int shortest(String word1, String word2) {
		List<Integer> l1 = map.get (word1), l2 = map.get (word2);
		int d = Integer.MAX_VALUE, idx1 = 0, idx2 = 0;
		while (idx1 < l1.size() && idx2 < l2.size()) {
			d = Math.min (d, Math.abs (l1.get (idx1) - l2.get (idx2)));
			if (l1.get (idx1) > l2.get (idx2)) idx2 ++; else idx1 ++;
		}
		return d;
	}

}
