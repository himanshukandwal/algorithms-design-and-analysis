package me.hxkandwal.daily.challanges.codefights;

import static org.junit.Assert.assertArrayEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * You're given an array of names, where each name is given as a string. Your
 * task is to link these names into a chain, so that the ith name starts with
 * the same letter the (i - th)th name ends, and return this chain as a list.
 * 
 * All names should be used. It is guaranteed that each name starts with a
 * unique letter. It is also guaranteed that there is only one solution.
 * 
 * Example : names = ["Raymond", "Nora", "Daniel", "Louie", "Peter", "Esteban"]
 * 
 * chainNames(names) = ["Peter", "Raymond", "Daniel", "Louie", "Esteban", "Nora"]
 * 
 * @author Heman
 *
 */
public class ChainNames {

	public static void main(String[] args) {
		
		assertArrayEquals(new String[] {"Peter", "Raymond", "Daniel", "Louie", "Esteban", "Nora"}, 
				chainNamesBackTracking(new String[] {"Raymond", "Nora", "Daniel", "Louie", "Peter", "Esteban"}));
		
		assertArrayEquals(new String[] {"Haydee", "Elliott", "Tai", "Inocencia", "Archie"}, 
				chainNamesBackTracking(new String[] {"Haydee", "Tai", "Elliott", "Inocencia", "Archie"}));
		
		System.out.println("ok !");
	}
	
	
	/* Back-tracking */
	//TODO work on this.
	
	public static String[] chainNamesBackTracking(String[] names) {
		LinkedList<String> ll = new LinkedList<>();
		
 		for (int idx = 0; idx < names.length; idx ++) {
 			ll.add(names[idx]);
 			
 			while (increamentable(ll, names));
 			
 			if (ll.size() == names.length)
 				return ll.toArray(new String[0]);
 			else 
 				ll.clear();
		}
 		
 		return null;
	}
	
	public static boolean increamentable(LinkedList<String> ll, String[] names) {
		for (String name : names) {
			if (!ll.contains(name) && ll.getLast().toLowerCase().charAt(ll.getLast().length() -1) == name.toLowerCase().charAt(0)) {
				ll.add(name);
				return true;
			}
		}
		return false;
	}
	
	/* Brute force (full blown) */
	public static String[] chainNames(String[] names) {
		if (names.length == 1)
			return names;
		else {
			for (int idx = 0; idx < names.length; idx++) {
				String[] answer = chainNamesInner(names[idx].toLowerCase().charAt(names[idx].length() -1), remainingArray(names, idx));
				
				if (answer != null) {
					List<String> list = new ArrayList<>();
					list.add(names[idx]);

					for (String name : answer)
						list.add(name);
							
					return list.toArray(new String[0]);	
				}
			}
			return null;
		}
	}
	
	public static String[] chainNamesInner(char parentChar, String[] names) {
		if (names.length == 1 && names[0].toLowerCase().charAt(0) == parentChar)
			return names;
		else if (names.length == 1 && names[0].toLowerCase().charAt(0) != parentChar)
			return null;
		else {
			String[] answer = null;
			for (int idx = 0; idx < names.length; idx++) {
				String[] futureNames = chainNamesInner(names[idx].toLowerCase().charAt(names[idx].length() -1), remainingArray(names, idx));
				
				if (futureNames != null && names[idx].toLowerCase().charAt(0) == parentChar) {
					List<String> list = new ArrayList<>();
					list.add(names[idx]);

					for (String name : futureNames)
						list.add(name);
						
					answer = list.toArray(new String[0]);
				}
			}

			return answer;
		}
	}
	
	public static String[] remainingArray(String[] names, int pos) {
		String[] res = new String[names.length -1];
		
		int idx = 0;
		for (int i = 0; i < names.length; i ++)
			if (i != pos)
				res [idx ++] = names [i];

		return res;
	}

}
