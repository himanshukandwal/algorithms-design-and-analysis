package challenges.leetcode;

import challenges.AbstractCustomTestRunner;

/**
 * 158. Read N Characters Given Read4 II - Call multiple times
 *
 * The API: int read4(char *buf) reads 4 characters at a time from a file.
 *
 * The return value is the actual number of characters read. For example, it returns 3 if there is only 3 characters left in the file.
 * By using the read4 API, implement the function int read(char *buf, int n) that reads n characters from the file.
 *
 * Note: The read function may be called multiple times.
 *
 * Example 1:
 *      Given buf = "abc"
 *      read("abc", 1) // returns "a"
 *      read("abc", 2); // returns "bc"
 *      read("abc", 1); // returns ""
 *
 * Example 2:
 *      Given buf = "abc"
 *      read("abc", 4) // returns "abc"
 *      read("abc", 1); // returns ""
 *
 * @author Hxkandwal
 */
public class ReadNCharactersGivenRead4II_CallMultipleTimes extends AbstractCustomTestRunner {

    /**
     * @param buf Destination buffer
     * @param n   Maximum number of characters to read
     * @return    The number of characters read
     */
    private int lrIdx = 0;
    private int len = 0;
    private char [] temp = new char [4];

    public int read(char[] buf, int n) {
        int idx = 0;

        while (n > 0) {
            for (; lrIdx < len && n > 0;  lrIdx ++, n --) buf [idx ++] = temp [lrIdx];
            if (n > 0) {
                len = read4(temp);
                if (len == 0) break;
                lrIdx = 0;
            }
        }

        return idx;
    }

    /* The read4 API is defined in the parent class Reader4.*/
    private int read4(char[] buf) { return 0; }
}
