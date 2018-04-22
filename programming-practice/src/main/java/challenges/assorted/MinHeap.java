package challenges.assorted;

import challenges.AbstractCustomTestRunner;

import java.util.Comparator;
import java.util.Random;

/**
 * Min Heap
 *
 * @author hxkandwal
 */
public class MinHeap extends AbstractCustomTestRunner {

    static class Heap {

        private int size;
        private int count = 1;
        private Integer[] data;
        private Comparator<Integer> comparator;

        public Heap (int size, Comparator<Integer> comparator) {
            this.size = size;
            this.data = new Integer [size + 1];
            this.comparator = comparator;
        }

        private int peek() { return data[1]; }

        public void offer (int num) {
            if (count >= size) return;

            data[count] = num;
            percolateUp(count ++);
        }

        public void percolateUp(int index) {
            for (int idx = index; idx > 1; idx /= 2) {
                if (comparator.compare(data[idx], data[idx/2]) < 0) {
                    int temp = data[idx];
                    data[idx] = data[idx/2];
                    data[idx/2] = temp;
                } else break;
            }
        }

        public int poll() {
            int ret = data[1];

            data[1] = data[-- count];
            data[count] = null;

            percolateDown(1);
            return ret;
        }

        public void percolateDown(int index) {
            for (int idx = index; idx <= count/2;) {
                int minIdx = 2 * idx;

                if (data[2 * idx + 1] != null) if (comparator.compare(data[2 * idx], data[2 * idx + 1]) > 0) minIdx = 2 * idx + 1;

                if (comparator.compare(data[idx], data[minIdx]) > 0) {
                    int temp = data[idx];
                    data[idx] = data[minIdx];
                    data[minIdx] = temp;

                    idx = minIdx;
                } else break;
            }
        }
    }

    // driver method
    public static void main(String[] args) {
        runTest(10);
    }

    public static void runTest(int size) {
        Heap minHeap = new Heap(size, (x, y) -> x - y);
        Random random = new Random();

        for (int idx = 0; idx < size; idx ++) {
            int num = random.nextInt(size);

            System.out.println("adding : " + num);
            minHeap.offer(num);

            if (num % 2 == 0) System.out.println(minHeap.peek());
        }
        System.out.println(minHeap.peek());
    }
}
