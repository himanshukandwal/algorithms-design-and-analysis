package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 245. Shortest Word Distance III
 * 
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.
 * 
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * 
 * word1 and word2 may be the same and they represent two individual words in the list.
 * 
 * For example,
 * 		Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * 
 * Given word1 = “makes”, word2 = “coding”, return 1.
 * Given word1 = "makes", word2 = "makes", return 3.
 * 
 * Note: You may assume word1 and word2 are both in the list.
 * 
 * @author Hxkandwal
 */
public class ShortestWordDistanceIII extends AbstractCustomTestRunner {

	// increment in hopping fashion when same, else usual process.
	public int shortestWordDistance(String[] words, String word1, String word2) {
        int p1 = -1, p2 = -1, distance = Integer.MAX_VALUE;
        boolean areSame = word1.equals (word2);
        
        for (int idx = 0; idx < words.length; idx ++) {
            String word = words [idx];
            if (word.equals (word1)) {
                if (areSame) { 
                    int t = p2; p2 = idx; p1 = t;
                } else p1 = idx;
            } 
            if (word.equals (word2) && !areSame) p2 = idx;
            
            if (p1 != -1 && p2 != -1)
                distance = Math.min (distance, Math.abs (p1 - p2));
        }
        return distance;
    }
	
}
