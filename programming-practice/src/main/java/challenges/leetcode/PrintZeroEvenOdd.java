package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * 1116. Print Zero Even Odd
 *
 * Suppose you are given the following code:
 *
 * class ZeroEvenOdd {
 *   public ZeroEvenOdd(int n) { ... }      // constructor
 *   public void zero(printNumber) { ... }  // only output 0's
 *   public void even(printNumber) { ... }  // only output even numbers
 *   public void odd(printNumber) { ... }   // only output odd numbers
 * }
 * The same instance of ZeroEvenOdd will be passed to three different threads:
 *
 * Thread A will call zero() which should only output 0's.
 * Thread B will call even() which should only ouput even numbers.
 * Thread C will call odd() which should only output odd numbers.
 * Each of the threads is given a printNumber method to output an integer. Modify the given program to output the series 010203040506...
 * where the length of the series must be 2n.
 *
 * Example 1:
 *      Input: n = 2
 *      Output: "0102"
 *      Explanation: There are three threads being fired asynchronously. One of them calls zero(), the other calls even(), and the last one calls odd().
 *                   "0102" is the correct output.
 *
 * Example 2:
 *      Input: n = 5
 *      Output: "0102030405"
 *
 * @author Hxkandwal
 */
public class PrintZeroEvenOdd extends AbstractCustomTestRunner {

    class ZeroEvenOddVolatile {

        private int n;
        private volatile int idx = 0;
        private Semaphore zeroSemaphore;
        private Semaphore evenSemaphore;
        private Semaphore oddSemaphore;

        public ZeroEvenOddVolatile(int n) {
            this.n = n;
            zeroSemaphore = new Semaphore(1);
            evenSemaphore = new Semaphore(0);
            oddSemaphore = new Semaphore(0);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            while (true) {
                zeroSemaphore.acquire();
                if (idx == n) {
                    oddSemaphore.release();
                    evenSemaphore.release();
                    return;
                }

                printNumber.accept(0);

                if (idx % 2 == 0) {
                    oddSemaphore.release();
                } else {
                    evenSemaphore.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            while (true) {
                evenSemaphore.acquire();
                if (idx == n) return;
                ++idx;
                printNumber.accept(idx);
                zeroSemaphore.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            while (true) {
                oddSemaphore.acquire();
                if (idx == n) return;
                ++idx;
                printNumber.accept(idx);
                zeroSemaphore.release();
            }
        }
    }

    class ZeroEvenOddSimple {

        private int n;
        private Semaphore zeroSemaphore;
        private Semaphore evenSemaphore;
        private Semaphore oddSemaphore;

        public ZeroEvenOddSimple(int n) {
            this.n = n;
            zeroSemaphore = new Semaphore(1);
            evenSemaphore = new Semaphore(0);
            oddSemaphore = new Semaphore(0);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void zero(IntConsumer printNumber) throws InterruptedException {
            for (int idx = 0; idx < n; idx ++) {
                zeroSemaphore.acquire();
                printNumber.accept(0);

                if (idx % 2 == 0) {
                    oddSemaphore.release();
                } else {
                    evenSemaphore.release();
                }
            }
        }

        public void even(IntConsumer printNumber) throws InterruptedException {
            for (int idx = 2; idx <= n; idx += 2) {
                evenSemaphore.acquire();
                printNumber.accept(idx);
                zeroSemaphore.release();
            }
        }

        public void odd(IntConsumer printNumber) throws InterruptedException {
            for (int idx = 1; idx <= n; idx += 2) {
                oddSemaphore.acquire();
                printNumber.accept(idx);
                zeroSemaphore.release();
            }
        }
    }
}
