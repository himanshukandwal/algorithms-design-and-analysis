package challenges.leetcode.assorted;

import challenges.AbstractCustomTestRunner;

/**
 * Replace two adjacent digits with larger one to find the smallest number that can be obtained
 * You are given an integer X. You must choose two adjacent digits and replace them with the larger of these two digits.
 * 
 * For example, from the integer X = 233614, you can obtain:
 * 		33614 (by replacing 23 with 3);
 * 		23614 (by replacing 33 with 3 or 36 with 6);
 * 		23364 (by replacing 61 with 6 or 14 with 4);
 * 
 * You want to find the smallest number that can be obtained from X by replacing two adjacent digits with the larger of the two. 
 * 
 * In the above example, the smallest such number is 23364.
 * 
 * Write a function:
 * 
 * 	class Solution { public int solution (int X); }
 * 
 * that, given a positive integer X, returns the smallest number that can be obtained from X by replacing two adjacent digits with the larger of the two.
 * For example, given X = 233614, the function should return 23364, as explained above.
 * 
 * Assume that:
 * 
 * 		X is an integer within the range [10..1,000,000,000].
 * 		In your solution, focus on correctness. The performance of your solution will not be the focus of the assessment.
 * 
 * link: https://discuss.leetcode.com/topic/55360/replace-two-adjacent-digits-with-larger-one-to-find-the-smallest-number-that-can-be-obtained
 * 
 * @author Hxkandwal
 */
public class ReplaceTwoAdjacentDigitsWithLargerOne extends AbstractCustomTestRunner {
	
	private static ReplaceTwoAdjacentDigitsWithLargerOne _instance = new ReplaceTwoAdjacentDigitsWithLargerOne();
	
	private ReplaceTwoAdjacentDigitsWithLargerOne() {}
	
	public int solution (int X) {
		
		return 0;
	}

}
