package challenges.leetcode;

import static com.google.common.truth.Truth.assertThat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import challenges.AbstractCustomTestRunner;

/**
 * 588. Design In-Memory File System
 * 
 * Design an in-memory file system to simulate the following functions:
 * 
 * ls: 		
 * 			Given a path in string format. If it is a file path, return a list that only contains this file's name. 
 * 			If it is a directory path, return the list of file and directory names in this directory. 
 * 			Your output (file and directory names together) should in lexicographic order.
 * 
 * mkdir: 	
 * 			Given a directory path that does not exist, you should make a new directory according to the path. If the 
 * 			middle directories in the path don't exist either, you should create them as well. This function has void 
 * 			return type.
 * 
 * addContentToFile: 
 * 			Given a file path and file content in string format. If the file doesn't exist, you need to create 
 * 			that file containing given content. If the file already exists, you need to append given content to original 
 * 			content. This function has void return type.
 * 
 * 
 * readContentFromFile: 
 * 			Given a file path, return its content in string format.
 * 
 * Example:
 * 		Input:	["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
 * 				[[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
 * 
 * 		Output:	[null,[],null,null,["a"],"hello"]
 * 
 * @author Hxkandwal
 */
public class DesignInMemoryFileSystem extends AbstractCustomTestRunner {

	private Map<String, Set<String>> dirMap = new HashMap<>();
	private Map<String, String> contentMap = new HashMap<>();
    private Comparator<String> comparator = (a, b) -> { 
    	if (a.contains(b) && !a.equals(b)) return 1;
    	if (b.contains(a) && !a.equals(b)) return -1;
    	return a.compareTo (b);
    };
    
    public DesignInMemoryFileSystem() {
        dirMap.put ("/", new TreeSet<>(comparator));
    }
    
    public List<String> ls(String path) {
    	List<String> ans = new ArrayList<>();
    	if (contentMap.containsKey(path)) return Arrays.asList(path.substring(path.lastIndexOf("/") + 1));
    	for (String dir : dirMap.getOrDefault (path, new TreeSet<>(comparator))) 
    		ans.add(dir.substring(path.length() + (path.charAt(path.length() - 1) == '/' ? 0 : 1)));
    	return ans;
    }
    
    public void mkdir(String path) {
        String prev = "/";
        for (int idx = 1; idx <= path.length(); idx ++) {
            if (idx == path.length() || path.charAt (idx) == '/') {
                String dir = path.substring (0, idx); 
                dirMap.computeIfAbsent (prev, k -> new TreeSet<>(comparator)).add (dir);
                prev = dir;
            }
        }
        dirMap.putIfAbsent (path, new TreeSet<>(comparator));
    }
    
    public void addContentToFile(String filePath, String content) {
    	mkdir(filePath);
        contentMap.put (filePath, contentMap.getOrDefault (filePath, "") + content);
    }
    
    public String readContentFromFile(String filePath) {
        return contentMap.getOrDefault (filePath, "");
    }

	// driver method
	public static void main(String[] args) {
		testCase1 ();
		testCase2 ();
		testCase3 ();
		testCase4 ();
		System.out.println ("ok!");
	}
	
	private static void testCase1() {
		DesignInMemoryFileSystem imfs = new DesignInMemoryFileSystem();
		assertThat(imfs.ls("/")).isEqualTo(Arrays.asList());
		imfs.mkdir("/a/b/c");
		imfs.addContentToFile("/a/b/c/d","hello");
		assertThat(imfs.ls("/")).isEqualTo(Arrays.asList("a"));
		assertThat(imfs.readContentFromFile("/a/b/c/d")).isEqualTo("hello");
	}

	private static void testCase2() {
		DesignInMemoryFileSystem imfs = new DesignInMemoryFileSystem();
		assertThat(imfs.ls("/")).isEqualTo(Arrays.asList());
		imfs.mkdir("/a/b/c");
		assertThat(imfs.ls("/a/b")).isEqualTo(Arrays.asList("c"));
	}
	
	private static void testCase3() {
		DesignInMemoryFileSystem imfs = new DesignInMemoryFileSystem();
		imfs.mkdir("/goowmfn");
		assertThat(imfs.ls("/goowmfn")).isEqualTo(Arrays.asList());
		assertThat(imfs.ls("/")).isEqualTo(Arrays.asList("goowmfn"));
		imfs.mkdir("/z");
		assertThat(imfs.ls("/")).isEqualTo(Arrays.asList("goowmfn", "z"));
		imfs.addContentToFile("/goowmfn/c","shetopcy");
		assertThat(imfs.ls("/z")).isEqualTo(Arrays.asList());
		assertThat(imfs.ls("/goowmfn/c")).isEqualTo(Arrays.asList("c"));
		assertThat(imfs.ls("/goowmfn")).isEqualTo(Arrays.asList("c"));
	}

	private static void testCase4() {
		DesignInMemoryFileSystem imfs = new DesignInMemoryFileSystem();
		assertThat(imfs.ls("/")).isEqualTo(Arrays.asList());
		imfs.mkdir("/a/b/c");
		imfs.mkdir("/a/c");
		imfs.mkdir("/a/b/a");
		imfs.mkdir("/a/c/a");
		imfs.mkdir("/a/c/c");
		imfs.mkdir("/a/c/aa");;
		assertThat(imfs.ls("/a/c")).isEqualTo(Arrays.asList("a","aa","c"));
		assertThat(imfs.ls("/a/b")).isEqualTo(Arrays.asList("a","c"));
		assertThat(imfs.ls("/a")).isEqualTo(Arrays.asList("b","c"));
	}
}
