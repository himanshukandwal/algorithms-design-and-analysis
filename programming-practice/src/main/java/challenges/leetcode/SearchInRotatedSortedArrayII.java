package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 81. Search in Rotated Sorted Array II
 * 
 * Follow up for "Search in Rotated Sorted Array": What if duplicates are allowed?
 * 
 * Would this affect the run-time complexity? How and why?
 * Suppose an array sorted in ascending order is rotated at some pivot unknown to you beforehand.
 * 
 * (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
 * 
 * Write a function to determine if a given target is in the array.
 * 
 * The array may contain duplicates.
 * 
 * @author Hxkandwal
 */
public class SearchInRotatedSortedArrayII extends AbstractCustomTestRunner {
	
	private static SearchInRotatedSortedArrayII _instance = new SearchInRotatedSortedArrayII();
	
	private SearchInRotatedSortedArrayII() {}
	
	// try to move away from plateaus
    public boolean _search(int[] nums, int target) {
    	int low = 0, high = nums.length - 1;
    	
    	while (low <= high) {
    		int mid = (low + high) >>> 1;
    		if (nums [mid] == target) return true;
    		
    		int rmid = mid;
    		while (rmid + 1 < nums.length && nums [rmid] == nums [rmid + 1]) rmid ++;
    		
    		if (rmid == nums.length - 1) high = mid - 1;
    		else {
    			if (nums [rmid] < nums [high]) {
    				if (target > nums [rmid] && target <= nums [high])
    					low = rmid + 1;
    				else 
    					high = mid - 1;
    			} else {
    				int lmid = mid;
    				while (lmid - 1 >= 0 && nums [lmid] == nums [lmid - 1]) lmid --;
    				
    				if (lmid == 0) low = rmid + 1;
    				else {
    					if (target >= nums [low] && target < nums [lmid]) high = lmid - 1;
    					else low = rmid + 1;
    				}
    			}
    		}
    		
    	}
    	
    	return false;
    }

    // Shorter code.
    public boolean _searchShorter(int[] A, int key) {
    	int l = 0, r = A.length - 1;
    	
        while (l <= r) {
            int m = l + (r - l)/2;
            if (A[m] == key) return true; //return m in Search in Rotated Array I
            if (A[l] < A[m]) { //left half is sorted
                if (A[l] <= key && key < A[m])
                    r = m - 1;
                else
                    l = m + 1;
            } else if (A[l] > A[m]) { //right half is sorted
                if (A[m] < key && key <= A[r])
                    l = m + 1;
                else
                    r = m - 1;
            } else l++;                // line which made all effects.
        }
        
        return false;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int [] { 1, 2, 1 }, 1, true);
	}

	public void runTest(final int[] nums, final int target, final boolean expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { nums, target });

		for (Object answer : answers)
			assertThat((Boolean) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
