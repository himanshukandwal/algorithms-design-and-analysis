package challenges.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import challenges.AbstractCustomTestRunner;

/**
 * 638. Shopping Offers
 * 
 * In LeetCode Store, there are some kinds of items to sell. Each item has a price.
 * However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.
 * 
 * You are given the each item's price, a set of special offers, and the number we need to buy for each item. The job is to output 
 * the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers.
 * 
 * Each special offer is represented in the form of an array, the last number represents the price you need to pay for this special 
 * offer, other numbers represents how many specific items you could get if you buy this offer.
 * 
 * You could use any of special offers as many times as you want.
 * 
 * Example 1:
 * 		Input	: [2,5], [[3,0,5],[1,2,10]], [3,2]
 * 		Output	: 14
 * 
 * 		Explanation: There are two kinds of items, A and B. Their prices are $2 and $5 respectively.
 * 					 In special offer 1, you can pay $5 for 3A and 0B
 * 					 In special offer 2, you can pay $10 for 1A and 2B.
 * 					 You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
 * 
 * Example 2:
 * 		Input	: [2,3,4], [[1,1,0,4],[2,2,1,9]], [1,2,1]
 * 		Output	: 11
 * 		Explanation: The price of A is $2, and $3 for B, $4 for C.
 * 					 You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C.
 * 					 You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C.
 * 					 You cannot add more items, though only $9 for 2A ,2B and 1C.
 * 
 * Note:
 * 	-	There are at most 6 kinds of items, 100 special offers.
 * 	-	For each item, you need to buy at most 6 of them.
 * 	-	You are not allowed to buy more items than you want, even if that would lower the overall price.
 * 
 * @author Hxkandwal
 */
public class ShoppingOffers extends AbstractCustomTestRunner {

	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return dfs (new HashMap<> (), price, special, needs);
    }
    
    private int dfs (Map<String, Integer> map, List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        if (map.containsKey (needs.toString())) return map.get (needs.toString());
        
        int sum = Integer.MAX_VALUE;
        for (List<Integer> offer : special) {
            List<Integer> reducedNeeds = reduce (offer, needs);
            if (reducedNeeds != null) 
                sum = Math.min (sum, offer.get (offer.size () - 1) + dfs (map, price, special, reducedNeeds));
        }
        
        if (sum == Integer.MAX_VALUE) {
            sum = 0;
            for (int idx = 0; idx < needs.size(); idx ++) sum += needs.get (idx) * price.get (idx);
        }
        
        map.put (needs.toString(), sum);
        return sum;
    }
    
    private List<Integer> reduce (List<Integer> offer, List<Integer> needs) {
        List<Integer> reducedNeeds = new ArrayList<>();
        for (int idx = 0; idx < needs.size (); idx ++) 
            if (offer.get (idx) > needs.get (idx)) return null;
            else reducedNeeds.add (needs.get (idx) - offer.get (idx));
        return reducedNeeds;
    }
    
}
