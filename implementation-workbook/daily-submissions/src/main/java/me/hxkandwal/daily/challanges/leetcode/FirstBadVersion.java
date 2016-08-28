package me.hxkandwal.daily.challanges.leetcode;

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
    
	// used concept of binary search to find the first bad version.
    public int firstBadVersion(int n) {
        if (n == 0 || n == 1)
            return n;
            
        int low = 1;
        int high = n;
        int mid = 0;
        
        while (low <= high) {
            mid = (low + high) >>> 1;
            
            if (isBadVersion(mid)) {
                if (!isBadVersion(mid - 1))
                    return mid;
                else 
                    return firstBadVersion(mid - 1);
            } else
                low = mid + 1;
        }
        
        return mid;
    }
    
    // method 2 : much better and makes less calls to isBadVersion(int) api method.
    public int findFirstBadVersion2(int n) {
        // write your code here
        if (n == 0) {
            return -1;
        }

        int start = 1, end = n, mid;
        while (start + 1 < end) {
            mid = start + (end - start)/2;
            if (isBadVersion(mid) == false) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (isBadVersion(start) == true) {
            return start;
        } else if (isBadVersion(end) == true) {
            return end;
        } else {
            return -1; // not found
        }
    }
    
    // mocked API method.
    private boolean isBadVersion(int num) {
    	return true;
	}
    
}
