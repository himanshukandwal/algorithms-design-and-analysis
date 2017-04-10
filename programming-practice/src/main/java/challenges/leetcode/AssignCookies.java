package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 455. Assign Cookies
 * 
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one cookie. 
 * Each child i has a greed factor gi, which is the minimum size of a cookie that the child will be content with; and each cookie j has 
 * a size sj. If sj >= gi, we can assign the cookie j to the child i, and the child i will be content. Your goal is to maximize the 
 * number of your content children and output the maximum number.
 * 
 * Note:
 * 		You may assume the greed factor is always positive. 
 * 		You cannot assign more than one cookie to one child.
 * 
 * Example 1:
 * 		Input: [1,2,3], [1,1]
 * 		Output: 1
 * 		Explanation: You have 3 children and 2 cookies. The greed factors of 3 children are 1, 2, 3.
 * 					 And even though you have 2 cookies, since their size is both 1, you could only make the child whose greed factor 
 * 					 is 1 content.
 * 					 You need to output 1.
 * 
 * Example 2:
 * 		Input: [1,2], [1,2,3]
 * 		Output: 2
 * 		Explanation: You have 2 children and 3 cookies. The greed factors of 2 children are 1, 2.
 * 					 You have 3 cookies and their sizes are big enough to gratify all of the children,
 * 					 You need to output 2.
 * 
 * @author Hxkandwal
 */
public class AssignCookies extends AbstractCustomTestRunner {
	
	private static AssignCookies _instance = new AssignCookies();

	public int _findContentChildren(int[] children, int[] cookies) {
		Arrays.sort(children);
        Arrays.sort(cookies);
        
        int child = 0;
        for (int cookie = 0; child < children.length && cookie < cookies.length; cookie ++)
            if (cookies [cookie] >= children [child]) child ++;
        
        return child;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new int[] { 1, 2 }, new int[] { 1, 2, 3 }, 2);
		_instance.runTest(new int[] { 1, 2, 3 }, new int[] { 1, 1 }, 1);
		_instance.runTest(new int[] { 10, 9, 8, 7 }, new int[] { 5, 6, 7, 8 }, 2);
	}

	public void runTest(final int[] g, final int[] s, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { g, s });

		for (Object answer : answers)
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
    
}
