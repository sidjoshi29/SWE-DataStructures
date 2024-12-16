/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This is the linked list test class and all of the methods within it
 */
class LinkedListTest {

	@Test
	void testLinkedList() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		assertEquals(0, linkedList.size());
	}
	
	@Test
	void testListIterator() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		assertEquals(0, linkedList.size());
		
		linkedList.add(0, 0);
		linkedList.add(1, 1);
		linkedList.add(2, 2);
		linkedList.add(3, 3);
		
		// [0,1,2,3]
		assertEquals(1, linkedList.listIterator(2).previous());
		assertEquals(0, linkedList.listIterator(0).next());
		
		linkedList.listIterator(4).add(4);
		linkedList.listIterator(2).add(8);
		
		// [0,1,8,2,3,4]
		assertEquals(0, linkedList.listIterator(0).next());
		assertEquals(1, linkedList.listIterator(1).next());
		assertEquals(8, linkedList.listIterator(2).next());
		assertEquals(2, linkedList.listIterator(3).next());
		assertEquals(3, linkedList.listIterator(4).next());
		assertEquals(4, linkedList.listIterator(5).next());
		
		assertEquals(4, linkedList.listIterator(5).previousIndex());
		assertEquals(0, linkedList.listIterator(0).nextIndex());
		
		assertEquals(0, linkedList.listIterator(0).next());
//		linkedList.listIterator(3).remove();
//		
//		assertEquals(0, linkedList.listIterator(0).next());
//		assertEquals(8, linkedList.listIterator(1).next());
//		assertEquals(2, linkedList.listIterator(2).next());
//		assertEquals(3, linkedList.listIterator(3).next());
//		assertEquals(4, linkedList.listIterator(4).next());
	}
	
	@Test 
	void testAdd() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		assertEquals(0, linkedList.size());
		
		linkedList.add(0, 0);
		linkedList.add(1, 1);
		linkedList.add(2, 2);
		linkedList.add(3, 3);
		
		assertEquals(4, linkedList.size());
		
		assertThrows(IllegalArgumentException.class, () -> linkedList.add(2, 0));
	}
	
	
	@Test
    public void testRemove() {
        // Remove the element at index 0
        LinkedList<Integer> linkedList = new LinkedList<>();
        assertEquals(0, linkedList.size());
        linkedList.add(0, 1);
        linkedList.add(1, 2);
        linkedList.remove(0);
        assertEquals(1, linkedList.size());
        assertEquals(2, linkedList.get(0));

        // Remove the element in the middle
        linkedList = new LinkedList<>();
        assertEquals(0, linkedList.size());
        linkedList.add(0, 1);
        linkedList.add(1, 2);
        linkedList.add(2, 3);
        linkedList.remove(1);
        assertEquals(2, linkedList.size());
        assertEquals(1, linkedList.get(0));
        assertEquals(3, linkedList.get(1));

        // Remove the element at the end
        linkedList = new LinkedList<>();
        assertEquals(0, linkedList.size());
        linkedList.add(0, 1);
        linkedList.add(1, 2);
        linkedList.add(2, 3);
        linkedList.remove(2);
        assertEquals(2, linkedList.size());
        assertEquals(1, linkedList.get(0));
        assertEquals(2, linkedList.get(1));

        // Remove the element at an invalid index
        linkedList = new LinkedList<>();
        assertEquals(0, linkedList.size());
        linkedList.add(0, 1);
        linkedList.add(1, 2);
        linkedList.add(2, 3);
        assertEquals(3, linkedList.size());

        // Remove element from an empty list
        linkedList = new LinkedList<>();
        assertEquals(0, linkedList.size());
        assertEquals(0, linkedList.size());
    }
	
	@Test
	void testSet() {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		assertEquals(0, linkedList.size());
		
		linkedList.add(0, 0);
		linkedList.add(1, 1);
		linkedList.add(2, 2);
		linkedList.add(3, 3);
		
		assertEquals(4, linkedList.size());
		
		linkedList.set(0, 4);
		linkedList.set(1, 5);
		linkedList.set(2, 6);
		linkedList.set(3, 7);
		
		assertEquals(4, linkedList.size());
		
		assertThrows(IllegalArgumentException.class, () -> linkedList.set(2, 5));
	}

}