package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 526. Beautiful Arrangement
 * 
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by these N numbers 
 * successfully if one of the following is true for the ith position (1 ? i ? N) in this array:
 * 
 * 1. The number at the ith position is divisible by i.
 * 2. i is divisible by the number at the ith position.
 * 
 * Now given N, how many beautiful arrangements can you construct?
 * 
 * Example 1:
 * 		Input: 2
 * 		Output: 2
 * 		Explanation:
 * 			The first beautiful arrangement is [1, 2]:
 * 			Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
 * 			Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
 * 			
 * 			The second beautiful arrangement is [2, 1]:
 * 			Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
 * 			Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 * 
 * Note: N is a positive integer and will not exceed 15.
 * 
 * @author Hxkandwal
 */
public class BeautifulArrangement extends AbstractCustomTestRunner {

	public int countArrangement(int N) {
        int [] arr = new int [N];
        for (int idx = 0; idx < N; idx ++) arr [idx] = idx + 1;
        return dfs (arr, 0);
    }
    
    private int dfs (int [] arr, int start) {
        int ans = 0;
        if (start == arr.length) {
            ans ++;
        } else {
            for (int idx = start; idx < arr.length; idx ++) {
                if (arr [idx] % (start + 1) != 0 && (start + 1) % arr [idx] != 0) continue;
                swap (arr, idx, start);
                ans += dfs (arr, start + 1);
                swap (arr, idx, start);
            }
        }
        return ans;
    }
    
    private void swap (int [] arr, int from, int to) {
        int val = arr [from];
        arr [from] = arr [to];
        arr [to] = val;
    }
    
}
