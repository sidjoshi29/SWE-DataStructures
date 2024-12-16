package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class LinkedListRecursiveTest {

	@Test
	void testLinkedListRecursive() {
		LinkedListRecursive<Integer> list = new LinkedListRecursive<Integer>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
	}
	
	@Test
	void testAdd() {
		LinkedListRecursive<Integer> list = new LinkedListRecursive<Integer>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		
		assertEquals(0, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
		assertEquals(3, list.get(3));
		
		list.add(2, 4);
		
		assertEquals(0, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(4, list.get(2));
		assertEquals(2, list.get(3));
		assertEquals(3, list.get(4));
		
		assertThrows(IllegalArgumentException.class, () -> list.add(null));
		assertThrows(IllegalArgumentException.class, () -> list.add(2));
		
		assertThrows(NullPointerException.class, () -> list.add(3, null));
		assertThrows(IndexOutOfBoundsException.class, () -> list.add(-1, 5));
		assertThrows(IllegalArgumentException.class, () -> list.add(2, 0));
		
	}
	
	@Test
	void testGet() {
		LinkedListRecursive<Integer> list = new LinkedListRecursive<Integer>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		
		assertEquals(0, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
		assertEquals(3, list.get(3));
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.get(-1));
	}
	
	@Test
	void testContains() {
		LinkedListRecursive<Integer> list = new LinkedListRecursive<Integer>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		
		list.contains(0);
		list.contains(1);
		list.contains(2);
		list.contains(3);
		
	}
	
	@Test
	void testRemove() {
		LinkedListRecursive<Integer> list = new LinkedListRecursive<Integer>();
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0));
		
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		
		assertEquals(0, list.get(0));
		assertEquals(1, list.get(1));
		assertEquals(2, list.get(2));
		assertEquals(3, list.get(3));
		
		list.remove(1);
		
		assertEquals(0, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(3, list.get(2));
		
		list.remove(0);
		
		assertEquals(2, list.get(0));
		assertEquals(3, list.get(1));
		
		LinkedListRecursive<String> list2 = new LinkedListRecursive<String>();
		assertEquals(0, list2.size());
		assertTrue(list2.isEmpty());
		
		list2.add("Arnav");
		list2.add("Siddhant");
		list2.add("Shaurya");
		
		assertEquals("Arnav", list2.get(0));
		assertEquals("Siddhant", list2.get(1));
		assertEquals("Shaurya", list2.get(2));
		
		assertTrue(list2.remove("Siddhant"));
		
		assertEquals("Arnav", list2.get(0));
		assertEquals("Shaurya", list2.get(1));
		
		assertTrue(list2.remove("Arnav"));
		
		assertEquals("Shaurya", list2.get(0));
		
	}
	
	@Test
	void testSet() {
		LinkedListRecursive<String> list2 = new LinkedListRecursive<String>();
		assertEquals(0, list2.size());
		assertTrue(list2.isEmpty());
		
		list2.add("Arnav");
		list2.add("Siddhant");
		list2.add("Shaurya");
		
		assertEquals("Arnav", list2.get(0));
		assertEquals("Siddhant", list2.get(1));
		assertEquals("Shaurya", list2.get(2));
		
		list2.set(0, "John");
		
		assertEquals("John", list2.get(0));
		assertEquals("Siddhant", list2.get(1));
		assertEquals("Shaurya", list2.get(2));
	}
	
	

}
