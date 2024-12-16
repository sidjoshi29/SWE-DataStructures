package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @param <E> the generic type of data to sort
 * @author Dr. King
 * @author Siddhant Joshi
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	/** Comparator field */
	private Comparator<E> comparator;
	
	
	/**
	 * Constructor to allow the client to specify the Comparator to be used.
	 * @param comparator comparator type to be used
	 */
	public InsertionSorter(Comparator<E> comparator) {
		super(comparator);
	}
	
	/**
	 * Non paramaterized constructor for insetionSorter that sets the object to null
	 */
	public InsertionSorter() {
        this(null);
    }

	/**
	 * Input an array data.
     * Output the array sorted in ascending order.
     * 
	 * @param data is the data that has to be sorted.
	 * 
	 */
	public void sort(E[] data) {
		
		//null check
		if(data == null) {
			throw new IllegalArgumentException("Data given is a null list");
		}
		
		//set to natural order if comparator is null
		if (comparator == null) {
	        comparator = Comparator.naturalOrder();
	    }
		
		for(int i = 1; i < data.length; i++) {
			E x = data[i];
			int j = i - 1;
			while (j >= 0 && super.compare(data[j], x) > 0) {
                data[j + 1] = data[j];
                j = j - 1;
            }
            data[j + 1] = x;
		}
	}
	
	
	
}
