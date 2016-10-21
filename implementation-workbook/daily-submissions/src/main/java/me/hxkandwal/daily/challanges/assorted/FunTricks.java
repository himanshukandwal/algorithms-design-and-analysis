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
		
		// this works because -2 is 11111110, i.e. last digit is zero, which never allows any odd number to pass though
		System.out.println("Pairwise increment : ");
		for (int idx = 0; idx < 10; idx ++) 
			System.out.println(idx + " " + (idx & -2));
		
		// power of negation
		System.out.println("Power of negation :  (closing to nearest two for odd number : (n + 1)/2)");
		System.out.println("~x equals (-x)-1 = -(x + 1) \n");
		for (int idx = 0; idx < 10; idx++) {
			System.out.println(idx + " :  (-~idx) : " + (-~idx) + " (~-idx) :" + (~-idx) +  " (-~idx/2) : " + (-~idx/2));
		}
		
		/* power of two (for power of two, there will be only one 1 and all other zeros, so when we decrement it will one, 
		 * all the zeros become 1 and 1 becomes zero. So AND operation will be zero always.
		 * 		for 2 : 10 & 01
		 * 		for 4 : 100 & 011
		 * 		for 8 : 1000 & 0111
		 * 
 		 * for any other number it will be more than zero, as atleast some places will have 1 (one or more times)
 		 * 		for 3 : 11 & 10 
 		 * 		for 5 : 101 & 100
 		 * 		for 11 : 1011 & 1010 
		 */
		System.out.println();
		System.out.println("power of 2 check : (v & (v - 1) == 0) ");
		System.out.println("for 3 : " + ((3 & (2 - 1)) == 0));
		System.out.println("for 4 : " + ((4 & (4 - 1)) == 0));
		System.out.println("for 6 : " + ((6 & (6 - 1)) == 0));
	}

}
