package challenges.interviewbit;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;

/**
 * Gas Station
 *
 * There are N gas stations along a circular route, where the amount of gas at station i is gas[i].
 *
 * You have a car with an unlimited gas tank and it costs cost[i] of gas to travel from station i to its next station (i+1). You begin the journey with an empty tank at
 * one of the gas stations.
 *
 * Return the minimum starting gas station’s index if you can travel around the circuit once, otherwise return -1.
 *
 * You can only travel in one direction. i to i+1, i+2, ... n-1, 0, 1, 2..
 * Completing the circuit means starting at i and ending up at i again.
 *
 * Example :
 *      Input :  Gas :   [1, 2]
 *               Cost :  [2, 1]
 *      Output : 1
 *      If you start from index 0, you can fill in gas[0] = 1 amount of gas. Now your tank has 1 unit of gas. But you need cost[0] = 2 gas to travel to station 1.
 *      If you start from index 1, you can fill in gas[1] = 2 amount of gas. Now your tank has 2 units of gas. You need cost[1] = 1 gas to get to station 0. So, you travel to
 *      station 0 and still have 1 unit of gas left over. You fill in gas[0] = 1 unit of additional gas, making your current gas = 2. It costs you cost[0] = 2 to get to station
 *      1, which you do and complete the circuit.
 *
 * link: https://www.interviewbit.com/problems/gas-station/
 *
 * @author Hxkandwal
 */
public class GasStation extends AbstractCustomTestRunner {

    private static GasStation _instance = new GasStation();

    public int _canCompleteCircuit(final List<Integer> A, final List<Integer> B) {
        int len = A.size();
        outer: for (int idx = 0; idx < len; idx ++) {
            int j = idx, curr = A.get(j) - B.get(j);
            if (curr >= 0) {
                j ++;
                while (j % len != idx) {
                    curr += (A.get(j % len) - B.get(j % len));
                    if (curr < 0) continue outer;
                    j ++;
                }
                return idx;
            }
        }
        return -1;
    }

    // driver method
    public static void main(String[] args) {
        _instance.runTest(
                Arrays.asList(237, 573, 278, 782, 819, 548, 14, 670, 81, 178, 448, 889, 200, 541, 582, 111, 718, 236, 292, 850, 536, 654, 230, 551, 917, 788, 531, 26, 206, 960, 381, 183, 207, 44, 141, 252, 74, 890, 857, 834, 396, 569, 597, 538, 370, 876, 481, 307, 643, 138, 652, 676, 340, 590, 565, 857, 584, 275, 703, 508, 388, 454, 944, 670),
                Arrays.asList(316, 803, 374, 899, 361, 548, 430, 366, 137, 993, 751, 9, 52, 774, 449, 533, 870, 271, 829, 232, 529, 9, 167, 738, 779, 89, 515, 67, 649, 831, 508, 397, 914, 236, 134, 914, 129, 77, 372, 921, 527, 588, 249, 275, 23, 793, 321, 264, 30, 360, 29, 27, 96, 150, 746, 338, 921, 846, 719, 193, 565, 236, 92, 453),
                37);
    }

    public void runTest(final List<Integer> a, final List<Integer> b, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { a, b });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }
}
