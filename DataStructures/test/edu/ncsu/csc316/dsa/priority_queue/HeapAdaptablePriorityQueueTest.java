package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priorty Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class HeapAdaptablePriorityQueueTest {

    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /** 
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");

        assertEquals(1, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(1, heap.size());
        
       
    
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");

        assertEquals(1, heap.size());
        
        heap.replaceValue(e8,  "EIGHT");
        assertEquals(8, (int)heap.min().getKey());
        assertEquals("EIGHT", heap.min().getValue());
        assertEquals(1, heap.size());
        assertEquals("EIGHT",  e8.getValue());

    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        

        heap.insert(1, "one");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(2, heap.size());
        
        heap.remove(e0);
        assertEquals(1, (int)heap.min().getKey());
        assertEquals("one", heap.min().getValue());
        assertEquals(1, heap.size());

    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        sHeap.insert(s2, "2");
        sHeap.insert(s5, "4");
        sHeap.insert(s3, "3");
        sHeap.insert(s1, "1");

        assertEquals(4, sHeap.size());
        assertEquals("1", sHeap.min().getValue());
        assertEquals("jk1", sHeap.deleteMin().getKey().getUnityID());
        assertEquals(3, sHeap.size());
        assertEquals("2", sHeap.min().getValue());
        assertEquals("js2", sHeap.deleteMin().getKey().getUnityID());
        assertEquals(2, sHeap.size());
        assertEquals("3", sHeap.min().getValue());
        assertEquals("sh3", sHeap.deleteMin().getKey().getUnityID());
        assertEquals(1, sHeap.size());
        assertEquals("4", sHeap.min().getValue());
    }
}