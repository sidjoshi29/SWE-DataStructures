package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;


/**
 * Test class for HeapPriorityQueue
 * Checks the expected outputs of the Priorty Queue abstract data type behaviors when using
 * a min-heap data structure 
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class HeapPriorityQueueTest {

    private PriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */     
    @Before
    public void setUp() {
        heap = new HeapPriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the insert(k,v) behavior
     */     
    @Test
    public void testInsert() {
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(3, "three");
        assertEquals(2, heap.size());
        assertEquals(3, (int)heap.min().getKey());  

        heap.insert(5, "five");
        assertEquals(3, heap.size());
        assertEquals(3, (int)heap.min().getKey());

    }
    
    /**
     * Test the output of the min behavior
     */ 
    @Test
    public void testMin() {
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        
        assertNull(heap.min());
        
        heap.insert(8, "eight");
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(3, "three");
        assertEquals(3, (int)heap.min().getKey()); 
        
        heap.insert(1, "one");
        assertEquals(1, (int)heap.min().getKey()); 
        
        assertEquals(3, heap.size());
        
        
    }
    
    /**
     * Test the output of the deleteMin behavior
     */     
    @Test 
    public void deleteMin() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        assertNull(heap.deleteMin());
        
        heap.insert(5, "seven");
        heap.insert(3, "four");
        heap.insert(8, "eight");

        assertEquals(3, (int)heap.deleteMin().getKey());  
        assertEquals(2, heap.size());  
        assertEquals(5, (int)heap.min().getKey()); 

        heap.deleteMin();  
        assertEquals(8, (int)heap.min().getKey());  
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */ 
    @Test
    public void testStudentHeap() {
        PriorityQueue<Student, String> sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator());
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