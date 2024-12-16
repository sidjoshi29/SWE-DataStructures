package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue.
 * Checks the expected outputs of the Queue abstract data type behaviors when using
 * a circular array-based data structure
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class ArrayBasedQueueTest {

    private Queue<String> queue;
    
    /**
     * Create a new instance of a circular array-based queue before each test case executes
     */ 
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }

    /**
     * Test the output of the enqueue(e) behavior
     */     
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("three");
        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("four");
        assertEquals(4, queue.size());
        assertFalse(queue.isEmpty());
        
        
        
        
    }
    
    /**
     * Test the output of the dequeue(e) behavior, including expected exceptions
     */     
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }   
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("three");
        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
        
        queue.enqueue("four");
        assertEquals(4, queue.size());
        assertFalse(queue.isEmpty());
        
        //dequue testing
        
        String removed1 = queue.dequeue();
        assertEquals("one", removed1);
        assertEquals(3, queue.size());
        
        
        String removed2 = queue.dequeue();
        assertEquals("two", removed2);
        assertEquals(2, queue.size());
        
       
        String removed3 = queue.dequeue();
        assertEquals("three", removed3);
        assertEquals(1, queue.size());

    }
    
    /**
     * Test the output of the front() behavior, including expected exceptions
     */     
    @Test
    public void testFront() {
    	assertEquals(0, queue.size());
        try {
            queue.front();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }    
    }

}