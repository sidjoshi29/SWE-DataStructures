package edu.ncsu.csc316.dsa.list.positional;

import static org.junit.Assert.*;

import java.util.Iterator;


import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class PositionalLinkedListTest {

	/** List intsance */
    private PositionalList<String> list;
    /** List 1 instance */
    private PositionalList<String> list1;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
        list1 = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first.getElement(), list.first().getElement());
        
        Position<String> new1 = list.addFirst("newOne");
        assertEquals(2, list.size());
        assertEquals(new1, list.first());
        
        
        Position<String> new2 = list.addFirst("newTwo");
        assertEquals(3, list.size());
        assertEquals(new2, list.first());
        
 
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertEquals(0, list1.size());
        assertTrue(list1.isEmpty());
        
        assertNull(list1.first());
        
        Position<String> last = list1.addFirst("last");
        assertEquals(1, list1.size());
        assertEquals(last, list1.last());
        
        list1.addFirst("newOne");
        assertEquals(2, list1.size());
        assertEquals(last, list1.last());
        
        
        Position<String> new2 = list1.addLast("newTwo");
        assertEquals(3, list1.size());
        assertEquals(new2, list1.last());
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(first, list.first());
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        
        Position<String> second = list.addFirst("second");
        assertEquals(second, list.first());
        assertEquals(2, list.size());
        assertFalse(list.isEmpty());
        
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        list.addLast("one");
        assertEquals(1, list.size());
        
        list.addLast("two");
        assertEquals(2, list.size());
        
        
        list.addLast("three");
        assertEquals(3, list.size());
       
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBefore() {
    	PositionalList<String> list2 = new PositionalLinkedList<String>();
    	
    	Position<String> first = list2.addFirst("First");
    	Position<String> last = list2.addLast("Last");
    	
    	assertEquals(first, list2.before(last));
    	
    	Position<String> last1 = list2.addLast("Last1");
    	
    	assertEquals(last, list2.before(last1));
    	
    	
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfter() {
    	PositionalList<String> list5 = new PositionalLinkedList<String>();
    	
    	Position<String> first = list5.addFirst("First");
    	Position<String> last = list5.addLast("Last");
    	
    	assertEquals(last, list5.after(first));
    	
    	Position<String> first1 = list5.addFirst("first1");
    	
    	assertEquals(first, list5.after(first1));
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
    	PositionalList<String> list6 = new PositionalLinkedList<String>();
    	
    	list.addFirst("First");
    	Position<String> last = list6.addLast("Last");
    	
    	Position<String> first1 = list6.addBefore(last, "first1");
    	
    	assertEquals(first1.getElement(), list6.addBefore(last, "first1").getElement());
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
    	PositionalList<String> list3 = new PositionalLinkedList<String>();
    	
    	Position<String> first = list3.addFirst("First");
    	Position<String> last = list3.addLast("Last");
    	
    	assertEquals(last, list3.last());
    	
    	Position<String> first1 = list3.addAfter(first, "first1");
    	
    	assertEquals(first1.getElement(), list3.addBefore(first, "first1").getElement());
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
        PositionalList<String> list4 = new PositionalLinkedList<String>();

        list.addFirst("First");
        Position<String> last = list4.addLast("Last");

        assertEquals("Last", list4.set(last, "lastReplacement")); // Replace "Last" with "lastReplacement"
        assertEquals("lastReplacement", list4.last().getElement()); // Check the element in the last position
    }

    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
    	PositionalList<String> list5 = new PositionalLinkedList<String>();
    	
    	Position<String> first = list5.addFirst("First");
    	Position<String> last = list5.addLast("Last");
    	
    	assertEquals(last, list5.last());
  
    	
    	assertEquals(first.getElement(), list5.remove(first));
    }

    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next()); 
        assertFalse(it.hasNext());      
  
    }
    
    /**
     * Tests for iterator
     */
    @Test
    public void testIterators() {
    	assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next()); 
        assertFalse(it.hasNext());
        
        
        Iterator<String> elementIterator = list.iterator();
        assertTrue(elementIterator.hasNext());
        assertEquals("one", elementIterator.next());
        assertEquals("two", elementIterator.next());
        assertEquals("three", elementIterator.next());
        assertFalse(elementIterator.hasNext());
        
       
        it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        it.remove();
        assertEquals(2, list.size());
        assertEquals(second, list.first());
        
       
        elementIterator = list.iterator();
        assertTrue(elementIterator.hasNext());
        assertEquals("two", elementIterator.next());
        elementIterator.remove();
        assertEquals(1, list.size());
        assertEquals(third, list.first());
        
        
        assertTrue(elementIterator.hasNext());
        assertEquals("three", elementIterator.next());
        elementIterator.remove();
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
    }
    
}