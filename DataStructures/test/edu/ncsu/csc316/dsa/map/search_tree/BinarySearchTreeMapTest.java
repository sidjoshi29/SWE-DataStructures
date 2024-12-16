package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map.Entry;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class BinarySearchTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(1, "one");
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        tree.put(3, "three");
        assertEquals(2, tree.size());
        assertEquals(1, (int)tree.root().getElement().getKey());
       
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        tree.put(3, "three");
        assertEquals(2, tree.size());
        
        assertEquals("one", tree.get(1));
        assertEquals("three", tree.get(3));
        
        tree.put(-1, "negative one");
        
        assertEquals(3, tree.size());
        assertEquals("negative one", tree.get(-1));
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        tree.put(1,  "one");
        assertEquals(1, tree.size());
        
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        tree.put(1, "one");
        tree.put(0, "zero");
        tree.put(4, "four");
        tree.put(2, "two");
        tree.put(5, "five");
        assertEquals(5, tree.size());
        
       // case: Remove root 
        assertEquals("one", tree.remove(1));
        assertEquals(2, (int)tree.root().getElement().getKey());
        assertEquals(4, tree.size());
        assertEquals("zero", tree.get(0));
        
        //remove node that has only right child = 5
        assertEquals("five", tree.remove(5));
        assertEquals(3, tree.size());
        assertEquals("zero", tree.get(0));
        tree.put(3, "three");
        
       //remove node that has only left child = 4
        assertEquals("four", tree.remove(4));
        assertEquals("zero", tree.get(0));
        assertEquals("three", tree.get(3));
        assertEquals("two", tree.get(2));
        assertNull(tree.get(4));
        
        // You should create tests to ensure removing works
        // in all special cases:
        //   - removing the root
        //   - removing from a node that has only a left child
        //   - removing from a node that has only a right child
        //   - removing from a node that has both children
        // etc.
    }
    
    /**
     * Test the output of the toString() behavior
     */     
    @Test
    public void testToString() {
        tree.put(1, "one");
        tree.put(-1, "negetive one");
        tree.put(2, "two");
        
        tree.toString();
        
        Iterator<Entry<Integer, String>> iterator = tree.entrySet().iterator();
        assertEquals("negetive one", iterator.next().getValue());
        assertEquals("one", iterator.next().getValue());
        assertEquals("two", iterator.next().getValue());
    }
}