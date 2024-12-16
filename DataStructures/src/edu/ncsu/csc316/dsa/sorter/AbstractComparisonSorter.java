package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * AbstractComparisonSorter holds the common methods for comparasion sorting algorithms
 * @param <E> the generic type of data to sort
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

    /** Comparator instance */
	private Comparator<E> comparator;
    
	
	/**
     * Constructs an AbstractComparisonSorter with the given comparator.
     *
     * @param comparator the comparator to be used for comparing elements during sorting.
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    

    /**
     * Sets the comparator for comparing elements during sorting. If the provided
     * comparator is null, it defaults to using natural ordering.
     *
     * @param comparator the comparator to be set.
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    /**
     * Private inner class for natural sorting
     */
    private class NaturalOrder implements Comparator<E> {
    	
    	/**
    	 * Compares two elements based on their natural order.
    	 * 
    	 * @param first is the first element
    	 * @param second is the second element.
    	 * 
    	 * @return the integer when we compare two integers.
    	 */
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /**
	 * Compares two elements based on their natural order.
	 * 
	 * @param first is the first element
	 * @param second is the second element.
	 * 
	 * @return the integer when we compare two integers.
	 */
    public int compare(E first, E second) {
        return comparator.compare(first,  second);
    }
}
