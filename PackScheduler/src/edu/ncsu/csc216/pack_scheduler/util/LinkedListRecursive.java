package edu.ncsu.csc216.pack_scheduler.util;


/**
 * LinkedListRecursive class
 * @author Siddhant Joshi
 * @author Shaurya Deepak
 * @author Arnav Ganguly
 * @param <E> Element type of list
 */
public class LinkedListRecursive<E> {
	
	/** size of the list */
	private int size;
	
	/** front of the list */
	private ListNode front;
	
	/**
	 * Constructor of LinkedListRecursive
	 */
	public LinkedListRecursive() {
		front  = null;
		size = 0;
	}
	/** 
	 * size method 
	 * @return int size
	 */
	public int size() {
		return size;
	}
	
	/**
	 * checks if size is empty
	 * @return boolean true if it is
	 */
	public boolean isEmpty() {
		return size() == 0;
	}
	/**
	 * Checks if it contains element
	 * @param element element
	 * @return boolean true false;
	 */
	public boolean contains(E element) {
		if (isEmpty()) {
			return false;
		}
		return front.contains(element);
		
	}
	
	/**
	 * Add method with single param
	 * @param element element
	 * @return element
	 */
	public boolean add(E element) {
		
		if (element == null) {
			throw new IllegalArgumentException("Element is null");
		}
		else if (this.contains(element)) {
			throw new IllegalArgumentException("Element already exists");
		}
		else if (this.isEmpty()) {
			front = new ListNode(element, null);
			size++;
			return true;
		}
		else {
			front.add(element);
			size++;
			return true;
		}
		

	}
	
	/**
	 * Add method
	 * @param index index
	 * @param element element
	 */
	public void add(int index, E element) {
		if(contains(element)) {
			throw new IllegalArgumentException("Element exists");
		}
		else if(index < 0 || index > size) {
			throw new IndexOutOfBoundsException("Out of bounds");
		}
		
		else if(element == null) {
			throw new NullPointerException("Element to add is null.");
		}
		else if(this.isEmpty()) {
			front = new ListNode(element, null);
			size++;
		}
		else if (index == 0) {
			front = new ListNode(element, front);
			size++;
		}
		else {
			front.add(index - 1, element);
			size++;
		}
		

	}
	
	/**
	 * get method
	 * @param index index
	 * @return element
	 */
	public E get(int index) {
		if(index < 0 || index >= size ) {
			throw new IndexOutOfBoundsException("Out of bounds");
		}
		else {
			return front.get(index);
		}
		
		
	}
	/**
	 * Remove remove
	 * @param index index
	 * @return element
	 */
	public E remove(int index) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Index outside of list size.");
		}
		if (isEmpty()) {
			return null;
		}
		if (index == 0) {
			E temp = front.data;
			front = front.next;
			size--;
			return temp;
		} 
		return front.remove(index - 1);
	}
	
	/**
	 * Remove
	 * @param element element
	 * @return true of it removes
	 */
	public boolean remove(E element) {
		if(isEmpty()) {
			return false;
		}
		
		else if (element == null) {
			return false;
		}
		else if (front.data.equals(element)) {
			front = front.next;
			size--;
			return true;
		}
		else {
			return front.remove(element);
		}
		
	}
	/**
	 * Set method
	 * @param index index
	 * @param element element
	 * @return the element it set
	 */
	public E set(int index, E element) {
		if (index >= size || index < 0) {
			throw new IndexOutOfBoundsException("Out of bounds");
		}
		else if (element == null) {
			throw new NullPointerException();
		}
		else if (contains(element)) {
			throw new IllegalArgumentException();
		}
		else {
			return front.set(index, element);
		}
		
	}
	
	private class ListNode {
		/**
		 * the data in the node
		 */
		private E data;
		
		/**
		 * the next node in the list
		 */
		private ListNode next;
		
		public ListNode(E data) {
			this.data = data;
		}
		
		public ListNode(E data, ListNode next) {
			this(data);
			this.next = next;
		}
		
		public void add (E element) {
			if (this.next == null) {
				this.next = new ListNode(element, null);
			}
			else {
				next.add(element);
			}
		}
		
		public void add(int index, E element) {
			if (index == 0) {
				next = new ListNode(element, next);
			} else {
				next.add(index - 1, element);
			}
		}
		
		public E get(int index) {
			if(index == 0) {
				return data;
			} 
			else {
				return this.next.get(index - 1);
			}
		}
		
		public E remove(int index) {
			if(index == 0) {
				E temp = next.data;
				this.next = this.next.next;
				size--;
				return temp;
			}
			else {
				return this.next.remove(index - 1);
			}
		}
		
		
		public boolean remove(E element) {
			if (this.next == null) {
				return false;
			}
			else if(this.next.data.equals(element)) {
				this.next = this.next.next;
				size--;
				return true;
			} else {
				return this.next.remove(element);
			}
		}
		
		public E set(int index, E element) {
			if (index == 0) {
				E temp = data;
				data = element;
				return temp;
			} else {
				return this.next.set(index - 1, element);
			}
		}
		
		public boolean contains(E element) {
			if(this.data.equals(element)) {
				return true;
				// if next is null
			} else if(this.next == null) {
				return false;
			} else {
				return this.next.contains(element);
			}
			
		}
		
		
	}
	
}
