package challenges.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

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

	Map<String, List<Integer>> cache = new HashMap<>();

	public ShortestWordDistanceII(String[] words) {
		for (int idx = 0; idx < words.length; idx++) {
			cache.putIfAbsent(words[idx], new LinkedList<>());
			cache.get(words[idx]).add(idx);
		}
	}

	public int shortest(String word1, String word2) {
		int p1 = 0, p2 = 0, distance = Integer.MAX_VALUE;
		List<Integer> l1 = cache.get(word1);
		List<Integer> l2 = cache.get(word2);

		while (p1 < l1.size() && p2 < l2.size()) {
			distance = Math.min(distance, Math.abs(l1.get(p1) - l2.get(p2)));
			if (l1.get(p1) < l2.get(p2))
				p1++;
			else
				p2++;
		}
		return distance;
	}

}
