package me.hxkandwal.daily.challanges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 71. Simplify Path
 * 
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example,
 * 		path = "/home/", => "/home"
 * 		path = "/a/./b/../../c/", => "/c"
 * 
 * @author Hxkandwal
 */
public class SimplifyPath extends AbstractCustomTestRunner {
	
	private static SimplifyPath _instance = new SimplifyPath();
	
	private SimplifyPath() {}
	
	public String _simplifyPathBetter(String path) {
		Stack<String> stk = new Stack<>();
        Set<String> skip = new HashSet<>(Arrays.asList("/",".", ".."));
        
        for (String p : path.split("/")) {
            p = p.trim();
            if (p.equals("..") && !stk.empty()) stk.pop();
            else if (p.length() > 0 && !skip.contains(p)) stk.push (p);
        }
        
        StringBuilder answer = new StringBuilder("/");
        for (int idx = 0; idx < stk.size(); idx ++) {
            answer.append(stk.get(idx));
            if (idx < stk.size() - 1) answer.append("/");
        }
        
        return answer.toString(); 
	}
	
    public String _simplifyPath(String path) {
    	Stack <String> stk = new Stack<>();
        StringBuilder p = new StringBuilder();
        
        for (int idx = 0; idx < path.length(); idx ++) {
            while (idx < path.length() && path.charAt(idx) != '/') p.append (path.charAt(idx ++));
            if (p.length() > 0) {
                if (p.toString().equals("..")) { if (!stk.empty()) stk.pop(); }
                else if (!p.toString().equals(".")) stk.push(p.toString()); 
                p.setLength(0);
            }
        }
        p.setLength(0);
        p.append("/");
        for (int idx = 0; idx < stk.size(); idx ++) {
            p.append(stk.get(idx));
            if (idx != stk.size() - 1) p.append("/");
        }
        return p.toString();
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("/", "/");
		_instance.runTest("/.", "/");
		_instance.runTest("/home/", "/home");
		_instance.runTest("/a/./b/../../c/", "/c");
	}

	public void runTest(final String path, final String expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { path });

		for (Object answer : answers)
				assertThat((String) answer).isEqualTo(expectedOutput);

		System.out.println("ok!");
	}    
    
}
