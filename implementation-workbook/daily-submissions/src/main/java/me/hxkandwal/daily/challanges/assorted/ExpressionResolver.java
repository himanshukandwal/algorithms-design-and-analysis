package me.hxkandwal.daily.challanges.assorted;

import static com.google.common.truth.Truth.assertThat;

import java.util.List;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * A program to evaluate expressions against set of operations.
 * 
 * @author Hxkandwal
 *
 */
public class ExpressionResolver extends AbstractCustomTestRunner {

	private static ExpressionResolver _instance = new ExpressionResolver();
	
	private ExpressionResolver() {}

	public static String _resolve(String line) throws Exception {
		int len = line.length();
		
		StringBuilder result = new StringBuilder();
		int idx = 0;
		
		for (; idx < len; idx ++) {
			if (line.charAt(idx) == '/')
				break;
			
			if (line.charAt(idx) != ' ')
				result.append(line.charAt(idx));
		}
		idx ++;
		
		if (idx == len)
			return result.toString();
		else {
			boolean isRpresent = false;
			for (; idx < len; idx ++) {

				if (line.charAt(idx) == 'S' && (result.length() == 0 || result.charAt(result.length() - 1) != 'S')) {
					// manage previous R state
					if (isRpresent) {
						reverseExpression(result);
						isRpresent = false;
					}
					
					result = new StringBuilder(resolveParenthesis(result, 0, result.length() - 1));
				}

				if (line.charAt(idx) == 'R')
					isRpresent = !isRpresent;
			}
			
			if (isRpresent)
				reverseExpression(result);
			
			return result.toString();
		}
	}

	// method to efficiently reverse the order of expression (R - reverse operation)
	public static void reverseExpression(StringBuilder expression) {
		
		for (int idx = 0; idx < expression.length()/2; idx ++) {
			char ch = expression.charAt(idx);
			ch = (ch == '(' ? ')' : (ch == ')' ? '(' : ch));
            
            char otherCh = expression.charAt(expression.length() - idx - 1);
            otherCh = (otherCh == '(' ? ')' : (otherCh == ')' ? '(' : otherCh));
            
			expression.setCharAt(idx, otherCh);
			expression.setCharAt(expression.length() - idx - 1, ch);
		}
	}

	// method to efficiently resolve the parenthesis (S - simplify operation)
	public static String resolveParenthesis(StringBuilder expression, int start, int end) {
		if (expression.charAt(start) == '(') {
			StringBuilder result = new StringBuilder();
			
			int parathesisCount = 1;
			int idx = start + 1;

			// build the parenthesis inner content
			for (; idx <= end; idx ++) {
				if (expression.charAt(idx) == '(') 
					parathesisCount ++;
				else if (expression.charAt(idx) == ')') {
					if (--parathesisCount == 0)
						break;
				}
				result.append(expression.charAt(idx));
			}
			
			// look for sub-trees
			while (++idx <= end) {
				if (expression.charAt(idx) == '(') {
					result.append(expression.charAt(idx));
					int childEnd = findChild (expression, idx, end);
					result.append (resolveParenthesis(expression, idx + 1, childEnd - 1));
					idx = childEnd;
				}
						
				result.append(expression.charAt(idx));
			}
			
			return result.toString();
		} else {
			return expression.substring(start, end + 1);
		}
	}

	private static int findChild(StringBuilder expression, int start, int end) {
		int parathesisCount = 1;
		int idx = start + 1;
		
		// build the parenthesis inner content
		for (; idx <= end; idx ++) {
			if (expression.charAt(idx) == '(') 
				parathesisCount ++;
			else if (expression.charAt(idx) == ')') {
				if (--parathesisCount == 0)
					break;
			}
		}
					
		return idx;
	}
	
	// driver method
	public static void main(String[] args) throws Exception {
		_instance.runTest("(AB) C((D E) F)/ SSS", "ABC(DEF)");
		_instance.runTest("A/ SSS", "A");
		_instance.runTest("AB/ RRSSS", "AB");
		_instance.runTest("AB/ RSSS", "BA");
		_instance.runTest("AB/ RSSS", "BA");
		_instance.runTest("(AB) C((D E)F)/ R", "(F(ED))C(BA)");
		_instance.runTest("(AB) C((D E)F)/ RRR", "(F(ED))C(BA)");
		_instance.runTest("(AB) C((D E)F)/ RR", "(AB)C((DE)F)");
		_instance.runTest("(AB) C((D E)F)/ S", "ABC(DEF)");
		_instance.runTest("(AB) C((D E)F)/ SSSSS", "ABC(DEF)");
		_instance.runTest("(AB)C((DE)F)gdfddsdf((DE)F((H)I))/ SSS", "ABC(DEF)gdfddsdf(DEF(HI))");
		_instance.runTest("(AB)C((DE)F)gdfddsdf((DE)F(H(I)))/ SSS", "ABC(DEF)gdfddsdf(DEF(H(I)))");
		_instance.runTest("(D)A(BC)/ S", "DA(BC)");
	}
	
	public void runTest(final String line, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { line });

		for (Object answer : answers)
			assertThat((String) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
}