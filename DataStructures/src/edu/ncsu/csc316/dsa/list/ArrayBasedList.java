package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author Siddhant Joshi
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
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
		checkIndexForAdd(index);
		
//		for (int i = 0; i < size(); i++) {
//			if (data[i].equals(element)) {
//				throw new IllegalArgumentException("Element is duplicate");
//			}
//		}
			
		//make space for new elements to be added
		ensureCapacity(size + 1);
			
		for (int i = size; i > index; i--) {
			data[i] = data[i - 1];
	    }

		data[index] = element;
	    size++;
		
	}

	/**
	 * Returns the element at the specified position in the list
	 * @param index the index of the element
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException if the index is out of range
	 */
	@Override
	public E get(int index) {
		checkIndex(index);
		if(index < 0 && index >= size) {
			throw new IndexOutOfBoundsException("Out of bounds index.");
		}
		
		return data[index];
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
			E element = data[index];
			for (int i = index; i < size - 1; i++) {
				data[i] = data[i + 1];
			}
			data[size - 1] = null;
			size--;
			return element;
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
		checkIndex(index);
		if (element == null) {
			throw new NullPointerException("Element is null");
		}
		else if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index is out of bounds");
		}

		E temp = data[index];
		data[index] = element;
		
		return temp;
		
	}

	/**
	 * Returns the number of elements in this list.
	 * @return the number of elements in the list
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Calls the element iterator
	 * @return new element iterator
	 */
	@Override
	public Iterator<E> iterator() {
	    return new ElementIterator();
	}
	
	/**
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = (oldCapacity * 2) + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }
    
    /**
     * Inner class ElemenrIterator for the `ArrayBasedList` class.
     */
	private class ElementIterator implements Iterator<E> {
		
		/** Instance of position of the iterator */
        private int position;
        /** A flag indicating whether it is safe to remove an element. */
        private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
        	//iterator always starts from the first index so lets start from 0
        	this.position = 0;
    		this.removeOK = false;
        }

        /**
         * Checks if there are more elements in the list to iterate.
         *
         * @return true if there are more elements to iterate.
         */
        @Override
        public boolean hasNext() {
        	return position < size;
        }

        /**
         * Retrieves the next element in the iteration and advances the iterator.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if there are no more elements to iterate
         */
        @Override
        public E next() {
        	if(position  == size) {
				throw new NoSuchElementException("No next element");
			}
			
			removeOK = true;
			return data[position++];
        }
            
        /**
         * Removes the last retrieved element from the list.
         *
         * @throws IllegalStateException if there is nothing to remove 
         */
        @Override
        public void remove() {
        	if(!removeOK) {
				throw new IllegalStateException("Nothing to remove");
			}
			ArrayBasedList.this.remove(position - 1);
			position--;
			removeOK = false;
        }
    }
    
}