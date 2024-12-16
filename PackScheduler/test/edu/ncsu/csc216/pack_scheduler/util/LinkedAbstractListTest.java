package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;

class LinkedAbstractListTest {
	
	@Test
	void testArrayList() {
		ArrayList<String> list = new ArrayList<String>();
		assertEquals(0, list.size());
		
		ArrayList<Integer> list2 = new ArrayList<Integer>();
		assertEquals(0, list2.size());
		
		ArrayList<Object> list3 = new ArrayList<Object>();
		assertEquals(0, list3.size());
	}


	@Test
	public void testSet(){
		LinkedAbstractList<String> list = new LinkedAbstractList<String>(10);
		// Test setting an element at an invalid index in an empty list
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(0, "test"));
        assertEquals(0, list.size());

        // Test setting an element at an invalid index in an empty list
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(5, "test"));
        assertEquals(0, list.size());

        // Test setting null at an index in an empty list
        assertThrows(NullPointerException.class, () -> list.set(0, null));
        assertEquals(0, list.size());

        // Add an element to the list
        list.add(0, "test");
        assertEquals(1, list.size());

        // Test setting an element at an invalid index in a non-empty list
        assertThrows(IndexOutOfBoundsException.class, () -> list.set(1, "test"));
        assertEquals(1, list.size());

        // Add more elements to the list
        list.add(0, "string");
        list.add(0, "words");

        // Verify the order of elements
        assertEquals("words", list.get(0));
        assertEquals("string", list.get(1));
        assertEquals("test", list.get(2));

        // Test setting elements at valid indices in a non-empty list
        list.set(0, "other");
        assertEquals("other", list.get(0));

        list.set(1, "letters");
        assertEquals("letters", list.get(1));
	}

	@Test
	void testAddE() {
		LinkedAbstractList<Integer> list = new LinkedAbstractList<Integer>(10);
		//Add - empty 
		list.add(0, 4); 
		assertEquals (1, list.size()); 
		assertEquals (4, list.get(0));
		//Add - front 
		list.add(0, 7); 
		assertEquals (2, list.size());
		assertEquals (7, list.get(0)); 
		assertEquals (4, list.get(1));
		
		//Add - middle 
		list.add(1, 23); 
		assertEquals (3, list.size());
		assertEquals(7, list.get(0));
		assertEquals (23, list.get(1)); 
		assertEquals (4, list.get(2));
		
		//Add - back 
		list.add(3, 14);
		assertEquals (4, list.size()); 
		assertEquals (7, list.get(0)); 
		assertEquals (23, list.get(1));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 100));
		
		
		
		
	}

	@Test
	void testRemoveE() {
		LinkedAbstractList<Integer> list = new LinkedAbstractList<Integer>(10);
		//Add - empty 
		list.add(0, 4); 
		assertEquals (1, list.size()); 
		assertEquals (4, list.get(0));
		//Add - front 
		list.add(0, 7); 
		assertEquals (2, list.size());
		assertEquals (7, list.get(0)); 
		assertEquals (4, list.get(1));
		//Add - middle 
		list.add(1, 23); 
		assertEquals (3, list.size());
		assertEquals(7, list.get(0));
		assertEquals (23, list.get(1)); 
		assertEquals (4, list.get(2));
		//Add - back 
		list.add(3, 14);
		assertEquals (4, list.size()); 
		assertEquals (7, list.get(0)); 
		assertEquals (23, list.get(1));
		
		list.remove(0);
		assertEquals(3, list.size());
		assertEquals(23, list.get(0));
		assertEquals(4, list.get(1));
		assertEquals(14, list.get(2));
		
		list.remove(1);
		assertEquals(2, list.size());
		assertEquals(23, list.get(0));
		assertEquals(14, list.get(1));
		
		list.remove(1);
		assertEquals(23, list.get(0));
		assertEquals(1, list.size());
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(-1));
		
		LinkedAbstractList<String> list2 = new LinkedAbstractList<String>(10);
		
		list2.add("orange");
		list2.add("banana");
		list2.add("apple");
		list2.add("kiwi");
		assertEquals(4, list2.size());
		
		list2.remove(1);
		assertEquals(3, list2.size());
		assertEquals("orange", list2.get(0));
		assertEquals("apple", list2.get(1));
		assertEquals("kiwi", list2.get(2));
		
		list2.remove(0);
		assertEquals(2, list2.size());
		assertEquals("apple", list2.get(0));
		assertEquals("kiwi", list2.get(1));
		
		assertEquals("kiwi", list2.remove(1));
		assertEquals(1, list2.size());
		assertEquals("apple", list2.get(0));
		
	}
	
	@Test
	void testGetE() {
		ArrayList<String> list = new ArrayList<String>();
		list.add(0, "Zero");
		assertEquals(1, list.size());
		
		list.add(1, "One");
		assertEquals(2, list.size());
		
		list.add(2, "Two");
		assertEquals(3, list.size());
		
		list.add(3, "Three");
		assertEquals(4, list.size());
		
		assertThrows(IndexOutOfBoundsException.class,
				() -> list.get(10));
		
		list.add(4, "Four");
		assertEquals(5, list.size());
		
		list.add(5, "Five");
		assertEquals(6, list.size());
		
		list.add(6, "Siz");
		assertEquals(7, list.size());
		
		list.add(7, "Seven");
		assertEquals(8, list.size());
		
		list.add(8, "Eight");
		assertEquals(9, list.size());
		
		list.add(9, "Nine");
		assertEquals(10, list.size());
		
		list.add(10, "Ten");
		assertEquals(11, list.size());
		
		list.add(11, "Eleven");
		assertEquals(12, list.size());
		
	}

}