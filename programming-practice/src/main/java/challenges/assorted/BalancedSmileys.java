package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import challenges.AbstractCustomTestRunner;

/**
 * Balance Smileys
 * 
 * Facebook hackercup.
 * 
 * https://www.facebook.com/hackercup/problem/403525256396727/
 * https://www.facebook.com/notes/598486173500621
 * 
 * @author Hxkandwal
 */
public class BalancedSmileys extends AbstractCustomTestRunner {
	
	private static BalancedSmileys _instance = new BalancedSmileys();
	
	public String[] _is_balanced(String[] expression) {
        String [] ans = new String [expression.length];
        int itr = 0;
        while (itr < expression.length) {
            String expr = expression [itr];
           
            // processing logic
            int maxOpen = 0, minOpen = 0;
            for (int idx = 0; idx < expr.length(); idx ++) {
            	char ch = expr.charAt(idx);
            	if (ch == '(') {
            		maxOpen ++;
            		if (idx == 0 || expr.charAt(idx - 1) != ':') minOpen ++;
            	} else if (ch == ')') {
            		minOpen = Math.max (0, minOpen - 1);
            		if (idx == 0 || expr.charAt(idx - 1) != ':') maxOpen --;
            		if (maxOpen < 0) break;
            	}
            }
            
            ans [itr] = (maxOpen >= 0 && minOpen == 0 ? "YES" : "NO");
            itr ++;
        }
        return ans;
    }
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest(new String [] { "coding: ", ":(:)" }, new String [] { "YES", "YES" });
		_instance.runTest(new String [] { ":) :):)", ":(:)" }, new String [] { "YES", "YES" });
		_instance.runTest(new String [] { ":):)", ":(:)" }, new String [] { "YES", "YES" });
		_instance.runTest(new String [] { ":):)", ":(:)" }, new String [] { "YES", "YES" });
		_instance.runTest(new String [] { "(:)", "(:))", ":(())" }, new String [] { "YES", "YES", "YES" });
		_instance.runTest(new String [] { "()", "abc (:))" }, new String [] { "YES", "YES" });
	}
	
	public void runTest(final String [] expression, final String [] expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { expression });
		
		for (Object answer : answers) 
			assertThat((String []) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}	
	
}
