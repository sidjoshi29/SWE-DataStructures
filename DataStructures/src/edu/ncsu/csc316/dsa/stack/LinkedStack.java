package edu.ncsu.csc316.dsa.stack;

import java.util.EmptyStackException;

import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * The Linked Stack is implemented as a singly-linked list data structure to
 * support efficient, O(1) worst-case Stack abstract data type behaviors.
 * 
 * @author Dr. King
 * @author Siddhant Joshi
 *
 * @param <E> the type of elements stored in the stack
 */
public class LinkedStack<E> extends AbstractStack<E> {

    /** Delegate to our existing singly linked list class **/
    private SinglyLinkedList<E> list;
    
    /** size of the stack **/
    private int size;

    /**
     * Construct a new singly-linked list to use when modeling the last-in-first-out
     * paradigm for the stack abstract data type.
     */
    public LinkedStack() {
        list = new SinglyLinkedList<E>();
    }

	@Override
	public void push(E element) {
		if (element == null) {
			throw new IllegalArgumentException("Cannot add null element");
		}
		list.addFirst(element);
		size++;
		
		
	}

	@Override
	public E pop() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		size--;
		return list.removeFirst();
	}

	@Override
	public E top() {
		if(isEmpty()) {
			throw new EmptyStackException();
		}
		return list.first();
	}

	@Override
	public int size() {
		return size;
	}
    
}