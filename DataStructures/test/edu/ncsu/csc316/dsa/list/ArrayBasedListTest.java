package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;


/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class ArrayBasedListTest {

	/** Private Instance of list */
    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new ArrayBasedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
        
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        assertFalse(list.isEmpty());
        
        list.add(3, "four");
        assertEquals(4, list.size());
        assertEquals("four", list.get(3));
        assertFalse(list.isEmpty());
        
        list.add(4, "five");
        assertEquals(5, list.size());
        assertEquals("five", list.get(4));
        assertFalse(list.isEmpty());
        
        list.add(5, "six");
        assertEquals(6, list.size());
        assertEquals("six", list.get(5));
        assertFalse(list.isEmpty());

        
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        

        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
    	ArrayBasedList<String> list1 = new ArrayBasedList<>();
        assertTrue(list1.isEmpty());

        list1.addLast("last1");
        assertEquals(1, list1.size());
        

        list1.addLast("last2");
        assertEquals(2, list1.size());
        assertEquals("last1", list1.get(0));
        assertEquals("last2", list1.get(1));
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	ArrayBasedList<String> list2 = new ArrayBasedList<>();
        assertTrue(list2.isEmpty());

        list2.add(0, "first");
        assertEquals(1, list2.size());
        assertEquals("first", list2.first());    
        
        list2.add(0, "second");
        assertEquals(2, list2.size());
        assertEquals("first", list2.last());
		
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	ArrayBasedList<String> list3 = new ArrayBasedList<>();
        assertTrue(list3.isEmpty());

        list3.addFirst("first");
        assertEquals(1, list3.size());
        assertEquals("first", list3.get(0));

        list3.addFirst("second");
        assertEquals(2, list3.size());
        assertEquals("second", list3.get(0));
        assertEquals("first", list3.get(1));
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
    	ArrayBasedList<String> list4 = new ArrayBasedList<>();
        assertTrue(list4.isEmpty());

        list4.add(0, "first");
        assertEquals(1, list4.size());
        assertEquals("first", list4.first());    
        
        list4.add(0, "second");
        assertEquals(2, list4.size());
        assertEquals("second", list4.first());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) { 
            assertTrue(e instanceof NoSuchElementException);
        }

        list = new ArrayBasedList<String>();
        list.addFirst("test1");
        list.add(1, "test2");
        list.add(2, "test3");
        list.add(3, "test4");
        list.addLast("test5");
        
        assertEquals(5, list.size());
        
        
        it = list.iterator();
        
        assertEquals("test1", it.next());
        assertTrue(it.hasNext());
        it.remove();
        
        assertEquals("test2", it.next());
        assertEquals("test3", it.next());
        assertEquals("test3", list.get(1));
        it.remove();
        //size should be 3 after 2 removes
        assertEquals(3, list.size());
        
        assertEquals("test4", list.get(1));
        assertEquals("test4", it.next());
        assertEquals("test5", it.next());
        it.remove();
        
        //size should be 3
        assertEquals(2, list.size());
        
        assertFalse(it.hasNext());
        
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	ArrayBasedList<Integer> list6 = new ArrayBasedList<Integer>();
		
    	list6.add(0, 1);
		assertEquals(1, list6.size());
		
		list6.add(1, 2);
		assertEquals(2, list6.size());
		
		list6.add(2, 3);
		assertEquals(3, list6.size());
		
		list6.add(3, 4);
		assertEquals(4, list6.size());
		
		list6.remove(1);
		assertEquals(3, list6.size());
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	ArrayBasedList<String> list7 = new ArrayBasedList<>();
    	list7.addFirst("first");
    	list7.addLast("second");

        String removed = list7.removeFirst();
        assertEquals("first", removed);
        assertEquals(1, list7.size());
        assertEquals("second", list7.get(0));
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	ArrayBasedList<String> list8 = new ArrayBasedList<>();
    	list8.add(0, "first");
    	list8.add(1, "second");

        assertEquals("second", list8.last());
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	ArrayBasedList<Integer> list9 = new ArrayBasedList<Integer>();
		
    	list9.add(0, 1);
		assertEquals(1, list9.size());
		
		list9.add(1, 2);
		assertEquals(2, list9.size());
		
		list9.add(2, 3);
		assertEquals(3, list9.size());
		
		list9.add(3, 4);
		assertEquals(4, list9.size());
		
		list9.set(0, 10);
		assertEquals(4, list9.size());
		
		list9.set(1, 20);
		assertEquals(4, list9.size());
		
		assertThrows(NullPointerException.class, () -> list9.set(0, null));
		assertThrows(IndexOutOfBoundsException.class, () -> list9.set(-1, 100));

    }
}