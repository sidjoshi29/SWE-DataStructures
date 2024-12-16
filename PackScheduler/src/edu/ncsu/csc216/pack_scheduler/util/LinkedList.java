package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
/**
 * Linked List Collection class for faculty implementation
 * @author Siddhant Joshi
 * @author Arnav Gangully
 * @author Shuarya Deepak
 *
 * @param <E> Element type of list
 */
public class LinkedList<E> extends AbstractSequentialList<E> {
	/** front of the List */
	private ListNode front;

	/** back of the list */
	private ListNode back;
	
	
	/** Number of elements in list */
	private int size;
	
	
	
	
	/**
	 * Constructor for LinkedList
	 */
	public LinkedList(){
		front = new ListNode(null, null, null);
		back = new ListNode(null, front, null);
		front.next = back;
		back.prev = front;
		size = 0;
		
	}
	
	/**
	 * Iterator constructor
	 * @param index for iterator to traverse to
	 */
	@Override
	public ListIterator<E> listIterator(int index) {
		LinkedListIterator iterator = new LinkedListIterator(index);
		return iterator;
	}

	/** 
	 * Adds element to specified index
	 * @param index of list
	 * @param element to add
	 * @throws IllegalArgumentException if the element already exists in the list
	 */
	@Override
	public void add(int index, E element) {
		for (int i = 0; i < size(); i++) {
			if (element.equals(get(i))) {
				throw new IllegalArgumentException("Duplicate Element.");
			}
		}
		super.add(index, element);
	}
	
	/**
	 * Sets element to a specified index
	 * @param index of list
	 * @param element to set
	 * @throws IllegalArgumentException if the element already exists in the list
	 */
	@Override
	public E set(int index, E element) {
		for (int i = 0; i < size(); i++) {
			if (element.equals(get(i))) {
				throw new IllegalArgumentException("Cannot set element.");
			}
		}
		return super.set(index, element);
	}

	/**
	 * Gets the size of the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	/**
	 * Iterator class
	 * @author Siddhant Joshi
	 * @author Arnav Gangully
	 * @author Shuarya Deepak
	 *
	 */
	private class LinkedListIterator implements ListIterator<E> {
		
		/** previous node */
		public ListNode previous;
		
		/** next node */
		public ListNode next;
		
		/** last retrieved node */
		public ListNode lastRetrieved;
		
		/** previous index the index that would be returned on a call to previousIndex() */
		public int previousIndex;
		
		/** the next index */
		public int nextIndex;
		
		/**
		 * Positions of the iterator at desired node in list
		 * @param index of the iterator
		 * @throws IndexOutOfBounds when the index is out of bounds
		 */
		public LinkedListIterator(int index) { 
			if(index < 0 || index > size )
				throw new IndexOutOfBoundsException("Out of bounds");
			ListNode current = front.next;
			for(int i = 0; i < index; i++){
				current = current.next;
			}
			next = current;
			previous = current.prev;
			previousIndex = index - 1;
			nextIndex = index;
			lastRetrieved = null;
		}

		/**
		 * Iterator adds desired element
		 * @param element Element to add
		 * @throws NullPointerException if the element is null
		 */
		@Override
		public void add(E element) {
			if(element == null)
				throw new NullPointerException("Element is null.");
		
			if(size == 0){
				ListNode newNode = new ListNode(element, previous, next);
				front.next = newNode;
				back.prev = newNode;
					
			} else{
				ListNode newNode = new ListNode(element, previous, next);
				previous.next = newNode;
				next.prev = newNode;
			}
			lastRetrieved = null;
			size++;
		}

		/**
		 * Checks if the next element is null
		 * @returns true if the element is not null
		 */
		@Override
		public boolean hasNext() {
			return next.data != null;
		}

		/**
		 * Checks if the previous element is null
		 * @return True if the element is not null
		 */
		@Override
		public boolean hasPrevious() {
			return previous.data != null;
		}

		/**
		 * Gets the next element
		 * @return The next element in the list
		 * @throws NoSuchElementException if the next element is null
		 */
		@Override
		public E next() {
			if (!hasNext()) {
				throw new NoSuchElementException("There is no such element in next");
			}
			lastRetrieved = next;
			next = next.next;
			previous = next.prev;
			nextIndex++;
			previousIndex++;
			return lastRetrieved.data;
		}

		/**
		 * Gets the index of next element
		 * @return The index of the next element
		 */
		@Override
		public int nextIndex() {
			return nextIndex;
		}

		/**
		 * Gets the previous element
		 * @return The previous element
		 * @throws NoSuchElementException if the previous element is null
		 */
		@Override
		public E previous() {
			if (!hasPrevious()) {
				throw new NoSuchElementException("There is no such element in previous.");
			}
			lastRetrieved = previous;
			previous = previous.prev;
			next = previous.next;
			previousIndex--;
			nextIndex--;
			return lastRetrieved.data;
		}

		/**
		 * Gets the previous index of  element
		 * @return The previous element's index
		 */
		@Override
		public int previousIndex() {
			return previousIndex;
		}

		/**
		 * Removes the element
		 * @throws IllegalStateException if the lastRetrieved element is null
		 */
		@Override
		public void remove() {
			if (lastRetrieved == null) {
				throw new IllegalStateException("There has not been a call to next() or previous()");
			}
			lastRetrieved.prev.next = lastRetrieved.next;
			lastRetrieved.next.prev = lastRetrieved.prev;
			size--;
		}

		/**
		 * sets the element
		 * @param element The new element
		 * @throws NullPointerException if the element in the parameter is null
		 * @throws IllegalStateException if the lastRetrieved element is null
		 */
		@Override
		public void set(E element) {
			if (element == null) {
				throw new NullPointerException("Element is null");
			}
			if (lastRetrieved == null) {
				throw new IllegalStateException();
			}
			
			ListNode newNode = new ListNode(element, lastRetrieved.prev, lastRetrieved.next);
			lastRetrieved.next.prev = newNode;
			lastRetrieved.prev.next = newNode;
		} 
	}
	
	/**
	 * ListNode inner class
	 * @author Siddhant Joshi
	 * @author Arnav Gangully
	 * @author Shuarya Deepak
	 *
	 */
	private class ListNode {
		/** The information being stored in the node */
		public E data;
		/** Reference to the next node in the list */
		public ListNode next;
		/** Reference to the previous node in the list */
		public ListNode prev;
		/**
		 * Constructs a new ListNode
		 * 
		 * @param data to store in the node
		 * @param previous reference to previous node
		 * @param next reference to the next node
		 */
		public ListNode(E data, ListNode previous, ListNode next) {
			this.data = data;
			this.next = next;
			this.prev = previous;
		}
		
	}

	
}