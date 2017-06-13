package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 324. Wiggle Sort II
 * 
 * Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * 
 * Example:
 * 		(1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6].
 * 
 *  	(2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 *  
 * Note: You may assume all input has valid answer.
 * 
 * Follow Up: Can you do it in O(n) time and/or in-place with O(1) extra space?
 * 
 * @author Hxkandwal
 */
public class WiggleSortII extends AbstractCustomTestRunner {
	
	private static WiggleSortII _instance = new WiggleSortII();

	/**
	 * Assume your original array is {6,13,5,4,5,2}. After you get median element, the 'nums' is partially sorted such that the 
	 * first half is larger or equal to the median, the second half is smaller or equal to the median, i.e
	 * 
	 * 		13   6   5   5   4   2
	 * 		         M
	 * 
	 * In the post https://leetcode.com/discuss/76965/3-lines-python-with-explanation-proof, we have learned that , to get wiggle 
	 * sort, you want to put the number in the following way such that
	 * 
	 * (1) elements smaller than the 'median' are put into the last even slots
	 * (2) elements larger than the 'median' are put into the first odd slots
	 * (3) the medians are put into the remaining slots.
	 * 
	 * Index :       0   1   2   3   4   5
	 * Small half:   M       S       S
	 * Large half:       L       L       M
	 * 
	 * M - Median, S-Small, L-Large. In this example, we want to put {13, 6, 5} in index 1,3,5 and {5,4,2} in index {0,2,4}
	 * 
	 * The index mapping, (1 + 2 * index) % (n | 1) combined with 'Color sort', will do the job.
	 * 
	 * After selecting the median element, which is 5 in this example, we continue as the following
	 * 
	 * Mapped_idx[Left] denotes the position where the next smaller-than median element  will be inserted.
	 * Mapped_idx[Right] denotes the position where the next larger-than median element  will be inserted.
	 * 
	 * Step 1:
	 * 		Original idx: 0    1    2    3    4    5
	 * 		Mapped idx:   1    3    5    0    2    4
	 * 		Array:        13   6    5    5    4    2
	 * 				     Left
	 * 						i
	 * 								              Right
	 * 
	 * nums[Mapped_idx[i]] = nums[1] = 6 > 5, so it is ok to put 6 in the first odd index 1. We increment i and left.
	 * 
	 * Step 2:
	 * 		Original idx: 0    1    2    3    4    5
	 * 		Mapped idx:   1    3    5    0    2    4
	 * 		Array:        13   6    5    5    4    2
	 * 					 Left
	 * 						i
	 * 											Right
	 * 
	 * nums[3] = 5 = 5, so it is ok to put 6 in the index 3. We increment i.
	 * 
	 * Step 3:
	 * 		Original idx: 0    1    2    3    4    5
	 * 		Mapped idx:   1    3    5    0    2    4
	 * 		Array:        13   6    5    5    4    2
	 * 					 Left
	 * 						i
	 * 							                 Right
	 * 
	 * nums[5] = 2 < 5, so we want to put it to the last even index 4 (pointed by Right). So, we swap nums[Mapped_idx[i]] with 
	 * nums[Mapped_idx[Right]], i.e. nums[5] with nums[4], and decrement Right.
	 * 
	 *  Step 4:
	 *  	Original idx: 0    1    2    3    4    5
	 *  	Mapped idx:   1    3    5    0    2    4
	 *  	Array:        13   6    5    5    2    4
	 *  					  Left
	 *  							i
	 *  			                       Right
	 *  
	 * nums[5] = 4 < 5, so we want to put it to the second last even index 2. So, we swap nums[5] with nums[2], and decrement Right.
	 * 
	 *  Step 5:
	 *  	Original idx: 0    1    2    3    4    5
	 *  	Mapped idx:   1    3    5    0    2    4
	 *  	Array:        13   6    4    5    2    5
	 *  			          Left
	 *  							i
	 *  			                    Right
	 *  
	 *	nums[5] = 5 < 5, it is ok to put it there, we increment i.
	 *
	 *	Step 6:
	 *		Original idx: 0    1    2    3    4    5
	 *		Mapped idx:   1    3    5    0    2    4
	 *		Array:        13   6    4    5    2    5
	 *			             Left
	 *						             i
	 *			                       Right
	 *
	 *	nums[0] = 13 > 5, so, we want to put it to the next odd index which is 3 (pointed by 'Left'). So, we swap nums[0] with nums[3], 
	 *	and increment 'Left' and 'i'.
	 *	
	 *	Step Final:
	 *		Original idx: 0    1    2    3    4    5
	 *		Mapped idx:   1    3    5    0    2    4
	 *		Array:        5    6    4    13   2    5
	 *							  Left
	 *						                  i
	 *			                      Right
	 *	i > Right, we get the final wiggle array 5 6 4 13 2 5 !
	 */
	public int[] _wiggleSort(int[] nums) {
		int median = findKthLargest(nums, (nums.length + 1) / 2);
        int n = nums.length;
        int left = 0, i = 0, right = n - 1;
        
        while (i <= right) {
			if (nums [newIndex (i, n)] > median) swap(nums, newIndex (left ++, n), newIndex (i ++, n));
			else if (nums [newIndex (i, n)] < median) swap (nums, newIndex (right --, n), newIndex (i, n));
            else i++;
        }
        return nums;
    }

    private int newIndex(int index, int n) {
		return (1 + 2 * index) % (n | 1);
    }
    
    public int findKthLargest(int[] nums, int k) {
    	return quickFind (nums, k - 1, 0, nums.length - 1);
    }
    
    public int quickFind (int[] nums, int k, int start, int end) {
    	if (start >= end) return nums [start];
    	
    	// get random pivot.
    	int pivotIdx = (start + end) >>> 1;
    	swap(nums, pivotIdx, end);
    	
    	int left = start, right = end - 1;
    	while (left < right) {
    		if (nums [left] > nums [end]) left ++;
    		else { swap (nums, left, right); right --; }
    	}
    	if (nums [right] <= nums [end]) swap (nums, right, end);
    	else swap (nums, ++ right, end);
    	
    	if (right == k) return nums [right];
    	else if (right > k) return quickFind (nums, k, start, right - 1);
    	else return quickFind (nums, k, right + 1, end);
    }
    
    private void swap (int [] nums, int from, int to) {
    	int temp = nums [from];
    	nums [from] = nums [to];
    	nums [to] = temp;
    }

	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 3, 2, 2, 3, 1 }, new int[] { 2, 3, 1, 3, 1, 2 });
		_instance.runTest(new int[] { 4, 5, 5, 6 }, new int[] { 5, 6, 4, 5 });	
		_instance.runTest(new int[] { 1, 2, 2, 1, 2, 1, 1, 1, 1, 2, 2, 2 }, new int[] { 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2 });
	}

	public void runTest(final int[] nums, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums });

		for (Object answer : answers)
			assertThat((int []) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}
