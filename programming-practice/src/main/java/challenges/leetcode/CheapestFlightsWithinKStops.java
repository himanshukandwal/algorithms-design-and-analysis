package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

/**
 * 787. Cheapest Flights Within K Stops
 *
 * There are n cities connected by m flights. Each fight starts from city u and arrives at v with a price w. Now given all the cities and flights, together with
 * starting city src and the destination dst, your task is to find the cheapest price from src to dst with up to k stops. If there is no such route, output -1.
 *
 * Example 1:
 *      Input: n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 1
 *      Output: 200
 *      Explanation: The cheapest price from city 0 to city 2 with at most 1 stop costs 200, as marked red in the picture.
 *
 * Example 2:
 *      Input: n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]], src = 0, dst = 2, k = 0
 *      Output: 500
 *      Explanation: The cheapest price from city 0 to city 2 with at most 0 stop costs 500, as marked blue in the picture.
 *
 * Note:
 *  1. The number of nodes n will be in range [1, 100], with nodes labeled from 0 to n - 1.
 *  2. The size of flights will be in range [0, n * (n - 1) / 2].
 *  3. The format of each flight will be (src, dst, price).
 *  4. The price of each flight will be in the range [1, 10000].
 *  5.  k is in the range of [0, n - 1].
 *  6. There will not be any duplicated flights or self cycles.
 *
 * @author Hxkandwal
 */
public class CheapestFlightsWithinKStops extends AbstractCustomTestRunner {

    private int minPrice = Integer.MAX_VALUE;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> g = new HashMap<>();
        for (int [] e : flights) g.computeIfAbsent(e [0], t -> new HashMap<>()).put(e [1], e [2]);

        Set<Integer> seen = new HashSet<>();
        seen.add (src);
        dfs (g, seen, src, dst, K, 0);
        return minPrice == Integer.MAX_VALUE ? -1 : minPrice;
    }

    private void dfs (Map<Integer, Map<Integer, Integer>> graph, Set<Integer> seen, int s, int dst, int k, int price) {
        if (k < 0 || !graph.containsKey(s) || price > minPrice) return;
        if (graph.get(s).containsKey(dst)) minPrice = Math.min(minPrice, price + graph.get(s).get(dst));
        for (Map.Entry<Integer, Integer> e : graph.get(s).entrySet())  {
            if (!seen.contains(e.getKey())) {
                seen.add (e.getKey());
                dfs (graph, seen, e.getKey(), dst, k - 1, price + e.getValue());
                seen.remove (e.getKey());
            }
        }
    }

    // using Dijkstra's Algorithm
    public int findCheapestPriceDijkstra(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int [] f : flights) graph.computeIfAbsent(f [0], t -> new HashMap<>()).put(f [1], f [2]);

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a [0] - b [0]);
        pq.offer (new int [] { 0, src, K + 1 });
        while (!pq.isEmpty()) {
            int [] v = pq.poll();
            if (v [1] == dst) return v [0];
            if (v [2] > 0 && graph.containsKey(v [1])) {
                for (Map.Entry<Integer, Integer> e : graph.get(v [1]).entrySet())
                    pq.offer (new int [] { v [0] + graph.get(v [1]).get(e.getKey()), e.getKey(), v [2] - 1 });
            }
        }
        return -1;
    }

    // Using Bellman ford (Not exactly as we don't keep on reset the cost array every time to INF in actual bellman ford, we keep working on it per iteration)
    // This is more like BFS, as we try to reach to next layer and try to get to dst in smallest number possible steps, (within K)
    //  itr:  Those which are 1 step away from src will be filled in first iteration, then
    //  itr: those which are 2 step away from src will be filled in second iteration (also the distance of src will be INF initially)
    //  itr: those which are k step away from src will be filled in second iteration (also the distance of src will be INF initially)
    // now if in between record the minimum distance for dst during these k runs. It will reset to INF in every iteration.
    public int findCheapestPriceBellmanFord(int n, int[][] flights, int src, int dst, int K) {
        int [] cost = new int [n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        cost [src] = 0;
        int ans = Integer.MAX_VALUE;
        while (K -- >= 0) {
            int [] curr = new int [n];
            Arrays.fill(curr, Integer.MAX_VALUE);
            for (int [] f : flights) {
                if (cost [f [0]] != Integer.MAX_VALUE)
                    curr [f [1]] = Math.min(curr [f [1]], cost [f [0]] + f [2]);
            }
            cost = curr;
            ans = Math.min(ans, cost [dst]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
