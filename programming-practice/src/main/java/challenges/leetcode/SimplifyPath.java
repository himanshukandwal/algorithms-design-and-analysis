package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

import java.util.List;
import java.util.Stack;

import static com.google.common.truth.Truth.assertThat;

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

	public String _simplifyPathBetter(String path) {
        String[] arr = path.split("\\/");
        Stack<String> stack = new Stack<>();
        for (String s : arr) {
            if (s.length() == 0 || s.equals(".")) continue;
            if (s.equals("..")) {
                if (!stack.isEmpty()) stack.pop();
            } else stack.push (s);
        }
        return "/" + String.join("/", stack.toArray(new String[0]));
    }
    
	// driver method
	public static void main(String[] args) {
		_instance.runTest("/", "/");
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
