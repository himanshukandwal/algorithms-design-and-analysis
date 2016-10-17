package me.hxkandwal.daily.challanges.geeksForGeeks;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

import java.util.List;

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

    // method : find nCk combinations, where k = 3 (as to be divisible by 8, last 3 digits have to be divisible by 8) and check if it is divisble by 8 or not.
    public static boolean _isDivisible(int number) {
        if (number < 100)
            return (number % 8 == 0) ? true :
                    (((number % 10) * 10 + (number / 10)) % 8 == 0 ? true : false);
        else {
            int[] elements = new int [(int) Math.log10(number)+1];

            int idx = 0;
            while (number/10 > 0 || number % 10 > 0) {
                elements [idx ++] = number % 10;
                number /= 10 ;
            }

            return combineAndCheck(elements, new boolean [elements.length], elements.length, 3);
        }
    }

    private static boolean combineAndCheck (int[] elements, boolean[] info, int size, int choose) {
        if (choose == 0) {
            int [] combinedElements = new int [3];
            int combinedElementsIdx = 0;
            for (int idx = 0; idx < info.length; idx ++)
                if (info[idx])
                    combinedElements[combinedElementsIdx ++] = elements[idx];

            return false;
        }

        if (choose <= size) {
            combineAndCheck(elements, info, size - 1, choose);
            info[size - 1] = true;
            combineAndCheck(elements, info, size - 1, choose - 1);
            info[size - 1] = false;
        }

        return false;
    }

//    private static boolean checkPermutation (int[] elements) {
//        int[] res = new int[3];
//        for (int idx = 0; idx <= posIdx; idx ++) {
//            res[idx] = elements [idx];
//        }
//        return false;
//    }

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
