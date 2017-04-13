package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import challenges.AbstractCustomTestRunner;

/**
 * 93. Restore IP Addresses
 * 
 * Given a string containing only digits, restore it by returning all possible valid IP address combinations.
 * 
 * For example: Given "25525511135", return ["255.255.11.135", "255.255.111.35"]. (Order does not matter)
 * 
 * @author Hxkanwal
 */
public class RestoreIPAddresses extends AbstractCustomTestRunner {
	
	private static RestoreIPAddresses _instance = new RestoreIPAddresses();

	public List<String> _restoreIpAddresses(String s) {
		if (s.length() > 12) return new ArrayList<>();
        return new ArrayList<>(dfs (new HashMap<>(), s, 0, s.length(), 3));
    }
    
    private Set<String> dfs (Map<String, Set<String>> map, String s, int start, int end, int k) {
    	String key = start + ":" + end + ":" + k;
        if (map.containsKey(key)) return map.get (key);
        
        Set<String> ans = new HashSet<>();
        if (k == 0) {
            String number = s.substring (start, end);
            map.put (key, ans);
            
            // main logic 
            if (start < end && !(number.startsWith ("0") && number.length() > 1) && Integer.valueOf (number) < 256) 
                ans.add (number);
            return map.get (key);
        }
        
    	for (int idx = start; idx < end; idx ++) {
        	for (int k1 = 0; k1 <= k; k1 ++) {
        		int k2 = k - k1 - 1;
        		
        		if (idx - start <= k1 || end - idx <= k2) continue;
        		Set<String> before = dfs (map, s, start, idx, k1);
        		Set<String> after = dfs (map, s, idx, end, k2);
        		for (String bstr : before) for (String astr : after)  ans.add(bstr + "." + astr);
        	}
        }
        map.put(key, ans);
        return ans;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("25525511135", Arrays.asList("255.255.11.135", "255.255.111.35"));
		_instance.runTest("010010", Arrays.asList("0.100.1.0", "0.10.0.10"));
	}

	@SuppressWarnings("unchecked")
	public void runTest(final String s, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
	
}
