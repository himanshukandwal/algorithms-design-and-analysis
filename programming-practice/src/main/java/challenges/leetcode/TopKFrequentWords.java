package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

/**
 * 692. Top K Frequent Words
 *
 * Given a non-empty list of words, return the k most frequent elements.
 * Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 *              Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 *              Output: ["i", "love"]
 *              Explanation:  "i" and "love" are the two most frequent words.
 *                            Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 *              Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 *              Output: ["the", "is", "sunny", "day"]
 *              Explanation:  "the", "is", "sunny" and "day" are the four most frequent words,
 *                            with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * Note:
 *  You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 *  Input words contain only lowercase letters.
 *
 * Follow up: Try to solve it in O(n log k) time and O(n) extra space.
 *
 * @author Hxkandwal
 */
public class TopKFrequentWords extends AbstractCustomTestRunner {

    public List<String> _topKFrequentNaive(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word: words)  map.put (word, map.getOrDefault(word, 0) + 1);

        PriorityQueue<String> queue = new PriorityQueue<>(
                (a, b) -> map.get(b) == map.get(a) ? a.compareTo(b) : map.get(b) - map.get(a)
        );
        for (String word : map.keySet()) queue.offer (word);

        List<String> ans = new ArrayList<>();
        while (k -- > 0) ans.add (queue.poll ());
        return ans;
    }

    public List<String> _topKFrequentBetter(String[] words, int k) {
        Map<String, Integer> map = new HashMap<>();
        for (String word: words)  map.put (word, map.getOrDefault(word, 0) + 1);

        PriorityQueue<String> queue = new PriorityQueue<>((a, b) -> map.get(a) == map.get(b) ? b.compareTo(a) : map.get(a) - map.get(b));
        for (String word : map.keySet()) {
            queue.offer (word);
            if (queue.size() > k) queue.poll();
        }

        LinkedList<String> ans = new LinkedList<>();
        while (!queue.isEmpty()) ans.addFirst (queue.poll ());
        return ans;
    }
}
