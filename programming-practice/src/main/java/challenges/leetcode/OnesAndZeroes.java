package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * 474. Ones and Zeroes
 * 
 * In the computer world, use restricted resource you have to generate maximum benefit is what we always want to pursue.
 * For now, suppose you are a dominator of m 0s and n 1s respectively. On the other hand, there is an array with strings 
 * consisting of only 0s and 1s.
 * 
 * Now your task is to find the maximum number of strings that you can form with given m 0s and n 1s. Each 0 and 1 can be 
 * used at most once.
 * 
 * Note:
 * 		1. The given numbers of 0s and 1s will both not exceed 100
 * 		2. The size of given string array won't exceed 600.
 * 
 * Example 1:
 * 		Input: Array = {"10", "0001", "111001", "1", "0"}, m = 5, n = 3
 * 		Output: 4
 * 		Explanation: This are totally 4 strings can be formed by the using of 5 0s and 3 1s, which are “10,”0001”,”1”,”0”
 * 
 * Example 2:
 * 		Input: Array = {"10", "0", "1"}, m = 1, n = 1
 * 		Output: 2
 * 		Explanation: You could form "10", but then you'd have nothing left. Better form "0" and "1".
 * 
 * @author Hxkandwal
 */
public class OnesAndZeroes extends AbstractCustomTestRunner {
	
	private static OnesAndZeroes _instance = new OnesAndZeroes();

    public int _findMaxForm(String[] strs, int m, int n) {
        int [][] dp = new int [m + 1][n + 1];
        
        for (String str : strs) {
            int [] count = count (str);
            for (int row = m; row >= count [0]; row --) {
                for (int col = n; col >= count [1]; col --)
                    dp [row][col] = Math.max (dp [row][col], dp [row - count [0]][col - count [1]] + 1);  
            } 
        }
        
        return dp [m][n];
    }
    
    private int[] count (String str) {
        int [] ans = new int [2];
        for (char c : str.toCharArray()) ans [c - '0'] ++;
        return ans;
    }
    
	public int findMaxForm(String[] strs, int m, int n) {
        return computeMax (strs, 0, 0, m, n);
    }
    
    private int computeMax(String[] strs, int idx, int count, int zeros, int ones) {
    	if (idx == strs.length || (ones == 0 && zeros == 0)) return count;
        
        // don't consider it.
        int vala = computeMax (strs, idx + 1, count, zeros, ones);
        
        // consider it.
        int valb = 0;
        if (strs [idx].length() <= (zeros + ones)) {
            int z = 0, o = 0;
            for (int iidx = 0; iidx < strs [idx].length(); iidx ++)
                if (strs [idx].charAt(iidx) == '0') z ++;
                else o ++;
                
            if (z <= zeros && o <= ones) count ++;
                
            valb = computeMax (strs, idx + 1, count, zeros - z, ones - o);
        }
        
        return Math.max (vala, valb);
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String [] { "10", "1", "0" }, 1, 1, 2);
		_instance.runTest(new String [] { "10", "0001", "111001", "1", "0" }, 5, 3, 4);
		_instance.runTest(new String [] { "10", "0001", "111001", "1", "0" }, 4, 3, 3);
		_instance.runTest(new String[] { "0", "11", "1000", "01", "0", "101", "1", "1", "1", "0", "0", "0", "0", "1",
										 "0", "0110101", "0", "11", "01", "00", "01111", "0011", "1", "1000", "0", 
										 "11101", "1", "0", "10", "0111" }, 9, 80, 17);
	}

	public void runTest(final String[] strs, final int m, final int n, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { strs, m, n });

		for (Object answer : answers)
				assertThat((Integer) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	} 
	
}
