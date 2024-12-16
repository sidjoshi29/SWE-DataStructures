/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.NoSuchElementException;

/**
 * This is the LinkedQueue class that helps us construct a queue using a linked list. It implements the Queue interface.
 * @param <E> is the undefined object type that is stored in the queue
 */
public class LinkedQueue<E> implements Queue<E> {
	
	/**
	 * This is the temporary list that holds all of the elements
	 */
	private LinkedAbstractList<E> list;
	
	/**
	 * This is the capacity of the list
	 */
	private int capacity;
	
	/**
	 * This is the size of the list
	 */
	private int size;
	
	/**
	 * This is the LinkedQueue constructor that constructs the LinkedAbstractList and sets the capacity using the set capacity method
	 * @param capacity is the capacity that we want to set
	 */
	public LinkedQueue(int capacity) {
		setCapacity(capacity);
		list = new LinkedAbstractList<E>(capacity);
	}
	
	
	/**
	 * Adds the specified element to the end of the list.
	 *
	 * @param element the element to be added to the list
	 * @throws IllegalArgumentException if the specified element is null
	 *         or if the capacity of the list has been reached
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
	 * Removes and returns the element at the front of the list.
	 *
	 * @return the element that was removed from the front of the list
	 * @throws NoSuchElementException if the list is empty
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
	 * IsEmpty method returns true if is empty.
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Returns the size
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Sets the capacity of the list to the specified value. The capacity
	 * represents the maximum number of elements that the list can hold.
	 *
	 * @param capacity the new capacity to set for the list
	 * @throws IllegalArgumentException if the specified capacity is negative
	 *                                  or less than the current size of the list
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
	
	/**
	 * This method helps us check if the queue contains the element or not
	 * @param element the element that we want to check
	 * @return true if the element is in the list, false otherwise
	 */
	public boolean contains(E element) {
		return list.contains(element);
	}
}
