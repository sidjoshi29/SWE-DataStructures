package edu.ncsu.csc216.pack_scheduler.util;

import java.util.AbstractList;

/**
 * This is a the custom arraylist class.
 * @param <E> The object with an undefined type E
 * 
 * @author Siddhant Joshi
 * @author Arnav Gnaguly
 * @author Shaurya Deepak
 */
public class ArrayList<E> extends AbstractList<E> { 
	/**
	 * the initialization size of the list
	 */
	private static final int INIT_SIZE = 10;
	
	/**
	 * the list
	 */
	private E[] list;
	
	/**
	 * the size of the list (how many elements its storing currently)
	 */
	private int size;
	
	/**
	 * the capacity of the list
	 */
	private int capacity;
	
	/**
	 * the constructor of the ArrayList
	 */
	@SuppressWarnings("unchecked")
	public ArrayList() {
		list = (E[]) new Object[INIT_SIZE];
		capacity = INIT_SIZE;
	}

	/**
	 * Returns the element at the specified position in the list
	 * @param index the index of the element
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public E get(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
		else {
			return list[index];
		}
		
	}

	/**
	 * Sets the element at the specified index with the given element.
	 * @param index index of the element to replace
	 * @param element the element to stored at the given index
	 * @return the element previously at the specified position
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element is already in the list
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than 
	 * this ArrayList's size.
	 */
	@Override
	public E set(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Element is null");
		}
		else if (index < 0 || index >= this.size) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
		else {
			for (int i = 0; i < size(); i++) {
				if (list[i].equals(element)) {
					throw new IllegalArgumentException("Element is duplicate");
				}
			}
		E temp = list[index];
		list[index] = element;
		
		return temp;
		}
	}

	/**
	 * Adds the element at the specified index in the list.
	 * @param index the location to add the element
	 * @param element the element to add
	 * @throws NullPointerException if element is null
	 * @throws IllegalArgumentException if element is already in the list
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater
	 * than the ArrayList's size.
	 */
	@Override
	public void add(int index, E element) {
		if (element == null) {
			throw new NullPointerException("Element is null");
		}
		else if (index < 0 || index > size()) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
		else {
			for (int i = 0; i < size(); i++) {
				if (list[i].equals(element)) {
					throw new IllegalArgumentException("Element is duplicate");
				}
			}
			
			if (size() == capacity) {
				growArray();
				for (int i = size; i > index; i--) {
		            list[i] = list[i - 1];
		        }

		        list[index] = element;
		        size++;
			}
			else {
				for (int i = size; i > index; i--) {
		            list[i] = list[i - 1];
		        }

		        list[index] = element;
		        size++;
			}
		}
		
	}

	/**
	 * Removes the element from the specified index in the list.
	 * @param index the location to remove from
	 * @return the element previously at the specified position
	 * @throws IndexOutOfBoundsException if index is less than 0 or greater than
	 * or equals to this ArrayList's size
	 */
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size()) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}
		else {
			E element = list[index];
			for (int i = index; i < size(); i++) {
				list[i] = list[i + 1];
			}
			list[size - 1] = null;
			size--;
			return element;
		}
		
		
	}

	/**
	 * Returns the number of elements in this list.
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}
	
	@SuppressWarnings("unchecked")
	private void growArray() {
		
		capacity = capacity * 2;
		E [] newArray = (E[]) new Object[capacity];
		for (int i = 0; i < size(); i++) {
			newArray[i] = list[i];
		}
		list = newArray;
		
	}
	
	
	
}
