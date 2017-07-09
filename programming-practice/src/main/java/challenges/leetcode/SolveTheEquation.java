package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 640. Solve the Equation
 * 
 * Solve a given equation and return the value of x in the form of string "x=#value". The equation contains only '+', '-' operation, the variable 
 * x and its coefficient.
 * 
 * If there is no solution for the equation, return "No solution".
 * If there are infinite solutions for the equation, return "Infinite solutions".
 * If there is exactly one solution for the equation, we ensure that the value of x is an integer.
 * 
 * Example 1:
 * 		Input: "x+5-3+x=6+x-2"
 * 		Output: "x=2"
 * 
 * Example 2:
 * 		Input: "x=x"
 * 		Output: "Infinite solutions"
 * 
 * Example 3:
 * 		Input: "2x=x"
 * 		Output: "x=0"
 * 
 * Example 4:
 * 		Input: "2x+3x-6x=x+2"
 * 		Output: "x=-1"
 * 
 * Example 5:
 * 		Input: "x=x+2"
 * 		Output: "No solution"
 * 
 * @author Hxkandwal
 */
public class SolveTheEquation extends AbstractCustomTestRunner {

	public class Expr {
        private int xw;
        private int cw;
        
        public Expr (String str) {
            int sign = 1;
            Integer num = null;
            for (int idx = 0; idx < str.length(); idx ++) {
                char ch = str.charAt (idx);
                if (ch >= '0' && ch <= '9') {
                    if (num == null) num = 0;
                    num = 10 * num + (ch - '0');
                } else if (ch == 'x') {
                    xw += sign * (num == null ? 1 : num);
                    num = null;
                } else {
                    if (num != null) cw += sign * num;
                    sign = ch == '+' ? 1 : -1;
                    num = null;
                }
            }
            if (num != null) cw += sign * num;
        }
    }
    
    public String solveEquation(String equation) {
        Expr l = new Expr (equation.split ("=") [0]);
        Expr r = new Expr (equation.split ("=") [1]);
        
        if (l.xw == r.xw && l.cw == r.cw) return "Infinite solutions";
        l.xw -= r.xw; l.cw -= r.cw;
        
        if (l.xw == 0) return "No solution";
        return "x=" + (-(l.cw/l.xw));        
    }
    
}
