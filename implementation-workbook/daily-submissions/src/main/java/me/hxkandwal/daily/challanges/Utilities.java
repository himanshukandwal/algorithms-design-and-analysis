package me.hxkandwal.daily.challanges;

import java.util.Iterator;
import java.util.List;

/**
 * common class holding commonly used utility methods.
 * 
 * @author Hxkandwal
 *
 */
public class Utilities {
	
	public static <T> String printList(List<T> list) {
		StringBuilder sb = new StringBuilder();
		
		for (Iterator<T> collectorIterator = list.iterator(); collectorIterator.hasNext();) {
			sb.append(collectorIterator.next());
			
			if (collectorIterator.hasNext())
				sb.append(",");
		}
		
		return sb.toString();
	}

}
