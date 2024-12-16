package edu.ncsu.csc217.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests the SortedList class
 * 
 * @author Siddhant Joshi
 * @author Dolly Jani
 * @author Chloe Israel

 */
public class SortedListTest {
	/**
	 * Tests the SortedList constructor
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		list.add("first element");
		list.add("second element");
		list.add("third element");
		list.add("fourth element");
		list.add("fifth element");
		list.add("sixth element");
		list.add("seventh element");
		list.add("eighth element");
		list.add("ninth element");
		list.add("tenth element");
		list.add("eleventh element");


		//checking if size of list is equal to 11
		assertEquals(11, list.size());
		
	}

	/**
	 * Testing the add() method 
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		//current list = [banana]
		
		//current list = [banana]

		// adding to the front of the list
		list.add("apple");
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		// current list = [apple, banana]

		//adding to the middle of list
		list.add("carrot");
		assertEquals(3, list.size());
		assertEquals("carrot", list.get(2));
		// current list = [apple, banana, carrot]

		//adding to the end of the list
		list.add("radish");
		assertEquals(4, list.size());
		assertEquals("radish", list.get(3));
		// current list = [apple, banana, carrot, radish]


		//adding a null element
		try {
			list.add(null);
			fail();
		} 
		catch(NullPointerException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("carrot", list.get(2));
			assertEquals("radish", list.get(3));
		}

		

		// adding a duplicate element
		try {
			list.add("apple");
			fail();
		} 
		catch(IllegalArgumentException e) {
			assertEquals(4, list.size());
			assertEquals("apple", list.get(0));
			assertEquals("banana", list.get(1));
			assertEquals("carrot", list.get(2));
			assertEquals("radish", list.get(3));
		}

	}
	
	/**
	 * Testing the get() method 
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//getting an element from an empty list
		try {
			list.get(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}

		// Add some elements to the list
		list.add("sid");
		list.add("chloe");
		list.add("dolly");

		// getting an element at an index < 0
		try {
			list.get(-1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}

		//getting an element at size

		try {
			list.get(3);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(3, list.size());
		}
		}

	/**
	 * Test the remove() method
	 */

	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e1) {
			assertEquals(0, list.size());
		}

		list.add("apple");
		list.add("banana");
		list.add("watermelon");
		list.add("strawberry");
		
		//Test removing an element at an index < 0
		try {
			list.remove(-1);
			fail();
		} catch (IndexOutOfBoundsException e2) {
			assertEquals(4, list.size());
		}
		
		//Test removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e3) {
			assertEquals(4, list.size());
		}
		
		// Test removing an element at an index > list.size
		try {
			list.remove(5);
			fail();
		} catch (IndexOutOfBoundsException e4) {
			assertEquals(4, list.size());
		}
		
		//Test removing a middle element
		list.remove(1);
		assertEquals(3, list.size());
		assertEquals("strawberry", list.get(1));
		
		//Test removing the last element
		list.remove(2);
		assertEquals(2, list.size());
		assertEquals("apple", list.get(0));
		assertEquals("strawberry", list.get(1));
		
		//Test removing the first element
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("strawberry", list.get(0));
		
		//Test removing the last element
		list.remove(0);
		assertEquals(0, list.size());
	}
	/**
	 * Test indexOf() method
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		//Test indexOf on an empty list
		assertEquals(-1, list.indexOf("apple"));
		
		list.add("apple");
		list.add("banana");
		list.add("watermelon");
		list.add("strawberry");
		
		//Test various calls to indexOf for elements in the list
		assertEquals(1, list.indexOf("banana"));
		assertEquals(3, list.indexOf("watermelon"));
		
		//Test calling indexOf for elements not in the list
		assertEquals(-1, list.indexOf("blueberry"));
		
		//Test checking the index of null
		try {
			list.indexOf(null);
			fail();
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
			assertEquals(0, list.indexOf("apple"));
			assertEquals(1, list.indexOf("banana"));
			assertEquals(2, list.indexOf("strawberry"));
			assertEquals(3, list.indexOf("watermelon"));
		}

	}
	
	/**
	 * Tests the clear() method
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("apple");
		list.add("banana");
		assertEquals(2, list.size());
		
		// Clear the list
		list.clear();
		assertEquals(0, list.size());
	}
	
	/**
	 * Tests the isEmpty() method
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		assertEquals(0, list.size());
		assertTrue(list.isEmpty());
		
		list.add("apple");
		
		//Check that the list is no longer empty
		assertEquals(1, list.size());
		assertFalse(list.isEmpty());
	}

	/**
	 * Tests the contains() method 
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//Test an empty list 
		assertFalse(list.contains("Projector"));
		
		list.add("Computer");
		list.add("Mouse");
		list.add("keyboard");
		
		// True cases
		assertTrue(list.contains("Computer"));
		assertTrue(list.contains("Mouse"));
		assertTrue(list.contains("keyboard"));
		
		// False cases
		assertFalse(list.contains("Projector"));
		assertFalse(list.contains("Laptop"));
	}
	
	/**
	 * Tests the equals() method 
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		// Two duplicate lists and one different
		list1.add("Computer");
		list1.add("Mouse");
		list1.add("keyboard");
		
		list2.add("Computer");
		list2.add("Mouse");
		list2.add("keyboard");
		
		list3.add("no Computer");
		list3.add("no Mouse");
		list3.add("no keyboard");
		
		// Compare
		assertEquals(list1, list2);
		assertEquals(list2, list1);
		
		assertNotEquals(list2, list3);
		assertNotEquals(list3, list2);
		}

	/**
	 * Test hashCode() method checks if the the two objects are equal if they have the same hash code
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("Computer");
		list1.add("Mouse");
		list1.add("keyboard");
		
		list2.add("Computer");
		list2.add("Mouse");
		list2.add("keyboard");
		
		list3.add("no Computer");
		list3.add("no Mouse");
		list3.add("no keyboard");
		
		assertEquals(list1.hashCode(), list2.hashCode());
		assertEquals(list2.hashCode(), list1.hashCode());
		
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list3.hashCode(), list1.hashCode());
	}

}
 
 