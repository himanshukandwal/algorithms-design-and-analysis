package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 874. Walking Robot Simulation
 *
 * A robot on an infinite grid starts at point (0, 0) and faces north.  The robot can receive one of three possible types of commands:
 *
 *     -2: turn left 90 degrees
 *     -1: turn right 90 degrees
 *     1 <= x <= 9: move forward x units
 *
 * Some of the grid squares are obstacles. The i-th obstacle is at grid point (obstacles[i][0], obstacles[i][1])
 * If the robot would try to move onto them, the robot stays on the previous grid square instead (but still continues following the rest of the route.)
 *
 * Return the square of the maximum Euclidean distance that the robot will be from the origin.
 *
 * Example 1:
 *      Input: commands = [4,-1,3], obstacles = []
 *      Output: 25
 *      Explanation: robot will go to (3, 4)
 *
 * Example 2:
 *      Input: commands = [4,-1,4,-2,4], obstacles = [[2,4]]
 *      Output: 65
 *      Explanation: robot will be stuck at (1, 4) before turning left and going to (1, 8)
 *
 * Note:
 *   0 <= commands.length <= 10000
 *   0 <= obstacles.length <= 10000
 *   -30000 <= obstacle[i][0] <= 30000
 *   -30000 <= obstacle[i][1] <= 30000
 *   The answer is guaranteed to be less than 2 ^ 31.
 *
 * @author hxkandwal
 */
public class WalkingRobotSimulation extends AbstractCustomTestRunner {

    public int robotSim(int[] commands, int[][] obstacles) {
        int result = 0;

        Map<String, boolean[]> map = new HashMap<>();
        map.put("lr", new boolean [] { false, false, true, false }); // -1
        map.put("ll", new boolean [] { false, false, false, true }); // -2
        map.put("rr", new boolean [] { false, false, false, true }); // -1
        map.put("rl", new boolean [] { false, false, true, false }); // -2

        map.put("ur", new boolean [] { false, true, false, false }); // -1
        map.put("ul", new boolean [] { true, false, false, false }); // -2
        map.put("dr", new boolean [] { true, false, false, false }); // -1
        map.put("dl", new boolean [] { false, true, false, false }); // -2

        Set<String> set = new HashSet<>();
        for (int[] o : obstacles) set.add(o [0] + "," + o [1]);

        int x = 0, y = 0;
        boolean l = false, r = false, u = true, d = false;
        for (int c : commands) {
            if (c > 0) {
                for (int idx = 0; idx < c; idx ++) {
                    int nx = x + (l ? -1 : (r ? 1 : 0));
                    int ny = y + (d ? -1 : (u ? 1 : 0));
                    if (!set.contains(nx + "," + ny)) {
                        x = nx;
                        y = ny;

                        result = Math.max(result, x * x + y * y);
                    } else break;
                }
            } else {
                String dir = (c == -1) ? "r" : "l";
                boolean[] v = null;
                if (l) v = map.get ("l" + dir);
                if (r) v = map.get ("r" + dir);
                if (u) v = map.get ("u" + dir);
                if (d) v = map.get ("d" + dir);

                l = v [0]; r = v [1]; u = v [2]; d = v [3];
            }
        }
        return result;
    }
}
