package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 152. Maximum Product Subarray
 * 
 * Find the contiguous subarray within an array (containing at least one number) which has the largest product.
 * 
 * For example, given the array [2,3,-2,4], the contiguous subarray [2,3] has the largest product = 6.
 *  
 * @author Hxkandwal
 */
public class MaximumProductSubarray extends AbstractCustomTestRunner {
	
    public int maxProduct(int[] A) {
        if (A.length == 0) return 0;
        
        int maxherepre = A[0];
        int minherepre = A[0];
        int maxsofar = A[0];
        int maxhere, minhere;
        
        for (int i = 1; i < A.length; i++) {
            maxhere = Math.max(Math.max(maxherepre * A[i], minherepre * A[i]), A[i]);
            minhere = Math.min(Math.min(maxherepre * A[i], minherepre * A[i]), A[i]);
            maxsofar = Math.max(maxhere, maxsofar);
            maxherepre = maxhere;
            minherepre = minhere;
        }
        
        return maxsofar;
    }
    
}
