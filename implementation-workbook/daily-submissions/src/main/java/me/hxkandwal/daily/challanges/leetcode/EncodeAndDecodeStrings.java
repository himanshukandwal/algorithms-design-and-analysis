package me.hxkandwal.daily.challanges.leetcode;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 271. Encode and Decode Strings
 * 
 * Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network 
 * and is decoded back to the original list of strings.
 * 
 * Machine 1 (sender) has the function:
 * 
 * string encode(vector<string> strs) {
 * 		// ... your code
 * 		return encoded_string;
 * }
 * 
 * Machine 2 (receiver) has the function:
 * vector<string> decode(string s) {
 * 		//... your code
 * 		return strs;
 * }
 * 
 * So Machine 1 does:
 * 	string encoded_string = encode(strs);
 * 
 * and Machine 2 does:
 * vector<string> strs2 = decode(encoded_string);
 * strs2 in Machine 2 should be the same as strs in Machine 1.
 * 
 * Implement the encode and decode methods.
 * 
 * @author Hxkandwal
 */
public class EncodeAndDecodeStrings extends AbstractCustomTestRunner {
	
	 // Encodes a list of strings to a single string.
    public String encode(List<String> strs) {
        StringBuilder sb = new StringBuilder();
        for (String str : strs) sb.append(str.length()).append ('/').append(str);
        return sb.toString();
    }

    // Decodes a single string to a list of strings.
    public List<String> decode(String s) {
        List<String> ans = new ArrayList<>();
        int idx = 0;
        while (idx < s.length()) {
            int slash = s.indexOf('/', idx);
            int size = Integer.valueOf (s.substring(idx, slash));
            ans.add (s.substring (slash + 1, slash + 1 + size));
            idx = slash + 1 + size;
        }
        return ans;
    }
    
}
