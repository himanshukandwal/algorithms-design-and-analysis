package challenges.hackerrank;

import java.util.Arrays;
import java.util.Scanner;

import challenges.AbstractCustomTestRunner;

/**
 * Priyanka and Toys
 * 
 * Little Priyanka visited a kids' shop. There are N toys and their weight is represented by an array. 
 * Each toy costs 1 unit, and if she buys a toy with weight w, then she can get all other toys whose weight 
 * lies between [w, w + 4] (both inclusive) free of cost.
 * 
 * Find minimum units with which Priyanka could buy all of toys.
 * 
 * @author Hxkandwal
 */
public class PriyankaAndToys extends AbstractCustomTestRunner {

	public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int size = sc.nextInt();
        int [] arr = new int [size];
        for (int idx = 0; idx < size; idx ++) arr [idx] = sc.nextInt ();
        sc.close();
        
        Arrays.sort (arr);
        int ans = 0;
        for (int idx = 0; idx < size; idx ++) {
            ans ++;
            int end = arr [idx] + 4;
            while (idx + 1 < arr.length && arr [idx + 1] <= end) idx ++;
        }
        System.out.println(ans);
    }
	
}
