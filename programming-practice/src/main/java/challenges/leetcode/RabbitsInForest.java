package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

/**
 * 781. Rabbits in Forest
 *
 * In a forest, each rabbit has some color. Some subset of rabbits (possibly all of them) tell you how many other rabbits have the same color as them.
 * Those answers are placed in an array.
 *
 * Return the minimum number of rabbits that could be in the forest.
 *
 * Examples:
 *      Input: answers = [1, 1, 2]
 *      Output: 5
 *      Explanation: The two rabbits that answered "1" could both be the same color, say red.
 *                   The rabbit than answered "2" can't be red or the answers would be inconsistent.
 *                   Say the rabbit that answered "2" was blue.
 *                   Then there should be 2 other blue rabbits in the forest that didn't answer into the array.
 *                   The smallest possible number of rabbits in the forest is therefore 5: 3 that answered plus 2 that didn't.
 *
 *      Input: answers = [10, 10, 10]
 *      Output: 11
 *
 *      Input: answers = []
 *      Output: 0
 *
 * Note:
 *  Answers will have length at most 1000.
 *  Each answers[i] will be an integer in the range [0, 999].
 *
 * @author Hxkandwal
 */
public class RabbitsInForest extends AbstractCustomTestRunner {

    private static RabbitsInForest _instance = new RabbitsInForest();

    public int _numRabbits(int[] answers) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : answers) {
            if (a == 0) ans ++;
            else {
                map.put (a, map.getOrDefault(a, 0) + 1);
                if (map.get (a) == 1 || map.get(a) > (a + 1)) {
                    ans += (a + 1);
                    map.put (a, 1);
                }
            }
        }
        return ans;
    }

    /**
     * If x+1 rabbits have same color, then we get x+1 rabbits who all answer x.
     * now n rabbits answer x.
     * If n % (x + 1) == 0, we need n / (x + 1) groups of x + 1 rabbits.
     * If n % (x + 1) != 0, we need n / (x + 1) + 1 groups of x + 1 rabbits.
     *
     * the number of groups is math.ceil(n / (x + 1))
     */
    public int _numRabbitsOther(int[] answers) {
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int a : answers) map.put(a, map.getOrDefault(a, 0) + 1);
        for (int k : map.keySet())
            ans += Math.ceil(map.get(k) * 1.0/(k + 1)) * (k + 1);
        return ans;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(new int [] { 1, 1, 2 }, 5);
    }

    public void runTest(final int[] A, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { A });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
