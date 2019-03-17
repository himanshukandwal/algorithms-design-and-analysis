package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 847. Shortest Path Visiting All Nodes
 *
 * An undirected, connected graph of N nodes (labeled 0, 1, 2, ..., N-1) is given as graph.
 * graph.length = N, and j != i is in the list graph[i] exactly once, if and only if nodes i and j are connected.
 *
 * Return the length of the shortest path that visits every node. You may start and stop at any node, you may revisit nodes multiple times, and you may reuse edges.
 *
 * Example 1:
 *              Input: [[1,2,3],[0],[0],[0]]
 *              Output: 4
 *              Explanation: One possible path is [1,0,2,0,3]
 *
 * Example 2:
 *              Input: [[1],[0,2,4],[1,3,4],[2],[1,2]]
 *              Output: 4
 *              Explanation: One possible path is [0,1,4,2,3]
 *
 * Note:
 *  1 <= graph.length <= 12
 *  0 <= graph[i].length < graph.length
 *
 * @author Hxkandwal
 */
public class ShortestPathVisitingAllNodes extends AbstractCustomTestRunner {

    public int shortestPathLength(int[][] graph) {
        int N = graph.length;
        int [][] dist = new int [1 << N][N];
        int finalMask = (1 << N) - 1;       // mask == state

        Queue<State> queue = new LinkedList<>();
        for (int idx = 0; idx < N; idx ++) queue.offer (new State(idx, 1 << idx));

        while (!queue.isEmpty()) {
            State s = queue.poll();
            if (s.mask == finalMask) return dist [s.mask][s.id]; // whoever reaches first gets the prize.

            for (int next : graph [s.id]) {
                int nextMask = s.mask | (1 << next);
                if (dist [nextMask][next] > 0) continue;

                dist [nextMask][next] = dist [s.mask][s.id] + 1; // answer to be used (distance)
                queue.offer (new State (next, nextMask));
            }
        }
        return -1;
    }

    class State {
        public int mask, id;        // mask = board/game information in an integer (bitset) and id = starting/standing from/at index. (should be unique once filled)
        public State (int id, int mask) {
            this.id = id;
            this.mask = mask;
        }
    }
}
