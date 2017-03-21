package me.hxkandwal.daily.challanges.hackerrank;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.google.common.truth.Truth.assertThat;

/**
 *
 * Mandragora Forest
 *
 * The evil forest is guarded by N vicious mandragoras. Each ith mandragora has H (0 .. N) health points.
 *
 * Garnet and her pet begin their journey through the evil forest with S = 1 strength points and P = 0 experience points.
 * For each undefeated mandragora i, she can perform either of the following actions:
 *
 *  1. Garnet's pet eats mandragora i. This increments S by 1 and defeats mandragora i.
 *  2. Garnet's pet battles mandragora i. This increases P by S X H experience points and defeats mandragora i.
 *
 * Each mandragora can only be defeated once, and Garnet can defeat the mandragoras in any order. Given the respective health points for
 * each mandragora, can you find the maximum number of experience points she can earn from defeating all N mandragoras?
 *
 * Example :
 *          3 2 2
 * Output : 10
 *
 *      There are N = 3 mandragoras having the following health points: H [3, 2, 2].
 *      Initially, S = 1 and P = 0.
 *      The following is an optimal sequence of actions for achieving the maximum number of experience points possible:
 *
 *          1. Eat the second mandragora (H1 = 2). S is increased from 1 to 2, and P is still 0.
 *          2. Battle the first mandragora (H0 = 3). S remains the same, but P increases by S X H0 = 2 * 3 = 6 experience points.
 *          3. Battle the third mandragora (H2 = 2). S remains the same, but P increases by S X H2 = 2 * 2 = 4  experience points.
 *
 *      Garnet earns P = 6 + 4 = 10 experience points, so we print 10 on a new line.
 *
 * Created by Hxkandwal
 *
 */
public class MandragoraForest extends AbstractCustomTestRunner {

    private static MandragoraForest _instance = new MandragoraForest();

    private MandragoraForest() {}

    // how to make intelligent solution for selecting the point and choosing action on it. need O(n) solution.
    public static long _defeatMandragoras(int[] healthPoints) {
        long sum = 0;
        for (int i = 0; i < healthPoints.length; i++) sum += healthPoints[i];

        Arrays.sort(healthPoints);

        long S = 1, P = sum, var = 0;

        // eat one by one and calculate the experience from the fight with the rest
        for (int i = 0; i < healthPoints.length; i ++) {
            S++;
            // eaten - update the sum and new exp
            sum -= healthPoints [i];
            var = sum*S;
            // save the biggest solution
            if (var > P) P = var;
        }

        return P;
    }

    // driver method
    public static void main(String[] args) throws FileNotFoundException {
        _instance.runTest(new int[] { 3, 2, 2 }, 10);

        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MandragoraForest-Big-1.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MandragoraForest-Big-2.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MandragoraForest-Big-3.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MandragoraForest-Big-4.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MandragoraForest-Big-5.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MandragoraForest-Big-6.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MandragoraForest-Big-7.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MandragoraForest-Big-8.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MandragoraForest-Big-9.txt");
        testComplex("/src/test/resources/me/hxkandwal/daily/challanges/hackerrank/MandragoraForest-Big-10.txt");
    }

    private static void testComplex(String filename) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(System.getProperty("user.dir") + filename));

        int[] input = new int[sc.nextInt()];
        for (int idx = 0; idx < input.length; idx ++)
            input [idx] = sc.nextInt();

        _instance.runTest(input, sc.nextLong());

        sc.close();
    }

    public void runTest(final int[] healthPoints, final long expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { healthPoints });

        for (Object answer : answers)
            assertThat((Long) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
