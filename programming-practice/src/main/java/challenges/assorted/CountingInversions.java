package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * ref: https://www.youtube.com/watch?v=kPaJfAUwViY
 * 
 * @author Hxkandwal
 */
public class CountingInversions extends AbstractCustomTestRunner {
	
	private static CountingInversions _instance = new CountingInversions();

	class FenwickTree {
        int [] bit;
        FenwickTree (int size) { this.bit = new int [size + 1]; }
        
        void add (int index, int val) {
            while (index < bit.length) {
                bit [index] += val;
                index += index & -index;
            }
        }
        
        long sum (int index) {
            long sum = 0;
            while (index > 0) {
                sum += bit [index];
                index -= index & -index;
            }
            return sum;
        }
        
        long sumBack (int index) {
            return sum (bit.length - 1) - sum (index);
        }
    }
    
    public long _countInversions(int[] arr){
        int [] sorted = Arrays.copyOf(arr, arr.length);
        Arrays.sort (sorted);
        FenwickTree ft = new FenwickTree (arr.length);
        long count = 0;
        for (int ai : arr) {
            int index = indexOf (sorted, ai);
            count += ft.sumBack (index);
            ft.add (index, 1);
        }
        return count;
    }
  
    private int indexOf (int [] arr, int value) {
        int low = 0, high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (arr [mid] >= value) high = mid - 1;
            else low = mid + 1;
        }
        return low + 1;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 7, 6, 2, 3, 1, 4, 5 }, 13l);
	}

	public void runTest(final int[] nums, final long expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((Long) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
	
}
