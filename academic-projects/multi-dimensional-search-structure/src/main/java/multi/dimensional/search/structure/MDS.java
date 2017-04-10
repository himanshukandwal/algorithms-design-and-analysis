package multi.dimensional.search.structure;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import multi.dimensional.search.structure.model.Item;
import multi.dimensional.search.structure.model.MDSItemStorage;

/**
 * Multi-Dimensional Data Structure created to store the data in such a fashion that it makes 
 * easier for user to search on various parameters like : 	
 * 		- id (long int), 
 * 		- description (one or more long ints), 
 * 		- price (dollars and cents)
 * 
 * This class acts as a facade to the MDSItemStorage, where the collection data actually resides.
 *  
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class MDS {
	
	private MDSItemStorage storage; // access to the singleton MDS-storage instance.
	
	public final static double EPSILON = 0.000001d; 
	
	public MDSItemStorage getStorage() {
		if (storage == null)
			storage = new MDSItemStorage();
		return storage;
	}
	
	/**
	 * An insert operation to the MDSItemStorage.
	 * 
	 * @param id
	 * @param price
	 * @param description
	 * @param size
	 * @return
	 */
	public int insert(long id, double price, long[] description, int size) {

		if (find(id) != 0) {
			Item previousItem = findItem(id);
			
			Double previousPrice = previousItem.getPrice();
			List<Long> previousDescription = previousItem.getDescription();
			
			if (size > 0) {
				Integer previousDescriptionHash = previousItem.getDescriptionHash();
				
				// Description of item is in description[0..size-1]. copy them into your data structure.
				previousItem.setDescription(size, (size == 0) ? null : description); 
				
				// adjust previousDescription
				getStorage().updateDescriptionDataSet(previousItem, previousDescription, price, (previousPrice == price ? null : previousPrice));

				// adjust previousPrice
				getStorage().updatePriceDataSet(previousItem, previousPrice);
				
				// refresh and re-check associated same-same hash with the previous item.
				getStorage().addressSameSameHashing(previousItem, previousDescriptionHash);
			} else {
				if (previousPrice != price) {
					if (size == 0)
						getStorage().updateDescriptionDataSet(previousItem, null, price, previousPrice);

					// adjust previousPrice
					getStorage().updatePriceDataSet(previousItem, previousPrice);
				}
			}
			
			return 0;
		} 

		// create the Item object.
		Item item = new Item();
		item.setId(id)
			.setPrice(price)
			.setDescription(size, (size == 0) ? null : description);
		
		// register new item
		getStorage().registerItem(item);
		
		return 1;
	}

	public double find(long id) {
		return  roundOff (getStorage().getIdItemsMap().containsKey(id) ? getStorage().getIdItemsMap().get(id).getPrice() : 0d);
	}
	
	public Item findItem(long id) {
		return  (getStorage().getIdItemsMap().containsKey(id) ? getStorage().getIdItemsMap().get(id) : null);
	}

	public long delete(long id) {
		Item item = findItem(id);
		if (item != null) {
			getStorage().deregister(item);
			
			long sum = 0;
			for (long description : item.getDescription())
				sum += description;
			
			return sum;
		}
		return 0;
	}

	public double findMinPrice(long des) {
		if (getStorage().getDescriptionIdsMap().containsKey(des))
			return roundOff (getStorage().getDescriptionIdsMap().get(des).firstKey());
		return 0;
	}

	public double findMaxPrice(long des) {
		if (getStorage().getDescriptionIdsMap().containsKey(des))
			return roundOff (getStorage().getDescriptionIdsMap().get(des).lastKey());
		return 0;
	}

	public int findPriceRange(long des, double lowPrice, double highPrice) {
		// calculate the range size and return the result.
		int itemsCount = 0;
		
		if ((highPrice >= lowPrice) && getStorage().getDescriptionIdsMap().containsKey(des)) {
			Map<Double, Set<Long>> rangeMap =  getStorage().getDescriptionIdsMap().get(des).subMap(lowPrice, true, highPrice, true);
			
			if (rangeMap != null && rangeMap.size() > 0)
				for (Map.Entry<Double, Set<Long>> rangeMapEntry : rangeMap.entrySet())
					itemsCount += rangeMapEntry.getValue().size();
		}
			
		return itemsCount;
	}

	public double priceHike(long minid, long maxid, double rate) {
		Map<Long, Item> rangeMap = ((TreeMap<Long, Item>) getStorage().getIdItemsMap()).subMap(minid, true, maxid, true);
		
		double totalIncrement = 0;
		if (rangeMap != null && rangeMap.size() > 0) {
			for (Map.Entry<Long, Item> rangeMapEntry : rangeMap.entrySet()) {
				Item item = rangeMapEntry.getValue();
				
				double previousPrice = item.getPrice();
				double increment = previousPrice * (rate / 100d);
				double newPrice = roundOff(previousPrice + increment);
				
				// adjust previousDescription
				getStorage().updateDescriptionDataSet(item, null, newPrice, previousPrice);
				
				// adjust previousPrice
				getStorage().updatePriceDataSet(item, previousPrice);

				// cumulative increments
				totalIncrement += roundOff(increment);
			}	
		}
		
		return roundOff (totalIncrement);
	}

	public int range(double lowPrice, double highPrice) {
		Map<Double, TreeSet<Long>> rangeMap = ((TreeMap<Double, TreeSet<Long>>) getStorage().getPriceIdsMap()).subMap(lowPrice, true, highPrice, true);
		
		int rangeItemsCount = 0;
		if (rangeMap != null && rangeMap.size() > 0) {
			for (Map.Entry<Double, TreeSet<Long>> rangeMapEntry : rangeMap.entrySet())
				rangeItemsCount += rangeMapEntry.getValue().size();
			
			return rangeItemsCount;
		}
		
		return rangeItemsCount;
	}

	public int samesame() {
		int samesameCount = 0;
		
		for (Map.Entry<Integer, TreeSet<Long>> hashedSameSameMapEntry : getStorage().getHashedSameSameMap().entrySet()) {
			// avoiding singular entries
			if (hashedSameSameMapEntry.getValue().size() > 1)
				samesameCount += hashedSameSameMapEntry.getValue().size();
		}
		
		return samesameCount;
	}
	
	/**
	 * helper function to round-off to acceptable decimal point.
	 * 
	 * @param value
	 * @return
	 */
	double roundOff (double value) {
		return Math.floor((value + EPSILON) * 100) / 100;
	}
	
}
