package multi.dimensional.search.structure.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * A class representing the in-memory database, upholding the data which is entered
 * by the MDS facade.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class MDSItemStorage extends AbstractItemStorage {
	
	/* index maps id with Item to provide faster search results */
	private Map<Long, Item> idItemsMap;

	/* index maps description with map of price and Items, to provide faster search results */
	private Map<Long, TreeMap<Double, Set<Long>>> descriptionIdsMap;

	/* index maps price with Items, to provide faster search results */
	private Map<Double, TreeSet<Long>> priceIdsMap;
	
	/* a simple map that keeps the hash-code of the arrays whose length is greater than 8 
	 * Treeset because it provides better interface to retrieve elements , other than using iterator (sometimes seems unnecessary) */
	private Map<Integer, TreeSet<Long>> hashedSameSameMap;

	/* a simple hash-set acting as a bookkeeper, hence it notes which ids have made entry to hashedSameSameMap. managed parallel with the hashedSameSameMap.  */
	private HashSet<Long> samesameBookkeepingSet;
	
	/**
	 * getter method for 'idItemsMap' map.
	 * 
	 * @return
	 */
	public Map<Long, Item> getIdItemsMap() {
		if (idItemsMap == null)
			idItemsMap = new TreeMap<Long, Item>();

		return idItemsMap;
	}

	/**
	 * getter method for 'descriptionIdsMap' map.
	 * 
	 * @return
	 */
	public Map<Long, TreeMap<Double, Set<Long>>> getDescriptionIdsMap() {
		if (descriptionIdsMap == null) 
			descriptionIdsMap = new TreeMap<>();
		
		return descriptionIdsMap;
	}

	/**
	 * getter method for 'priceIdsMap' map.
	 * 
	 * @return
	 */
	public Map<Double, TreeSet<Long>> getPriceIdsMap() {
		if (priceIdsMap == null)
			priceIdsMap = new TreeMap<>();

		return priceIdsMap;
	}
	
	/**
	 * getter method for 'hashedSameSameMap' map.
	 * 
	 * @return
	 */
	public Map<Integer, TreeSet<Long>> getHashedSameSameMap() {
		if (hashedSameSameMap == null) 
			hashedSameSameMap = new HashMap<>();
		
		return hashedSameSameMap;
	}
	
	/**
	 * getter method for 'samesameBookkeepingSet' set.
	 * 
	 * @return
	 */
	public HashSet<Long> getSamesameBookkeepingSet() {
		if (samesameBookkeepingSet == null) 
			samesameBookkeepingSet = new HashSet<>();
		
		return samesameBookkeepingSet;
	}

	/**
	 * method to manage the descriptionIdsMap following an update of an item. 
	 * we would receive the old and new description. 
	 * 
	 * @return
	 */
	public void updateDescriptionDataSet(final Item item, List<Long> previousDescription, final Double newPrice, final Double previousPrice) {
		
		if (previousDescription != null) {
			Iterator<Long> oldInputIterator = previousDescription.iterator();
			Iterator<Long> newInputIterator = item.getDescription().iterator();
			
			Long fromOld = oldInputIterator.next();
			Long fromNew = newInputIterator.next();
			
			 while (fromOld != null && fromNew != null) {
				if (fromOld.compareTo(fromNew) > 0) {
					addDescriptionData(item, fromNew, null);
					fromNew = (newInputIterator.hasNext() ? newInputIterator.next() : null);
				} else if (fromOld.compareTo(fromNew) == 0) {
					fromOld = (oldInputIterator.hasNext() ? oldInputIterator.next() : null);
					fromNew = (newInputIterator.hasNext() ? newInputIterator.next() : null);
				} else {
					removeDescriptionData(item, fromOld, null);
					fromOld = (oldInputIterator.hasNext() ? oldInputIterator.next() : null);
				}
			}
			 
			// load old data, if remaining
			if (fromOld != null)
				removeDescriptionData(item, fromOld, null);
			
			while (oldInputIterator.hasNext())
				removeDescriptionData(item, oldInputIterator.next(), null);

			// load new data, if remaining
			if (fromNew != null)
				addDescriptionData(item, fromNew, null);
			
			while (newInputIterator.hasNext())
				addDescriptionData(item, newInputIterator.next(), null);
		}
		
		if (previousPrice != null) {
			item.setPrice(newPrice);
			addDescriptionDataSet(item, null, previousPrice);	
		}
	}
	
	/**
	 * (method to manage new descriptions in existing item)
	 * 
	 * a wrapper method to add descriptions against the specified item, in the description map.
	 * 
	 * @param item
	 * @param descriptions
	 */
	public void addDescriptionDataSet(final Item item, List<Long> descriptions) {
		addDescriptionDataSet(item, descriptions, null);	
	}
	
	/**
	 * (method to add items descriptions)
	 * 
	 * a wrapper method to add item's descriptions against the item, in the description map.
	 * 
	 * @param item
	 * @param descriptions
	 */
	public void addDescriptionDataSet(final Item item) {
		addDescriptionDataSet(item, null, null);	
	}
	
	/**
	 * (method to manage old price and new descriptions in existing item)
	 * 
	 * a wrapper method to add external descriptions and removing the old price against the specified item, in the description map.
	 * 
	 * @param item
	 * @param descriptions
	 */
	public void addDescriptionDataSet(final Item item, List<Long> descriptions, Double previousPrice) {
		if (descriptions == null)
			descriptions = item.getDescription();
		
		// add the incoming description
		for (long description : descriptions)
			addDescriptionData(item, description, previousPrice);
	}
	
	/**
	 * (method to manage old price and new description in existing item)
	 * 
	 * a wrapper method to add external description and removing the old price against the specified item, in the description map.
	 * 
	 * @param item
	 * @param description
	 * @param previousPrice
	 */
	@SuppressWarnings("serial")
	public void addDescriptionData(final Item item, Long description, Double previousPrice) {
		// add the incoming description		
		if (getDescriptionIdsMap().containsKey(description))
			add(getDescriptionIdsMap().get(description), item, previousPrice);
		else 
			getDescriptionIdsMap().put(description, 
					new TreeMap<Double, Set<Long>>(pricesComparator) {{ put(item.getPrice(), new HashSet<Long>(){{ add(item.getId()); }}); }});
			
	}
	
	/**
	 * (method to remove old price and existing description in existing item)
	 * 
	 * a wrapper method to remove existing description and removing the old price against the specified item, in the description map.
	 * 
	 * @param item
	 * @param description
	 * @param previousPrice
	 */
	public void removeDescriptionData(final Item item, Long description, Double previousPrice) {
		// add the incoming description		
		remove(getDescriptionIdsMap().get(description), item, previousPrice);
		
		/* 	check for straying entries. */
		if (getDescriptionIdsMap().get(description).isEmpty())
			getDescriptionIdsMap().remove(description);
			
	}
	
	/**
	 * (method to remove old price and existing descriptions in existing item)
	 * 
	 * a wrapper method to remove existing descriptions and removing the old price against the specified item, in the description map.
	 * 
	 * @param item
	 * @param description
	 * @param previousPrice
	 */
	public void removeDescriptions(Item item) {
		// add the incoming description
		for (long description : item.getDescription())
			removeDescriptionData(item, description, null);
	}
	
	public void updatePriceDataSet(Item item, double previousPrice) {
		getPriceIdsMap().get(previousPrice).remove(item.getId());
		
		// remove entry from priceIds map if there is not associated Items with the previousPrice. This will help us to remove false positives in many other cases.
		if (getPriceIdsMap().get(previousPrice).size() == 0)
			getPriceIdsMap().remove(previousPrice);
		
		addPriceDataSet(item);
	}
	
	@SuppressWarnings("serial")
	public void addPriceDataSet(final Item item) {
		if (getPriceIdsMap().containsKey(item.getPrice()))
			getPriceIdsMap().get(item.getPrice()).add(item.getId());
		else 
			getPriceIdsMap().put(item.getPrice(), new TreeSet<Long>() {{ add(item.getId()); }});  // the natural implemented comparator will come into play.
	}
	
	public void removePrice(Item item) {
		getPriceIdsMap().get(item.getPrice()).remove(item.getId());
		
		// remove entry from priceIds map if there is not associated Items with the price. This will help us to remove false positives in many other cases. 
		if (getPriceIdsMap().get(item.getPrice()).size() == 0)
			getPriceIdsMap().remove(item.getPrice());
	}

	/**
	 * registers a new Item in the MDS Storage
	 * 
	 * @param item
	 */
	public void registerItem(Item item) {
		getIdItemsMap().put(item.getId(), item);

		// update/add description information
		addDescriptionDataSet(item);

		// update/add price information
		addPriceDataSet(item);
		
		// check for same-same eligibility
		addressSameSameHashing(item);
	}
	
	/**
	 * unregisters a new Item in the MDS Storage
	 * 
	 * @param item
	 */
	public void deregister(Item item) {
		// remove traces of item from all the relevant places.
		
		getIdItemsMap().remove(item.getId());
		removeDescriptions(item);
		removePrice(item);
		removeFromSameSame(item);
	}
	
	/**
	 * function checks the pre-conditions to enter into 'hashedSameSameMap'
	 * 
	 * @param item
	 */
	@SuppressWarnings("serial")
	public void addressSameSameHashing(final Item item, Integer previousHash) {
		// remove previous hash entry
		if (getSamesameBookkeepingSet().contains(item.getId()) && previousHash != null) {
			getHashedSameSameMap().get(previousHash).remove(item.getId());
			
			// remove stray entries, to keep the map clean. 
			if (getHashedSameSameMap().get(previousHash).isEmpty()) 
				getHashedSameSameMap().remove(previousHash);
			
			getSamesameBookkeepingSet().remove(item.getId());
		}
		
		// check for eligibility
		if (item.getDescription().size() >= 8 && !getSamesameBookkeepingSet().contains(item.getId())) {
			
			if (getHashedSameSameMap().containsKey(item.getDescriptionHash())) {
				if (contestWithInnerPool(getHashedSameSameMap().get(item.getDescriptionHash()), item)) {
					getHashedSameSameMap().get(item.getDescriptionHash()).add(item.getId());
					getSamesameBookkeepingSet().add(item.getId());
				}
			} else {
				getHashedSameSameMap().put(item.getDescriptionHash(), new TreeSet<Long>() {{ add(item.getId()); }});
				getSamesameBookkeepingSet().add(item.getId());
			}
			
		}
	}

	public void addressSameSameHashing(final Item item) {
		addressSameSameHashing(item, null);
	}

	/**
	 * method that compares the detail level comparison with the first member of the set.
	 * (as previous entries has been made the same way hence, it doesn't matter which entry to choose).
	 * 
	 * @param hashedSameSameMap
	 * @param item
	 * @return
	 */
	
	private boolean contestWithInnerPool(TreeSet<Long> sameElementsItemSet, Item item) {
		Item sameElementsItem = getIdItemsMap().get(sameElementsItemSet.first());
		
		if (sameElementsItem.getDescription().size() == item.getDescription().size()) {
			Iterator<Long> sameElementsItemDescIterator = sameElementsItem.getDescription().iterator();
			Iterator<Long> itemDescIterator = item.getDescription().iterator();
			
			boolean equality = true;
			while (itemDescIterator.hasNext())
				equality = equality && itemDescIterator.next().equals(sameElementsItemDescIterator.next());
			
			return equality;
		}
		return false;
	}
	
	/**
	 * function removed the item from 'hashedSameSameMap', if present.
	 * 
	 * @param item
	 */
	private void removeFromSameSame(Item item) {
		if (getSamesameBookkeepingSet().contains(item.getId())) {
			getHashedSameSameMap().get(item.getDescriptionHash()).remove(item.getId());
			getSamesameBookkeepingSet().remove(item.getId());
		}
	}

}
