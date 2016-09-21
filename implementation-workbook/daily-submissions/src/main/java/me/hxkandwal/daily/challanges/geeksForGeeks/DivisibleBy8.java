package me.hxkandwal.daily.challanges.geeksForGeeks;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;

import static com.google.common.truth.Truth.assertThat;

/**
 * Divisible by 8
 *
 * Given a number, you need to check whether any permutation of the number is divisible by 8 or not.
 * Print Yes or No.
 *
 * link : http://www.practice.geeksforgeeks.org/problem-page.php?pid=1326
 *
 * Created by Hxkandwal
 *
 */
public class DivisibleBy8 extends AbstractCustomTestRunner {

    private static DivisibleBy8 _instance = new DivisibleBy8();

    private DivisibleBy8() {}

    // method : find nCk combinations, where k = 3 (as to be divisible by 8, last 3 digits have to be divisible by 8)
    //          and check if it is divisble by 8 or not.
    public static boolean _isDivisible(int number) {
        if (number < 100)
            return (number % 8 == 0) ? true :
                    (((number % 10) * 10 + (number / 10)) % 8 == 0 ? true : false);
        else {
            int [] input = new int[String.valueOf(number).length()];
            int idx = 0;

            while (number / 10 != 0 || number % 10 != 0) {
                input[idx ++] = number % 10;
                number /= 10;
            }

            boolean [] arr = new boolean[input.length];
            return combineAndCheck(input, arr, input.length - 1, 3);
        }
    }

    // implementation of A, n, k
    private static boolean combineAndCheck(int[] input, boolean[] arr, int n, int k) {
        if (k > n)
            return false;

        if (k == 0) {
            int[] numArr = new int[3];
            int innerIdx = 0;

            // selection made, now permute
            for (int idx = 0; idx < input.length; idx ++)
                if (arr [idx])
                    numArr [innerIdx ++] = input [idx];
            System.out.println(numArr[0] + " " + numArr[1] + " " + numArr[2]);
            return permuteAndCheck(numArr, 2);

        } else {
            if (combineAndCheck(input, arr, n - 1, k))
                return true;

            arr [n] = true;

            if (combineAndCheck(input, arr, n - 1, k - 1))
                return true;

            arr [n] = false;
        }

        return false;
    }

    private static boolean permuteAndCheck(int[] numArr, int i) {
        if (i == 0) {
            System.out.println((numArr[0] * 100 + numArr[1] * 10 + numArr[2]) + " " + ((numArr[0] * 100 + numArr[1] * 10 + numArr[2]) % 8 == 0 ? true : false));
            return (numArr[0] * 100 + numArr[1] * 10 + numArr[2]) % 8 == 0 ? true : false;
        } else {
            for (int j = 0; j <= i; j++) {
                swap(numArr, j, i);
                if (permuteAndCheck(numArr, i - 1))
                    return true;
                swap(numArr, i, j);
            }
        }

        return false;
    }

    private static void swap(int[] a, int i, int k) {
        int temp = a[i];
        a[i] = a[k];
        a[k] = temp;
    }

    // driver method
    public static void main(String[] args) {
//        _instance.runTest(46, true);
//        _instance.runTest(345, false);
//        _instance.runTest(15725, true);
        _instance.runTest(5706, true);
    }

    public void runTest(final int array, final boolean expectedOutput) {
        List<Object> answers = runAll(getClass(), new Object[] { array });

        for (Object answer : answers)
            assertThat((Boolean) answer).isEqualTo(expectedOutput);

        System.out.println("ok!");
    }

}
