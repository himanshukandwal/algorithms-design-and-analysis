package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

/**
 * Grading Students
 *
 * link: https://www.hackerrank.com/challenges/grading/problem
 */
public class GradingStudents extends AbstractCustomTestRunner {

    public int[] _solve(int[] grades) {
        for (int idx = 0; idx < grades.length; idx ++) {
            if (grades [idx] < 38) continue;
            if (grades [idx] % 5 >= 3) grades [idx] += (5 - (grades [idx] % 5));
        }
        return grades;
    }
}
