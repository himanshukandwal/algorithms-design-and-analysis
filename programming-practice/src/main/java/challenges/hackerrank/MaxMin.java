package challenges.hackerrank;

import challenges.AbstractCustomTestRunner;

import java.util.Arrays;

/**
 * Max Min
 *
 * Given a list of N integers, your task is to select K integers from the list such that its unfairness is minimized.
 *
 * link: https://www.hackerrank.com/challenges/angry-children
 *
 * Created by Hxkandwal
 */
public class MaxMin extends AbstractCustomTestRunner {

    public int _findUnfaireness (int N, int K, int [] list) {
        int unfairness = Integer.MAX_VALUE;
        Arrays.sort (list);
        for (int itr = 0; itr <= N - K; itr ++)
            unfairness = Math.min (unfairness, list [itr + K - 1] - list [itr]);
        return unfairness;
    }

}
