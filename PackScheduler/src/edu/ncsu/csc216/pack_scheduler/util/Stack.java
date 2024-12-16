package edu.ncsu.csc216.pack_scheduler.util;

/**
 * This is the stack interface that is implemented by the ArrayList and LinkedList
 * @param <E> is the variable object type for the Stack
 */
public interface Stack<E> {
	
	/**
	 * Adds an element to the top of the stack
	 * @param element is the element that needs to be added 
	 * @throws IllegalArgumentException if the element is null or if the stack is full
	 */
	void push(E element);
	
	/**
	 * Remove the element at the top of the stack
	 * @return the element that is removed
	 * @throws IllegalArgumentException if the stack is empty
	 */
	E pop();
	
	/**
	 * This is the method to determine if a Stack is empty or not
	 * @return true if the stack is empty (no elements) and false otherwise
	 */
	boolean isEmpty();
	
	/**
	 * This method returns the number of elements in the stack
	 * @return the number of elements in a stack
	 */
	int size();
	
	/**
	 * This method sets the capacity of the stack
	 * @param capacity is the number we want to set our capacity to
	 * @throws IllegalArgumentException if the capacity is negative or less than size
	 */
	void setCapacity(int capacity);
}
