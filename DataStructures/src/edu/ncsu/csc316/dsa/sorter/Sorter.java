package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @author Siddhant Joshi
 * @param <E> the generic type of data to sort
 */
public interface Sorter<E> {
	/**
	 * Sort method for the interface
	 * @param data data to be sorted
	 */
    void sort(E[] data);
}
