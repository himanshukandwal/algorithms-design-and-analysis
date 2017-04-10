package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 282. Expression Add Operators
 * 
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators (not unary)
 * +, -, or * between the digits so they evaluate to the target value.
 * 
 * Examples:
 * 		"123", 6 -> ["1+2+3", "1*2*3"]
 * 		"232", 8 -> ["2*3+2", "2+3*2"]
 * 		"105", 5 -> ["1*0+5","10-5"]
 * 		"00", 0 -> ["0+0", "0-0", "0*0"]
 * 		"3456237490", 9191 -> []
 * 
 * @author Hxkandwal
 */
@SuppressWarnings({ "unchecked", "rawtypes", "serial" })
public class ExpressionAddOperators extends AbstractCustomTestRunner {
	
	private static ExpressionAddOperators _instance = new ExpressionAddOperators();
	
	private ExpressionAddOperators() {}
	
	public List<String> _addOperatorsCorrect(String num, int target) {
		List<String> rst = new ArrayList<String>();
	    if (num == null || num.length() == 0) return rst;
	    helper (rst, "", num, target, 0, 0, 0);
	    return rst;
	}
	
	public void helper(List<String> rst, String path, String num, int target, int pos, long eval, long multed){
	    if (pos == num.length()) {
	        if (target == eval) rst.add(path);
	        return;
	    }
	    
	    for (int i = pos; i < num.length(); i++) {
	        if (i != pos && num.charAt (pos) == '0') break;
	        long cur = Long.parseLong (num.substring (pos, i + 1));
	        
	        if (pos == 0)  helper (rst, path + cur, num, target, i + 1, cur, cur);
	        else {
	            helper (rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);
	            helper (rst, path + "-" + cur, num, target, i + 1, eval - cur, -cur);
	            helper (rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
	        }
	    }
	}

	// (Incorrect Attempt, worth mentioning)
    public List<String> addOperators(String num, int target) {
    	List<String> answer = new ArrayList<>();
        if (num == null || num.length() == 0 || target <= 0) return answer;
        
        Map<Integer, Map <Integer, List<String>>> map = new HashMap<>();
        Map<Integer, List<String>> innerMap = new HashMap<>();
        innerMap.put (0, null);
        map.put (num.length(), innerMap);
        
        addOperators (map, num, target, 0);
        
        return map.get (0).getOrDefault (target, new ArrayList<>());
    }
    
    private static void addOperators (Map<Integer, Map <Integer, List<String>>> map, String num, int target, int start) {
        if (start >= num.length() || map.containsKey (start)) return;
        
        int val = 0;
        for (int idx = start; idx < num.length(); idx ++) {
            val = 10 * val + (num.charAt(idx) - '0');
        
            addOperators (map, num, target, idx + 1);
            
            map.putIfAbsent (start, new HashMap<>());
            Map <Integer, List<String>> myInnerMap = map.get (start);
            Map <Integer, List<String>> innerMap = map.get (idx + 1);
            
            for (Map.Entry<Integer, List<String>> entry : innerMap.entrySet()) {
                int computedVal = entry.getKey();
                List<String> computedStrings = entry.getValue();
                
                if (computedStrings == null)
                    myInnerMap.put (val, Arrays.asList(String.valueOf(val)));  
                else {
                    if (computedVal + val <= target) { 
                        if (!myInnerMap.containsKey(computedVal + val))
                            myInnerMap.put (computedVal + val, new ArrayList<>());
                        
                        for (String computedString : computedStrings)
                            myInnerMap.get (computedVal + val).add (val + "+" + computedString);
                    }
                    if (val - computedVal <= target) { 
                        if (!myInnerMap.containsKey(val - computedVal))
                            myInnerMap.put (val - computedVal, new ArrayList<>());
                        
                        for (String computedString : computedStrings)
                            myInnerMap.get (val - computedVal).add (val + "-" + computedString);
                    }
                    if (computedVal * val <= target) { 
                        if (!myInnerMap.containsKey(computedVal * val))
                            myInnerMap.put (computedVal * val, new ArrayList<>());
                        
                        for (String computedString : computedStrings)
                            myInnerMap.get (computedVal * val).add (val + "*" + computedString);
                    }
                }
            }
        }
    }
    
	// driver method
	public static void main(String[] args) {
//		_instance.runTest("123", 6, new ArrayList() {{ add("1+2+3"); add("1*2*3"); }});
		_instance.runTest("232", 8, new ArrayList() {{ add("2*3+2"); add("2+3*2"); }});
	}

	public void runTest(final String num, final int target, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num, target });

		for (Object answer : answers)
				assertThat((List<String>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
