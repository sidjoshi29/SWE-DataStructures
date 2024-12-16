package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Bubble Sorter Class which implements the Bubble Sort Comparison Algorithm.
 * 
 * @param <E> the generic type of data to sort
 * @author Siddhant Joshi
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

	
	/** Comparator field */
	private Comparator<E> comparator;
	
	/**
	 * Constructor to allow the client to specify the Comparator to be used.
	 * @param comparator comparator type to be used
	 */
	public BubbleSorter(Comparator<E> comparator) {
		super(comparator);
	}

	
	/**
	 * Non paramaterized constructor for insetionSorter that sets the object to null
	 */
	public BubbleSorter() {
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
		
		if (comparator == null) {
	        comparator = Comparator.naturalOrder();
	    }
		
		boolean r = true;
		while(r) {
			r = false;
			for(int i = 1; i < data.length; i++) {
				if(super.compare(data[i], data[i - 1]) < 0) {
					//swapping them
					E x = data[i - 1];
                    data[i - 1] = data[i];
                    data[i] = x;
                    r = true;
				}
			}
		}
		
	}

}
