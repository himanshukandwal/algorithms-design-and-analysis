package challenges.interviewbit;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Matrix Median
 * 
 * Given a N cross M matrix in which each row is sorted, find the overall median of the matrix. Assume N*M is odd.
 * For example,
 * 		Matrix =
 * 				[1, 3, 5]
 * 				[2, 6, 9]
 * 				[3, 6, 9]
 * 
 * A = [1, 2, 3, 3, 5, 6, 6, 9, 9] Median is 5. So, we return 5.
 * 
 * Note: No extra memory is allowed.
 * 
 * @author Hxkandwal
 */
public class MatrixMedian extends AbstractCustomTestRunner {
	
	private static MatrixMedian _instance = new MatrixMedian();

	public int _findMedian(List<List<Integer>> A) {
        int m = A.size(), n = A.get (0).size();
        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (List<Integer> ai : A) { min = Math.min (min, ai.get(0)); max = Math.max(max, ai.get(ai.size() - 1)); }
        
        int lcount = 0, mid = 0;
        
        while (min < max) {
        	mid = (min + max)/2;
            for (List<Integer> ai : A) {
            	 int index = binarySearch (ai, mid) + 1;
            	 if (index < 0) index = -index;
            	 lcount += index;
            }
            
            if (lcount > (m*n + 1)/2) max = mid - 1;  
            else if (lcount < (m*n + 1)/2) min = mid + 1;
            else break;
        }
        return mid;
    }
    
    public int binarySearch (List<Integer> A, int value) {
        int left = 0, right = A.size() - 1;
        while (left <= right) {
            int mid = (left + right)/2;
            if (A.get (mid) > value) right = mid - 1;
            else if (A.get (mid) < value) left = mid + 1;
            else return mid;
        }
        return -(left + 1);
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(Arrays.asList(Arrays.asList(1, 3, 5), Arrays.asList(2, 6, 9), Arrays.asList(3, 6, 9)), 5);
		_instance.runTest(Arrays.asList(Arrays.asList(2), 
				                     	Arrays.asList(1), 
				                     	Arrays.asList(4), 
				                     	Arrays.asList(1), 
				                     	Arrays.asList(2), 
				                     	Arrays.asList(2), 
				                     	Arrays.asList(5)), 2);
	}

	public void runTest(final List<List<Integer>> A, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { A });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
