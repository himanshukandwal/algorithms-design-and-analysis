package challenges.leetcode;

/**
 * 278. First Bad Version
 * 
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest 
 * version of your product fails the quality check. Since each version is developed based on the previous version, 
 * all the versions after a bad version are also bad.
 * 
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following
 * ones to be bad. 
 * 
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a 
 * function to find the first bad version. You should minimize the number of calls to the API.
 * 
 * @author Hxkandwal
 *
 */
public class FirstBadVersion {
    
    public int findFirstBadVersion2(int n) {
        if (n == 0) return -1;
        int start = 1, end = n;
        
        // the while loop will break when start and end are next to each other. Then check just start and end.
        while (start + 1 < end) {
            int mid = (start + end) >>> 1;
            if (!isBadVersion(mid)) start = mid;
            else end = mid;
        }

        if (isBadVersion(start)) return start;
        else if (isBadVersion(end)) return end;
        else return -1; // not found
    }
    
    // mocked API method.
    private boolean isBadVersion(int num) {
    	return true;
	}
    
}
