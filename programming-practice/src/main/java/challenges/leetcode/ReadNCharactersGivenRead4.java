package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 157. Read N Characters Given Read4
 * 
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 * 
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters 
 * left in the file. By using the read4 API, implement the function int read(char *buf, int n) that reads n characters 
 * from the file.
 * 
 * Note: The read function will only be called once for each test case.
 * 
 * @author Hxkandwal
 */
public class ReadNCharactersGivenRead4 extends AbstractCustomTestRunner {
	
	public int read(char[] buf, int n) {
        int total = 0;
        char [] temp = new char [4];
        
        while (n > 0) {
            int len = read4 (temp);
            len = Math.min (len, n);
            for (int idx = 0; idx < len; idx ++) buf [total ++] = temp [idx];
            if (len == 0 || len >= n) break;
            n -= len;
        }
        
        return total;
    }
	
	/* The read4 API is defined in the parent class Reader4.*/
    private int read4(char[] buf) { return 0; }

}
