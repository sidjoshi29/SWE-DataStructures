/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * This is the Stack implemented using a LinkedList. It implements the Stack interface.
 * @param <E> is the undefined type of object that is stored in the list
 */
public class LinkedStack<E> implements Stack<E> {
	
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
	 * This is the constructor for the LinkedStack that sets the capacity and constructs the list
	 * @param capacity is the number that we want to set as the capacity for this Stack
	 */
	public LinkedStack(int capacity) {
		setCapacity(capacity);
		list = new LinkedAbstractList<E>(capacity);
	}
	
	/**
	 * Adds the specified element to the top of the stack. If the element is null,
	 * an IllegalArgumentException is thrown. If the stack has reached its capacity,
	 * an IllegalArgumentException is thrown.
	 *
	 * @param element the element to be added to the stack
	 * @throws IllegalArgumentException if the specified element is null or
	 *                                  if the stack has reached its capacity
	 */
	@Override
	public void push(E element) {
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
	 * Removes and returns the element at the top of the stack. If the stack is empty,
	 * an EmptyStackException is thrown.
	 *
	 * @return the element at the top of the stack
	 * @throws EmptyStackException if the stack is empty
	 */
	@Override
	public E pop() {
		if (this.size() == 0) {
			throw new EmptyStackException();
		}
		else {
			E removedElement = list.remove(size() - 1);
			size--;
			return removedElement;
		}
	}
	
	/**
	 * Checks if the stack is empty.
	 *
	 * @return true if the stack is empty, false otherwise
	 */
	@Override
	public boolean isEmpty() {
		return this.size() == 0;
	}
	
	/**
	 * Returns the number of elements in the stack.
	 *
	 * @return the number of elements in the stack
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Sets the capacity of the stack. If the specified capacity is negative or less than
	 * the current size of the stack, an IllegalArgumentException is thrown.
	 *
	 * @param capacity the new capacity for the stack
	 * @throws IllegalArgumentException if the specified capacity is negative or less than
	 *                                  the current size of the stack
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
