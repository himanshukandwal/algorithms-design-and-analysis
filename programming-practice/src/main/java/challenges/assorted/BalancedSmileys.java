package challenges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * Balance Smileys
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
            boolean balanced = true;
           
            // processing logic
            Character prev = null;
            Stack<Integer> stack = new Stack<>();
            List<Integer> hpair = new ArrayList<>();
            List<Integer> fpair = new ArrayList<>();
            
            for (int idx = 0; idx < expr.length(); idx ++) {
                char ch = expr.charAt (idx);
                if (ch == '(') {
                    if (prev != null && prev == ':') fpair.add (idx - 1);
                    else stack.push (idx);
                } else if (ch == ')') {
                    if (prev != null && prev == ':') hpair.add (idx - 1);
                    else {
                        if (!stack.isEmpty()) stack.pop();
                        else if (fpair.size() == 0)  { balanced = false; break; }
                        else fpair.remove(fpair.size() - 1);
                    }
                }
                prev = ch;
            }
            
            for (int openIdx : stack) {
                for (Iterator <Integer> iterator = hpair.iterator(); iterator.hasNext();) {
                    int start = iterator.next();
                    if (start < openIdx) iterator.remove ();
                    else break;
                }
                if (hpair.size() > 0) hpair.remove(0);
                else { balanced = false; break; }
            }
            ans [itr] = (balanced ? "YES" : "NO");
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
