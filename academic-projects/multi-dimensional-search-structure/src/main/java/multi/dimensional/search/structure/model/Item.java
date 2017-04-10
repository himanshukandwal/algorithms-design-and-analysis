package multi.dimensional.search.structure.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * A item class depicting object that we need to manage.
 * 
 * @author G31 (Himanshu Kandwal and Dharmam Buch)
 *
 */
public class Item  implements Comparable<Item> {
	
	private long id;
	private List<Long> description;
	private double price;
	private int size;
	private int descriptionHash;
	
	public long getId() {
		return id;
	}
	
	public Item setId(long id) {
		this.id = id;
		return this;
	}
	
	public List<Long> getDescription() {
		return description;
	}
	
	public Item setDescription(int size, long[] description) {
		if (description != null) {
			setSize(size);
			
			List<Long> desc = new ArrayList<>(size);
			
			for (int index = 0; index < size; index++)
				desc.add(description [index]);
			
			this.description = desc;
			recomputeDescriptionHash();
		}
		return this;
	}
	
	public void recomputeDescriptionHash() {
		Collections.sort(description);
		setDescriptionHash(Arrays.hashCode(description.toArray(new Long[0])));
	}
	
	public double getPrice() {
		return price;
	}
	
	public Item setPrice(double price) {
		this.price = price;
		return this;
	}
	
	public int getSize() {
		return size;
	}
	
	public Item setSize(int size) {
		this.size = size;
		return this;
	}
	
	public int getDescriptionHash() {
		return descriptionHash;
	}
	
	public void setDescriptionHash(int descHashcode) {
		this.descriptionHash = descHashcode;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || !(obj instanceof Item))
			return false;
		Item otherItem = (Item) obj;
		
		if (otherItem.id == id)
			return true;
		
		return false;
	}

	@Override
	public int compareTo(Item o) {
		return (getId() - o.getId() > 0 ? 1 : (getId() - o.getId() == 0 ? 0 : -1));
	}
	
	@Override
	public String toString() {
		return Long.valueOf(id).toString();
	}
}
