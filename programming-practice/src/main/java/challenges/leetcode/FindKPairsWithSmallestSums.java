package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import challenges.AbstractCustomTestRunner;

/**
 * 373. Find K Pairs with Smallest Sums
 * 
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * 
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * 
 * Example 1:
 * 		Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3
 * 		Return: [1,2],[1,4],[1,6]
 * 
 * 		The first 3 pairs are returned from the sequence:
 * 		[1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 
 * Example 2:
 * 		Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
 * 		Return: [1,1],[1,1]
 * 
 * 		The first 2 pairs are returned from the sequence:
 * 		[1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 
 * Example 3:
 * 		Given nums1 = [1,2], nums2 = [3],  k = 3
 *  	Return: [1,3],[2,3]
 *  
 *  	All possible pairs are returned from the sequence:
 *  	[1,3],[2,3]
 *  
 * @author Hxkandwal
 */
@SuppressWarnings({ "serial", "rawtypes", "unchecked" })
public class FindKPairsWithSmallestSums extends AbstractCustomTestRunner {
	
	private static FindKPairsWithSmallestSums _instance = new FindKPairsWithSmallestSums();

	public List<int[]> _kSmallestPairs(int[] nums1, int[] nums2, int k) {
		List<int[]> answer = new ArrayList<>();
        if (nums1.length == 0 || nums2.length == 0) return answer;
        
        k = (k > nums1.length * nums2.length) ? nums1.length * nums2.length : k;
        PriorityQueue<Tuple> minheap = new PriorityQueue<>();
        int [][] sum = new int [nums1.length][nums2.length];
        for (int row = 0; row < nums1.length; row ++) 
            for (int col = 0; col < nums2.length; col ++) 
                sum [row][col] = nums1 [row] + nums2 [col];
        
        for (int col = 0; col < nums2.length; col ++) minheap.offer (new Tuple (sum [0][col], 0, col));
        for (int idx = 0; idx < k; idx ++) {
            Tuple t = minheap.poll();
            answer.add (new int [] { nums1 [t.x], nums2 [t.y] });
            if (t.x + 1 == nums1.length) continue;
            minheap.offer (new Tuple (sum [t.x + 1][t.y], t.x + 1, t.y));
        }
        return answer;
    }

    public class Tuple implements Comparable<Tuple> {
        int val;
        int x; 
        int y; 
        
        public Tuple (int val, int x, int y) {
            this.val = val; this.x = x; this.y = y;
        }
        
        public int compareTo(Tuple that) {
            return this.val - that.val;
        }
    }
	
   	// driver method
   	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 1, 2 }, new int[] { 1, 2, 3 }, 2, new ArrayList() {{ add(new int[] { 1, 1 }); add(new int[] { 1, 1 }); }});
		_instance.runTest(new int[] { 1, 7, 11 }, new int[] { 2, 4, 6 }, 3, new ArrayList() {{ add(new int[] { 1, 2 }); add(new int[] { 1, 4 }); add(new int[] { 1, 6 }); }});
   	}

   	public void runTest(final int[] nums1, final int[] nums2, final int k, final List<int[]> expectedOutput) {
   		List<Object> answers = runAll(getClass(), new Object[] { nums1, nums2, k });

   		for (Object answer : answers)
   			for (int idx = 0; idx < ((List<int[]>) answer).size(); idx ++)
   				assertThat(((List<int[]>) answer).get(idx)).isEqualTo(expectedOutput.get(idx));

   		System.out.println("ok!");
   	}
    
}
