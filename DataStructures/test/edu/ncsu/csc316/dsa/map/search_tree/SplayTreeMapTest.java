package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


/**
 * Test class for SplayTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a splay tree data structure 
 *
 * @author Dr. King
 * @author Siddhant Joshi
 *
 */
public class SplayTreeMapTest {

    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a splay tree-based map before each test case executes
     */     
    @Before
    public void setUp() {
        tree = new SplayTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        tree.put(5, "five");
        tree.put(6, "six");
        assertEquals(6, (int)tree.root().getElement().getKey());  
        tree.put(3, "three");
        assertEquals(3, (int)tree.root().getElement().getKey());
        tree.put(4, "four");
        assertEquals(4, (int)tree.root().getElement().getKey());
        tree.put(17, "twenty");
        tree.put(3, "e");
        assertEquals(3, (int)tree.root().getElement().getKey());
       
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
    
    /**
     * Test the output of the get(k) behavior
     */ 
    @Test
    public void testGet() {
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        tree.put(1, "one");
        assertEquals("one", tree.get(1));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
    	tree.put(3, "three");
    	tree.put(2, "two");
    	tree.put(1, "one");
    	tree.remove(2);
    	assertEquals(1, (int)tree.root().getElement().getKey());
    	
    	tree.put(6, "six");
    	tree.put(5, "five");
    	tree.put(2, "two");
    	tree.remove(8);
    	assertEquals(6, (int)tree.root().getElement().getKey());
        // You should create test cases to check all the
        // splay scenarios. The textbook has examples
        // that you can use to create your test cases
        
        // You should check the specific keys in each node after adding or
        // removing from the tree. For example, you might use:
        //  assertEquals(4, (int)tree.root().getElement().getKey());
        //  assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());         
    }
}