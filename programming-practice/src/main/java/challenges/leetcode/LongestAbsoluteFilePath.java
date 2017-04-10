package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import challenges.AbstractCustomTestRunner;

/**
 * 388. Longest Absolute File Path
 * 
 * Suppose we abstract our file system by a string in the following manner:
 * 
 * The string
 *  
 * 		"dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext"
 *  
 * represents:
 * 
 * dir 
 * 		subdir1 
 * 		subdir2 
 * 			file.ext
 * 
 * The directory dir contains an empty sub-directory subdir1 and a sub-directory subdir2 containing a file file.ext.
 * 
 * The string
 * 
 * 		"dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext"
 * 
 * represents:
 * 
 * dir 
 * 		subdir1 
 * 			file1.ext 
 * 			subsubdir1 
 *		subdir2 
 *			subsubdir2 
 *				file2.ext
 *
 * The directory dir contains two sub-directories subdir1 and subdir2. subdir1 contains a file file1.ext and an empty second-level 
 * sub-directory subsubdir1. 
 * 
 * subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.
 * 
 * We are interested in finding the longest (number of characters) absolute path to a file within our file system.
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
	
	private LongestAbsoluteFilePath() {}
	
	/**
	 * Notes : use index only while working with big strings.
	 * 		   process information required while building the tree (avoid re-recursion for process only)
	 * 		   if have to recurse, make it one pass.
	 * 
	 * Other approaches (avoided for too much string processing)
	 * 	- substrings
	 * 	- stringTokenizer
	 * 	- Regex processing
	 * 
	 * @author Hxkandwal
	 */
	// data-structure to store the information
	public static class DirectoryTreeNode {
		public DirectoryTreeNode parent;
		public String name;
		public List<DirectoryTreeNode> children = new ArrayList<>();
		public int depth;
		public int maxArmLength;	// this is max of children maxArmLength + name.length()
		
		public DirectoryTreeNode(String name, int depth) { 
			this.name = name;
			this.depth = depth;
		}
		
		public DirectoryTreeNode(String name) { 
			this(name, 0);
		}
		
		@Override
		public String toString() { return this.name; }
	}

	public int lengthLongestPath(String input) {
		if (input == null || input.length() == 0)
			return 0;
	
		int idx = 0;
		int maxLength = 0;
		
		while (idx < input.length()) {
			boolean isFile = false;
		
			StringBuilder sb = new StringBuilder();
			for (; idx < input.length(); idx ++) {
				char ch = input.charAt(idx) ;
				if (ch == '\n')
					break;
				
				sb.append(ch);
				
				if (ch == '.')
					isFile = true;
			}
			
			DirectoryTreeNode root = new DirectoryTreeNode(sb.toString());
			
			int localMaxLength = 0;
			if (!isFile) {
				idx = generateDirectoryTreeRecursively (input, idx + 1, root);
				localMaxLength = root.maxArmLength;
			} else
				localMaxLength = sb.length();
			
			if (localMaxLength > maxLength)
				maxLength = localMaxLength;
		}
		
		return maxLength;
	}
	
	// generates the complete directory tree from the string representation.
	private static int generateDirectoryTreeRecursively(String input, int startIdx, DirectoryTreeNode parentNode) {
		int depth = 0;
		int idx = startIdx;
		StringBuilder sb = new StringBuilder();
		
		boolean isFile = false, foundtab = false;
		int spacecount = 0;
		for (; idx < input.length(); idx ++) {
			char ch = input.charAt(idx);
			if (ch == '\n')
				break;	
			
			if (ch == '\t') {
				foundtab = true;
				depth ++;
			} else if (ch == ' ' && spacecount < 4 &&  !foundtab) {
				spacecount ++;
				
				if (spacecount == 4) 
					depth ++;
			} else {
				sb.append(ch);
				
				if (ch == '.')
					isFile = true;
			}
		}
		
		DirectoryTreeNode validParent = parentNode;
		while (validParent != null && depth != validParent.depth + 1)
			validParent = validParent.parent;
		
		if (validParent != null) {
			DirectoryTreeNode node = new DirectoryTreeNode(sb.toString(), depth);
			
			if (isFile) {
				int currentLength = sb.length(); 		
				
				DirectoryTreeNode parentHierarchyTraverser = validParent;
				
				while (parentHierarchyTraverser != null && 
						parentHierarchyTraverser.maxArmLength < (currentLength + parentHierarchyTraverser.name.length() + 1)) { // + 1 for / in the path.
					currentLength = parentHierarchyTraverser.maxArmLength = currentLength + parentHierarchyTraverser.name.length() + 1;
					parentHierarchyTraverser = parentHierarchyTraverser.parent;
				}
			}
			
			// attach parent to child and child to parent.
			validParent.children.add(node);
			node.parent = validParent;
			
			if (idx + 1 < input.length())
				 return generateDirectoryTreeRecursively (input, idx + 1, node);
		}
		
		return startIdx;
	}

	// method 2 : simple + cleaner. (stack used only to keep track of directories and their length. Max file length is kept with the max variable.
	public int _lengthLongestPath2(String input) {
		int maxLength = 0, pathLength = 0, currentDepth = 0, idx = 0, len = input.length();
        Stack <Integer> stk = new Stack<>();
        
        while (idx < len) {
            boolean isFile = false;
            int currentLength = 0;
            
            while (stk.size() > currentDepth) pathLength -= stk.pop();
            
            for (; idx < len && input.charAt(idx) != '\n'; idx ++, currentLength ++) 
                if (input.charAt(idx) == '.') isFile = true;
            
            idx ++;
            if (isFile) maxLength = Math.max (maxLength, pathLength + currentLength);
            else pathLength += stk.push (currentLength + 1);
            
            for (currentDepth = 0; idx < len && input.charAt(idx) == '\t'; idx ++, currentDepth ++); 
        }
        
        return maxLength;
	}
	
	// driver method
	public static void main(String[] args) {
		_instance.runTest("", 0);
		_instance.runTest("a", 0);
		_instance.runTest("dir\n	file.txt", 12);
		_instance.runTest("dir\n    file.txt", 12);
		_instance.runTest("dir\n        file.txt", 16);
		_instance.runTest("dir\n\t        file.txt\n\tfile2.txt", 20);
		_instance.runTest("a\n\tb.txt\na2\n\tb2.txt", 9);
		_instance.runTest("dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext", "dir/subdir2/file.ext".length());
		_instance.runTest("dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext", 
				"dir/subdir2/subsubdir2/file2.ext".length());
	}
	
	public void runTest(final String s, final int expectedOutput) {
		List<Object> answers = runAll(getClass(), new Object[] { s });
		
		for (Object answer : answers) 
			assertThat((Integer) answer).isEqualTo(expectedOutput);
		
		System.out.println("ok!");
	}
	
}