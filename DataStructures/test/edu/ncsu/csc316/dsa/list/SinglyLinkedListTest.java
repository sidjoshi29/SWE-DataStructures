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
public class SinglyLinkedListTest {

	/** instance of list */
    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new SinglyLinkedList<String>();
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
        
        
        
        // Use the statements above to help guide your test cases
        // for data structures: Start with an empty data structure, then
        // add an element and check the accessor method return values.
        // Then add another element and check again. Continue to keep checking
        // for special cases. For example, for an array-based list, you should
        // continue adding until you trigger a resize operation to make sure
        // the resize operation worked as expected.
        
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
    	SinglyLinkedList<String> list1 = new SinglyLinkedList<>();
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
    	SinglyLinkedList<String> list2 = new SinglyLinkedList<>();
        assertTrue(list2.isEmpty());

        list2.add(0, "first");
        assertEquals(1, list2.size());
        assertEquals("first", list2.first());    
        
        list2.add(0, "second");
        assertEquals(2, list2.size());
        assertEquals("first", list2.last());
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
            assertTrue(e instanceof UnsupportedOperationException);
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

        // Add more elements
        list.addLast("two");
        list.addLast("three");
        assertEquals(3, list.size());

        // Iterate over all elements
        it = list.iterator();
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertTrue(it.hasNext());
        assertEquals("two", it.next());
        assertTrue(it.hasNext());
        assertEquals("three", it.next());
        assertFalse(it.hasNext());

        
        it = list.iterator();
        assertEquals("one", it.next()); // Move to the first element
        assertEquals(3, list.size());
        assertEquals("one", list.get(0)); // Now two should be the first element

        // Test IllegalStateException for remove
        try {
            it.remove();
            fail("An IllegalStateException should have been thrown");
        } catch (UnsupportedOperationException e) {
            //do nothing
        }

    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	SinglyLinkedList<Integer> list3 = new SinglyLinkedList<Integer>();
		
    	list3.add(0, 1);
		assertEquals(1, list3.size());
		
		list3.add(1, 2);
		assertEquals(2, list3.size());
		
		list3.add(2, 3);
		assertEquals(3, list3.size());
		
		list3.add(3, 4);
		assertEquals(4, list3.size());
		
		list3.remove(1);
		assertEquals(3, list3.size());
    }


    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	SinglyLinkedList<Integer> list4 = new SinglyLinkedList<Integer>();
		
    	list4.add(0, 1);
		assertEquals(1, list4.size());
		
		list4.add(1, 2);
		assertEquals(2, list4.size());
		
		list4.add(2, 3);
		assertEquals(3, list4.size());
		
		list4.add(3, 4);
		assertEquals(4, list4.size());
		
		list4.set(0, 10);
		assertEquals(4, list4.size());
		
		list4.set(1, 20);
		assertEquals(4, list4.size());

    }
}