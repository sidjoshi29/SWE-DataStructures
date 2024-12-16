package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;

import java.util.NoSuchElementException;
/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Siddhant Joshi
 * 
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

	/** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;
    
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }

	/**
	 * Add element to any index in Linked List
	 * @param index index to be added at
	 * @param element element to be placed at that index
	 */
	@Override
	public void add(int index, E element) {
		checkIndexForAdd(index);

	    LinkedListNode<E> current = front;

	    for (int i = 0; i < index; i++) {
	        current = current.next;
	    }

	    current.next = new LinkedListNode<E>(element, current.next);

	    if (index == size) {
	        tail = current.next;
	    }
	    size++;

	}

	@Override
	public E get(int index) {
	    checkIndex(index);
	    if(index < 0 || index >  size) {
	    	throw new IndexOutOfBoundsException();
	    }
	    LinkedListNode<E> current = front.next;
	    for (int i = 0; i < index; i++) {
	        current = current.next;
	    }
	    return current.element;
	}


	@Override
	public E remove(int index) {
		checkIndex(index);
		
		E returnVal = null;
		
		//remove first index == 0;
		if(index == size - 1) {
			LinkedListNode<E> current = front;
            while (current.next.next != null) {
                current = current.next;
            }
            returnVal = current.next.element;
            current.next = null;
            tail = current;
		} else {
			LinkedListNode<E> current = front;
			for (int i = 0; i < index; i++) {
		        current = current.next;
		    }
			returnVal = current.next.element;
			current.next = current.next.next;
			
		}
		size--;
		return returnVal;
	    

	}

	@Override
	public E set(int index, E element) {
		checkIndex(index);
		if(index < 0 || index >  size) {
	    	throw new IndexOutOfBoundsException();
	    }
		LinkedListNode<E> current = front;
		for (int i = 0; i <= index; i++) {
			current = current.getNext();
		}
		
		//swap
		E temp = current.element;
		current.element = element;
		return temp;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public Iterator<E> iterator() {
		return new ElementIterator();
	}

	/**
	 * {@inheritDoc} For a singly-linked list, this behavior has O(1) worst-case
     * runtime.
	 */
	@Override
	public E last() {
		if (isEmpty()) {
			throw new IndexOutOfBoundsException("The list is empty");
		}
		//get the last element of the linked list
		return tail.getElement();
	}

	/**
	 * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
	 */
	@Override
	public void addLast(E element) {
	    if (size() == 0) {
	        tail = new LinkedListNode<E>(element);
	        front.next = tail;
	    } else {
	        tail.next = new LinkedListNode<E>(element);
	        tail = tail.getNext();
	    }
	    size++; 
	}


	/**
	 * LinkedList Node inner class that has the functionality and operations of the list node.
	 * 
	 * @author Dr. King
	 * @author Siddhant Joshi
	 *
	 * @param <E> the generic type the LinkedListNode encapsulates
	 */
	private static class LinkedListNode<E> {
		/** The data this ListNode encapsulates */
		private E element;
		/** The next ListNode in the sequence of nodes */
		private LinkedListNode<E> next;

		/**
		 * ListNode constructor
		 * @param element element of the list node.
		 */
		public LinkedListNode(E element) {
			this.element = element;
		}

		/**
		 * ListNode constructor with parameters
		 * @param element of the list node.
		 * @param next next listNode.
		 */
		public LinkedListNode(E element, LinkedListNode<E> next) {
			this(element);
			this.next = next;
		}

		/**
		 * Gets the data of that ListNode
		 * 
		 * @return data the data the ListNode has.
		 */
		public E getElement() {
			return element;
		}



		/**
		 * Gets the next of the ListNode
		 * 
		 * @return next the next ListNode in the list.
		 */
		public LinkedListNode<E> getNext() {
			return next;
		}
	}

	/**
	 * ElementIterator is an iterator that moves forward and traverse a LinkedList
	 * of elements. 
	 * 
	 * @author Dr. King
	 * @author Siddhant Joshi
	 *
	 */
	private class ElementIterator implements Iterator<E> {
		/**
	     * Keep track of the next node that will be processed
	     */
		 private LinkedListNode<E> current;

			/**
			 * Construct a new element iterator where the cursor is initialized to the
			 * beginning of the list.
			 */
			public ElementIterator() {
				//initalize
				current = front.next;
			}

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public E next() {
				if (!hasNext()) { 
					throw new NoSuchElementException();
				}
				E ans = current.getElement();
				current = current.next;
				return ans;
			}

			@Override
			public void remove() {
				// DO NOT CHANGE THIS METHOD
		        throw new UnsupportedOperationException(
		            "This SinglyLinkedList implementation does not currently support removal of elements when using the iterator.");
			}
	}

}