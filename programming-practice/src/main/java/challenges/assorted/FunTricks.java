package challenges.assorted;

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
		
		System.out.println();
		// cool trick to append computed number of zeros in front of a number.
		System.out.println(String.format("%" + Integer.toString(8) + "s", " "));	// create 8 white spaces
		System.out.println(String.format("%" + Integer.toString(8) + "s", "abc"));	// create 8 white spaces, and try to fill abc in that
		// create 8 white spaces, and try to fill Integer.toString(3, 2) in that, and the space remaining can be replaced with 0s.
        System.out.println(String.format("%" + Integer.toString(8) + "s", Integer.toString(3, 2)).replace(" ","0")); 
        
        // create 5 white spaces, and try to fill spaces in that, and the spaces can be replaced with 9s.
        System.out.println(Integer.valueOf(String.format("%" + 5 +"s", " ").replace(" ", "9")));
        
        // to cover range for all upper lower alphabets 
        int[] count = new int['z' - 'A' + 1];
        
        /* For max, use class not primitives. If null, assign first value else compute max. Handle return statement wisely. */
        
        /* Keep code simple, crisp and fast. (too much logic, will make you look at more potential breakpoints). Always read question properly. */
        
        /* Euclid formula for GCD and LCM is only for two number. For more extension, use piping. */
        
        /* In (poorly) unstructured problems (like graphs, trees) or multiple  (uncontable) case handling scenarios, run the recursion insect and let will
         * spawn its web for us (branching non-uniformly if necessary). Handle or focus only on base case. Avoid looping (send collector that manages 
         * states discovered kinda-like graph search) and if found break it.
         * Even consider this step for the case when we have multiple steps to choose at a junction. (try all strategy)
         * power the algorithm then with DP (managing sub-states).
         */
		
        /* Always go for the Test driven development (TDD). to understand the question, write down all the corner test cases that you can think of, this will give you
         * a good understanding of the question, whats expected and also this is a time where we can devise the strategy while usefully channeling our attention.
         */
        
        /* Calculation of binomial coefficients. DP solution : in catalan numbers.
         * This strategy provides solutions to varied problems, from finding path in a grid to counting combinations. 
         * 
         * Must visit: 	http://mathworld.wolfram.com/BinomialCoefficient.html
         * 				http://www.robertdickau.com/lattices.html				<-- regular problem.
         * 				http://www.robertdickau.com/catalan.html				<-- not to go below horizon. (no crossing is a very big hint)
         * 				http://www.robertdickau.com/delannoy.html#schroeder 	<-- extra direction.
         * 				http://mathcircle.berkeley.edu/BMC6/pdf0607/catalan.pdf
         */

	}

}
