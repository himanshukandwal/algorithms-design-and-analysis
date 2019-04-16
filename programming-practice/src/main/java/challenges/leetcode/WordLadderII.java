package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

import static com.google.common.truth.Truth.assertThat;

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

    private static WordLadderII _instance = new WordLadderII();

    // https://efficientcodeblog.wordpress.com/2017/12/13/bidirectional-search-two-end-bfs/
    // https://www.youtube.com/watch?v=CHvQ3q_gJ7E
    // https://stackoverflow.com/questions/10995699/how-do-you-use-a-bidirectional-bfs-to-find-the-shortest-path
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> ans = new ArrayList<>();
        Set<String> words = new HashSet<>(), set1 = new HashSet<>(), set2 = new HashSet<>();
        for (String word : wordList) words.add (word);

        if (!words.contains (endWord)) return ans;

        set1.add (beginWord); set2.add (endWord);
        List<String> build = new ArrayList<>();
        build.add (beginWord);
        Map<String, List<String>> map = new HashMap<>();

        bfs (ans, map, set1, set2, words, true);
        dfs (ans, build, map, beginWord, endWord);
        return ans;
    }

    private void bfs (List<List<String>> ans, Map<String, List<String>> map, Set<String> set1, Set<String> set2, Set<String> words, boolean isForward) {
        if (set1.isEmpty ()) return;
        if (set1.size () > set2.size()) { bfs (ans, map, set2, set1, words, !isForward); return; }
        words.removeAll (set1);
        words.removeAll (set2);

        Set<String> next = new HashSet<>();
        boolean merged = false;
        for (String s : set1) {
            for (int idx = 0; idx < s.length (); idx ++) {
                for (char c = 'a'; c <= 'z'; c ++) {
                    String word = s.substring (0, idx) + c + s.substring (idx + 1);
                    String key = (isForward) ? s : word;
                    String value = (isForward) ? word : s;
                    if (set2.contains (word)) {
                        merged = true;
                        map.computeIfAbsent (key, k -> new ArrayList<>()).add (value);
                    } else if (words.contains (word)) {
                        next.add (word);
                        map.computeIfAbsent (key, k -> new ArrayList<>()).add (value);
                    }
                }
            }
        }
        if (!merged) bfs (ans, map, set2, next, words, !isForward);
    }

    private void dfs (List<List<String>> ans, List<String> build, Map<String, List<String>> map, String start, String end) {
        if (start.equals (end)) {
            ans.add (new ArrayList<> (build));
            return;
        }
        if (!map.containsKey (start)) return;
        for (String child : map.get (start)) {
            build.add (child);
            dfs (ans, build, map, child, end);
            build.remove (build.size () - 1);
        }
    }

    // Note down the shortest path, and use that information while DFS path construction.
    public List<List<String>> _findLaddersBetter(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) return Arrays.asList();
        List<List<String>> ans = new ArrayList<>();

        set.add (beginWord);
        Queue<String> queue = new LinkedList<String>();
        Map<String, List<String>> graph = new HashMap<>();
        Map<String, Integer> shortestDistance = new HashMap<>();

        for (String w : set) graph.put (w, new ArrayList<>());
        shortestDistance.put (beginWord, 0);
        queue.offer (beginWord);

        while (!queue.isEmpty()) {
            boolean found = false;
            int size = queue.size();
            while (size -- > 0) {
                String w = queue.poll();
                for (int idx = 0; idx < w.length(); idx ++) {
                    for (char c = 'a'; c <= 'z'; c ++) {
                        if (c == w.charAt(idx)) continue;
                        String next = w.substring(0, idx) + c + w.substring(idx + 1);

                        if (set.contains(next)) {
                            graph.get(w).add (next);
                            if (!shortestDistance.containsKey(next)) {
                                if (next.equals(endWord)) found = true;
                                shortestDistance.put(next, shortestDistance.get(w) + 1);
                                queue.offer (next);
                            }
                        }
                    }
                }
            }

            if (found) break;
        }

        dfs (ans, new ArrayList<>(), shortestDistance, graph, beginWord, endWord);
        return ans;
    }

    private void dfs (List<List<String>> ans, List<String> build, Map<String, Integer> shortestDistance, Map<String, List<String>> graph, String current, String endWord) {
        build.add (current);
        if (current.equals (endWord)) ans.add (new ArrayList<>(build));
        else {
            for (String n : graph.get (current)) {
                if (shortestDistance.get(n) == shortestDistance.get (current) + 1)
                    dfs (ans, build, shortestDistance, graph, n, endWord);
            }
        }
        build.remove (build.size() - 1);
    }

    // driver method
    public static void main(String[] args) {
        Set<String> dict = new HashSet<>();
        dict.addAll(Arrays.asList("hot","dot","dog","lot","log","cog"));
        _instance.runTest("hit", "cog", dict, null);
    }

    public void runTest(final String start, String end, Set<String> dict, final List<List<String>> expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { start, end, dict });

        for (Object answer : answers)
            assertThat((List<List<String>>) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
