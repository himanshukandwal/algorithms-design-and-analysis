package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * Logic to partion array k ways.
 * 
 * @author Hxkandwal
 */
public class PartitionArrayKWays extends AbstractCustomTestRunner {
	
	private static PartitionArrayKWays _instance = new PartitionArrayKWays();

	public List<String> _partition(String s) {
        Map<String, List<String>> map = new HashMap<>();
        List<String> ans = dfs (map, s, 0, s.length(), 4);
        for (String str : ans) System.out.println(str);
        return null;
    }
    
    private List<String> dfs (Map<String, List<String>> map, String s, int start, int end, int k) {
    	String key = start + ":" + end + ":" + k;
    	if (map.containsKey(key)) return map.get(key);
    	if (k == 0) { map.put(key, Arrays.asList(s.substring(start, end))); return map.get(key); }
    	List<String> ans = new ArrayList<>();
        for (int idx = start; idx < end; idx ++) {
        	for (int k1 = 0; k1 <= k; k1 ++) {
        		int k2 = k - k1 - 1;
        		
        		if (idx - start <= k1 || end - idx <= k2) continue;
        		
        		List<String> before = dfs (map, s, start, idx, k1);
        		List<String> after = dfs (map, s, idx, end, k2);
        		for (String bstr : before) for (String astr : after)  ans.add(bstr + "," + astr);
        	}
        }
        map.put(key, ans);
        return ans;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("11223344", null);
	}

	@SuppressWarnings("unchecked")
	public void runTest(final String s, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });

		for (Object answer : answers)
			assertThat((List<String>) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	} 
	
}
