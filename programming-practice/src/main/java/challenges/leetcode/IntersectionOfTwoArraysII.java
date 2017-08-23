package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 350. Intersection of Two Arrays II
 * 
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example: Given nums1 = [1, 2, 2, 1], nums2 = [2, 2] return [2, 2].
 * 
 * Note :
 * 		a) Each element in the result should appear as many times as it shows in both arrays.
 * 		b) The result can be in any order.
 * 
 * @author Hxkandwal
 *
 */
public class IntersectionOfTwoArraysII extends AbstractCustomTestRunner {
	
	private static IntersectionOfTwoArraysII _instance = new IntersectionOfTwoArraysII();

	public int[] _intersection(int[] nums1, int[] nums2) {
		Arrays.sort (nums1);
        Arrays.sort (nums2);
        List<Integer> ans = new ArrayList<>();
        int idx1 = 0, idx2 = 0;
        while (idx1 < nums1.length && idx2 < nums2.length) {
            if (nums1 [idx1] > nums2 [idx2]) idx2 ++;
            else if (nums1 [idx1] < nums2 [idx2]) idx1 ++;
            else { ans.add (nums1 [idx1]); idx1 ++; idx2 ++; }
        }
        int [] ansArr = new int [ans.size()];
        for (int idx = 0; idx < ans.size(); idx ++) ansArr [idx] = ans.get (idx);
        return ansArr;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] {1, 2, 2, 1}, new int[] {2, 2}, new int[] {2, 2});
		_instance.runTest(new int[] {1, 2, 3, 1}, new int[] {2, 3}, new int[] {2, 3});
		_instance.runTest(new int[] {}, new int[] {}, new int[] {});
	}
	
	public void runTest(final int[] num1, final int[] num2, final int[] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num1, num2 });
		
		for (Object answer : answers) 
			assertThat((int[]) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}

}
