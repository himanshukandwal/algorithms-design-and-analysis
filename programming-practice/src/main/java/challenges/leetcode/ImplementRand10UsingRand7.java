package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.Random;

/**
 * 470. Implement Rand10() Using Rand7()
 *
 * Given a function rand7 which generates a uniform random integer in the range 1 to 7, write a function rand10 which generates a uniform random integer in the range 1 to 10.
 *
 * Do NOT use system's Math.random().
 *
 * Example 1:
 *      Input: 1
 *      Output: [7]
 *
 * Example 2:
 *      Input: 2
 *      Output: [8,4]
 *
 * Example 3:
 *      Input: 3
 *      Output: [8,1,10]
 *
 * Note:
 *  - rand7 is predefined.
 *  - Each testcase has one argument: n, the number of times that rand10 is called.
 *
 * Follow up:
 *  - What is the expected value for the number of calls to rand7() function?
 *  - Could you minimize the number of calls to rand7()?
 *
 * @author hxkandwal
 */
public class ImplementRand10UsingRand7 extends AbstractCustomTestRunner {

    public int _rand10() {
        int r, c, idx;
        do {
            r = rand7();
            c = rand7();
            idx = c + (r - 1) * 7; //meaning that we have seen (r - 1) times completly the 7's and on row r, we only seen c elements
        } while (idx > 40); // this is because 7 * 7 = 49, and the last 9 we cannot distribute evently (rand 10), so the asterisk suggest that we cannot use the element 41-19. (not evenly distributed from rand 7)
        return 1 + (idx - 1) % 10; // as the result should not be 0 based (modulus 10)
    }

    public int rand7() {
        return 1 + new Random().nextInt(7);
    }
}
