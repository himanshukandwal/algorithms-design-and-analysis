package multi.dimensional.search.structure.model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeMap;

/**
 * an abstract class to hold all the boiler plate code and trivial operations.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public abstract class AbstractItemStorage {

	/**
	 * item price comparator
	 */
	protected static final Comparator<Double> pricesComparator = new Comparator<Double>() {
		
		@Override
		public int compare(Double o1, Double o2) {
			return o1.compareTo(o2);
		}
		
	};
	
	/**
	 * remove feature in indexes.
	 * 
	 * @param item
	 */
	protected void remove(TreeMap<Double, Set<Long>> map, Item item, Double price) {
		if (price == null) 
			price = item.getPrice();
	
		// 	remove from sorted price map
		if (map.containsKey(price)) {
			map.get(price).remove(item.getId());

			// in-case all the items associated with that price get removed, remove the price (key) too. This would ignore the false positives.
			if (map.get(price).isEmpty() || map.get(price).size() == 0)
				map.remove(price);
		}
	}
	
	protected void remove(TreeMap<Double, Set<Long>> map, Item item) {
		remove(map, item, null);
	}

	/**
	 * add feature in indexes.
	 * 
	 * @param item
	 */
	@SuppressWarnings("serial")
	protected void add(TreeMap<Double, Set<Long>> map, final Item item) {
		// add item in sorted prices map
		if (map.containsKey(item.getPrice()))
			map.get(item.getPrice()).add(item.getId());
		else
			map.put(Double.valueOf(item.getPrice()), new HashSet<Long>() {{ add(item.getId()); }});
	}

	protected void add(TreeMap<Double, Set<Long>> map, Item item, Double oldPrice) {
		add(map, item);
		
		if (oldPrice != null && (oldPrice.compareTo(item.getPrice()) != 0))
			remove(map, item, oldPrice);
	}
	
}
