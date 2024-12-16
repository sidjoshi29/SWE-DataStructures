package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;


/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * @author Dr. King
 * @author Siddhant Joshi
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E>  {

	/** Comparator field */
	private Comparator<E> comparator;
	
	
	
	/**
	 * Non paramaterized constructor for insetionSorter that sets the object to null
	 */
	public SelectionSorter() {
		this(Comparator.naturalOrder());
    }
	/**
	 * Constructor to allow the client to specify the Comparator to be used.
	 * @param comparator comparator type to be used
	 */
	public SelectionSorter(Comparator<E> comparator) {
		super(comparator);
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
		
		for (int i = 0; i < data.length; i++) {
            int min = i;
            for (int j = i + 1; j < data.length; j++) {
                if (super.compare(data[j], data[min]) < 0) {
                    min = j;
                }
            }
            if (min != i) {
                E temp = data[i];
                data[i] = data[min];
                data[min] = temp;
            }
        }
	}
	

	
}
