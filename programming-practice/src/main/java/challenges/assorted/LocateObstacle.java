package challenges.assorted;

import java.util.*;

public class LocateObstacle {

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
