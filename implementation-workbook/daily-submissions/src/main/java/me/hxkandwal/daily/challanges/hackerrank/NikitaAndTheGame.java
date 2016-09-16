package me.hxkandwal.daily.challanges.hackerrank;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import static com.google.common.truth.Truth.assertThat;

/**
 * Nikita and the Game
 *
 * Nikita just came up with a new array game. The rules are as follows:
 *
 * a) Initially, there is an array, A, containing  integers.
 * b) In each move, Nikita must partition the array into 2 non-empty parts such that the sum of the elements in the left
 *    partition is equal to the sum of the elements in the right partition. If Nikita can make such a move, she gets 1 point;
 *    otherwise, the game ends.
 * c) After each successful move, Nikita discards either the left partition or the right partition and continues playing by
 *    using the remaining partition as array .
 *
 * Nikita loves this game and wants your help getting the best score possible. Given A, can you find and print the maximum number
 * of points she can score?
 *
 * Example :
 *
 * a)       3 3 3
 *    Output : 0
 *
 * b)       2 2 2 2
 *    Output : 2
 *
 * c)       4 1 0 1 1 0 1
 *    Output : 3
 *
 * Created by Hxkandwal
 *
 */
public class NikitaAndTheGame extends AbstractCustomTestRunner {

    private static NikitaAndTheGame _instance = new NikitaAndTheGame();

    private NikitaAndTheGame() {}

    public static int _score(int[] input) {
        return computeRecursively(input, 0, input.length - 1);
    }

    // a recursive method that will call itself calculating all the possibilities (going both ways) and maximizing the score.
    private static int computeRecursively(int[] input, int start, int end) {

        // base condition
        if (start >= end)
            return 0;

        int total = 0, runningTotal = 0, score = 0;

        for (int idx = start; idx <= end; idx ++)
            total += input[idx];

        for (int idx = start; idx <= end; idx ++) {
            runningTotal += input[idx];

            if (runningTotal == total - runningTotal) {
                // computing all cases
                score = Math.max(computeRecursively(input, start, idx), computeRecursively(input, idx + 1, end));

                // increment score one more time for current finding.
                score ++;
                break;
            }
        }

        return score;
    }

    // driver method
    public static void main(String[] args) throws FileNotFoundException  {
        _instance.runTest(new int[] { 3, 3, 3 }, 0);
        _instance.runTest(new int[] { 2, 2, 2, 2 }, 2);
        _instance.runTest(new int[] { 4, 1, 0, 1, 1, 0, 1 }, 3);

        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/NikitaAndTheGame-Big-1.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/NikitaAndTheGame-Big-2.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/NikitaAndTheGame-Big-3.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/NikitaAndTheGame-Big-4.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/NikitaAndTheGame-Big-5.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/NikitaAndTheGame-Big-6.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/NikitaAndTheGame-Big-7.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/NikitaAndTheGame-Big-8.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/NikitaAndTheGame-Big-9.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/NikitaAndTheGame-Big-10.txt");

    }

    private static void testComplex(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + filename));

        int[] input = new int[sc.nextInt()];
        for (int idx = 0; idx < input.length; idx ++)
            input [idx] = sc.nextInt();

        _instance.runTest(input, sc.nextInt());

        sc.close();
    }

    public void runTest(final int[] input, final int expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { input });

        for (Object answer : answers)
            assertThat((Integer) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
