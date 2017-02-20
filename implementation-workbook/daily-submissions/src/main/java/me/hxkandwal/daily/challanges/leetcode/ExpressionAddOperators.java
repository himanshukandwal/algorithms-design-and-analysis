package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

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
	
	// dp substring problem
    public List<String> _addOperators(String num, int target) {
    	List<String> answer = new ArrayList<>();
        if (num == null || num.length() == 0 || target <= 0) return answer;
        
        if (num.length() == 1) { 
        	if ((num.charAt(0) - '0') == target) answer.add (num);
        	return answer;
        }
        
        int val = 0;
        for (int idx = 0; idx < num.length(); idx ++) {
            val = 10 * val + num.charAt(idx) - '0';
        
            if (val > 0) {
                for (String response : _addOperators(num.substring(idx + 1), target - val))
                    answer.add (val + "+" + response);
                    
                for (String response : _addOperators(num.substring(idx + 1), val - target))
                    answer.add (val + "-" + response);
                    
                for (String response : _addOperators(num.substring(idx + 1), target / val))
                    answer.add (val + "*" + response);                    
            }
        }
        
        return answer;
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("123", 6, new ArrayList() {{ add("1+2+3"); add("1*2*3"); }});
		_instance.runTest("232", 8, new ArrayList() {{ add("2*3+2"); add("2+3*2"); }});
	}

	public void runTest(final String num, final int target, final List<String> expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { num, target });

		for (Object answer : answers)
				assertThat((List<String>) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}
	
}
