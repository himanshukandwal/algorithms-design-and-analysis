package challenges.assorted;

import java.util.*;

public class LocateObstacle {

    /**
     * In this program we are visualizing the given LOT as a graph and traversing it in a breadth-first-search manner, to find the shortest path to the Obstacle.
     * We start from the root (top-left corner) of a graph and explore all of the neighboring nodes at the current depth prior to moving on to the nodes present
     * at the next depth level.
     *
     * We are using an iterative approach by using a QUEUE (First in first out) to maintain the order and depth of the traversal, and a SEEN set to check whether
     * a node has been discovered before enqueueing it. We also check other constraints that are given to us before enqueue operation such as the location should
     * be within the lot and should not be a trench (0).
     *
     * For the horizontal and vertical movements, we are employing two set of arrays (RDIR and CDIR), that help us to easily obtain the valid movement combinations
     * : [(1, 0), (-1, 0), (0, 1), (0, -1)]
     *
     * During this traversal if at any point we find that the dequeued node is the location of the obstacle (9), we return the depth at which it's discovered. The
     * depth from the root (starting point) is maintained by a variable DEPTH.
     *
     * Time complexity: O(numRows * numColumns)
     * Space complexity: O(numRows * numColumns)
     */
    public static int locateObstacle(int r, int c, List<List<Integer>> grid) {
        if (r == 0) return -1;
        int [] rdir = { 1, 0, -1, 0 };
        int [] cdir = { 0, 1, 0, -1 };

        Set<String> seen = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer (new int [] { 0, 0 });
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size -- > 0) {
                int row = queue.peek()[0], col = queue.poll()[1];
                if (grid.get(row).get(col) == 9) return level;
                for (int k = 0; k < 4; k ++) {
                    int rrow = row + rdir [k], ccol = col + cdir [k];
                    if (rrow >= 0 && rrow < grid.size() && ccol >= 0 && ccol < grid.get(0).size() && grid.get(rrow).get(ccol) != 0 && seen.add(rrow + "#" + ccol))
                        queue.offer(new int [] { rrow, ccol });
                }
            }
            level ++;
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(locateObstacle(3, 3 ,
                Arrays.asList(
                        Arrays.asList(1, 0, 0),
                        Arrays.asList(1, 0, 0),
                        Arrays.asList(1, 9, 1)
                )));

        System.out.println(locateObstacle(5, 4 ,
                Arrays.asList(
                        Arrays.asList(1, 1, 1, 1),
                        Arrays.asList(0, 1, 1, 1),
                        Arrays.asList(0, 1, 0, 1),
                        Arrays.asList(1, 1, 9, 1),
                        Arrays.asList(0, 0, 1, 1)

                )));
    }
}
