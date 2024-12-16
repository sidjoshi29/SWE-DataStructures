package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * This is the Queue interface that contains 5 generic methods for both ArrayList queue and the LinkedList queue
 * @param <E> This is the undefined object type for the Queue
 */
public interface Queue<E> {
	
	/**
	 * This method adds an element to the end of the queue
	 * @param element the element that needs to be added
	 * @throws IllegalArgumentException if there no room in the queue
	 */
	void enqueue(E element);
	
	/**
	 * Removes an element from the front of the list
	 * @return the element that was removed
	 * @throws NoSuchElementException if the queue is empty
	 */
	E dequeue();
	
	/**
	 * This is the method used to check if the queue is empty
	 * @return true if the Queue is empty
	 */
	boolean isEmpty();
	
	/**
	 * This method returns the size of the queue
	 * @return the size of the queue
	 */
	int size();
	
	/**
	 * This is the method that is used to set the capacity of the queue
	 * @param capacity of the queue that we want to set
	 * @throws IllegalArgumentException if the parameter is negative or less than the number of elements in the queue
	 */
	void setCapacity(int capacity);
}
