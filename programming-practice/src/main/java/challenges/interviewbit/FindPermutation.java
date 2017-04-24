package challenges.interviewbit;

import challenges.AbstractCustomTestRunner;

/**
 * Find Permutation
 * 
 * Given a positive integer n and a string s consisting only of letters D or I, you have to find any permutation of first n positive integer 
 * that satisfy the given input string.
 * 
 * D means the next number is smaller, while I means the next number is greater.
 * 
 * Notes
 * 	- Length of given string s will always equal to n - 1
 * 	- Your solution should run in linear time and space.
 * 
 * Example :
 * 		Input 1:	n = 3, s = ID
 * 		Return: [2, 3, 1]

 * @author Hxkandwal
 *
 */
public class FindPermutation extends AbstractCustomTestRunner {

	public ArrayList<Integer> findPerm(final String A, int B) {
        int [] arr = new int [A.length() + 1];
        char ch = ' ';
        int larger = B, smaller = 1;
        for (int idx = 0; idx < A.length(); idx ++) {
            ch = A.charAt(idx);
            arr [idx] = (ch == 'I') ? smaller ++ : larger --;
        }
        arr [arr.length - 1] = (ch == ' ' || ch == 'I') ? larger : smaller;
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int ai : arr) ans.add (ai);
        return ans;
    }
	
}
