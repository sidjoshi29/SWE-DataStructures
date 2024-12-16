	package edu.ncsu.csc316.dsa.list.positional;
	
	import java.util.Iterator;
	import java.util.NoSuchElementException;
	
	import edu.ncsu.csc316.dsa.Position;
	
	/**
	 * The Positional Linked List is implemented as a doubly-linked list data
	 * structure to support efficient, O(1) worst-case Positional List abstract data
	 * type behaviors.
	 * 
	 * Size is maintained as a global field to ensure O(1) worst-case runtime of
	 * size() and isEmpty().
	 * 
	 * The PositionalLinkedList class is based on the implementation developed for
	 * use with the textbook:
	 *
	 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
	 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
	 * 
	 * @author Dr. King
	 * @author Siddhant Joshi
	 *
	 * @param <E> the type of elements stored in the positional list
	 */
	public class PositionalLinkedList<E> implements PositionalList<E> {
	
	    /** A dummy/sentinel node representing at the front of the list **/
	    private PositionalNode<E> front;
	
	    /** A dummy/sentinel node representing at the end/tail of the list **/
	    private PositionalNode<E> tail;
	
	    /** The number of elements in the list **/
	    private int size;
	
	    /**
	     * Constructs an empty positional linked list
	     */
	    public PositionalLinkedList() {
	    	front = new PositionalNode<E>(null);
	        tail = new PositionalNode<E>(null, null, front);
	        front.next = tail;
	        size = 0;
	    }
	
	    /**
	     * The code for this method is based on the Positional Lists on page 278 in the course textbook
	     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
	     * @param p the position
	     * @return the positional node
	     */
	    private PositionalNode<E> validate(Position<E> p) {
	        if (p instanceof PositionalNode) {
	            return (PositionalNode<E>) p;
	        }
	        throw new IllegalArgumentException("Position is not a valid positional list node.");
	    }
	    
	    /**
	     * The code for this method is based on the Positional Lists on page 278 in the course textbook
	     * "Data Structures and Algorithms" by Goodrich, Tamassia, Goldwasser.
	     * @param element element of node
	     * @param next next of the node
	     * @param prev prev of the node
	     * @return the newest 
	     */
	    private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
	    	//increase size then add the elements
	    	size++;
	    	PositionalNode<E> newest = new PositionalNode<>(element, next, prev);
	    	prev.next = newest;
	    	next.previous = newest;
	    	return newest;
	        
	    }
	    
	    /**
	     * Private helper method position.
	     * @param node is the positional node.
	     * @return node.
	     */
	    private Position<E> position(PositionalNode<E> node ){
	    	if (node == front || node == tail) {
	    		return null;
	    	}
	    	return node;
	    }
	    
	    /**
	     * Returns the element iterator
	     */
		@Override
		public Iterator<E> iterator() {
			return new ElementIterator();
		}
	
		/**
		 * Inserts a new element after the given position in the positional list.
		 * @param p The position after which the new element should be inserted.
		 * @param element The element to be inserted.
		 * @return The position of the newly inserted element.
		 */
		@Override
		public Position<E> addAfter(Position<E> p, E element) {
			PositionalNode<E> node = validate(p);
			return addBetween(element, node.next, node);
		}
	 
		/**
		 * Inserts a new element before the given position in the positional list.
		 * 
		 * @param p The position before which the new element should be inserted.
		 * @param element The element to be inserted.
		 * @return The position of the newly inserted element.
		 */
		@Override
		public Position<E> addBefore(Position<E> p, E element) {
			PositionalNode<E> node = validate(p);
			return addBetween(element, node, node.previous);
		}
	
		
		/**
		 * Inserts a new element at the beginning of the positional list.
		 * 
		 * @param element The element to be inserted.
		 * @return The position of the newly inserted element.
		 */
		@Override
		public Position<E> addFirst(E element) {
			return addBetween(element, front.next, front);
		}
	
		
		/**
		 * Inserts a new element at the end of the positional list.
		 * 
		 * @param element The element to be inserted.
		 * @return The position of the newly inserted element.
		 */
		@Override
		public Position<E> addLast(E element) {
			return addBetween(element, tail, tail.previous); 
		}
	
		/**
		 * Returns the position of the element that precedes the given position in the positional list.
		 * @param p The position for which to find the preceding position.
		 * @return The position of the element before the given position, or null if none exists.
		 */
		@Override
		public Position<E> before(Position<E> p) {
			PositionalNode<E> node = validate(p);
			//should return null if there is no position before the given position - done in validate()
			return position(node.previous);
		}
		
		/**
		 * Returns the position of the element that follows the given position in the positional list.
		 * @param p The position for which to find the following position.
		 * @return The position of the element after the given position, or null if none exists.
		 */
		@Override
		public Position<E> after(Position<E> p) {
			PositionalNode<E> node = validate(p);
			//should return null if there is no position after the given position - done in validate()
			return position(node.next);
		}
	
		/**
		 * Returns the position of the first element in the positional list.
		 * 
		 * @return The position of the first element.
		 */
		@Override
		public Position<E> first() {
			// should return null if list is empty - done in position()
			return position(front.next);
		}
	
		
		/**
		 * Checks if the positional list is empty.
		 * 
		 * @return true if the list is empty (size is 0), false otherwise.
		 */
		@Override
		public boolean isEmpty() {
			//return true if size is 0 else false
			return size == 0;
		}
	
		
		/**
		 * Returns the position of the last element in the positional list.
		 * 
		 * @return The position of the last element.
		 */
		@Override
		public Position<E> last() {
			//should retunr null if list is empty - done in position()
			return position(tail.previous);
		}
	
		/**
		 * Returns an iterable collection of all positions in the positional list.
		 * 
		 * @return An iterable collection of positions.
		 */
		@Override
		public Iterable<Position<E>> positions() {
			return new PositionIterable();
		}
	
		/**
		 * Removes the element at the given position and returns its value.
		 * 
		 * @param p The position of the element to be removed.
		 * @return The value of the removed element.
		 */
		@Override
		public E remove(Position<E> p) {
			PositionalNode<E> node = validate(p);
			PositionalNode<E> pred = node.previous;
			PositionalNode<E> nex = node.next;
			pred.next = nex;
			nex.previous = pred;
			size--;
			return node.getElement();
		}
	
		
		/**
		 * Sets the element at that position
		 * 
		 * @param p The position of the element to be replaced.
		 * @param element The new element to be placed at the position.
		 * @return The value of the element that was replaced.
		 */
		@Override
		public E set(Position<E> p, E element) {
			PositionalNode<E> node = validate(p);
			E temp = node.getElement();
			node.element = element;
			return temp;
		}
		
		
		/**
		 * Returns the number of elements in the positional list.
		 * 
		 * @return The number of elements in the list.
		 */
		@Override
		public int size() {
			return size;
		}
	
		 private static class PositionalNode<E> implements Position<E> {
	
		        private E element;
		        private PositionalNode<E> next;
		        private PositionalNode<E> previous;
	
		        public PositionalNode(E value) {
		            this(value, null);
		        }
	
		        public PositionalNode(E value, PositionalNode<E> next) {
		            this(value, next, null);
		        }
	
		        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
		            this.element = value;
		            this.next = next;
		            this.previous = prev;
		        }
	
		        @Override
		        public E getElement() {
		            return element;
		        }
		        
		    }
		 
		 
		 private class ElementIterator implements Iterator<E> {
	
		        private Iterator<Position<E>> it;
	
		        public ElementIterator() {
		            it = new PositionIterator();
		        }
	
		        @Override
		        public boolean hasNext() {
		            return it.hasNext();
		        }
	
		        @Override
		        public E next() {
		            return it.next().getElement();
		        }
	
		        @Override
		        public void remove() {
		            it.remove();
		        }
		    }
		 
		 
		 private class PositionIterator implements Iterator<Position<E>> {
	
		        private Position<E> current;
		        private boolean removeOK;
	
		        public PositionIterator() {
		            current = new PositionalNode<E>(null);
		            current = front;
		            removeOK  = false;
		        }
	
		        @Override
		        public boolean hasNext() {
		        	//we check if the position is not at tail. if it is at tail there is no next
					return validate(current).next != tail;
		        }
	
		        @Override
		        public Position<E> next() {
		        	//we cant get next we are at tail
		        	if(!hasNext()) {
						throw new NoSuchElementException("No next element");
					} else {
						//curr = curr.next;
						current = validate(current).next;
						removeOK = true;
						//return current position
						return current;
					}
		        }
	
		        @Override
		        public void remove() {
		        	if(!removeOK) {
						throw new IllegalStateException("Nothing to remove");
					}
		            PositionalLinkedList.this.remove(current);
		            removeOK = false;  
		        }
		    }
		 
		 private class PositionIterable implements Iterable<Position<E>> {
		        
		        @Override
		        public Iterator<Position<E>> iterator() {
		            return new PositionIterator();
		        }
		    }
	
	
	
	}