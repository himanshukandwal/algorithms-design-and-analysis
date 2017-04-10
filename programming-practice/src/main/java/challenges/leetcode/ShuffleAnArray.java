package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 384. Shuffle an Array
 * 
 * Shuffle a set of numbers without duplicates. 
 * 
 * Example:	
 * 		// Init an array with set 1, 2, and 3.
 * 		int[] nums = {1,2,3};
 * 		Solution solution = new Solution(nums);
 * 
 * 		// Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to 
 * 		be returned.
 * 		solution.shuffle();
 * 
 * 		// Resets the array back to its original configuration [1,2,3].
 * 		solution.reset();
 * 
 * 		// Returns the random shuffling of array [1,2,3].
 * 		solution.shuffle();
 * 
 * @author Hxkandwal
 *
 */
public class ShuffleAnArray extends AbstractCustomTestRunner {
	
	private int [] nums;
	private int [] backup;

	public ShuffleAnArray (int[] nums) {
		this.nums = nums;
        this.backup = new int [nums.length];
        
        for (int idx = 0; idx < nums.length; idx ++)  backup [idx] = nums [idx];
	}
	
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
    	for (int idx = 0; idx < nums.length; idx ++)  nums [idx] = backup [idx];
    	return nums;
    }
    
    /** Returns a random shuffling of the array. */
    public String shuffle() {
    	knuthLAlgorithmIteration();
    	
    	StringBuilder ans = new StringBuilder();
    	for (int idx  = 0; idx  < backup.length; idx ++) {
			ans.append(nums [idx]);
			if (idx + 1 < nums.length)
				ans.append(" ");
		}
    	
    	return ans.toString();
    }
    
    /**
     * Sources : 
     * 		http://www.geeksforgeeks.org/shuffle-a-given-array/
     * 		https://discuss.leetcode.com/topic/53978/first-accepted-solution-java
     */
    public String shuffleBetter() {
    	Random rnd = new Random();
    	
    	for (int idx = 0; idx < nums.length; idx ++) {
    		int swapIdx = rnd.nextInt(idx + 1);
    		int t = nums [swapIdx];
			nums [swapIdx] = nums [idx];
			nums [idx] = t;	
    	}
    	
    	StringBuilder ans = new StringBuilder();
    	for (int idx  = 0; idx  < nums.length; idx ++) {
			ans.append(nums [idx]);
			if (idx + 1 < nums.length)
				ans.append(" ");
		}
    	
    	return ans.toString();
    }
    
    private void knuthLAlgorithmIteration() {
    	int idx = nums.length - 1;
    	while (idx >= 1) {
    		if (nums [idx] > nums [idx - 1])
    			break;
    		idx --;
    	}
    	
    	if (idx == 0) return;
    	
    	for (int revIdx = nums.length - 1; revIdx >= idx; revIdx --) {
    		if (nums [revIdx] > nums [idx - 1]) {
    			int t = nums [revIdx];
    			nums [revIdx] = nums [idx - 1];
    			nums [idx - 1] = t;
    			break;
    		}	
    	}
    	
    	for (int delta = 0; delta < (nums.length - idx) / 2; delta ++) {
    		int t = nums [idx + delta];
			nums [idx + delta] = nums [nums.length - delta - 1];
			nums [nums.length - delta - 1] = t;
    	}
    }
     
    public static void main(String[] args) {
		int [] array = new int [] { 1, 2, 3 };
		
		ShuffleAnArray shuffler = new ShuffleAnArray(array);
		shuffler.reset();
		Set<String> seen = new HashSet<>();
		
		int count = 1;
		while (seen.add(shuffler.shuffle()))
			count ++;
		
		assertThat(count).isEqualTo(6);
		System.out.println("ok !");
		
		/**
		 * with shuffle-better, idea is to we always start with the original copy (clone) of the array, not working ahead with the permuted copy.
		 */
		
		shuffler.reset();
		seen.clear();
		
		count = 1;
		while (seen.add(shuffler.shuffleBetter()))
			count ++;
		
		assertThat(count).isEqualTo(6);
		System.out.println("ok (Better) !");
	} 
	
}
