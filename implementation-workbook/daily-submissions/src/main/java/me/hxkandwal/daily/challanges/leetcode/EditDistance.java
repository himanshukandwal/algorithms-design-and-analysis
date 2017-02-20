package me.hxkandwal.daily.challanges.leetcode;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 72. Edit Distance
 * 
 * Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. 
 * (each operation is counted as 1 step.)
 * 
 * You have the following 3 operations permitted on a word:
 * 		a) Insert a character
 * 		b) Delete a character
 * 		c) Replace a character
 * 
 * @author Hxkandwal
 */
public class EditDistance extends AbstractCustomTestRunner {
	
	private static EditDistance _instance = new EditDistance();
	
	private EditDistance() {}
	
	public int minDistance(String word1, String word2) {
        if (word1.length() == 0) return word2.length();
        if (word2.length() == 0) return word1.length();
        
        int [][] dp = new int [word1.length () + 1][word2.length() + 1];
        
        for (int idx = 1; idx < dp[0].length; idx ++) 
            dp [0][idx] = idx;
            
        for (int idx = 1; idx < dp.length; idx ++) 
            dp [idx][0] = idx;
            
        for (int row = 0; row < word1.length(); row ++)
            for (int col = 0; col < word2.length(); col ++)
                if (word1.charAt(row) == word2.charAt(col))
                    dp [row + 1][col + 1] = dp [row][col];
                else
                    dp [row + 1][col + 1] = Math.min (dp [row][col + 1], Math.min (dp [row + 1][col], dp [row][col])) + 1;
        
        return dp [dp.length - 1][dp[0].length - 1];
    }

}
