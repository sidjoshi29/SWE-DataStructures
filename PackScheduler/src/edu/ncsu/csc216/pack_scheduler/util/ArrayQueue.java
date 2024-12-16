/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * This is the Array Queue class that helps us construct a queue using a array list. It implements the Queue interface
 * @param <E> is the undefined object type that is stored in the queue
 */
public class ArrayQueue<E> implements Queue<E> {
	
	/**
	 * This is the 1d array that stores the list of elements
	 */
	private ArrayList<E> list;
	/**
	 * This is the capacity of the list
	 */
	private int capacity;
	/**
	 * This is the size of the list
	 */
	private int size;
	
	/**
	 * This is the arrayQueue constructor that constructs the arraylist and sets its capacity
	 * @param capacity is the capacity we want to set for the arraylist
	 */
	public ArrayQueue(int capacity){
		setCapacity(capacity);
		list = new ArrayList<E>();
	}
	
	
	/**
	 * Adds the specified element to the end of the queue.
	 *
	 * @param element the element to be added to the queue
	 * @throws IllegalArgumentException if the specified element is null
	 *                                  or if the capacity of the queue has been reached
	 */
	@Override
	public void enqueue(E element) {
		if (element == null) {
			throw new IllegalArgumentException("Cannot add null element");
		}
		else if (this.size() == this.capacity) {
			throw new IllegalArgumentException("Capacity of the list has been reached");
		}
		else {
			list.add(element);
			size++;
		}
		
	}

	/**
	 * Removes and returns the element at the front of the queue.
	 *
	 * @return the element that was removed from the front of the queue
	 * @throws NoSuchElementException if the queue is empty
	 */
	@Override
	public E dequeue() {
		if (this.size() == 0) {
			throw new NoSuchElementException();
		}
		else {
			E removedElement = list.remove(0);
			size--;
			return removedElement;
		}
	}

	/**
	 * Checks if the queue is empty.
	 *
	 * @return true if the queue is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Returns the number of elements in the queue.
	 *
	 * @return the number of elements in the queue
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the capacity of the queue to the specified value. The capacity
	 * represents the maximum number of elements that the queue can hold.
	 *
	 * @param capacity the new capacity to set for the queue
	 * @throws IllegalArgumentException if the specified capacity is negative
	 *                                  or less than the current size of the queue
	 */
	@Override
	public void setCapacity(int capacity) {
		if (capacity < 0 || capacity < size()) {
			throw new IllegalArgumentException();
		}
		else {
			this.capacity = capacity;
		}
		
	}

}
