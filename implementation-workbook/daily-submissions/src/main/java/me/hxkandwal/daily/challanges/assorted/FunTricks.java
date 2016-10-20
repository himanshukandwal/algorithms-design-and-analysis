package me.hxkandwal.daily.challanges.assorted;

/**
 * a collection of cool tricks.
 * 
 * @author Hxkandwal
 *
 */
public class FunTricks {

	public static void main(String[] args) {
		// fun with bit operations
		
		// this works because -2 is 1110, i.e. last digit is zero, which never allows any odd number to pass though
		System.out.println("Pairwise increment : ");
		for (int idx = 0; idx < 10; idx ++) 
			System.out.println(idx + " " + (idx & -2));
		
	}

}
