package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.*;

/**
 * 815. Bus Routes
 *
 * We have a list of bus routes. Each routes[i] is a bus route that the i-th bus repeats forever. For example if routes[0] = [1, 5, 7], this means that the first
 * bus (0-th indexed) travels in the sequence 1->5->7->1->5->7->1->... forever.
 *
 * We start at bus stop S (initially not on a bus), and we want to go to bus stop T. Travelling by buses only, what is the least number of buses we must take to reach
 * our destination? Return -1 if it is not possible.
 *
 * Example:
 *          Input:  routes = [[1, 2, 7], [3, 6, 7]]
 *                  S = 1
 *                  T = 6
 *          Output: 2
 *          Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.
 *
 * Note:
 *  1 <= routes.length <= 500.
 *  1 <= routes[i].length <= 500.
 *  0 <= routes[i][j] < 10 ^ 6.
 *
 * @author Hxkandwal
 */
public class BusRoutes extends AbstractCustomTestRunner {

    public int _numBusesToDestination(int[][] routes, int S, int T) {
        if (S == T) return 0;
        Map<Integer, List<Integer>> stopBusMap = new HashMap<>();
        for (int idx = 0; idx < routes.length; idx ++) {
            for (int stop : routes [idx]) {
                if (!stopBusMap.containsKey(stop)) stopBusMap.put(stop, new ArrayList<>());
                stopBusMap.get(stop).add (idx); // add busId with the stop.
            }
        }

        // one bus route (shuttle-circle) is one layer.
        // BFS: checking that do we need to hop between on shuttle-circle to another.
        //      all same distance shuttle/circle are considered on equal distance.
        //      here, node of BFS is actually a shuttle-circle.
        int distance = 0;
        Set<Integer> seenBusIds = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer (S);
        while (!queue.isEmpty()) {
            distance ++;
            int size = queue.size();
            // System.out.println(queue);
            while (size -- > 0) {
                int stop = queue.poll();

                for (Integer busId : stopBusMap.get(stop)) {
                    if (seenBusIds.contains(busId)) continue;
                    seenBusIds.add (busId);

                    for (int nextStop: routes [busId]) {
                        if (nextStop == T) return distance;
                        // check is this stop has been added by seenBusIds before (conjoin)
                        queue.offer (nextStop);
                    }
                }
            }

        }
        return -1;
    }

}
