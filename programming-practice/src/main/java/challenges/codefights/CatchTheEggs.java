package challenges.codefights;

import challenges.AbstractCustomTestRunner;

/**
 * Catch the Eggs
 *
 * Approach: proceed backwards through the eggs array, keeping track of the max number of the eggs that could be gathered from each position starting
 * at that step. To handle conveyor length, we add steps at the end with no eggs being laid. At the end, the max number of eggs that could be gathered
 * from the first position is the answer.
 *
 * Example:
 * If the conveyor belt is 3 long, and eggs is [[2,5], [2,5], [5], [2,5], [2,5], [4]], we start with a max of one egg in position 4:
 *  [4]:
 *  [0, 0, 0, 1, 0]
 *
 * On the next step, each position gets the maximum from itself and the two surrounding spaces on the previous step (because that's how far the
 * catcher would be able to reach in the next turn). Then we increment for the eggs laid in that step, because the catcher would be able to get that
 * one too:
 *  [2,5]:
 *  [0, 0, 0, 1, 0]
 *       \ | /     (e.g. position 3 takes the max of 2, 3, and 4 in the prior step)
 *  [0, 0, 1, 1, 1] --> [0, 1, 1, 1, 2]
 *
 * Succeeding turns:
 *  [2,5]:
 *
 *  [0, 1, 1, 1, 2]
 *  [1, 1, 1, 2, 2] --> [1, 2, 1, 2, 3]
 *
 *  [5]:
 *  [1, 2, 1, 2, 3]
 *  [2, 2, 2, 3, 3] --> [2, 2, 2, 3, 4]
 *
 *  [2,5]:
 *  [2, 2, 2, 3, 4]
 *  [2, 2, 3, 4, 4] --> [2, 3, 3, 4, 5]
 *
 *  [2,5]:
 *  [2, 3, 3, 4, 5]
 *  [3, 3, 4, 5, 5] --> [3, 4, 4, 5, 6]
 *
 * At the end, since our conveyor belt is 3 units long, we have 3 turns to reach the maximum possible number of eggs. So we take the max of the surrounding
 * spaces 3 times (we just don't add any new eggs these turns):
 *
 * conveyor 1:
 * [3, 4, 4, 5, 6]
 * [4, 4, 5, 6, 6]
 *
 * conveyor 2:
 * [4, 4, 5, 6, 6]
 * [4, 5, 6, 6, 6]
 *
 * conveyor 3:
 * [4, 5, 6, 6, 6]
 * [5, 6, 6, 6, 6]
 *
 * So, starting from the first position, the max eggs you could catch is 5.
 *
 * The conveyor length determines which chickens it is possible to begin at. If you could catch 10 eggs by starting at chicken 5 but only 9 eggs by starting at
 * chicken 1; a conveyor length of 1 means you can only start at chicken 1 or 2 - so your answer will be 9. If you had a conveyor length of 4, you could reach
 * chicken 5 before the eggs start falling, so you could catch all 10 eggs.
 *
 * https://codefights.com/challenge/7scRyRXmG5R8PigCY/solutions/PtsNqJENWoJs7Jpdy
 *
 * @author hxkandwal
 */
public class CatchTheEggs extends AbstractCustomTestRunner {

    int catchTheEggs(int numberOfChickens, int conveyorLength, int[][] eggs) {
        int s[] = new int[numberOfChickens + 2], t[], m = 0, i = conveyorLength + 2; // boundary for 0 and s.length

        for (; i < numberOfChickens + 2;)  s[i++] -= 1e4;

        for (int[] egg : eggs) {

            for (int e : egg) s[e]++;

            t = new int[numberOfChickens + 2];

            for (i = 1; i < numberOfChickens + 1; i++)
                m = Math.max(m, t[i] = Math.max(Math.max (s[i], s[i - 1]), s[i + 1]));

            s = t;
        }

        return m;
    }

}
