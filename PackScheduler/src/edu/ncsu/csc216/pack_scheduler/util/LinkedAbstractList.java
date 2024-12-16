package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * this is a class that ...
 * @param <E> the element
 */
public class LinkedAbstractList<E> extends AbstractList<E> {
	
	/**
	 * foremost element in the list
	 */
	private ListNode front;
	
	/** back instance to improve efficiency of queues */
	private ListNode back;
	
	/**
	 * size of the list
	 */
	private int size;
	
	/**
	 * capacity of the list
	 */
	private int capacity;
	
	/**
	 * Constructor for LinkedAbstractList
	 * @param capacity the capacity of the list
	 */
	public LinkedAbstractList(int capacity) {
		this.size = 0;
		this.front = null;
		this.back = null;
		setCapacity(capacity);
	}
	
	
	/**
	 * Adds the specified element at the specified index in this list. If the element is null,
	 * a NullPointerException is thrown. If the index is out of bounds or the list is full,
	 * an IndexOutOfBoundsException or IllegalArgumentException is thrown, respectively.
	 * If the element is a duplicate, an IllegalArgumentException is thrown.
	 *
	 * @param index   the index at which the specified element is to be inserted
	 * @param element the element to be inserted
	 * @throws NullPointerException      if the specified element is null
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 * @throws IllegalArgumentException  if the list is full or the element is a duplicate
	 */
	@Override
	public void add(int index, E element) {
	    if (element == null) {
	        throw new NullPointerException("The element is null");
	    } else if (index < 0 || index > size()) {
	        throw new IndexOutOfBoundsException("The index is out of bounds");
	    } else if (size == capacity) {
	        throw new IllegalArgumentException("There is no space in the list");
	    } else {
	        if (index == size) { // Adding to the end of the list
	            if (size == 0) { // Special case: adding the first element
	                front = new ListNode(element, null);
	                back = front;
	            } else {
	                back.next = new ListNode(element, null);
	                back = back.next;
	            }
	            size++;
	        } else {
	            ListNode temp = front;
	            for (int i = 0; i < size(); i++) {
	                if (element.equals(temp.data)) {
	                    throw new IllegalArgumentException("The element is a duplicate");
	                }
	                temp = temp.next;
	            }
	            if (!(capacity == size)) {
	                if (index == 0) {
	                    front = new ListNode(element, front);
	                } else {
	                    ListNode current = front;
	                    for (int i = 0; i < index - 1; i++) {
	                        current = current.next;
	                    }
	                    current.next = new ListNode(element, current.next);
	                }
	                size++;
	            }
	        }
	    }
	}

	/**
	 * Returns the number of elements in this list.
	 *
	 * @return the number of elements in this list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Returns the element at the specified index in this list.
	 *
	 * @param index the index of the element to return
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("The index is out of bounds");
		}
		else {
			if (index == 0) {
				return front.data;
			}
			else{
				ListNode current = front;
				for (int i = 0; i < index; i++) {
					current = current.next;
				}
				return current.data;
			}
		}
	}
	
	/**
	 * Removes the element at the specified index in this list.
	 *
	 * @param index the index of the element to be removed
	 * @return the removed element
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 */
	@Override
	public E remove(int index) {
	    if (index < 0 || index >= size()) {
	        throw new IndexOutOfBoundsException("The index is out of bounds");
	    } else {
	        E value = null;
	        if (index == 0) {
	            value = front.data;
	            front = front.next;
	            if (size == 1) {
	                // If removing the only element in the list, update 'back' as well
	                back = null;
	            }
	        } else {
	            ListNode current = front;
	            for (int i = 0; i < index - 1; i++) {
	                current = current.next;
	            }
	            value = current.next.data;
	            current.next = current.next.next;

	            if (index == size - 1) {
	                // If removing the last element, update 'back'
	                back = current;
	            }
	        }
	        size--;
	        return value;
	    }
	}

	/**
	 * Replaces the element at the specified index in this list with the specified element.
	 * If the element is null, a NullPointerException is thrown. If the index is out of bounds,
	 * an IndexOutOfBoundsException is thrown. If the element is a duplicate, an
	 * IllegalArgumentException is thrown.
	 *
	 * @param index   the index at which to replace the element
	 * @param element the element to be stored at the specified position
	 * @return the element previously at the specified position
	 * @throws NullPointerException      if the specified element is null
	 * @throws IndexOutOfBoundsException if the index is out of bounds
	 * @throws IllegalArgumentException  if the element is a duplicate
	 */
	@Override
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException("The element is null");
		}
		else if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("The index is out of bounds");
		}
		else {
			ListNode temp = front;
			for (int i = 0; i < size(); i++) {
				if (element.equals(temp.data)) {
					throw new IllegalArgumentException("The element is a duplicate");
				}
				temp = temp.next;
			}
			
			E value = null;
			if (index == 0) {
				value = front.data;
				front = new ListNode(element, front.next);
			}
			else {
				ListNode current = front;
				for (int i = 0; i < index - 1; i++) {
					current = current.next;
				}
				value = current.next.data;
				current.next = new ListNode(element, current.next.next);
			}
			return value;
		}
	}
	
	/**
	 * This is the method used to set the capacity of the LinkedAbstractList
	 * @param capacity is the number that we want to set as the capacity
	 * @throws IllegalArgumentException if the capacity is less than or equal to zero 
	 * or less than size
	 */
	public void setCapacity(int capacity) {
		if (capacity <= 0 || capacity < size()) {
			throw new IllegalArgumentException("Invalid capacity");
		}
		else {
			this.capacity = capacity;
		}
		
	}
	
	/**
	 * This is the list node class that helps us construct the list nodes in this linkedlist. This is a private inner class
	 * because this ListNode class should only be used by this class. 
	 */
	private class ListNode {
		/**
		 * the data in the node
		 */
		private E data;
		
		/**
		 * the next node in the list
		 */
		private ListNode next;
		
		/**
		 * This is the ListNode constructor that constructs the last node of the list
		 * @param data that will be stored in the list node
		 */
		public ListNode(E data) {
			this.data = data;
		}
		
		/**
		 * This is the ListNode that constructs all of the other nodes of the list
		 * @param data stored in the link node
		 * @param next element in the list
		 */
		public ListNode(E data, ListNode next) {
			this(data);
			this.next = next;
		}
		
	}

	
}