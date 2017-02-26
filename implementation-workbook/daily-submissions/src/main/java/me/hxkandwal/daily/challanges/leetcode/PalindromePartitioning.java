package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 131. Palindrome Partitioning
 * 
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 * 
 * Return all possible palindrome partitioning of s.
 * 
 * For example, given s = "aab",
 * 		Return
 * 			[
 * 				["aa","b"],
 * 				["a","a","b"]
 * 			]
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class PalindromePartitioning extends AbstractCustomTestRunner {
	
	private static PalindromePartitioning _instance = new PalindromePartitioning();
	
	public List<List<String>> _partition(String s) {
        List<List<String>> ans = new ArrayList<>();
        if (s.length() == 0) return ans;
        Map<Integer, List<String>> map = getAllPalindromes (s);
        return generate (map, s, 0);
    }
    
    private List<List<String>> generate (Map<Integer, List<String>> map, String s, int start) {
        List<List<String>> ans = new ArrayList<>();
        if (start == s.length()) { ans.add(new ArrayList<>()); return ans; }
        if (map.containsKey (start)) {
            for (String p : map.get (start)) {
                for (List<String> fs : generate (map, s, start + p.length())) {
                    List<String> res = new ArrayList<>();
                    res.add (p);
                    res.addAll (fs);
                    ans.add (res);
                }
            }
        }
        
        for (List<String> fs : generate (map, s, start + 1)) {
            List<String> res = new ArrayList<>();
            res.add (String.valueOf(s.charAt(start)));
            res.addAll (fs);
            ans.add (res);
        }
        return ans;
    }
    
    private Map<Integer, List<String>> getAllPalindromes (String s) {
        Map<Integer, List<String>> map = new HashMap<>();
        int [][] dp = new int [s.length() + 1][s.length() + 1];
        
        for (int row = 0; row < s.length(); row ++) {
            for (int col = 0; col <= row; col ++) {
                if (s.charAt(row) == s.charAt(col)) {
                    dp [row + 1][col + 1] = (row == col) ? 1 : (dp [row][col + 2] > 0 || row - col == 1 ? dp [row][col + 2] + 2 : 0) ;
                    
                    if (dp [row + 1][col + 1] > 1) { 
                        map.putIfAbsent (col, new ArrayList<>());
                        map.get (col).add (s.substring (col, row + 1));
                    }
                }
            }
        }
        
        return map;
    }
    
}
