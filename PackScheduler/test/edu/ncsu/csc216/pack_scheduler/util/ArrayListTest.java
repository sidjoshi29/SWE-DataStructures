package edu.ncsu.csc216.pack_scheduler.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ArrayListTest {
	
	
	

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
	void testSetIntE() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		list.add(0, 1);
		assertEquals(1, list.size());
		
		list.add(1, 2);
		assertEquals(2, list.size());
		
		list.add(2, 3);
		assertEquals(3, list.size());
		
		list.add(3, 4);
		assertEquals(4, list.size());
		
		list.set(0, 10);
		assertEquals(4, list.size());
		assertEquals(10, list.get(0));
		
		list.set(1, 20);
		assertEquals(4, list.size());
		assertEquals(20, list.get(1));
		
		assertThrows(NullPointerException.class, () -> list.set(0, null));
		assertThrows(IndexOutOfBoundsException.class, () -> list.set(-1, 100));
		assertThrows(IllegalArgumentException.class, () -> list.set(2, 10));
	}

	@Test
	void testAddE() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		//adding at the end of the list
		list.add(0, 1);
		assertEquals(1, list.size());
		
		list.add(1, 2);
		assertEquals(2, list.size());
		
		list.add(2, 3);
		assertEquals(3, list.size());
		
		list.add(3, 4);
		assertEquals(4, list.size());
		
		//adding in the middle
		list.add(2, 5);
		assertEquals(5, list.size());
		
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(5, list.get(2));
		assertEquals(3, list.get(3));
		assertEquals(4, list.get(4));
		
		//adding at the end
		list.add(4, 10);
		assertEquals(6, list.size());
		
		assertEquals(1, list.get(0));
		assertEquals(2, list.get(1));
		assertEquals(5, list.get(2));
		assertEquals(3, list.get(3));
		assertEquals(10, list.get(4));
		assertEquals(4, list.get(5));
		
		
		
		
	}

	@Test
	void testRemoveE() {
		ArrayList<Integer> list = new ArrayList<Integer>();
		
		list.add(0, 1);
		assertEquals(1, list.size());
		
		list.add(1, 2);
		assertEquals(2, list.size());
		
		list.add(2, 3);
		assertEquals(3, list.size());
		
		list.add(3, 4);
		assertEquals(4, list.size());
		
		list.remove(1);
		assertEquals(3, list.size());
		
		assertEquals(1, list.get(0));
		assertEquals(3, list.get(1));
		assertEquals(4, list.get(2));
		
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
