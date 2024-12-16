/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import java.util.EmptyStackException;

/**
 * This is the Stack implemented using ArrayLists. It implements the Stack interface.
 * @param <E> This is undefined type of object that will be stored in this ArrayStack
 */
public class ArrayStack<E> implements Stack<E> {
	
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
	 * This is the constructor that helps us initialize the list and set its capacity
	 * @param capacity is the capacity of the list
	 */
	public ArrayStack(int capacity) {
		setCapacity(capacity);
		list = new ArrayList<E>();
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
