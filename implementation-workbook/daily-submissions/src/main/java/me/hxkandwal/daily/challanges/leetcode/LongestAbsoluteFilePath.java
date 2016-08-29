package me.hxkandwal.daily.challanges.leetcode;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import me.hxkandwal.daily.challanges.AbstractCustomTestRunner;

/**
 * 388. Longest Absolute File Path
 * 
 * Suppose we abstract our file system by a string in the following manner:
 * 
 * The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:
 * 
 * dir subdir1 subdir2 file.ext
 * 
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory
 * subdir2 containing a file file.ext.
 * 
 * The string
 * "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
 * represents:
 * 
 * dir subdir1 file1.ext subsubdir1 subdir2 subsubdir2 file2.ext
 *
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1
 * contains a file file1.ext and an empty second-level sub-directory subsubdir1.
 * subdir2 contains a second-level sub-directory subsubdir2 containing a file
 * file2.ext.
 * 
 * We are interested in finding the longest (number of characters) absolute path
 * to a file within our file system.
 * 
 * For example, in the second example above, the longest absolute path is "dir/subdir2/subsubdir2/file2.ext", and its length is 32
 *  
 * (not including the double quotes)
 * 
 * @author Hxkandwal
 *
 */
public class LongestAbsoluteFilePath extends AbstractCustomTestRunner {
	
	private static LongestAbsoluteFilePath _instance = new LongestAbsoluteFilePath();
	
	private Map<Integer, Integer> levelMaxMap = new HashMap<>();
	
	private static int index = 0;
	
	private LongestAbsoluteFilePath() {}

	public int lengthLongestPath(String input) {
		Pattern pattern = Pattern.compile("\\n");
		Matcher matcher = pattern.matcher(input);
		
		if (matcher.find()) {
			levelMaxMap.put(0, matcher.end() - matcher.start());
			index = matcher.end();
			return lengthLongestPathInner(0, matcher);
		}
		
		return input.length();
	}
	
	public int lengthLongestPathInner(int parentLevel, Matcher matcher) {
		if (matcher.find()) {
			
		} else {
//			matcher.region(matcher.end(), 
		}
		return 0;
	}

	@Override
	public Object coreTestRun(Method method, Object[] externalVariables) {	
		return null;
	}
	
}
