package challenges.lintcode;

import challenges.AbstractCustomTestRunner;

/**
 * Nuts & Bolts Problem
 * 
 * Given a set of n nuts of different sizes and n bolts of different sizes. There is a one-one mapping between nuts and bolts. 
 * Comparison of a nut to another nut or a bolt to another bolt is not allowed. It means nut can only be compared with bolt and 
 * bolt can only be compared with nut to see which one is bigger/smaller.
 * 
 * We will give you a compare function to compare nut with bolt.
 * 
 * Example:
 * 	Given nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC'].
 * 	
 * 		Your code should find the matching bolts and nuts.
 * 		One of the possible return:
 * 
 * 		nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG'].
 * 
 * 	we will tell you the match compare function. If we give you another compare function.
 * 	the possible return is the following:
 * 	
 * 	nuts = ['ab','bc','dd','gg'], bolts = ['BC','AA','DD','GG'].
 * 
 * So you must use the compare function that we give to do the sorting.
 * The order of the nuts or bolts does not matter. You just need to find the matching bolt for each nut.
 * 
 * @author Hxkandwal
 */
public class NutsAndBoltsProblem extends AbstractCustomTestRunner {
	public interface NBComparator {
		 public int cmp(String a, String b);
	}
	
	/**
	 * Examples of first binding to understand the meaning of problems, problems matching nuts and bolts to the present, 
	 * entitled, but the need to subject the comparison function is provided, and between the nuts and the nuts elements 
	 * not directly comparable, compare only performed between the nuts and bolts. First, we consider if there is no limit 
	 * comparison function, then we are on the nuts and bolts can be sorted, because it is eleven pairs, so drained after 
	 * the order to complete the pairing. So how can only be completed in sorting it by comparing the relative size of the 
	 * other elements of that?
	 * 
	 * We readily by reference to a set of elements traversed obtained twenty-two equal elements, so that in the worst case 
	 * time complexity of O (n2) O (n ^ 2) O (n2), equivalent bubble sort . O (nlogn) O (n \ log n) O (nlogn), that is to 
	 * say that this question should be further optimized based on a comparison of the best sort of time complexity of the 
	 * algorithm is based on the theory known sorting algorithm. Memories of the comparison based sorting algorithms relationship, 
	 * to achieve O (nlogn) O (n \ log n) with a stack row (nlogn) time complexity from O, quick sort and merge sort, since 
	 * here only obtained by comparing the relative magnitudes , it is possible to think of the quick sort.
	 * 
	 * The core is the quick sort of set benchmarks, divided intervals. Because here only as a reference to the other elements, 
	 * it is only after a trip divided sections obtained position of the reference element either sort, that by the introduction 
	 * of O (n) O (n) additional space O (n) to have been how to mark the reference element processing performed it? This method 
	 * is more difficult to implement, because only one of the elements of the division range, while the other elements can not 
	 * be divided into sections leading to recursion can not be normal.
	 * 
	 * Failure looms, vista. Because only by comparing each other, so the need to cooperate with each other partition operations 
	 * (this point is really difficult to think of). The core comprising: first nuts used in reference to a certain element as a 
	 * partition for the operation of bolts, the bolts are subsequently obtained for a reference element as a reference for nuts 
	 * partition operation.
	 * 
	 */
	public void sortNutsAndBolts(String[] nuts, String[] bolts, final NBComparator compare) {
		if (nuts == null || bolts == null) return;
		if (nuts.length != bolts.length) return;
		qsort(nuts, bolts, compare, 0, nuts.length - 1);
	}

	private void qsort(String[] nuts, String[] bolts, NBComparator compare, int l, int u) {
		if (l >= u) return;

		// find the partition index for nuts with bolts[l]
		int part_inx = partition(nuts, bolts[l], compare, l, u);
		
		// partition bolts with nuts[part_inx]
		partition(bolts, nuts[part_inx], compare, l, u);
		
		// qsort recursively
		qsort(nuts, bolts, compare, l, part_inx - 1);
		qsort(nuts, bolts, compare, part_inx + 1, u);
	}

	private int partition(String[] str, String pivot, NBComparator compare, int l, int u) {
		int m = l;
		for (int i = l + 1; i <= u; i++) {
			if (compare.cmp(str[i], pivot) == -1 || compare.cmp(pivot, str[i]) == 1) {
				m++;
				swap(str, i, m);
			} else if (compare.cmp(str[i], pivot) == 0 || compare.cmp(pivot, str[i]) == 0) {
				// swap nuts[l]/bolts[l] with pivot
				swap(str, i, l);
				i--;
			}
		}
		
		// move pivot to proper index
		swap(str, m, l);
		return m;
	}

	private void swap(String[] str, int l, int r) {
		String temp = str[l];
		str[l] = str[r];
		str[r] = temp;
	}
	    
}
